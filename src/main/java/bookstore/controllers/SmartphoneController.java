package bookstore.controllers;

import bookstore.models.entities.Smartphone;
import bookstore.models.repositories.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class SmartphoneController {

    @Autowired
    SmartphoneRepository smartphoneRepository;

    /**
     * Полный список - GET
     */
    @GetMapping("/smartphones")
    public List<Smartphone> index() {
        return (List<Smartphone>) smartphoneRepository.findAll();
    }

    /**
     * Возвращаем одну книгу - GET
     * @param id
     */
    @GetMapping("/smartphones/{id}")
    public Smartphone get(@PathVariable long id) {
        Optional<Smartphone> result = smartphoneRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    /**
     * Создание новой записи - POST
     */
    @PostMapping("/smartphones")
    @ResponseStatus(HttpStatus.CREATED)
    public Smartphone create(@RequestBody Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    /**
     * Сохранение записи - PUT
     * @param id
     */
    @PutMapping("/smartphones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Smartphone save(@PathVariable long id, @RequestBody Smartphone newSmartphone) {
        return smartphoneRepository.findById(id)
            .map(smartphone -> {
                smartphone.setModelName(newSmartphone.getModelName());
                smartphone.setBrand(newSmartphone.getBrand());
                smartphone.setPrice(newSmartphone.getPrice());
                smartphone.setCpu(newSmartphone.getCpu());
                smartphone.setGpu(newSmartphone.getGpu());
                smartphone.setRam(newSmartphone.getRam());
                smartphone.setRom(newSmartphone.getRom());
                return smartphoneRepository.save(smartphone);
            })
            .orElseGet(() -> {
                return null;
            });
    }

    /**
     * Удаление записи - DELETE
     * @param id
     */
    @DeleteMapping("/smartphones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        smartphoneRepository.deleteById(id);
    }

}
