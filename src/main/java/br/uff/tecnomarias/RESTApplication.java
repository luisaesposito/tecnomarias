package br.uff.tecnomarias;

import br.uff.tecnomarias.rest.resource.HealthResource;
import br.uff.tecnomarias.rest.resource.VagaResource;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("api")
public class RESTApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(VagaResource.class));
    }

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.disableMoxyJson", true);

        return properties;
    }

}
