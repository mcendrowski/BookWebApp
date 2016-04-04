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
    <body style="color:${color};">
        <p></p>
        <form method="POST" action="<%= response.encodeURL("BookController")%>"> 
            <input type="text" name="updated_book_id" value="${updated_record.bookId}" disabled><br>
            <input type="text" name="new_title" value="${updated_record.title}"><br>
            <input type="text" name="new_isbn" value="${updated_record.isbn}"><br>          


            <select name="new_author_id">
                <c:forEach var="author_item" items="${authorList}">



                    <c:if test="${updated_record.authorId.authorId==author_item.authorId}">
                        <option value="${author_item.authorId}" selected>${author_item.authorName}</option>  
                    </c:if> 

                    <c:if test="${updated_record.authorId.authorId!=author_item.authorId}">
                        <option value="${author_item.authorId}">${author_item.authorName}</option>  
                    </c:if> 


                </c:forEach>

            </select><br>



            <input type="submit" name="submit" value="save">
            <input type="hidden" name ="confirm_update" value ="confirm_update"> 
            <input type="hidden" name="updated_book_id" value="${updated_record.bookId}">
        </form>



        <h3> Parameter names :

            <%
                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(e.nextElement() + "     ");
                }

            %>
        </h3>

        <h3> Parameter values: 
            <%                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(request.getParameter(e.nextElement()) + "     ");
                }


            %>
        </h3>
        <h3> Attribute names : 
            <%                for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements();) {

                    out.println(e.nextElement() + "     ");
                }


            %>
        </h3>

        <h3> Session attribute names : 
            <%                for (Enumeration<String> s = session.getAttributeNames(); s.hasMoreElements();) {

                    out.println(s.nextElement() + "     ");
                }


            %>
        </h3>


        <h3> Session attributes : 
            <%                for (Enumeration<String> s = session.getAttributeNames(); s.hasMoreElements();) {

                    out.println(session.getAttribute(s.nextElement()).toString() + "     ");
                }


            %>
        </h3>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
