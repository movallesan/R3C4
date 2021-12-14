package com.cuatroa.retotres.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modela una coleccion de usuarios, para anticiparnos a los retos que se avecinan se agregaròn los campos birthtDay y monthBirthtDay, que
 * corresponden a la fecha de cumpleaños y mes de cumpleaños del usuario respectivamente
 * @author desaextremo
 */

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer id;
    private String identification;
    private String name;
    private Date birthtDay;
    private String monthBirthtDay;
    private String address;
    private String cellPhone;
    private String email;
    private String password;
    private String zone;
    private String type;
}
