package itu.eval_2.newapp.services.frappe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.AuthenticationException;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.exceptions.ErpNextCallException;
import itu.eval_2.newapp.models.action.FrappeDocument;
import itu.eval_2.newapp.models.api.responses.ApiResourceResponse;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.responses.MethodApiResponse;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import itu.eval_2.newapp.models.api.responses.SingletonApiResourceResponse;
import itu.eval_2.newapp.models.filter.FrappeFilter;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.utils.http.HeadersUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class FrappeWebService<T extends FrappeDocument> {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public FrappeWebService(ApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    
    public List<T> getAllDocuments(UserErpNext user, T document, Class<T> modelClass) throws ERPNexException {
        return getAllDocuments(user, document, null, modelClass);
    } 
    public List<T> getAllDocuments(UserErpNext user, T document, FrappeFilter filter, Class<T> modelClass) 
        throws ERPNexException {
        try {

            ResponseEntity<String> response = callResource(user, document, null, null, HttpMethod.GET, ApiConfig.ALL_FIELDS, filter);

            ApiResourceResponse<T> result = objectMapper.readValue(
                    response.getBody(),
                    objectMapper.getTypeFactory().constructParametricType(
                            ApiResourceResponse.class,
                            modelClass
                    )
            );

            return result.getData();
        } catch (ErpNextCallException e) {
            throw new ERPNexException(
                String.format("Failed to fetch %s documents: %s", document.getDoctype(), e.getMessage()),
                e
            );
        }
        catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public T getDocumentById(UserErpNext user, String id, T document, Class<T> modelClass)
        throws ERPNexException {
        try {
            ResponseEntity<String> response = callResource(user,document,id,null, HttpMethod.GET, null, null);

            SingletonApiResourceResponse<T> result = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(
                    SingletonApiResourceResponse.class,
                    modelClass
                )
            );

            return result.getData();
        } catch (ErpNextCallException e) {
            throw new ERPNexException(
                String.format("Failed to fetch %s document with ID %s: %s", document.getDoctype(), id, e.getMessage()),
                e
            );
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public T updateDocument(UserErpNext user, String id, T document, Class<T> modelClass)throws ERPNexException {
        return updateDocument(user, id, document, document,modelClass);
    }

    public T updateDocument(UserErpNext user, String id, T document, Object body , Class<T> modelClass)
        throws ERPNexException {
        document.update_cotnrole();
        return callForUpdateOrCreateResource(user, id, document, body, HttpMethod.PUT,modelClass);
    }

    public T createDocument(UserErpNext user, T document, Object body, Class<T> modelClass) throws ERPNexException {
        document.save_controle();
        return callForUpdateOrCreateResource(user, null, document, body, HttpMethod.POST, modelClass);
    }

    public T createDocument(UserErpNext user, T document, Class<T> modelClass) throws ERPNexException {
        return createDocument(user, document, document, modelClass);
    }

    /**
     * Send data to ErpNext
     */
    public T callForUpdateOrCreateResource(UserErpNext user, String id, T document, Object body , HttpMethod method , Class<T> modelClass) throws ERPNexException {
        if (method != HttpMethod.PUT && method != HttpMethod.POST) {
            throw new RuntimeException("The method need to be PUT or POST when sending ressource data");
        }
        try {
            ResponseEntity<String> response = callResource(user,document,id,body,method,null,null);

            SingletonApiResourceResponse<T> doc = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructParametricType( SingletonApiResourceResponse.class , modelClass ));

            return doc.getData();

        } catch (Exception e) {
            throw new ERPNexException(
                String.format("Failed to update %s document: %s", document.getDoctype(), e.getMessage()),
                e
            );
        }
    }

    /**
     * Fait un appel a ERP Next en utilisant l'endpoit d'appel de ressource
     *
     * @param id identification specifique d'une ressorce
     * @param user l'utilisateur connecter qui veut faire l'appel
     * @param document est le document qui represente la ressource desirer
     * @param fields la liste des attributs a recuperer pour limiter la reponse
     * @param method la method http a utiliser lors de l'envoie de la requete
     * @param body le corps de la requette http pour envoyer les donnees
     * @param filter un filtre pour definir la contrainte des donnees a recuperer
     *
     * */
    private ResponseEntity<String> callResource(UserErpNext user,T document,String id,Object body,HttpMethod method, String[] fields, FrappeFilter filter) throws ErpNextCallException {

        String url = apiConfig.getResourceUrl(document.getDoctype(), id, fields, filter != null ? filter.getFilters().getFilters() : null);
        log.info("Targeting api {} document at URL: {}", document.getDoctype(), url);
        try {
            return frappeCall(user, url, method, body);
        } catch (Exception e) {
            throw new ErpNextCallException(url, method,e);
        }
    }

    /**
     * Fait un appel api vers ERPNext Rest Api
     * @param user
     * @param url
     * @param method
     * @param body
     * @return La reponse de la requete en tant que String
     * @throws JsonProcessingException
     * @throws RestClientException
     */
    private ResponseEntity<String> frappeCall(UserErpNext user, String url, HttpMethod method,Object body) throws JsonProcessingException , RestClientException, AuthenticationException {
        HttpHeaders headers =  HeadersUtils.createHeaders(user);

        HttpEntity<?> httpEntity = null;
        if (body != null) {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(body),headers);
            log.info("= = = WRITE BODY = = =", headers);
        }
        else {
            httpEntity = new HttpEntity<>(headers);
        }

        return restTemplate.exchange(
            url,
            method,
            httpEntity,
            String.class
        );
    }

    /**
     * Fait un appel de method vers ERPNext avec une reponse conforme au model T
     * @param user l'utilisateur connecter et auteur de la requete
     * @param methodPath le chemin vers la methode a appeler
     * @param method la method HTTP a utiliser pour faire l'appel
     * @param body le corps de la requete pour envoyer des donnees
     * @param modelClass class representative de la reponse attendue
     * @return le Model T format de la reponse
     * @throws ERPNexException
     */
    public T callMethod(UserErpNext user, String methodPath, HttpMethod method, Object body, Class<T> modelClass) throws ERPNexException {
        try {
            String url = apiConfig.getMethodUrl(methodPath);

            ResponseEntity<String> response = frappeCall(user, url, method, body);

            MethodApiResponse<T> data = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructParametricType(MethodApiResponse.class,modelClass));

            return data.getMessage();

        } catch (Exception e) {
            throw new ERPNexException("Error while calling the method \""+methodPath+"\" : "+e.getMessage());
        }
    }

    /**
     * Fait un appel de method avec une reponse structurer en APiResponse avec un data conforme au format du Response Model
     * @param user
     * @param methodPath
     * @param method
     * @param body
     * @param modelClass
     * @return une ApiResponse dont la data est au format du ResponseModel
     * @throws ERPNexException
     */
    public ApiResponse<? extends ResponseModel> callApiResponseMethod(UserErpNext user, String methodPath, HttpMethod method, Object body, Class<? extends ResponseModel> modelClass) throws ERPNexException {
        try {
            String url = apiConfig.getMethodUrl(methodPath);

            ResponseEntity<String> response = frappeCall(user, url, method, body);

            MethodApiResponse<ApiResponse<? extends ResponseModel>> data = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(
                    MethodApiResponse.class,
                    objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        modelClass
                    )
                )
            );
            return data.getMessage();
        } catch (Exception e) {
            throw new ERPNexException("Error while calling the method \"" + methodPath + "\" : " + e.getMessage());
        }
    }

    /**
     * Fait un appel de method vers ERPNext avec une response structurer en ApiResponse avec un data conforme au format de list du document T
     * @param user
     * @param methodPath
     * @param method
     * @param body
     * @param modelClass
     * @return une ApiResponse dont la data est une liste du document T
     * @throws ERPNexException
     */
    public ApiResponse<List<T>> callApiListResponseMethod(UserErpNext user, String methodPath, HttpMethod method, Object body, Class<T> modelClass) throws ERPNexException {
        ResponseEntity<String> response = null;
        try {
            String url = apiConfig.getMethodUrl(methodPath);

            try {
                response = frappeCall(user, url, method, body);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (RestClientException e) {
                throw new RuntimeException(e);
            }

            MethodApiResponse<ApiResponse<List<T>>> data = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(
                    MethodApiResponse.class,
                    objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        objectMapper.getTypeFactory().constructParametricType(
                            List.class,
                            modelClass
                            )
                            )
                            )
            );
            return data.getMessage();
        } catch (Exception e) {
//            throw new ERPNexException("Error while calling the method \"" + methodPath + "\" : " + e.getMessage(), response);
            throw new ERPNexException("Error while calling the method \"" + methodPath + "\" : " + e.getMessage());
        }
    }
}