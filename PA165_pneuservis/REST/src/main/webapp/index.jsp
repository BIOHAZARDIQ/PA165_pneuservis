<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REST interface</title>
    </head>
    <body>
        <h1>REST Interface for PA165 pneuservis project</h1>
        <table border="1" cellpadding="5">
            <tr>
                <td style="font-weight:bold">Path (all paths starts with ./pa165/rest/)</td>
                <td style="font-weight:bold">Functionality</td> 
            </tr>
            <tr>
                <td><a href="./tires">./tires</a></td>
                <td>List of all tires in database</td> 
            </tr>
            <tr>
                <td><a href="./tires/htmllist">./tires/htmllist</a></td>
                <td>List of all tires in database in HTML table format (only Id, Name, Price)</td> 
            </tr>
            <tr>
                <td><a href="./tires/{id}">./tires/{id}</a></td>
                <td>Tire with given id (replace {id} for id of tire)</td> 
            </tr>
            <tr>
                <td>./tires/create</td>
                <td>Ability to use curl to create new Tire with given parameters</td> 
            </tr>
            <tr>
                <td>./tires/update/{id}</td>
                <td>Ability to use curl to update new Tire with given parameters and given id</td> 
            </tr>
            <tr>
                <td><a href="./tires/delete/{id}">./tires/delete/{id}</a></td>
                <td>Ability to use curl or webpage to delete existing Tire with given id</td> 
            </tr>
            <tr>
                <td><a href="./customers">./customers</a></td>
                <td>List of all customers in database</td> 
            </tr>
            <tr>
                <td><a href="./services">./services</a></td>
                <td>List of all services in database</td> 
            </tr>
            <tr>
                <td><a href="./orders">./orders</a></td>
                <td>List of all orders in database</td> 
            </tr>
        </table>
    </body>
</html>
