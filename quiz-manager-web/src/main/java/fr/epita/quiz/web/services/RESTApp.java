package fr.epita.quiz.web.services;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class RESTApp extends ResourceConfig{
    public RESTApp() {
    	packages("fr.epita.quiz.web.services");
    	register(JacksonJsonProvider.class);
    }
}
