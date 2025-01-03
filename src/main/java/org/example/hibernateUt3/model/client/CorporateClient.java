package org.example.hibernateUt3.model.client;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cli_empresa")
public class CorporateClient extends Client implements Serializable {

    @Column(name = "nombre_cli_empresa")
    private String name;

    @Column(name = "contacto_cli_empresa")
    private String contact;

    @Column(name = "CIF_cli_empresa", unique = true)
    private String VAT;

    public CorporateClient() {
    }

    public CorporateClient(String province, String municipality, String street, String name, String contact, String VAT) {
        super(province, municipality, street);
        this.name = name;
        this.contact = contact;
        this.VAT = VAT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    @Override
    public String toString() {
        return "CorporateClient{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", VAT='" + VAT + '\'' +
                "} " + super.toString();
    }
}
