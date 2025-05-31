package itu.eval_2.newapp.controllers.api;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.api.responses.ApiListResponse;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.item.Item;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
@Slf4j
public class ItemApiController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getItems(HttpSession session){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        Map<String, Object> body = new HashMap<>();
        try {
            List<Item> items = itemService.fetchAllItem(user);
            ApiListResponse<Item> itemResponse = new ApiListResponse<>(true, "Items Fetched successfully", items, null);

            return  ResponseEntity.ok(itemResponse);
        } catch (ERPNexException e) {
            return ResponseEntity.ok(e.getAsApiResponse());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(body);
        }
    }
}
