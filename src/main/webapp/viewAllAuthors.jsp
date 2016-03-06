<%-- 
    Document   : viewAllAuthors
    Created on : Feb 7, 2016, 9:36:30 PM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <title>JSP Page</title>
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
                    
                    <form method="POST" action="AuthorController">        
                        <input type="submit" name="submit" value="RESET">
                        <input type="hidden" name="reset_mode" value="reset_mode">
                    </form>
                        
                        
                    <form method="POST" action="AuthorController">        
                        <input type="submit" name="mode" value="${mode}">
                        <input type="hidden" name="switch_mode" value="switch_mode">
                    </form>
                        
                        <br> 
                        
               
                        
                </div>
                        
            </div>
            <p></p>
            <p></p>
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
                                <%-- <td class="text-left">${item.authorName}</td> --%>

                                <td class="col-md-4 text-center">${item.authorName}</td>
                            
                            <td class="text-left">${item.dateAdded}</td>
                            <td class="col-md-2 text-center">
                                
                                <form method="POST" action="AuthorController"> 
                                <input type="submit" name="submit" value="update">
                                <input type="hidden" name="update_author_id" value="${item.authorId}">
                                <input type="hidden" name ="update" value ="update">                                
                                </form>
                            </td>
                            

                            <td class="col-md-2 text-center">

                                <form method="POST" action="AuthorController">        
                                    <input type="submit" name="submit" value="delete"> 
                                    <input type="hidden" name="delete" value="delete">
                                    <input type="hidden" name="delete_author_id" value="${item.authorId}">

                                </form>


                            </td>


                            </tr>
                        </c:forEach>
                        <form method="POST" action="AuthorController"> 
                            <tr>

                                <td class="col-md-1 text-center"></td>    
                                <td class="col-md-4 text-center"><input type="text" name="insert_value" value=""></td>
                                <td class="col-md-3 text-center"></td>
                                <td class="col-md-2 text-center">


                                    <input type="submit" name="submit" value="insert"> 
                                    <input type="hidden" name="insert" value="insert">
                                </td>

                            </tr>
                        </form>

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
