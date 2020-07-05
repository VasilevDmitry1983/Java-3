package dbase;

import lombok.Data;

@Data
public class User {

    private String nickname, login, password;


    public User(String nickname, String login, String password) {
        this.nickname = nickname;
        this.login = login;
        this.password = password;

    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
