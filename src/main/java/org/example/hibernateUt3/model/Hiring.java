package org.example.hibernateUt3.model;

import jakarta.persistence.*;
import org.example.hibernateUt3.model.client.Client;
import java.io.Serializable;

@Entity
@Table(name = "contratacion") // Asumo que la tabla se llama "contratacion"
public class Hiring implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contratacion_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_cliente")
    private Client client;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_evento")
    private Event event;

    @Column(name = "ciudad_evento")
    private String eventCity;

    public Hiring() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEventCity() {
        return eventCity;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    @Override
    public String toString() {
        return "Hiring{" +
                "id=" + id +
                ", client=" + client +
                ", event=" + event +
                ", eventCity='" + eventCity + '\'' +
                '}';
    }
}

