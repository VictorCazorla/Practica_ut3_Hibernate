package org.example.hibernateUt3.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "evento")
public class Event implements Serializable { // Here I implement Serializable in case it's needed in future uses

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "nombre_evento", unique = true, nullable = false)
    private String name;

    @Column(name = "horario_evento")
    private LocalDateTime timetable;

    @Column(name = "precio_evento")
    private double price;

    @Column(name = "descripcion_evento", length = 500) // I've chosen to give a 500char room for the description, 255 seemed a bit short for me
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_presentador")
    private Host eventHost;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "evento_animador",
            joinColumns= @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_animador")
    )
    private Set<Entertainer> eventEntertainers = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Hiring> hirings = new HashSet<>();

    public Event() {
    }

    public Event(String name, LocalDateTime timetable, double price, String description) {
        this.name = name;
        this.timetable = timetable;
        this.price = price;
        this.description = description;
    }

    public Event(String name, LocalDateTime timetable, double price, String description, Host eventHost) {
        this.name = name;
        this.timetable = timetable;
        this.price = price;
        this.description = description;
        this.eventHost = eventHost;
    }

    public Event(String name, LocalDateTime timetable, double price, String description, Host eventHost, Set<Entertainer> eventEntertainers) {
        this.name = name;
        this.timetable = timetable;
        this.price = price;
        this.description = description;
        this.eventHost = eventHost;
        this.eventEntertainers = eventEntertainers;
    }

    public Event(String name, LocalDateTime timetable, double price, String description, Host eventHost, Set<Entertainer> eventEntertainers, Set<Hiring> eventClients) {
        this.name = name;
        this.timetable = timetable;
        this.price = price;
        this.description = description;
        this.eventHost = eventHost;
        this.eventEntertainers = eventEntertainers;
        this.hirings = eventClients;
    }

    public Event(Long id, String name, LocalDateTime timetable, double price, String description, Host eventHost, Set<Entertainer> eventEntertainers, Set<Hiring> eventClients) {
        this.id = id;
        this.name = name;
        this.timetable = timetable;
        this.price = price;
        this.description = description;
        this.eventHost = eventHost;
        this.eventEntertainers = eventEntertainers;
        this.hirings = eventClients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimetable() {
        return timetable;
    }

    public void setTimetable(LocalDateTime timetable) {
        this.timetable = timetable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Host getEventHost() {
        return eventHost;
    }

    public void setEventHost(Host eventHost) {
        this.eventHost = eventHost;
    }

    public Set<Entertainer> getEventEntertainers() {
        return eventEntertainers;
    }

    public void setEventEntertainers(Set<Entertainer> eventEntertainers) {
        this.eventEntertainers = eventEntertainers;
    }

    public Set<Hiring> getHirings() {
        return hirings;
    }

    public void setHirings(Set<Hiring> hirings) {
        this.hirings = hirings;
    }

    public void addEntertainer(Entertainer entertainer) {
        eventEntertainers.add(entertainer);
    }

    public void removeEntertainer(Entertainer entertainer) {
        eventEntertainers.remove(entertainer);
    }

    public void addHiring(Hiring hiring) {
        hirings.add(hiring);
    }

    public void removeHiring(Hiring hiring) {
        hirings.remove(hiring);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timetable=" + timetable +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
