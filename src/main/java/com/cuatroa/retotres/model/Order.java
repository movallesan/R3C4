package com.cuatroa.retotres.model;

import java.util.Date;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modela una coleccion de ordenes de pedido, tiene las siguiente relaciones embebidas:
 * 1 Con User (Atributo de relaciòn) para identificacr al vendedor o Asesor
 * 2 Con Producto (El nombre de la clase depende de cada problema o proyecto, para este caso es Vegetarian)
 * 3 Con HashMap de cantidades el cual se indexa por el código del producto (Para el caso de este proyecto la llave es String, pero hay otros proyectos en donde este valor puede ser de tipo Interger)
 * Proyectos en los que el identificador del producto que vende la empresa es de tipo Integer:
 * 
 * El Mercader LTDA         (G1)
 * Ocho Bits LTDA           (G2)
 * El Cacharrero            (G3)
 * Te lo Reparo LTDA        (G5)
 * Blanquita LTDA           (G20) 
 * Hogar Casa Limpia LTDA   (G21)
 * 
 * Para esos casos la clave o llave en los Map debe se de tipo Integer
 * 
 * @author desarrolloextremo
 */
@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public static String PENDING = "Pendiente";
    public static String APROVED = "Aprobada";
    public static String REJECTED = "Rechazada";
    
    @Id
    private Integer id;
    private Date registerDay;
    private String status;
    private User salesMan;
    
    private Map<String, Vegetarian> products;
    private Map<String, Integer> quantities;    
}
