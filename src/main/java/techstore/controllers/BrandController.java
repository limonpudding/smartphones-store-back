package techstore.controllers;

import org.springframework.security.access.annotation.Secured;
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
    @Secured("USER")
    public List<Brand> index() {
        return brandRepository.findAll();
    }
    
    @GetMapping("/brands/{id}")
    @Secured("USER")
    public Brand get(@PathVariable long id) {
        Optional<Brand> result = brandRepository.findById(id);
        return result.orElse(null);
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ADMIN")
    public Brand create(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @PutMapping("/brands/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
    public Brand save(@PathVariable long id, @RequestBody Brand newBrand) {
        return brandRepository.findById(id)
            .map(brand -> {
                brand.setName(newBrand.getName());
                return brandRepository.save(brand);
            })
            .orElse(null);
    }

    @DeleteMapping("/brands/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ADMIN")
    public void delete(@PathVariable long id) {
        brandRepository.deleteById(id);
    }

}
