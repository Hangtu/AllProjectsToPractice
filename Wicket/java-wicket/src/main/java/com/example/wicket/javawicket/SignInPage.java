package com.example.wicket.javawicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

public class SignInPage extends WebPage {

    private DropDownChoice<String> type; 
    private User user;
    

    public SignInPage() {
        user = new User();
        add(new Label("title", "Sign In"));
        add(new SigInForm("form"));
        add(new FeedbackPanel("feedback"));
    }

    private class SigInForm extends Form<Void> {
        public SigInForm(String id) {
            super(id);

            add(new TextField<String>("username", new PropertyModel<String>(user, "username")));
            add(new PasswordTextField("password", new PropertyModel<String>(user, "password")));
        }

        @Override
        protected void onSubmit() {
            // User loggedUser = ((WicketApplication)
            // WebApplication.get()).getUser(user.getUsername(), user.getPassword());
           
            user.setName("Hang Tu");
            if (user != null) {
                setResponsePage(new FirstPage(user));
            } else {
                // Register the error message with the feedback// panel
                error("Wrong username or password");
            }
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}