package org.example.hibernateUt3.model.client;

import jakarta.persistence.*;
import org.example.hibernateUt3.model.Hiring;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "provincia_cliente")
    private String province;

    @Column(name = "municipio_cliente")
    private String municipality;

    @Column(name = "calle_cliente")
    private String street;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Hiring> hirings = new HashSet<>();

    public Client() {
    }

    public Client(String province, String municipality, String street) {
        this.province = province;
        this.municipality = municipality;
        this.street = street;
    }

    public Client(Long id, String province, String municipality, String street, Set<Hiring> clientEvents) {
        this.id = id;
        this.province = province;
        this.municipality = municipality;
        this.street = street;
        this.hirings = clientEvents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Hiring> getHirings() {
        return hirings;
    }

    public void setHirings(Set<Hiring> hirings) {
        this.hirings = hirings;
    }

    public void addClientEvent(Hiring hiring) {
        this.hirings.add(hiring);
    }

    public void removeClientEvent(Hiring hiring) {
        this.hirings.remove(hiring);
    }

    @Override
    public String toString() {
        return "Client{" + "\n" +
                "    id=" + id + ",\n" +
                "    province='" + province + "',\n" +
                "    municipality='" + municipality + "',\n" +
                "    street='" + street + "',\n" +
                '}';
    }
}
