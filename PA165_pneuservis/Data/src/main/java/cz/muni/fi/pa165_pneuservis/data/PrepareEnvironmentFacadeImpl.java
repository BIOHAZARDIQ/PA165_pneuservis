/*
* Team project for course PA165 - Enterprise Applications in Java
* For more informations see file README.md
*/
package cz.muni.fi.pa165_pneuservis.data;

import com.github.javafaker.Faker;
import cz.muni.fi.pa165_pneuservis.model.Customer;
import cz.muni.fi.pa165_pneuservis.model.Order;
import cz.muni.fi.pa165_pneuservis.model.Service;
import cz.muni.fi.pa165_pneuservis.model.ServiceType;
import cz.muni.fi.pa165_pneuservis.model.Tire;
import cz.muni.fi.pa165_pneuservis.model.VehicleType;
import cz.muni.fi.pa165_pneuservis.service.CustomerService;
import cz.muni.fi.pa165_pneuservis.service.OrderService;
import cz.muni.fi.pa165_pneuservis.service.PneuBusinessException;
import cz.muni.fi.pa165_pneuservis.service.ServiceService;
import cz.muni.fi.pa165_pneuservis.service.TireService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.security.SecureRandom;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author Jozef Sumaj <374029@mail.muni.cz>
 */
@Component
@Transactional
public class PrepareEnvironmentFacadeImpl implements PrepareEnvironmentFacade {
    
    @Autowired
    private TireService tireService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private OrderService orderService;
    
    private final Faker faker = new Faker();
    
    /**
     * Fills system with random test data
     * Password for Customer contains firstName of customer -> password(firstName)
     * @deprecated Use PrepareCustomEnvironment instead.
     */
    @Override
    @Deprecated
    public void PrepareRandomEnvironment() {
        Random randomGenerator;
        Integer index;
        SecureRandom secureRandom;

        //Item
        String name;
        String description;
        Double priceDouble;
        BigDecimal priceBigDecimal;

        //Tire
        String brand;
        Integer width;
        Integer ratio;
        Integer rim;

        //Customer
        String firstName;
        String lastName;
        String streetName;
        Integer streetNumber;
        String city;
        String state;
        String postalNumber;
        String phoneNumber;
        String email;
        String password;

        //Service
        ServiceType serviceType;
        
        //List of names that will be randomly selected when creating Tires
        ArrayList<String> tireBrand = new ArrayList<>(Arrays.asList("Michelin","Yokohama","Pirelli","Goodyear","Barum"));
        ArrayList<Integer> tireWidth = new ArrayList<>(Arrays.asList(155,165,175,185,195));
        ArrayList<Integer> tireRatio = new ArrayList<>(Arrays.asList(55,60,65,70,75));
        ArrayList<Integer> tireRim = new ArrayList<>(Arrays.asList(14,15,16,17,18,19));
        
        //List of names that will be randomly selected when creating Tires
        ArrayList<String> custFirstName = new ArrayList<>(Arrays.asList("Pavel","Martin","Erik","Honza","Juraj"));
        ArrayList<String> custLastName = new ArrayList<>(Arrays.asList("Horky","Studeny","Tmavy","Bledy","Zeleny"));
        ArrayList<String> custStreetName = new ArrayList<>(Arrays.asList("Vodova","Pekna","Hladka ulica","Krasna","Namestie mesta"));
        ArrayList<String> custCity = new ArrayList<>(Arrays.asList("Brno","Praha","Hradec Kralove","Breclav","Ostrava"));
        ArrayList<String> custMail = new ArrayList<>(Arrays.asList("franta@gmail.com","jitka@seznam.cz","dmitrij@mail.ru","adam@email.com","user@securemail.net"));
        ArrayList<String> custPostal = new ArrayList<>(Arrays.asList("61200","61300","12300","81500","74000"));
           
        Tire tire = null;
        Customer customer = null;
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Service service = null;
        Order order = null;
        randomGenerator = new Random();
        
        //create 10 Tires with random parameters
        for (long i = 1; i <= 10; i++) {
            tire = new Tire();
            
            index = randomGenerator.nextInt(tireBrand.size());
            brand = tireBrand.get(index);
            index = randomGenerator.nextInt(tireWidth.size());
            width = tireWidth.get(index);
            index = randomGenerator.nextInt(tireRatio.size());
            ratio = tireRatio.get(index);
            index = randomGenerator.nextInt(tireRim.size());
            rim = tireRim.get(index);
            
            //Composed tire name and description
            name = brand+"/"+width+"/"+ratio+"/"+rim;
            description = "This is "+brand+" tire with width:"+width+", ratio:"+ratio+" and rim:"+rim;
            
            //Random price from interval 200-600 => (max-min)+min
            priceDouble = Math.random() * 400 + 200;
            priceBigDecimal = BigDecimal.valueOf(priceDouble).setScale(2, RoundingMode.CEILING);
            
            //create tire with random parameters
            tire.setName(name);
            tire.setBrand(brand);
            tire.setDescription(description);
            tire.setWidth(width);
            tire.setRatio(ratio);
            tire.setRim(rim);
            tire.setPrice(priceBigDecimal);
            
            try {
                tireService.createTire(tire);
            } catch (PneuBusinessException ex) {
                //TODO log
            }
        }
        
        int number;
        secureRandom = new SecureRandom();
        String mailAddr;
        
        //create 30 Customers with random parameters
        for (long i = 1; i <= 30; i++) {
            customer = new Customer();
            
            index = randomGenerator.nextInt(custFirstName.size());
            firstName = custFirstName.get(index);
            index = randomGenerator.nextInt(custLastName.size());
            lastName = custLastName.get(index);
            index = randomGenerator.nextInt(custStreetName.size());
            streetName = custStreetName.get(index);
            index = randomGenerator.nextInt(custCity.size());
            city = custCity.get(index);
            index = randomGenerator.nextInt(custPostal.size());
            postalNumber = custPostal.get(index);
            
            //Random street number from interval 1-250 => (max-min)+min
            streetNumber = (int)(Math.random() * 249) + 1;
            
            //Random phone number from interval 700 000 000 - 900 000 000 => (max-min)+min
            number = (int)(Math.random() * 200000000) + 700000000;
            phoneNumber = "+420"+number;
            
            //Random e-mail.
            index = randomGenerator.nextInt(custMail.size());
            email = custMail.get(index);
            
            //create tire with random parameters
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setCity(city);
            customer.setStreetName(streetName);
            //customer.setStreetNumber(streetNumber);
            customer.setPostalNumber(postalNumber);
            customer.setState("Ceska republika");
            customer.setPhoneNumber(phoneNumber);
            customer.setEmail(email);
            //Password for Customer will be always containing firstName of customer -> "heslo{firstName}"
            customer.setPassword("heslo" + firstName);
            
            customers.add(customer);
            
            customerService.createCustomer(customer);
        }
        
        //create 20 Services with random parameters
        for (long i = 1; i <= 20; i++) {
            service = new Service();
            
            //Enum name of service
            index = randomGenerator.nextInt(ServiceType.values().length);
            serviceType = ServiceType.values()[index];
            
            //Random price from interval 50-1000 => (max-min)+min
            priceDouble = Math.random() * 950 + 50;
            priceBigDecimal = BigDecimal.valueOf(priceDouble).setScale(2, RoundingMode.CEILING);
            
            //Composed tire name and description
            name = serviceType.toString();
            description = serviceType + " service for price " + priceBigDecimal + " for your vehicle";
            
            service.setServiceType(serviceType);
            service.setName(name);
            service.setDescription(description);
            service.setPrice(priceBigDecimal);
            
            serviceService.createService(service);
        }
        
        
        //create 20 Orders with random parameters
        for (long i = 1; i <= 20; i++) {
            order = new Order();
            
            Random  rnd;
            Date    dt, dt2;
            long    ms, ms2;

            rnd = new Random();
            ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            ms2 = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
            dt = new Date(ms);
            dt2 = new Date(ms2);
                        
            //Random price from interval 50-1000 => (max-min)+min
            priceDouble = Math.random() * 950 + 50;
            priceBigDecimal = BigDecimal.valueOf(priceDouble).setScale(2, RoundingMode.CEILING);
                        
            order.setTotalPrice(priceBigDecimal);
            order.setCustomer(customers.get((int)i % customers.size()-1));
            order.setCreateDate(dt);
            order.setCompleteDate(dt2);
            
            orderService.createOrder(order);
        }
    }

    /**
     * Fills system with specific test data
     */
    @Override
    public void PrepareCustomEnvironment()
    {
        // create admin for presentation purposes
        Customer exampleAdmin = exampleCustomer("John", "Privileged",
                "admin@pneuservis.com", "password", VehicleType.Car);
        exampleAdmin.setIsAdmin(true);
        customerService.createCustomer(exampleAdmin);
        
        // create customer for presentation purposes
        Customer exampleCustomer = exampleCustomer("John", "Doe",
                "customer@pneuservis.com", "password", VehicleType.Van);
        customerService.createCustomer(exampleCustomer);
        
        // create another customers to fill data views
        customerService.createCustomer(genericCustomer(VehicleType.Car));
        customerService.createCustomer(genericCustomer(VehicleType.Car));
        customerService.createCustomer(genericCustomer(VehicleType.Motocycle));
        customerService.createCustomer(genericCustomer(VehicleType.Truck));
        customerService.createCustomer(genericCustomer(VehicleType.Van));
        
        // create tires
        try
        {
            tireService.createTire(exampleTire("Alpin A4", "The new Michelin Alpin A4 "
                    + "tire allows you to drive in confidence through snow, ice and rain "
                    + "year after year.", "Michelin", 165, 60, 14, 
                    BigDecimal.valueOf(119.0)));
            
            tireService.createTire(exampleTire("Premier LTX", "The MICHELIN Premier "
                    + "LTX tire still stops shorter on wet roads than leading "
                    + "competitors’ brand-new tires.", "Michelin", 185, 60, 15, 
                    BigDecimal.valueOf(149.0)));
            
            tireService.createTire(exampleTire("Defender LTX", "The Michelin Defender "
                    + "LTX combines the proven tread design of the LTX M/S2 with "
                    + "Evertread compound.", "Michelin", 195, 55, 16, 
                    BigDecimal.valueOf(189.0)));
            
            tireService.createTire(exampleTire("Pilot Sport", "The Pilot Sport 3 is "
                    + "Michelin’s ultimate Ultra High Performance All-Season tire.", 
                    "Michelin", 205, 45, 17, BigDecimal.valueOf(219.0)));
            
            tireService.createTire(exampleTire("P Zero", "The P ZERO has been "
                    + "chosen for the most performance orientated and powerful "
                    + "models on the market.", "Pirelli", 215, 45, 18, 
                    BigDecimal.valueOf(239.0)));
            
            tireService.createTire(exampleTire("Cinturato P7", "A Performance All "
                    + "Season Tire specifically Designed for the North American "
                    + "Luxury Touring Market", "Pirelli", 205, 50, 17, 
                    BigDecimal.valueOf(229.0)));
        }
        catch(PneuBusinessException ex)
        {
            Logger.getLogger(PrepareEnvironmentFacadeImpl.class.getName())
                    .log(Level.SEVERE,ex.getMessage());
        }
        
        // create services
        serviceService.createService(exampleService("Tire Installation", "Once a driver "
                + "buys the new tires, they will need to be correctly installed on the "
                + "vehicle. Contact us today to learn more about our tire offerings.", 
                BigDecimal.valueOf(29.0), ServiceType.TireChange));
        
        serviceService.createService(exampleService("Wheel Balancing", "Professionals "
                + "use computerized wheel balancers to pinpoint weight differentiation "
                + "within a tire and wheel assembly. Computerized wheel balancers are "
                + "highly accurate machines that identify the weight distribution "
                + "problem areas within an assembly.",
                BigDecimal.valueOf(49.0), ServiceType.TireTelemetry));
        
        serviceService.createService(exampleService("Tire Repair (Van)", "For a punctured "
                + "tire, our staff will patch, plug, or seal the damaged area. If your "
                + "tire is losing air due to valve stem damage or if the tire is "
                + "not securely attached to the wheel’s rim, our tire repair service "
                + "staff will be able to help.",
                BigDecimal.valueOf(69.0), ServiceType.TireMaintenance));
        
        serviceService.createService(exampleService("Tire Repair (Truck)", "The methods "
                + "used by our service staff to repair a tire comply with the "
                + "Rubber Manufacturer’s Association (RMA). The RMA states that "
                + "a flat tire can be repaired if the puncture is ¼ inch or smaller "
                + "and if the puncture is located on the tread of a tire.",
                BigDecimal.valueOf(39.0), ServiceType.TireMaintenance));
    }
    
    private Service exampleService(String name, String description,
            BigDecimal price, ServiceType serviceType)
    {
        Service service = new Service();
        service.setName(name);
        service.setDescription(description);
        service.setPrice(price);
        service.setServiceType(serviceType);
        return service;
    }
    
    private Tire exampleTire(String name, String description, String brand,
            Integer width, Integer ratio, Integer rim, BigDecimal price)
    {
        Tire tire = new Tire();
        tire.setName(name);
        tire.setDescription(description);
        tire.setBrand(brand);
        tire.setWidth(width);
        tire.setRatio(ratio);
        tire.setRim(rim);
        tire.setPrice(price);
        return tire;
    }
    
    private Customer exampleCustomer(String firstName, String LastName, 
            String email, String password, VehicleType vehicleType)
    {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(LastName);
        customer.setStreetName(faker.address().streetName());
        customer.setStreetNumber(faker.address().streetAddressNumber());
        customer.setCity(faker.address().city());
        customer.setState(faker.address().state());
        customer.setPostalNumber(faker.address().zipCode());
        customer.setPhoneNumber(faker.phoneNumber().phoneNumber());
        customer.setEmail(email);
        customer.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        customer.setVehicleType(vehicleType);
        customer.setIsAdmin(false);
        return customer;
    }
    
    private Customer genericCustomer(VehicleType vehicleType)
    {
        return exampleCustomer(faker.name().firstName(), faker.name().lastName(),
                faker.internet().emailAddress(), "password", vehicleType);
    }
}
