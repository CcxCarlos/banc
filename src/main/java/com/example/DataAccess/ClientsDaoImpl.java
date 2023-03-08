package com.example.DataAccess;

import com.example.Model.Clients;

import java.util.List;
import java.util.stream.Collectors;

public class ClientsDaoImpl extends Dao implements ClientsDao{

    @Override
    public Clients getClientByIdFiscal(String idFiscal) {
        return (Clients) getValues(executeQuery(Querys.CLIENTS_BY_ID_FISCAL, Querys.getParameter(Querys.CLIENTS_BY_ID_FISCAL)
                , idFiscal, Clients.class)).get(0);
    }

    @Override
    public Clients getClientByCuenta(String cuenta) {
        return (Clients) getValues(executeQuery(Querys.CLIENT_BY_CUENTA, Querys.getParameter(Querys.CLIENT_BY_CUENTA),
                cuenta, Clients.class)).get(0);
    }

    @Override
    public List<Clients> getAllClients() {
        return objectsToClients(getValues(executeQuery(Querys.ALL_CLIENTS, Clients.class)));
    }

    @Override
    public List<Clients> getClientsByEmail(String email) {
        return objectsToClients(getValues(executeQuery(Querys.CLIENTS_BY_EMAIL, Querys.getParameter(Querys.CLIENTS_BY_EMAIL),
                        email, Clients.class)));
    }

    @Override
    public List<Clients> getClientsByNombre(String name) {
        return objectsToClients( getValues(executeQuery(Querys.CLIENTS_BY_NAME, Querys.getParameter(Querys.CLIENTS_BY_NAME),name,
                Clients.class)));
    }

    @Override
    public List<Clients> getClientsByPais(String pais) {
        return objectsToClients(getValues(executeQuery(Querys.CLIENTS_BY_PAIS, Querys.getParameter(Querys.CLIENTS_BY_PAIS), pais,
                Clients.class)));
    }
    private List<Clients> objectsToClients (List<Object> list){
        return list.stream().filter(obj -> obj instanceof Clients)
                .map(obj -> (Clients) obj)
                .collect(Collectors.toList());
    }
}
