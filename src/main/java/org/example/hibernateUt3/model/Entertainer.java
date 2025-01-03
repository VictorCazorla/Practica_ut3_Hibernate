package org.example.hibernateUt3.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animador")
public class Entertainer implements Serializable { //Here I implement Serializable in case it's needed in future uses

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animador")
    private Long id;

    @Column(name = "dni_animador", unique = true, length = 9)
    private String nationalId;

    @Column(name = "nombre_animador")
    private String name;

    @Column(name = "apellido_animador")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "id_disfraz")
    private Costume costume;

    @ManyToMany(mappedBy = "eventEntertainers")
    private Set<Event> entertainerEvents = new HashSet<>();

    public Entertainer() {
    }

    public Entertainer(String nationalId, String name) {
        this.nationalId = nationalId;
        this.name = name;
    }

    public Entertainer(String nationalId, String name, String surname) {
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
    }

    public Entertainer(String nationalId, String name, String surname, Costume costume) {
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.costume = costume;
    }

    public Entertainer(String nationalId, String name, String surname, Costume costume, Set<Event> entertainerEvents) {
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.costume = costume;
        this.entertainerEvents = entertainerEvents;
    }

    public Entertainer(Long id, String nationalId, String name, String surname, Costume costume, Set<Event> entertainerEvents) {
        this.id = id;
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.costume = costume;
        this.entertainerEvents = entertainerEvents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Costume getCostume() {
        return costume;
    }

    public void setCostume(Costume costume) {
        this.costume = costume;
    }

    public Set<Event> getEntertainerEvents() {
        return entertainerEvents;
    }

    public void setEntertainerEvents(Set<Event> entertainerEvents) {
        this.entertainerEvents = entertainerEvents;
    }

    public void addEvent(Event event) {
        entertainerEvents.add(event);
    }

    public void removeEvent(Event event) {
        entertainerEvents.remove(event);
    }

    @Override
    public String toString() {
        return "Entertainer{" +
                "id=" + id +
                ", nationalId='" + nationalId + '\'' +
                ", name='" + name + '\'' +
                ", costume=" + costume +
                '}';
    }
}
