<%-- 
    Document   : DiscountCodeViews
    Created on : 7 nov. 2018, 14:12:11
    Author     : pierr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    <body>
        <h1>Edition des taux de remise</h1>

        <c:choose>
            <%-- On n'a pas trouvé les codes de promotions --%>
            <c:when test="${empty listDiscountCode}">
			Aucun code de promo			
            </c:when>
            <c:otherwise> <%-- On a trouvé --%>
                <%--On fait un formulaire pour y ajouter un code --%>
                <form method="GET">
                    Code promo :  <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES">
                    Valeur : <input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="2"><br/>
                    <input type="hidden" name="action" value="ADD">
                    <input type="submit" value="Ajouter">
                </form>
                
                <table border=2>
                <%-- On affiche un tableau avec les codes --%>
                <tr> <th>Code</th> <th>Taux</th><th>Supprimer</th></tr>           
                <c:forEach var="codeDiscount" items="${listDiscountCode}">
                    <tr><td>${codeDiscount.discountCode}</td><td>${codeDiscount.rate}</td><td><a href="?action=DELETE&code=${codeDiscount.discountCode}">Supprimer</a></td></tr>
                </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>         
    </body>
</html>
