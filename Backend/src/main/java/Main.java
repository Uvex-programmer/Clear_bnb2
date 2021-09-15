import express.Express;

public class Main {

    public static void main(String[] args) {
        Express app = new Express();
        new ConnectMysql();

        app.listen(4000);
    }
}
