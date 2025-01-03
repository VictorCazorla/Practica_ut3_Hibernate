package org.example.hibernateUt3.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "disfraz")
public class Costume implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disfraz")
    private Long id;

    @Column(name = "personaje_disfraz", unique = true)
    private String character;

    @Column(name = "precio_disfraz")
    private double price;

    @OneToMany(mappedBy = "costume")
    private Set<Entertainer> entertainers = new HashSet<>();

    public Costume() {
    }

    public Costume(String character, double price) {
        this.character = character;
        this.price = price;
    }

    public Costume(String character, double price, Set<Entertainer> entertainers) {
        this.character = character;
        this.price = price;
        this.entertainers = entertainers;
    }

    public Costume(Long id, String character, double price, Set<Entertainer> entertainers) {
        this.id = id;
        this.character = character;
        this.price = price;
        this.entertainers = entertainers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Entertainer> getEntertainers() {
        return entertainers;
    }

    public void setEntertainers(Set<Entertainer> entertainers) {
        this.entertainers = entertainers;
    }

    public void addEntertainer(Entertainer entertainer) {
        this.entertainers.add(entertainer);
    }

    public void removeEntertainer(Entertainer entertainer) {
        this.entertainers.remove(entertainer);
    }

    @Override
    public String toString() {
        return "Costume{" +
                "id=" + id +
                ", character='" + character + '\'' +
                ", price=" + price +
                '}';
    }
}
