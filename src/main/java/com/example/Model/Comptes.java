package com.example.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Comptes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nCuenta")
    private String nCuenta;
    @Basic
    @Column(name = "saldo")
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Clients client;

    public Comptes() {
    }

    public Comptes(String nCuenta, Double saldo) {
        this.nCuenta = nCuenta;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnCuenta() {
        return nCuenta;
    }

    public void setnCuenta(String nCuenta) {
        this.nCuenta = nCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comptes comptes = (Comptes) o;

        return Objects.equals(nCuenta, comptes.nCuenta);
    }

    @Override
    public int hashCode() {
        return nCuenta != null ? nCuenta.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Comptes{" +
                "id=" + id +
                ", nCuenta='" + nCuenta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
