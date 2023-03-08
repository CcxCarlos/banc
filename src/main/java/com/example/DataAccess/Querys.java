package com.example.DataAccess;

public class Querys {
    public static final String ALL_CLIENTS = "SELECT c FROM Clients c";
    public static final String CLIENTS_BY_ID_FISCAL = "SELECT c FROM Clients c WHERE idFiscal= :id";
    public static final String CLIENTS_BY_NAME = "SELECT c FROM Clients c WHERE nom like :name";
    public static final String CLIENTS_BY_EMAIL = "SELECT c FROM Clients c WHERE email like :email";
    public static final String CLIENTS_BY_PAIS = "SELECT c FROM Clients c WHERE pais like :pais";

    public static final String CLIENT_BY_CUENTA = "SELECT c FROM Clients c JOIN c.comptes comptes WHERE comptes.compte = :compte";

    public static final String CUENTA = "SELECT c FROM Comptes c WHERE nCuenta = :compte";

    public static final String MULTIPLE_FILTERS = "SELECT c FROM Clients c WHERE :parameter";

    public static String getParameter(String s){
        for (String sting : s.split(" ")) {
            if (sting.contains(":")) return sting.substring(1);
        }
        return "";
    };
}
