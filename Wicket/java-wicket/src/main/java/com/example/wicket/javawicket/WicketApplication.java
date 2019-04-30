package com.example.wicket.javawicket;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {
    public WicketApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */

    /*
     * @Override public Class getHomePageTest() { return FirstPage.class; }
     */

    // our DB ;)
    private List<User> users = Arrays.asList(new User[] { new User("Administrator", "admin", "admin"),
            new User("Peter", "peter", "peter"), new User("Ana", "ana", "ana") });

    @Override
    public Class<? extends WebPage> getHomePage() {
        return SignInPage.class;
    }

    @Override
    public void init() {
        // call the init() method of the superclass
        super.init();
    }

    // search in our DB
    public User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        // if not found, return null
        return null;
    }
}