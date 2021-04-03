package techstore.controllers;

import techstore.models.entities.Brand;
import techstore.models.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BrandController {

    @Autowired
    BrandRepository brandRepository;
    
    @GetMapping("/brands")
    public List<Brand> index() {
        return brandRepository.findAll();
    }
    
    @GetMapping("/brands/{id}")
    public Brand get(@PathVariable long id) {
        Optional<Brand> result = brandRepository.findById(id);
        return result.orElse(null);
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    public Brand create(@RequestBody Brand smartphone) {
        return brandRepository.save(smartphone);
    }

    @PutMapping("/brands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Brand save(@PathVariable long id, @RequestBody Brand newBrand) {
        return brandRepository.findById(id)
            .map(smartphone -> {
                smartphone.setName(newBrand.getName());
                return brandRepository.save(smartphone);
            })
            .orElse(null);
    }

    @DeleteMapping("/brands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        brandRepository.deleteById(id);
    }

}
