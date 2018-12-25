<%--
  Created by IntelliJ IDEA.
  User: Jan
  Date: 24. 12. 2018
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Songs">
<jsp:attribute name="body">
    <div class="container">

        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form method="GET" action="">
                    <div class="input-group">
                        <input id="q" name="q" type="text" class="form-control" placeholder="Search for songs..." value="<c:if test="${q != ''}">${q}</c:if>">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Search</button>
                        </span>
                    </div>

                    <form class="form-inline">
                        <fieldset>
                            <div class="form-group">
                                <label class="radio-inline">
                                    <input id="title" name="o" value="title" type="radio" checked>
                                    Title
                                </label>
                                <label class="radio-inline">
                                    <input id="artist" name="o" value="artist" type="radio" <c:if test="${o == 'artist'}">checked</c:if>>
                                    Artist
                                </label>
                                <label class="radio-inline">
                                    <input id="album" name="o" value="album" type="radio" <c:if test="${o == 'album'}">checked</c:if>>
                                    Album
                                </label>
                            </div>
                        </fieldset>
                    </form>
                </form>
            </div>
        </div>
        <br />

        <div class="row">
            <div class="col-md-offset-1 col-md-9">
                <c:choose>
                    <c:when test="${songs.size() != 0}">
                        <table class="table text-center">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Artist</th>
                                <th>Album</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${songs}" var="song">
                                    <tr>
                                        <td><c:out value="${song.title}"/></td>
                                        <td><c:out value="${song.artist.name}"/></td>
                                        <td><c:out value="${song.album.title}"/></td>
                                        <td>
                                            <button type="button" class="btn btn-info">
                                                <span class="glyphicon glyphicon-plus"></span> Playlist
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:when test="${songs.size() == 0}">
                        <h3 class="text-center">No results found</h3>
                    </c:when>

                </c:choose>
            </div>
        </div>

    </div>
</jsp:attribute>
</my:pagetemplate>

