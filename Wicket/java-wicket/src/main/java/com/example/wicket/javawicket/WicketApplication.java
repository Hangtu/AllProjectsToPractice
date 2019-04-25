package com.example.wicket.javawicket;

import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {
    public WicketApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
   
   @Override
    public Class getHomePage() {
        return FirstPage.class;
    }
}