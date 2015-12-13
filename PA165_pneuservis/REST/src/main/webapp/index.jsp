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
                <td><a>./tires</a></td>
                <td>Rest interface subpage for class Tire (no functionality)</td> 
            </tr>
            <tr>
                <td><a href="./tires/list">./tires/list</a></td>
                <td>List of all tires in database</td> 
            </tr>
            <tr>
                <td><a href="./tires/htmllist">./tires/htmllist</a></td>
                <td>List of all tires in database in HTML table format (only Id, Name, Price)</td> 
            </tr>
            <tr>
                <td>./tires/id/{id}</a></td>
                <td>Tire with given id (not working yet)</td> 
            </tr>
        </table>
    </body>
</html>
