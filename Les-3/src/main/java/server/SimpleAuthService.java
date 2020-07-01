package server;

import dbase.DBHelper;
import dbase.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {

    // Задание Java 3-2 (N1)
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {

        DBHelper helper = new DBHelper();
        helper.connect();
        helper.init();
        helper.insert(new User("User111", "User1", "1"));
        helper.insert(new User("User222", "User2", "2"));
        helper.insert(new User("User333", "User3", "3"));
        return helper.selectAuth(login, password);

    }
}
