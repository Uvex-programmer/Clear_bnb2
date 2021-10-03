import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import express.Express;
import routes.*;

public class Main {
    
    public static void main(String[] args) {
        Express app = new Express();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        new UserRoutes(app);
        new PropertyRoutes(app);
        new BookingRoutes(app);
        new ReviewRoutes(app);
        new SocketRoutes(app);
        new WorkerRoutes(app);
        new AddressRoutes(app);


        app.listen(4000);
    }
}
