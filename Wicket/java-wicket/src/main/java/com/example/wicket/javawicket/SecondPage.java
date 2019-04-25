package com.example.wicket.javawicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class SecondPage extends WebPage {

    public SecondPage() {
        add(new Label("message", "Hello Apache Wicket 2"));
    }
    
}