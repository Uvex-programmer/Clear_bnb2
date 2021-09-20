import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import models.*;
import repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();
        app.listen(4000);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        
        UserRepository userRepository = new UserRepository(entityManager, app, mapper);
        PropertyRepository propertyRepository = new PropertyRepository(entityManager, app);
        BankAccountRepository bankRepository = new BankAccountRepository(entityManager, app, mapper);
        BookingRepository bookingRepository = new BookingRepository(entityManager, app, mapper);
        TransactionRepository transResp = new TransactionRepository(entityManager, app, mapper);

//        User user = new User("Jöns", "Hårfager", "jösse@gmail.com", "Tarmvred!");
//        Property property = new Property("Sten Hus", "Här bor Sten", 4, 3, 7,
//        Date.valueOf("2021-10-10"), Date.valueOf("2021-12-10"), 999);
//        BankAccount account = new BankAccount(30000);
//        Booking booking = new Booking(71, Date.valueOf("2021-11-12"), Date.valueOf("2021-11-18"));
//        Address address = new Address("Kostigen 48", "123 45", "Kabul");
//        Transaction trans = new Transaction(3300);
//
        Image image = new Image("Stens_hus.jpg", true);
        Image image2 = new Image("Vuxenbild.jpg", false);
        User sten = userRepository.findById(64);
        User jons = userRepository.findById(66);
        bankRepository.findById(62);
        Optional<Property> stensHus = propertyRepository.findById(72);
        Booking stensBooking = bookingRepository.findById(1);
        Transaction stenTrans = transResp.findById(5);


//        sten.addTransaction(trans, jons, stensBooking);
//        stensHus.get().addAddress(address);
//        sten.addBooking(booking);
//        stensHus.get().addImage(image);
//        stensHus.get().addImage(image2);
//        sten.addAccount(account);
//        sten.addProperty(property);
        
        System.out.println(stenTrans);
//        System.out.println(sten.getTransactions());
//        System.out.println(sten.getReceivedTransactions());

//        bookingRepository.save(stensBooking);
//        propertyRepository.save(stensHus.get());
//        transResp.save(trans);
    }
}
