<%-- 
    Document   : viewAllAuthors
    Created on : Feb 7, 2016, 9:36:30 PM
    Author     : mcendrowski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
         <p></p>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <table class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-1 text-center">Id</th>    
                            <th class="col-md-4 text-center">Name</th>
                            <th class="col-md-3 text-center">Date</th>
                        </tr>

                        <c:forEach var="item" items="${authorList}">

                            <tr>
                                 <td class="text-right">${item.authorId}</td>
                                <td class="text-left">${item.authorName}</td>
                                <td class="text-left">${item.dateAdded}</td>
                                

                            </tr>
                        </c:forEach>


                    </table>
                </div>
            </div>
        </div>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
