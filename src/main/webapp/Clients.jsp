<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 20.08.2016
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<body>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Roles</th>
            <th>Registered</th>
            <th>Birthday</th>
            <th>Act</th>
        </tr>
        </thead>
        <c:forEach items="${clientList}" var="client">
            <tr>
                <td><c:out value="${client.name}"/></td>
                <td><c:out value="${client.surname}"/></td>
                <td><a href="mailto:${client.email}">${client.email}</a></td>
                <td>${client.roles}</td>
                <td><fmt:formatDate value="${client.registered}" pattern="dd-MMMM-yyyy"/></td>
                <td><fmt:formatDate value="${client.birth}" pattern="dd-MMMM-yyyy"/></td>
                <td><a href="clients?action=delete&id=${client.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
