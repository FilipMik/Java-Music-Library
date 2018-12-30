<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Artists">
<jsp:attribute name="body">
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th class="col-md-3">Name</th>
                <th class="col-md-3">Date of birth/formation</th>
                <th class="col-md-4">Info</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${artists}" var="artist">
                <tr>
                    <td class="col-md-3"><c:out value="${artist.name}"/></td>
                    <td class="col-md-3"><fmt:formatDate value="${artist.birthDate}" pattern="dd-MM-yyyy"/></td>
                    <td class="col-md-4"><c:out value="${artist.artistInfo}"/></td>
                    <td class="button">
                        <div style="float: right;">
                             <my:a href="${pageContext.request.contextPath}/artist/detail/${artist.artistId}"
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
