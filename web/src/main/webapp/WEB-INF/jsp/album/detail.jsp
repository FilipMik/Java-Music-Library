<%--
  Created by IntelliJ IDEA.
  User: peter žiška
  Date: 28/12/2018
  Time: 22:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="${album.title}">
<jsp:attribute name="body" >
    <div>
        <h3>Album Detail</h3>
    </div>
    <div class="container">
        <table class="table table-striped">
            <tbody>
            <tr>
                <th>ID</th>
                <td><c:out value="${album.albumId}"/></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><c:out value="${album.title}"/></td>
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
    <c:if test="${album.songList.size() > 0}">
        <div>
            <h3>Songs</h3>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${album.songList}" var="song">
                            <tr>
                                <td><c:out value="${song.title}"/></td>
                                <td><c:out value="${song.commentary}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</jsp:attribute>
</my:pagetemplate>
