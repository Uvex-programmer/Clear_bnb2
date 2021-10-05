import express.Express;
import routes.*;
import util.MongoDB;

public class Main {
    
    public static void main(String[] args) {

        Express app = new Express();

        new UserRoutes(app);
        new PropertyRoutes(app);
        new BookingRoutes(app);
        new ReviewRoutes(app);
        new SocketRoutes(app);
        new WorkerRoutes(app);
        new AddressRoutes(app);
        new MongoDB();

        app.listen(4000);
    }
}
