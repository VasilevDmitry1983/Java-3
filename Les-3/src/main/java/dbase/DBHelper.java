package dbase;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Statement stmt;
    private Connection conn;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(JDBC.PREFIX + "myDB");
            stmt = conn.createStatement();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(User user) throws SQLException {
        String insertQuery = String.format("insert into users values('%s', '%s', '%s');",
                user.getNickname(), user.getLogin(), user.getPassword());
        stmt.execute(insertQuery);
    }

    public void delete(String nickname) {
    }

    public void init() throws SQLException {
        String createTable = "create table if not exists users(nickname TEXT, login TEXT, password TEXT);";
        stmt.execute(createTable);
    }

    public void update(User user) {
    }

    public List<User> select() throws SQLException {
        String query = "select * from users;";
        ResultSet rs = stmt.executeQuery(query);
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            String nickname = rs.getString("nickname");
            String login = rs.getString("login");
            String password = rs.getString("password");

            users.add(new User(nickname, login, password));
        }
        return users;
    }
    // Задание Java 3-2 (N1)(Авторизация через базу данных)
    public String selectAuth(String log, String pass) throws SQLException {
        String query = "select * from users WHERE login='" + log + "';";
        ResultSet rs = stmt.executeQuery(query);
        String nickname = rs.getString("nickname");
        String password = rs.getString("password");
        if (password.equals(pass)){
            return nickname;
        }else {
            return "Неправильный логин или пароль";
        }
    }

    // Задание Java 3-2 (N2) (Смена никнейма)
    public String updateNickname(String log, String pass, String newNickname) throws SQLException {
        // проверка пары логин -пароль у меняющего свой ник
        String query = "select * from users WHERE login='" + log + "';";
        ResultSet rs = stmt.executeQuery(query);
        String password = rs.getString("password");

        // смена ника

        if (checkUniNickname(newNickname)){
            return "Такой ник уже существует";
        }else {
            if (password.equals(pass)){
                String query2 = "UPDATE users SET nickname = '" + newNickname + "' WHERE login = '" + log + "';";
                stmt.executeUpdate(query2);
                return "nickname изменен";
            }else {
                return "Неправильный логин или пароль";
            }
        }

    }

    // проверка на уникальность ника
    public boolean checkUniNickname(String nick) {
        String query = "select * from users WHERE nickname='" + nick + "';";
        ResultSet rs1 = null;
        try {
            rs1 = stmt.executeQuery(query);
            String nickname = rs1.getString("nickname");
        } catch (SQLException e) {
            return false;
        }
        return true;


    }
}
