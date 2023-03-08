package com.example.controller;

import com.example.DataAccess.ClientsDaoImpl;
import com.example.DataAccess.ComptesDaoImpl;
import com.example.Model.Clients;
import com.example.Model.Comptes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "servlet", value = "/servlet")
public class Servlet extends HttpServlet {
    private ClientsDaoImpl clientsDao;
    private ComptesDaoImpl comptesDao;
    private String[] values;
    private final String[] nameAttributes = {"idFiscal", "nom", "email", "pais", "nCuenta"};
    boolean sinFiltros = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fileJSP = "";
        values = new String[]{request.getParameter("idFiscal"), request.getParameter("nom"), request.getParameter("email"),
                request.getParameter("pais"), request.getParameter("nCuenta"), request.getParameter("saldo")};
        clientsDao =  new ClientsDaoImpl();
        comptesDao = new ComptesDaoImpl();
        String submitButton = request.getParameter("submit");

        if (submitButton.equals("crearClients")) {
            request.setAttribute("mensaje",  insertData());
            fileJSP = "index.jsp";
        } else {
            List<Clients> clientsList = displayData();
            if (clientsList.isEmpty()) fileJSP = "clientsNotFound.jsp";
            else {
                request.setAttribute("clientsList", clientsList);
                fileJSP = "verDatos.jsp";
            }
        }
        try {
            request.getRequestDispatcher(fileJSP).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Clients> displayData() {
        String[][] data = getValuesToFilter();
        List<Clients> clientsList;
        if (sinFiltros) clientsList = clientsDao.getAllClients();
        else clientsList = clientsDao.getClientsbyByMultipleFilters(data);
        return clientsList;

    }

    private String[][] getValuesToFilter() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < nameAttributes.length; i++) {
            if (!values[i].isEmpty()) {
                list.add(values[i]);
                list2.add(nameAttributes[i]);
            }
        }
        sinFiltros = list.isEmpty();
        int size = list.size();
        String[][] v = new String[2][size];
        for (int i = 0; i < size; i++) {
            v[0][i] = list2.get(i);
            v[1][i] = list.get(i);
        }
        return v;
    }

    private String insertData() {
        Clients client = new Clients(values[0] , values[1], values[2], values[3]);
        Comptes compte = new Comptes(values[4],  Double.valueOf(values[5]));
        boolean clientExitst = clientsDao.objectExists(client);
        boolean compteExists = comptesDao.objectExists(compte);

        StringBuilder msg = new StringBuilder();
        if (!clientExitst && !compteExists){
            clientsDao.addObject(client);
            compte.setClient(client);
            comptesDao.addObject(compte);
            msg.append("Cuenta ").append(compte.getnCuenta()).append(" y Cliente ").append(client.getIdFiscal()).append(" creados correctamente ");
        }else if(clientExitst && !compteExists){
            compte.setClient(clientsDao.getClientByIdFiscal(client.getIdFiscal()));
            comptesDao.addObject(compte);
            msg.append("Cuenta ").append(compte.getnCuenta()).append(" creada en el cliente ").append(client.getIdFiscal());
        }else {
            msg.append("Error al crear el cliente y la cuenta: la cuenta ya existe ");
        }
        return msg.toString();
    }
}