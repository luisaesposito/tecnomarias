package br.uff.tecnomarias;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(JsonParseExceptionMapper.class);
        context.register(JsonMappingExceptionMapper.class);
        context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
        return true;
    }
}
