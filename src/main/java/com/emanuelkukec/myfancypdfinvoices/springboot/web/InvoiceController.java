package com.emanuelkukec.myfancypdfinvoices.springboot.web;

import com.emanuelkukec.myfancypdfinvoices.springboot.dto.InvoiceDto;
import com.emanuelkukec.myfancypdfinvoices.springboot.model.Invoice;
import com.emanuelkukec.myfancypdfinvoices.springboot.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public Iterable<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("invoices/user/{userId}")
    public Iterable<Invoice> findByUserId(@PathVariable String userId) {
        return invoiceService.findByUserId(userId);
    }

    @PostMapping("/invoices")
    public Invoice create(@Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }

}
