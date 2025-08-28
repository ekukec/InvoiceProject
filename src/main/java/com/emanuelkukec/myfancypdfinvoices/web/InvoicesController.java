package com.emanuelkukec.myfancypdfinvoices.web;

import com.emanuelkukec.myfancypdfinvoices.model.Invoice;
import com.emanuelkukec.myfancypdfinvoices.service.InvoiceService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class InvoicesController {

    private InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

//    USING PATH PARAMS
//    @PostMapping("/invoices/{userId}/{amount}")
//    public Invoice createInvoice(@PathVariable("userId") String userId, @PathVariable("amount") Integer amount){
//        return invoiceService.create(userId, amount);
//    }

//    USING REQUEST BODY
//    @PostMapping("/invoices")
//    public Invoice createInvoice(@RequestBody @Valid InvoiceDto  invoiceDto) {
//        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
//    }

//    USING REQUEST PARAM
    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") @NotBlank String userId, @RequestParam("amount") @Min(10) @Max(50) Integer amount) {
        return invoiceService.create(userId, amount);
    }
}
