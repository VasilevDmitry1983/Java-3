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
        // Проверка задания 1
/*        System.out.println(helper.select());
        System.out.println(helper.selectAuth("User1", "1"));
        System.out.println(helper.selectAuth("User1", "111"));
*/
        // Проверка задания 2

//        System.out.println(helper.updateNickname("User2", "2", "newUser222" ));
//        System.out.println(helper.updateNickname("User1", "1", "User333" ));
//        System.out.println(helper.select());



    }

}
