<%-- 
    Document   : updateDetails
    Created on : Feb 29, 2016, 7:03:27 PM
    Author     : mcendrowski
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update detail page</title>
    </head>
    <body>
        <p></p>
        <form method="POST" action="AuthorController"> 
            <input type="text" name="updated_author_id" value="${authorRecord.authorId}"><br>
            <input type="text" name="update_value" value="${authorRecord.authorName}"><br>
            <input type="text" name="date_added" value="${authorRecord.dateAdded}">
            <input type="submit" value="save" name="submit">
            <input type="hidden" name ="execute" value ="save">            
        </form>

   

                  <h3> Parameter names :

            <%
                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(e.nextElement()+"     ");
                }

            %>
        </h3>

        <h3> Parameter values: 
            <%                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(request.getParameter(e.nextElement())+"     ");
                }


            %>
            
                    <h3> Attribute names : 
            <%                for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements();) {

                    out.println(e.nextElement()+"     ");
                }


            %>
        </h3>

            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
