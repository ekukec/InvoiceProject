package com.emanuelkukec.myfancypdfinvoices.service;

import com.emanuelkukec.myfancypdfinvoices.model.Invoice;
import com.emanuelkukec.myfancypdfinvoices.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String cdnUrl;

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if(user == null){
            throw new IllegalStateException();
        }
//        TODO  real pdf creation and storing it on server
        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }

//    WILL BE USING CONSTRUCTOR INJECTION INSTEAD OF SETTER OR FIELD INJECTION
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @PostConstruct
    public void init(){
        System.out.println("Fetching PDF templates from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }
}
