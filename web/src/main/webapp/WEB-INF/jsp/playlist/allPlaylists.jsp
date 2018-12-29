<%--
  Author: Klara Minsterova
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:pagetemplate title="Playlists">
<jsp:attribute name="body">
    <div class="container">

        <div class="form-group">
            <my:a href="/playlist/new" class="btn btn-primary">Add new playlist</my:a>
            <hr/>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th class="col-md-3">Title</th>
                    <th class="col-md-3">Created</th>
                    <th class="col-md-3">User</th>
                    <th class="col-md-3"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${playlists}" var="playlist">
                    <tr>
                        <td class="col-md-3"><c:out value="${playlist.title}"/></td>
                        <td class="col-md-3"><c:out value="${playlist.dateCreated}"/></td>
                        <td class="col-md-3"><c:out value="${playlist.user.username}"/></td>
                        <td class="button">
                            <form method="get"
                                  action="/pa165/playlist/detail/${playlist.playlistId}" >
                                <input class="btn btn-info" type="submit" value="Show" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</jsp:attribute>
</my:pagetemplate>
