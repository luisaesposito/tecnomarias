package br.uff.tecnomarias;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("api")
public class RESTApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.disableMoxyJson", true);
        return properties;
    }

}
