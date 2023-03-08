package com.example.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Clients {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "idFiscal")
    private String idFiscal;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "pais")
    private String pais;
    @OneToMany(mappedBy = "client")
    private List<Comptes> comptes;

    public Clients() {
    }

    public Clients(String idFiscal, String nom, String email, String pais) {
        this.idFiscal = idFiscal;
        this.nom = nom;
        this.email = email;
        this.pais = pais;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdFiscal() {
        return idFiscal;
    }

    public void setIdFiscal(String idFiscal) {
        this.idFiscal = idFiscal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Comptes> getComptes() {
        return comptes;
    }

    public void setComptes(List<Comptes> comptes) {
        this.comptes = comptes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients clients = (Clients) o;

        return Objects.equals(idFiscal, clients.idFiscal);
    }

    @Override
    public int hashCode() {
        return idFiscal != null ? idFiscal.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "<b>Nom</b>='" + nom + '\'' +
                ", <b>IdFiscal</b>='" + idFiscal + '\'' +
                ", <b>Email</b>='" + email + '\'' +
                ", <b>Pais</b>='" + pais + '\'' + ":";
    }
}
