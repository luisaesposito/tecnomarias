package br.uff.tecnomarias.domain.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Produces;

/**
 * Producer com escopo de aplicação porque utiliza muitos recursos ao ser criado.
 * EntityManager com escopo de request, para ser usado e fechado a cada requisicao.
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit()
    EntityManagerFactory emFactory;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        if (emFactory == null)
            emFactory = Persistence.createEntityManagerFactory("tecnomariasPU");
        return emFactory.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
