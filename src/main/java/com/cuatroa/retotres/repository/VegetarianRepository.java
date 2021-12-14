package com.cuatroa.retotres.repository;

import com.cuatroa.retotres.model.Vegetarian;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cuatroa.retotres.repository.crud.VegetarianCrudRepository;

/**
 *
 * @author desaextremo
 */
@Repository
public class VegetarianRepository {
    @Autowired
    private VegetarianCrudRepository vegetarianCrudRepository;

    public List<Vegetarian> getAll() {
        return vegetarianCrudRepository.findAll();
    }

    public Optional<Vegetarian> getVegetarian(String reference) {
        return vegetarianCrudRepository.findById(reference);
    }
    
    public Vegetarian create(Vegetarian vegetarian) {
        return vegetarianCrudRepository.save(vegetarian);
    }

    public void update(Vegetarian vegetarian) {
        vegetarianCrudRepository.save(vegetarian);
    }
    
    public void delete(Vegetarian vegetarian) {
        vegetarianCrudRepository.delete(vegetarian);
    }


}
