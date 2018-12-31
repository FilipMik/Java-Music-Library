<%--
  Created by IntelliJ IDEA.
  User: peter žiška
  Date: 23/12/2018
  Time: 11:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="${user.username}">
<jsp:attribute name="body" >
    <div>
        <h3>Profile Information</h3>
    </div>
    <div class="container">
        <table class="table table-striped">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td><c:out value="${user.userId}"/></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><c:out value="${user.username}"/></td>
                </tr>
                <tr>
                    <th>User Level</th>
                    <td><c:out value="${user.userLevel}"/></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </tbody>
        </table>
    </div>
    <br>
    <c:if test="${playlists.size() > 0}">
        <div>
            <h3>Playlists</h3>
        </div>
        <div class="container">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${playlists}" var="playlist">
                    <tr>
                        <td><c:out value="${playlist.playlistId}"/></td>
                        <td><c:out value="${playlist.title}"/></td>
                        <td>
                            <div style="float:right;">
                                <my:a href="/playlist/detail/${playlist.playlistId}"
                                      class="btn btn-info">Show</my:a>
                            </div>
                            <c:if test="${authUser.userId == user.userId}">
                                <div style="float:right; padding-right: 5px">
                                <my:a href="/user/profile/${user.userId}/delete/playlist/${playlist.playlistId}"
                                      class="btn btn-danger">Delete</my:a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</jsp:attribute>
</my:pagetemplate>
