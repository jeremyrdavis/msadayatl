package com.redhat.msadayatl;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Todo extends PanacheEntity {

    public String title;

    @Column(name="ordering")
    public int order;

    public boolean completed;
}
