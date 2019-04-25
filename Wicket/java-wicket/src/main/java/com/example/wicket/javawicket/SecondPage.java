package com.example.wicket.javawicket;

import org.apache.wicket.markup.html.WebPage;

public class SecondPage extends WebPage {

    public SecondPage() {
        add(new NavomaticBorder("navomaticBorder"));
    }
    
}