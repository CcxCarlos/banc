package com.example.DataAccess;

import com.example.Model.Clients;

import java.util.List;

public interface ClientsDao {
    Clients getClientByIdFiscal(String idFiscal);
    Clients getClientByCuenta(String cuenta);
    List<Clients> getAllClients();

    List<Clients> getClientsByEmail(String email);

    List<Clients> getClientsByNombre(String name);

    List<Clients> getClientsByPais(String pais);

}
