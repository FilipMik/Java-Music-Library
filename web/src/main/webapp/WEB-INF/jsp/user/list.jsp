<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 23/12/2018
  Time: 16:52
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Users">
<jsp:attribute name="body">
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th>E-mail</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.userLevel}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td>
                            <div style="float: right;">
                                 <my:a href="/user/profile/${user.userId}"
                                       class="btn btn-info">Show</my:a>
                            </div>
                            <c:if test="${isAdmin}">
                                <div style="float: right; padding-right: 5px">
                                     <my:a href="/user/delete/${user.userId}"
                                           class="btn btn-danger">Delete</my:a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</jsp:attribute>
</my:pagetemplate>
