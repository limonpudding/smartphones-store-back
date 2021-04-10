package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import techstore.enums.OrderStatus;
import techstore.models.entities.AppUser;
import techstore.models.entities.PurchaseOrder;
import techstore.models.entities.Smartphone;
import techstore.models.repositories.AppUserRepository;
import techstore.models.repositories.PurchaseOrderRepository;
import techstore.models.repositories.SmartphoneRepository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PurchaseOrdersController {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    SmartphoneRepository smartphoneRepository;
    
    @GetMapping("/purchaseOrders")
    public List<PurchaseOrder> index() {
        return purchaseOrderRepository.findAll();
    }
    
    @GetMapping("/purchaseOrders/{id}")
    public PurchaseOrder get(@PathVariable long id) {
        Optional<PurchaseOrder> result = purchaseOrderRepository.findById(id);
        return result.orElse(null);
    }

    @PostMapping("/purchaseOrders")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder create(@RequestBody PurchaseOrder order) {

        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String purchaseNumber = sdf.format(new Date());
        order.setNumber(purchaseNumber);

        Optional<AppUser> dbUser = appUserRepository.findByUserName(order.getUserName());
        if (dbUser.isPresent()) {
            order.setUserId(dbUser.get().getId());
        } else {
            return null;
        }

        List<Long> ids = order.getSmartphones().stream().map(Smartphone::getId).collect(Collectors.toList());
        List<Smartphone> dbSmartphones = smartphoneRepository.findAllById(ids);
        if (dbSmartphones.isEmpty()) {
            return null;
        }
        order.setSmartphones(dbSmartphones);

        BigDecimal fullPrice = dbSmartphones.stream().map(Smartphone::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setFullPrice(fullPrice);

        order.setStatus(OrderStatus.PROCESSING);

        return purchaseOrderRepository.save(order);
    }

//    @PutMapping("/purchaseOrders/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public PurchaseOrder save(@PathVariable long id, @RequestBody PurchaseOrder newPurchaseOrder) {
//
//    }

    @DeleteMapping("/purchaseOrders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        purchaseOrderRepository.deleteById(id);
    }

}
