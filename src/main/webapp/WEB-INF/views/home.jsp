<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 29/06/2015
  Time: 01:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="partials/header.jsp"/>
<jsp:include page="partials/navbar-perfil.jsp"/>
<jsp:include page="partials/modal-form-template.jsp"/>
<div class="container">

    <h3 class="teal-text">Opciones del Sistema</h3>

    <section>
        <div class="row">
            <c:forEach var="option" items="${options}">
                <div class="col s12 m6">
                    <div class="card hoverable blue-grey darken-2">
                        <div style="display: inline; width: 25px; height: 25px; background: red; border-radius: 50%;"></div>
                        <div class="card-content white-text">
                            <span class="card-title">${option.nombre}</span>

                            <p>${option.descripcion}</p>
                        </div>
                        <div class="card-action">
                            <a class="" href="${option.uri}">${option.nombre}</a>
                        </div>
                    </div>
                </div>

                <%--<div class="col s12 m6 l4">
                    <div class="card small">
                        <div class="card-image">
                            <img src="http://materializecss.com/images/sample-1.jpg">
                            <span class="card-title">${option.nombre}</span>
                        </div>
                        <div class="card-content">
                            <p>${option.descripcion}</p>
                        </div>
                        <div class="card-action deep-orange white-text">
                            <a href="${option.uri}">${option.nombre}</a>
                        </div>
                    </div>
                </div>--%>
            </c:forEach>
        </div>
    </section>


</div>
<jsp:include page="partials/footer.jsp"/>