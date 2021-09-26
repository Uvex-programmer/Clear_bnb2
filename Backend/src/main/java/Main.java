import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.*;
import routes.PropertyRoutes;
import routes.UserRoutes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();

//        app.use((req, res) -> {
//            //System.out.println("HÄR KOMMER MIN SESSION: ");
//            //Session ska vara i 15-30 minuter
//            // Set an cookie (you can call setCookie how often you want)
//            //res.setCookie(new Cookie("my-cookie", "Hello World!"));
//        });
        
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        
        SessionRepository sessionRepository = new SessionRepository(entityManager);
        UserRepository userRepository = new UserRepository(entityManager);
        PropertyRepository propertyRepository = new PropertyRepository(entityManager);
        AmenityRepository amenityRepository = new AmenityRepository(entityManager);
        BookingRepository bookingRepository = new BookingRepository(entityManager);
        
        new UserRoutes(app, mapper, userRepository, sessionRepository, bookingRepository);
        new PropertyRoutes(app, mapper, propertyRepository);

//        Amenity amen1 = new Amenity("wifi");
//        Amenity amen2 = new Amenity("dishwasher");
//        Amenity amen3 = new Amenity("ac");
//        Amenity amen4 = new Amenity("iron");
//        Amenity amen5 = new Amenity("microwave");
//        Amenity amen6 = new Amenity("fridge");
//
//        Timestamp ts1 = new Timestamp(new Date().getTime());
//        Timestamp ts2 = new Timestamp(1635909136530L);
//
//        Property property = new Property("Sofiero", "Fint slott i bla bla bla", 17, 12, 22, ts1, ts2, 100);
//        property.addAmenities(amen1);
//        property.addAmenities(amen2);
//        property.addAmenities(amen3);
//        property.addAmenities(amen4);
//        property.addAmenities(amen5);
//        property.addAmenities(amen6);
//        propertyRepository.save(property);
//
        
        
        app.listen(4000);
    }
}
