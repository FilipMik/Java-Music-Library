<%--
  Author: Klara Minsterova
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:pagetemplate title="${artist.name}">
<jsp:attribute name="body">
    <div class="container">

        <div class="form-group">
            <p><strong>Date of birth/formation: </strong><fmt:formatDate value="${artist.birthDate}" pattern="dd-MM-yyyy"/>
            <p><strong>Info: </strong><c:out value="${artist.artistInfo}"/>
            <hr/>
        </div>

        <table class="table">
            <p><strong>Albums:</strong></p>
            <thead>
            <tr>
                <th class="col-md-4">Title</th>
                <th class="col-md-4">Released</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${albums}" var="album">
                <tr>
                    <td class="col-md-4"><c:out value="${album.title}"/></td>
                    <td class="col-md-4"><fmt:formatDate value="${album.releaseDate}" pattern="dd-MM-yyyy"/></td>
                    <td class="button">
                        <div style="float: right;">
                             <my:a href="/album/detail/${album.albumId}"
                                   class="btn btn-info">Show</my:a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</jsp:attribute>
</my:pagetemplate>
