<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    table {
        border-collapse: collapse;
        width: 100%;
    }
    th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
        vertical-align: top;
    }
    th {
        width: 20%;
        font-weight: bold;
    }
    form {
        margin: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    input {
        width: 100%;
        padding: 6px;
        box-sizing: border-box;
    }
    button {
        padding: 8px;
        background-color: #333333;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        display: inline-block;
        margin: 10px;
    }
    #buttons{
        display: flex;
        justify-content: center;
    }
    button:hover {
        background-color: #111111;
    }
</style>
<head>
    <title>Banc</title>
</head>
<body onload="mostrarMensaje()">
<h1>Banco</h1>
<br>
<br>
<form id="form" action="Servlet.do" method="post">
    <table>
        <tr>
            <th> <label for="nom">Nombre Cliente:</label></th>
            <td> <input type="text" id="nom" name="nom" maxlength="100"></td>
        </tr>

        <tr>
            <th> <label for="idFiscal">Id Fiscal(DNI,NIE):</label></th>
            <td> <input type="text" id="idFiscal" name="idFiscal" minlength="9" maxlength="9" pattern="[0-9]{8}[A-Za-z]"></td>
        </tr>

        <tr>
            <th> <label for="email">Email cliente:</label></th>
            <td> <input type="email" id="email" name="email" maxlength="50"></td>
        </tr>

        <tr>
            <th> <label for="pais">País:</label></th>
            <td> <input type="text" id="pais" name="pais" maxlength="20"></td>
        </tr>

        <tr>
            <th> <label for="nCuenta">Cuenta:</label></th>
            <td> <input type="text" id="nCuenta" name="nCuenta" minlength="20" maxlength="20"   ></td>
        </tr>

        <tr>
            <th> <label for="saldo">Ingreso inicial(€):</label></th>
            <td> <input type="number" id="saldo" name="saldo" step="0.01" min="0.01" maxlength="16" max="9999999999999.99"></td>
        </tr>
    </table>
    <br>
    <div id="buttons">
        <button type="submit" name="submit" value="crearClients">Crear cliente</button>
        <button type="submit" name="submit" value="mostrarClients">Mostrar clientes</button>
    </div>
</form>


<script>
    const form = document.getElementById("form");

    form.addEventListener('submit', function (event) {
        const buttonPressed = event.submitter.value;
        if (buttonPressed==="crearClients"){
            if (isEmpty()) {
                event.preventDefault();
                alert("Por favor, complete todos los campos");
            }
        }/*else {
            if (isEmpty2()) {
                event.preventDefault();
                alert("Por favor, complete algun campo para realizar la busqueda");
            }
        }*/
    });
    function isEmpty() {
        const inputs = document.getElementsByTagName('input');
        for (let i = 0; i < inputs.length; i++) {
            if (inputs[i].value.trim() === "") return true;
        }
        return false;
    }
    function isEmpty2() {
        const inputs = document.getElementsByTagName('input');
        for (let i = 0; i < inputs.length-1; i++) {
            if (!(inputs[i].value.trim() === "")) return false;
        }
        return true;
    }
    function mostrarMensaje() {
        var mostrado = false;
        var mensaje = "${mensaje}";
        if (mensaje !== "" && !mostrado) {
            mostrado = true;
            alert(mensaje);
        }
    }

</script>
</body>
</html>