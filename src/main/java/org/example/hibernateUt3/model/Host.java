package org.example.hibernateUt3.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "presentador")
public class Host implements Serializable { // Here I implement Serializable in case it's needed in future uses

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presentador")
    private Long id;

    @Column(name = "dni_presentador", length = 9, unique = true, nullable = false)
    private String nationalId;

    @Column(name = "nombre_presentador", nullable = false)
    private String name;

    @Column(name = "apellido_presentador", nullable = false)
    private String surname;

    @Column(name = "anio_presentador", nullable = false)
    private int year;

    @OneToMany(mappedBy = "eventHost", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Event> hostEvents = new HashSet<>();

    public Host() {
    }

    public Host(String nationalId, String name, String surname, int year) {
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Host(String nationalId, String name, String surname, int year, Set<Event> hostEvents) {
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.hostEvents = hostEvents;
    }

    public Host(Long id, String nationalId, String name, String surname, int year, Set<Event> hostEvents) {
        this.id = id;
        this.nationalId = nationalId;
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.hostEvents = hostEvents;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Event> getHostEvents() {
        return hostEvents;
    }

    public void setHostEvents(Set<Event> hostEvents) {
        this.hostEvents = hostEvents;
    }

    public void addEvent(Event event) {
        hostEvents.add(event);
        event.setEventHost(this);
    }

    public void removeEvent(Event event) {
        hostEvents.remove(event);
        event.setEventHost(null);
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", nationalId='" + nationalId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", hostEvents=" + hostEvents +
                '}';
    }
}
