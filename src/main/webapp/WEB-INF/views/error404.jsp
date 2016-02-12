<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="partials/header.jsp"/>

<jsp:include page="partials/navbar-empty.jsp"/>

<div class="container">

    <div class="section">
        <h2 class="deep-orange-text">Error 404 <small class="grey-text">página no encontrada</small></h2>
    </div>

    <section class="main">
        <h3 class="grey-text">No encontramos lo que buscaba</h3>
        <a href="/" class="btn deep-orange waves-orange">Regresar a la página principal</a>
    </section>


    <div class="section">
    </div>

</div>

<jsp:include page="partials/footer.jsp"/>
