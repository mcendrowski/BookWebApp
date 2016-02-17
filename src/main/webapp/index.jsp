<%-- 
    Document   : index
    Created on : Feb 7, 2016, 9:35:44 PM
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
         <form id="startingForm" name="startingForm" method="POST" action="AuthorController" >
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <input type="submit" class="btn btn-info btn-large" name="submit" value="Open View All Authors page"/>
                    </div>
                </div>
            </div>
        </form>
            
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
