package com.example.wicket.javawicket;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;


import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

@WicketHomePage
public class FirstPage extends WebPage {
    
    public FirstPage() {
        add(new Label("message", "Hello"));
        add(new Link("signout") {
            @Override
            public void onClick() {
                setResponsePage(SignInPage.class);
            }

            @Override
            public MarkupContainer setDefaultModel(IModel model) {
                return null;
            }
        });
    }
    
    public FirstPage(User loggedUser) {
        add(new Label("message", "Hello, " + loggedUser.getName()));
        add(new Link("signout") {
            @Override
            public void onClick() {
                System.out.println("Trying to exit");
                setResponsePage(SignInPage.class);
            }

            @Override
            public MarkupContainer setDefaultModel(IModel model) {
                return null;
            }
        });
    }
    
}
