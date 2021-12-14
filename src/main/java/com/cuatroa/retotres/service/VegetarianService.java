package com.cuatroa.retotres.service;

import com.cuatroa.retotres.model.Vegetarian;
import com.cuatroa.retotres.repository.VegetarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author desaextremo
 */
@Service
public class VegetarianService {

    @Autowired
    private VegetarianRepository vegetarianRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Vegetarian> getAll() {
        return vegetarianRepository.getAll();
    }

    public Optional<Vegetarian> getVegetarian(String reference) {
        return vegetarianRepository.getVegetarian(reference);
    }

    public Vegetarian create(Vegetarian vegetarian) {
        if (vegetarian.getReference() == null) {
            return vegetarian;
        } else {
            return vegetarianRepository.create(vegetarian);
        }
    }

    public Vegetarian update(Vegetarian vegetarian) {

        if (vegetarian.getReference() != null) {
            Optional<Vegetarian> vegetarianDb = vegetarianRepository.getVegetarian(vegetarian.getReference());
            if (!vegetarianDb.isEmpty()) {
                if (vegetarian.getBrand() != null) {
                    vegetarianDb.get().setBrand(vegetarian.getBrand());
                }

                if (vegetarian.getCategory() != null) {
                    vegetarianDb.get().setCategory(vegetarian.getCategory());
                }

              /*  if (vegetarian.getMaterial() != null) {
                    vegetarianDb.get().setMaterial(vegetarian.getMaterial());
                }*/

                if (vegetarian.getDescription() != null) {
                    vegetarianDb.get().setDescription(vegetarian.getDescription());
                }
                if (vegetarian.getPrice() != 0.0) {
                    vegetarianDb.get().setPrice(vegetarian.getPrice());
                }
                if (vegetarian.getQuantity() != 0) {
                    vegetarianDb.get().setQuantity(vegetarian.getQuantity());
                }
                if (vegetarian.getPhotography() != null) {
                    vegetarianDb.get().setPhotography(vegetarian.getPhotography());
                }
                vegetarianDb.get().setAvailability(vegetarian.isAvailability());
                vegetarianRepository.update(vegetarianDb.get());
                return vegetarianDb.get();
            } else {
                return vegetarian;
            }
        } else {
            return vegetarian;
        }
    }

    public boolean delete(String reference) {
        Boolean aBoolean = getVegetarian(reference).map(vegetarian -> {
            vegetarianRepository.delete(vegetarian);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
