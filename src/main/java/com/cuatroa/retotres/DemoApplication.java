package com.cuatroa.retotres;
import com.cuatroa.retotres.model.Vegetarian;
import com.cuatroa.retotres.model.Order;
import com.cuatroa.retotres.model.User;
import com.cuatroa.retotres.repository.crud.VegetarianCrudRepository;
import com.cuatroa.retotres.repository.crud.OrderCrudRepository;
import com.cuatroa.retotres.repository.crud.UserCrudRepository;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private VegetarianCrudRepository crudRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private OrderCrudRepository orderCrudRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
       SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        crudRepository.deleteAll();
        userCrudRepository.deleteAll();
        orderCrudRepository.deleteAll();

        crudRepository.saveAll(List.of(
                new Vegetarian("AP-903", "ACME", "PRESENTACION 1", "DESCRIPCION DETALLADA", true, 120000, 10, "https://www.avasoluciones.com/uploads/2021/09/910-006127.jpg"),
                new Vegetarian("AP-904", "ACME", "PRESENTACION 2", "DESCRIPCION DETALLADA",  true, 130000, 10, "https://www.avasoluciones.com/uploads/2021/09/910-007.jpg")
        ));

        userCrudRepository.saveAll(List.of(
                new User(1, "123123", "ALAN BRITO", ft.parse("1986-11-15"),"11","CR 34-45", "311222222", "abrito@gmail.com", "Demo123.", "ZONA 2", "ADM"),
                new User(2, "61123211", "NAPOLEON BONAPARTE", ft.parse("1966-11-15"),"11","CR 34-45", "3168965645", "nbonaparte@gmail.com", "Demo123.", "ZONA 2", "COORD"),
                new User(3, "46669989", "BLODY MARRY", ft.parse("1996-11-15"),"11","CR 34-45", "3174565625", "stellez@gmail.com", "Demo123.", "ZONA 2", "ASE"),
                new User(4, "52369563", "JUANA DE ARCO", ft.parse("1987-05-15"),"05","CR 34-45", "3265632", "jdarco@gmail.com", "Demo123.", "ZONA 2", "ASE"),
                new User(5, "123456789", "ALCIRA LA ALPACA", ft.parse("1966-02-15"),"02","CR 34-45", "3168965645", "aalpaca@gmail.com", "Demo123.", "ZONA 1", "COORD"),
                new User(6, "213456789", "PEDRO CAPAROSA", ft.parse("1966-02-15"),"02","CR 34-45", "3168965645", "pcaparosa@gmail.com", "Demo123.", "ZONA 1", "ASE"),
                new User(7, "312456789", "LUIS IXV UN SOL", ft.parse("1966-02-15"),"02","CR 34-45", "3168965645", "reysol@gmail.com", "Demo123.", "ZONA 1", "ASE")
        ));

        //Instanciar las ordenes
        Order orderOne = new Order();
        orderOne.setId(1);
        Order orderTwo = new Order();
        orderTwo.setId(2);
        
        //Recuperar vendedores
        Optional<User> salesManOne = userCrudRepository.findById(3);
        Optional<User> salesManTwo = userCrudRepository.findById(6);

        //Mapa de productos asociados a una orden
        Map<String, Vegetarian> productOrderOne = new HashMap<String, Vegetarian>();
        Map<String, Vegetarian> productOrderTwo = new HashMap<String, Vegetarian>();

        //Mapa de cantidades de producto asociados a una orden
        Map<String, Integer> quantitiesOrderOne = new HashMap<String, Integer>();
        Map<String, Integer> quantitiesOrderTwo = new HashMap<String, Integer>();
        
        //lista de producto de la orden 1
        productOrderOne.put("AP-903", crudRepository.findById("AP-903").get());
        productOrderOne.put("AP-904", crudRepository.findById("AP-904").get());

        //Cantidades de producto de la orden 1      
        quantitiesOrderOne.put("AP-903", 1);
        quantitiesOrderOne.put("AP-904", 1);

        //lista de producto de la orden 2
        productOrderTwo.put("AP-903", crudRepository.findById("AP-903").get());
        productOrderTwo.put("AP-904", crudRepository.findById("AP-904").get());

        //Cantidades de producto de la orden 2
        quantitiesOrderTwo.put("AP-903", 1);
        quantitiesOrderTwo.put("AP-904", 1);
             
        //Configuraciòn datos orden 1
        orderOne.setRegisterDay(ft.parse("2021-09-15"));
        orderOne.setStatus(Order.PENDING);
        orderOne.setSalesMan(salesManOne.get());
        orderOne.setProducts(productOrderOne);
        orderOne.setQuantities(quantitiesOrderOne);
        
        //Configuraciòn datos orden 2
        orderTwo.setRegisterDay(ft.parse("2021-09-15"));
        orderTwo.setStatus(Order.PENDING);
        orderTwo.setSalesMan(salesManTwo.get());
        orderTwo.setProducts(productOrderTwo);
        orderTwo.setQuantities(quantitiesOrderTwo);


        //registra las ordenes en la base de datos
        //imprime el los datos de las ordenes
        orderCrudRepository.saveAll(List.of(orderOne, orderTwo));
        
        System.out.println("Listado de ordenes");
        orderCrudRepository.findAll().forEach(System.out::println);       
        
    }
}
