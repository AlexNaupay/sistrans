<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="blue-grey darken-3" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="/" class="brand-logo center">SISTRANS</a>
        <ul class="right hide-on-med-and-down">
            <%--<li><a href="#">Navbar Link</a></li>--%>
        </ul>

        <ul class="left hide-on-med-and-down">
            <%--<c:forEach var="option" items="${options}">
                <li class="&lt;%&ndash;active&ndash;%&gt;"><a href="${option.uri}">${option.nombre}</a></li>
            </c:forEach>--%>

            <%--<li class="active"><a href="sass.html">Gestionar Plantillas</a></li>
            <li><a href="badges.html">Procesar Archivos</a></li>--%>
        </ul>

        <ul class="right hide-on-med-and-down">
            <li><a href="#">${user.nombre}</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>


        <ul id="nav-mobile" class="side-nav">
            <c:forEach var="option" items="${options}">
                <li class="<%--active--%>"><a href="${option.uri}">${option.nombre}</a></li>
            </c:forEach>
            <li><a href="/logout">Logout</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse">
            <i class="mdi-action-view-list"></i></a>
    </div>
</nav>

