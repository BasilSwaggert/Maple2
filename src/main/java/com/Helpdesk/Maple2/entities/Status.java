package com.Helpdesk.Maple2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status implements java.io.Serializable {
    @Id
    private Integer id;
    private String name;
    private boolean display;
    private String color;
    private Set<Tickets> tickets = new HashSet<Tickets>(0);

    public Status() {
        super();
    }

    public Status(String name, boolean display, String color) {
        this.name = name;
        this.display = display;
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
