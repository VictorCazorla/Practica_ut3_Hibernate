package org.example.hibernateUt3.model.client;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cli_particular")
public class PrivateClient extends Client implements Serializable {

    @Column(name = "nombre_cli_particular")
    private String name;

    @Column(name = "apellido_cli_particular")
    private String surname;

    @Column(name = "dni_cli_particular", unique = true)
    private String nationalId;

    public PrivateClient() {
    }

    public PrivateClient(String province, String municipality, String street, String name, String surname, String nationalId) {
        super(province, municipality, street);
        this.name = name;
        this.surname = surname;
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

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public String toString() {
        return "PrivateClient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nationalId='" + nationalId + '\'' +
                "} " + super.toString();
    }
}
