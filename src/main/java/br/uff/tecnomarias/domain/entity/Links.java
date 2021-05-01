package br.uff.tecnomarias.domain.entity;

import javax.persistence.*;

@Entity
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String linkedIn;
    private String gitHub;
    private String portfolio;
    private String facebook;

    public Links() {
    }

    public Links atualizarLinks(final Links links) {
        this.linkedIn = links.getLinkedIn();
        this.gitHub = links.getGitHub();
        this.portfolio = links.getPortfolio();
        this.facebook = links.getFacebook();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
