<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 28/12/2018
  Time: 22:02
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Album">
<jsp:attribute name="body">

    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Title</th>
                <th>Release Date</th>
                <th>Commentary</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${albums}" var="album">
                    <tr>
                        <td><c:out value="${album.title}"/></td>
                        <td><c:out value="${album.releaseDate}"/></td>
                        <td><c:out value="${album.commentary}"/></td>
                        <td>
                            <div style="float: right;">
                                 <my:a href="/album/detail/${album.albumId}"
                                       class="btn btn-info">Show</my:a>
                            </div>
                            <c:if test="${isAdmin}">
                                <div style="float: right; padding-right: 5px">
                                     <my:a href="/album/delete/${album.albumId}"
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
