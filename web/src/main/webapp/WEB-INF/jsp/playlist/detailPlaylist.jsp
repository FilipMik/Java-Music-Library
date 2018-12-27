<%--
  Author: Klara Minsterova
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:pagetemplate title="${playlist.title}">
<jsp:attribute name="body">
    <div class="container">

        <div class="form-group">
            <p><strong>Created: </strong><c:out value="${playlist.dateCreated}"/>
            <p><strong>User: </strong><c:out value="${playlist.user.username}"/>
            <hr/>
        </div>

        <table class="table">
            <p><strong>Songs:</strong></p>
            <thead>
            <tr>
                <th>Title</th>
                <th>Artist</th>
                <th>Album</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${playlist.songList}" var="song">
                    <tr>
                        <td><c:out value="${song.title}"/></td>
                        <td><c:out value="${song.artist.name}"/></td>
                        <td><c:out value="${song.album.title}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</jsp:attribute>
</my:pagetemplate>
