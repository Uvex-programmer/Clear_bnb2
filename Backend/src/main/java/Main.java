import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import repositories.*;
import routes.BookingRoutes;
import routes.PropertyRoutes;
import routes.ReviewRoutes;
import routes.UserRoutes;
import util.MongoDB;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        SessionRepository sessionRepository = new SessionRepository();
        UserRepository userRepository = new UserRepository();
        PropertyRepository propertyRepository = new PropertyRepository();
        BookingRepository bookingRepository = new BookingRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        new UserRoutes(app, mapper, userRepository, sessionRepository);
        new PropertyRoutes(app, mapper, propertyRepository);
        new BookingRoutes(app, mapper, bookingRepository);
        new ReviewRoutes(app, mapper, reviewRepository);
        new MongoDB(propertyRepository);


        app.listen(4000);
    }
}
