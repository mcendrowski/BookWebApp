<%-- 
    Document   : viewAuthorIds
    Created on : Apr 13, 2016, 4:19:46 PM
    Author     : MCENDROWSKI
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>Books Page</title>
    </head>
    <body style="color:${color};">


        <%--
            <jsp:useBean id="user" class="user.UserData" scope="session"/>
<jsp:setProperty name="user" property="*"/>
        --%>
        <p></p>
        <p></p>
        <p></p>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-4">

                    <p>${user}</p>
                    <form method="POST" action="<%= response.encodeURL("ReportController")%>">        
                        <input type="submit" name="submit" value="RESET">
                        <input type="hidden" name="reset_mode" value="reset_mode">
                    </form>                     




                </div>

            </div>
            <p></p>
            <p></p>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <table class="table table-bordered table-striped table-hover">
                        <tr>
                            <th class="col-md-1 text-center">Author Id</th>    


                        </tr>

                        <c:forEach var="item" items="${bookList}">

                            <tr>
                                <td class="text-right">${item.bookId}</td>
                                <%-- <td class="text-left">${item.authorName}</td> --%>

                                <td class="col-md-2 text-center">

                                </td>


                            </tr>
                        </c:forEach>


                    </table>
                </div>
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
        </h3>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
