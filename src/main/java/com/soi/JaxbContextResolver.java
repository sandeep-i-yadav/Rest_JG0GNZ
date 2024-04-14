package com.soi;

import com.soi.model.Movie;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;

@Provider
public class JaxbContextResolver implements ContextResolver<JAXBContext> {
    private final JAXBContext context;

    public JaxbContextResolver() throws JAXBException {
        // Register all JAXB-annotated classes here
        this.context = JAXBContext.newInstance(Movie.class, ArrayList.class);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        if (objectType.equals(Movie.class) || objectType.equals(ArrayList.class)) {
            return context;
        }
        return null;
    }
}
