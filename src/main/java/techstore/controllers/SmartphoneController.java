package techstore.controllers;

import org.springframework.security.access.annotation.Secured;
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

    @GetMapping("/smartphones")
    @Secured("USER")
    public List<Smartphone> index() {
        return smartphoneRepository.findAll();
    }

    @GetMapping("/smartphones/brand/{brandId}")
    @Secured("USER")
    public List<Smartphone> allByBrand(@PathVariable long brandId) {
        return smartphoneRepository.findSmartphonesByBrand_Id(brandId);
    }

    @GetMapping("/smartphones/{id}")
    @Secured("USER")
    public Smartphone get(@PathVariable long id) {
        Optional<Smartphone> result = smartphoneRepository.findById(id);

        return result.orElse(null);
    }

    @PostMapping("/smartphones")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ADMIN")
    public Smartphone create(@RequestBody Smartphone smartphone) {
        smartphone.setBrand(brandRepository.findById(smartphone.getBrand().getId()).get());
        return smartphoneRepository.save(smartphone);
    }

    @PutMapping("/smartphones/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
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

    @DeleteMapping("/smartphones/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
    public void delete(@PathVariable long id) {
        smartphoneRepository.deleteById(id);
    }

}
