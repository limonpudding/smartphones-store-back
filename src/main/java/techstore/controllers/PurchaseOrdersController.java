package techstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import techstore.models.entities.PurchaseOrder;
import techstore.models.repositories.PurchaseOrderRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PurchaseOrdersController {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    
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
    public PurchaseOrder create(@RequestBody PurchaseOrder smartphone) {
        return purchaseOrderRepository.save(smartphone);
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
