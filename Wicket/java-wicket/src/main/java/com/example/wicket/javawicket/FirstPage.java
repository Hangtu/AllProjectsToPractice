package com.example.wicket.javawicket;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

@WicketHomePage
public class FirstPage extends WebPage {
    public FirstPage() {
        add(new Label("message", "Hello Apache Wicket 1"));
        add(new Label("helloWorldMessage", "Hello world!!!"));
    }

	public FirstPage(User loggedUser) {
	}
}
