package itu.eval_2.newapp.services.frappe.invoice;

import java.util.List;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface PurchaseInvoiceService {

    List<PurchaseInvoice> getAllInvoices(UserErpNext user) throws ERPNexException;

    PurchaseInvoice getInvoinceById(UserErpNext user, String id) throws ERPNexException;
}
