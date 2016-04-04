<%-- 
    Document   : viewAllBooks_2
    Created on : Apr 3, 2016, 8:49:52 PM
    Author     : MCENDROWSKI
--%>

<%@page import="java.util.Enumeration"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Books Page</title>
    </head>
    <body style="color:${color};">    
        <h1>Hello World!</h1>

        <p>${user}</p>
        <form method="POST" action="<%= response.encodeURL("BookController")%>">        
            <input type="submit" name="submit" value="RESET">
            <input type="hidden" name="reset_mode" value="reset_mode">
        </form>

        <br> 

        <form method="POST" action="<%= response.encodeURL("BookController")%>">        
            <input type="submit" name="mode" value="${mode}">
            <input type="hidden" name="switch_mode" value="switch_mode">
        </form>

        <br> 
        <form method="POST" action="<%= response.encodeURL("BookController")%>">        
            <input type="submit" name="submit" value="${show_hide_user_button}">
            <input type="hidden" name="show_hide_user" value="show_hide_user">

        </form>  

        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <table class="table table-bordered table-striped table-hover">
                    <tr>
                        <th class="col-md-1 text-center">Book Id</th>    
                        <th class="col-md-4 text-center">Book Title</th>
                        <th class="col-md-3 text-center">ISBN</th>
                        <th class="col-md-3 text-center">Author Id</th>

                    </tr>
  <c:forEach var="item" items="${bookList}">

                            <tr>
                                <td class="text-right">${item.bookId}</td>
                                <td class="text-right">${item.title}</td>
                                <td class="text-right">${item.isbn}</td>
                                <td class="text-right">${item.authorId.authorId}</td>
                                
                                
                            
                          


                            </tr>
                        </c:forEach>
                 
                </table>
            </div>
        </div>
        <h3> Parameter names :

            <%
                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(e.nextElement());
                }

            %>
        </h3>
        <h3> Attribute names : 
            <%                for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements();) {

                    out.println(e.nextElement());
                }


            %>
        </h3>
        <h3> Parameter values: 
            <%                for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {

                    out.println(request.getParameter(e.nextElement()));
                }


            %>
        </h3>
        <h3> Session attribute names : 
            <%                for (Enumeration<String> s = session.getAttributeNames(); s.hasMoreElements();) {

                    out.println(s.nextElement());
                }


            %>
        </h3>

        <h3> Session attributes :

            <%                for (Enumeration<String> s = session.getAttributeNames(); s.hasMoreElements();) {

                    out.println(session.getAttribute(s.nextElement()).toString());
                }


            %>
        </h3>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
