package techstore.controllers;

import techstore.models.entities.Brand;
import techstore.models.entities.Smartphone;
import techstore.models.repositories.BrandRepository;
import techstore.models.repositories.SmartphoneRepository;
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

    @Autowired
    BrandRepository brandRepository;
    /**
     * Полный список - GET
     */
    @GetMapping("/smartphones")
    public List<Smartphone> index() {
        return smartphoneRepository.findAll();
    }

    /**
     * Список по бренду - GET
     */
    @GetMapping("/smartphones/brand/{brandId}")
    public List<Smartphone> allByBrand(@PathVariable long brandId) {
        return smartphoneRepository.findSmartphonesByBrand_Id(brandId);
    }

    /**
     * Возвращаем одну книгу - GET
     * @param id
     */

    @GetMapping("/smartphones/{id}")
    public Smartphone get(@PathVariable long id) {
        Optional<Smartphone> result = smartphoneRepository.findById(id);

        return result.orElse(null);
    }

    /**
     * Создание новой записи - POST
     */
    @PostMapping("/smartphones")
    @ResponseStatus(HttpStatus.CREATED)
    public Smartphone create(@RequestBody Smartphone smartphone) {
        smartphone.setBrand(brandRepository.findById(smartphone.getBrand().getId()).get());
        return smartphoneRepository.save(smartphone);
    }

    /**
     * Сохранение записи - PUT
     * @param id
     */
    @PutMapping("/smartphones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Smartphone save(@PathVariable long id, @RequestBody Smartphone newSmartphone) {
        Brand brand = brandRepository.findById(newSmartphone.getBrand().getId()).get();
        return smartphoneRepository.findById(id)
            .map(smartphone -> {
                smartphone.setModelName(newSmartphone.getModelName());
                smartphone.setBrand(brand);
                smartphone.setPrice(newSmartphone.getPrice());
                smartphone.setCpu(newSmartphone.getCpu());
                smartphone.setGpu(newSmartphone.getGpu());
                smartphone.setRam(newSmartphone.getRam());
                smartphone.setRom(newSmartphone.getRom());
                return smartphoneRepository.save(smartphone);
            })
            .orElse(null);
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
