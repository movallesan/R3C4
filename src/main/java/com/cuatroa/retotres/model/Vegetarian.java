package com.cuatroa.retotres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Esta clase modela una coleccion de productos, e incluye cada uno de sus campos. Muchos de los proyectos incluyen
 * un identificador de tipo String denominado referencia (reference). Sin embargo es importante resaltar que algunos de los proyectos tienen
 * un identificador de tipo Integer como:
 * El Mercader LTDA         (G1)
 * Ocho Bits LTDA           (G2)
 * El Cacharrero            (G3)
 * Te lo Reparo LTDA        (G5)
 * Blanquita LTDA           (G20) 
 * Hogar Casa Limpia LTDA   (G21)
 * 
 * @author desaextremo
 */
@Document(collection = "accessories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vegetarian {
    @Id
    private String reference;
    private String brand;
    private String category;
    /*private String material;*/
    private String description;
    private boolean availability = true;
    private double price;
    private int quantity;
    private String photography;
}
