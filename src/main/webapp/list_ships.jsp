<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<style>
    #resultTable {
        border: darkgray 1px dashed;
        padding: 10px;
    }

    #resultTable td {
        padding: 10px ;

    }

    #resultTable td form {
        margin-bottom: 0;
    }
    th {
        text-align: left;
    }
    input[type=submit]{
        padding: 10px;
        border-radius: 10px;
    }

    input.submit {
        background-color: chartreuse;
        color: #000000;

    }

    input.delete {
        background-color: #ff9189;
        color: #FFFFFF;
    }

</style>

<table id="resultTable">
    <thead>
    <tr>
        <th>Name</th>
        <th>Designation</th>
        <th>Type</th>
        <th>Warp capabilities</th>
    </tr>
    </thead>
    <c:forEach items="${ship}" var="ship">
        <tr>
            <td><c:out value="${ship.name}"/></td>
            <td><c:out value="${ship.designation}"/></td>
            <td><c:out value="${ship.type}"/></td>
            <td><c:out value="${ship.warpCapabilities}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/ship/delete?id=${ship.id}"><input class="delete" type="submit" value="Delete"></form></td>
            <%--<td><form method="post" action="${pageContext.request.contextPath}/crewman/edit?id=${crewman.id}"><input--%>
                    <%--class="update" type="submit" value="Update">--%>
            <%--</form></td>--%>
        </tr>
    </c:forEach>
</table>

<h2>Input new ship</h2>
<c:if test="${not empty error}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/ship/add" method="post">
    <table>
        <tr>
            <th>Ship name:</th>
            <td><input type="text" name="name" value="<c:out value='${param.name}'/>"/></td>
        </tr>
        <tr>
            <th>Ship designation:</th>
            <td><input type="text" name="designation" value="<c:out value='${param.designation}'/>"/></td>
        </tr>
        <tr>
            <th>Ship type:</th>
            <td>
                <select name="type" id="" value="<c:out value='${param.type}'/>">
                    <option value="NEBULA">Nebula</option>
                    <option value="GALAXY">Galaxy</option>
                    <option value="WARSHIP">Warship</option>
                    <option value="SCIENCE">Science</option>
                    <option value="TRANSPORT">Transport</option>
                    <option value="COLONISATION">Colonisation</option>
                    <option value="SHUTTLE">Shuttle</option>
                </select>
                
                
                <%--<input type="text" name="rank" value="<c:out value='${param.author}'/>"/></td>--%>
        </tr>
        <tr>
            <th>Warp capabilities:</th>
            <td>
                <input type="text" name="warpCapabilities" value="<c:out value='${param.warpCapabilities}'/>"/></td>
        </tr>
    </table>
    <input class="submit" type="Submit" value="Submit" />
</form>

</body>
</html>