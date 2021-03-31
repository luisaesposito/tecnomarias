package br.uff.tecnomarias;

import br.uff.tecnomarias.rest.resource.VagaResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("api")
public class RESTApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(VagaResource.class);
    }
}
