package com.sportscape.api.bookingservice.service;

import com.sportscape.api.bookingservice.model.Invoice;
import com.sportscape.api.bookingservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
