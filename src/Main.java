/**
 * Created by peach on 12.05.16
 */
public class Main {

    //private static SqlConnector sqlConnector;
    //private static MongoDbConnector mongoDbConnector;

    public static void main(String[] args) {
        MigrateHelper helper = new MigrateHelper();
        helper.migrateVersionOne();
        helper.migrateVersionTwo();
    }
}
