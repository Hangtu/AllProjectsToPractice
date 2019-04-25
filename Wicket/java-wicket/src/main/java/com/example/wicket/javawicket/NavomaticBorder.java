package com.example.wicket.javawicket;

import org.apache.wicket.markup.html.border.Border;
public class NavomaticBorder extends Border {
    public NavomaticBorder(final String componentName) {
        super(componentName);
        addToBorder(new MyBorder("navigationBorder"));
        addToBorder(new MyBorder("bodyBorder"));
    }
}