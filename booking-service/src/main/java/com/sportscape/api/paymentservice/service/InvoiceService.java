package com.sportscape.api.paymentservice.service;

import com.sportscape.api.paymentservice.model.Invoice;
import com.sportscape.api.paymentservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;


    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

//    public List<Invoice> getUserInvoices(Long userId) {
//        return invoiceRepository.findAllById()
//    }
}
