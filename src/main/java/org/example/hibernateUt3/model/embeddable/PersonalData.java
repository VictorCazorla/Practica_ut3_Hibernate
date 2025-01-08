package org.example.hibernateUt3.model.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PersonalData {
    private String name;
    private String nationalId;
    private String surname;


    public PersonalData() {

    }

    public PersonalData(String name, String nationalId, String surname) {
        this.name = name;
        this.nationalId = nationalId;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
