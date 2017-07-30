package com.blogspot.that_java_guy.model;

/**
 * @author Marko Bekhta
 */
public class Credentials {

    private String userName;

    private String password;

    public Credentials() {
    }

    private Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Credentials of(String userName, String password) {
        return new Credentials(userName, password);
    }
}
