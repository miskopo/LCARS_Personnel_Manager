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
        <th>Rank</th>
    </tr>
    </thead>
    <c:forEach items="${crewman}" var="crewman">
        <tr>
            <td><c:out value="${crewman.name}"/></td>
            <td><c:out value="${crewman.rank}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/crewman/delete?id=${crewman.id}"><input class="delete" type="submit" value="Delete"></form></td>
            <td><form method="post" action="${pageContext.request.contextPath}/crewman/edit?id=${crewman.id}"><input
                    class="update" type="submit" value="Update"></form></td>
        </tr>
    </c:forEach>
</table>

<h2>Input new crewman</h2>
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
            <td>
                <select name="rank" id="" value="<c:out value='${param.author}'/>">
                    <option value="CAPTAIN">Captain</option>
                    <option value="COMMANDER">Commander</option>
                    <option value="LIEUTENANT">Lieutenant</option>
                    <option value="ENSIGN">Ensign</option>
                </select>
                
                
                <%--<input type="text" name="rank" value="<c:out value='${param.author}'/>"/></td>--%>
        </tr>
    </table>
    <input class="submit" type="Submit" value="Submit" />
</form>

</body>
</html>