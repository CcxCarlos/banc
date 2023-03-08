<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
    <style>
        table {
            border-collapse: collapse;
            margin-left: 50px;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h1>Clientes:</h1>
<c:forEach var="c" items="${clientsList}">
    <h3> Cliente Nº${c.getId()}:</h3>
    <p>${c}</p>
    <table>
        <tr>
            <th>Numero cuenta</th>
            <th>Saldo</th>
        </tr>
        <c:forEach var="comptes" items="${c.getComptes()}">
            <tr>
                <td>${comptes.getnCuenta()}</td>
                <td>${comptes.getSaldo()}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</c:forEach>
</body>
</html>
