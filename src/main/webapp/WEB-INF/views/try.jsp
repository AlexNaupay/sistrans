<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="partials/header.jsp"/>

<jsp:include page="partials/navbar-empty.jsp"/>

<div class="container">

    <div class="section">
        <h2 class="teal-text">Try</h2>
    </div>

    <section class="main">
        ${varon} and ${mujer}
        ${otracosa}
    </section>


    <div class="section">
    </div>

</div>

<jsp:include page="partials/footer.jsp"/>
