package dbase;
import java.sql.SQLException;

public class DBaseTest {
    public static void main(String[] args) throws SQLException {
        DBHelper helper = new DBHelper();
        helper.connect();
        helper.init();

        helper.insert(new User("User111", "User1", "1"));
        helper.insert(new User("User222", "User2", "2"));
        helper.insert(new User("User333", "User3", "3"));




    }

}
