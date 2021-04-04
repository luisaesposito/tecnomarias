package br.uff.tecnomarias;

import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public class JacksonFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        // add the default Jackson exception mappers and allow jaxb annotations
        context.register(JsonParseExceptionMapper.class);
        context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        return true;
    }
}
