package itu.eval_2.newapp.services.frappe.supplier;

import java.util.List;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.supplier.ErpNextSupplier;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface SupplierService {
    /**
     * Fetching the data from erpnext
     */
    List<ErpNextSupplier> getAllSuppliers(UserErpNext user)  throws ERPNexException;
    
}
