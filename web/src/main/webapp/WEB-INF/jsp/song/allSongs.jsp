<%--
  Created by IntelliJ IDEA.
  User: Jan Ficko
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
                        <input id="q" name="q" type="text" class="form-control" placeholder="Search for songs..."
                               value="<c:if test="${q != ''}">${q}</c:if>">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Search</button>
                        </span>
                    </div>

                    <form class="form-inline">
                        <fieldset>
                            <div class="form-group">
                                <label class="radio-inline">
                                    <input name="o" id="title" value="title" type="radio" checked/>
                                    Title
                                </label>
                                <label class="radio-inline">
                                    <input name="o" id="artist" value="artist" type="radio"
                                           <c:if test="${o == 'artist'}">checked</c:if> />
                                    Artist
                                </label>
                                <label class="radio-inline">
                                    <input name="o" id="album" value="album" type="radio"
                                           <c:if test="${o == 'album'}">checked</c:if> />
                                    Album
                                </label>
                            </div>
                        </fieldset>
                    </form>
                </form>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-md-offset-1 col-md-10">
                <c:choose>
                    <c:when test="${songs.size() != 0}">
                        <table class="table">
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
                                        <td>
                                            <my:a href="${pageContext.request.contextPath}/artist/detail/${song.artist.artistId}">
                                                <c:out value="${song.artist.name}"/>
                                            </my:a>
                                        </td>
                                        <td>
                                            <my:a href="${pageContext.request.contextPath}/album/detail/${song.album.albumId}">
                                                <c:out value="${song.album.title}"/>
                                            </my:a>
                                        </td>
                                        <td>
                                            <c:if test="${user.playlists.size() != 0}">
                                                <button type="button" class="btn btn-info" id="playlist" name="playlist"
                                                        data-toggle="modal" data-target="#playlistModal"
                                                        data-id="${song.songId}">
                                                    <span class="glyphicon glyphicon-plus"></span> Playlist
                                                </button>
                                            </c:if>
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

    <div class="modal fade" id="playlistModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Choose playlist</h4>
                </div>

                <form method="GET" action="">
                    <div class="modal-body">

                        <c:forEach items="${user.playlists}" var="playlist">
                            <div class="radio">
                                <label><input type="radio" name="p" value="${playlist.playlistId}">${playlist.title}
                                </label>
                            </div>
                        </c:forEach>

                        <input type="hidden" name="q" id="q"/>
                        <input type="hidden" name="o" id="o"/>
                        <input type="hidden" name="s" id="s"/>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</jsp:attribute>
</my:pagetemplate>

<script type="text/javascript">

    $(document).on("click", ".btn-info", function () {
        if (document.getElementById("title").checked) {
            $(".modal-body #o").val("title");
        } else if (document.getElementById("artist").checked) {
            $(".modal-body #o").val("artist");
        } else if (document.getElementById("album").checked) {
            $(".modal-body #o").val("album");
        }
        $(".modal-body #q").val(document.getElementById("q").value);
        $(".modal-body #s").val($(this).data('id'));
    });

</script>
