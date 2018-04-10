<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Rank</th>
    </tr>
    </thead>
    <c:forEach items="${crewman}" var="crewman">
        <tr>
            <td><c:out value="${crewman.name}"/></td>
            <td><c:out value="${crewman.rank}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/crewman/delete?id=${crewman.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Delete"></form></td>
        </tr>
    </c:forEach>
</table>

<h2>Zadejte knihu</h2>
<c:if test="${not empty error}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/crewman/add" method="post">
    <table>
        <tr>
            <th>Crewman name:</th>
            <td><input type="text" name="name" value="<c:out value='${param.name}'/>"/></td>
        </tr>
        <tr>
            <th>Crewman rank:</th>
            <td><input type="text" name="rank" value="<c:out value='${param.author}'/>"/></td>
        </tr>
    </table>
    <input type="Submit" value="Submit" />
</form>

</body>
</html>%