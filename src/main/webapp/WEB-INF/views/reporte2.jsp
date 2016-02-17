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

    <h3 class="teal-text">Reporte de Transacciones por Autorizador</h3>

    <section>
        <div ng-controller="ReportSecondController">

            <form >

                <materialize-date-range date-model="dateRange"></materialize-date-range>

                <div class="row">
                    <div class="input-field col s12 m9">
                        <select-and-filter data="selectAutorizador"></select-and-filter>
                    </div>
                    <div class="col s12 m3">
                        <div class="right">
                            <button class="btn deep-orange waves-effect waves-orange" ng-click="enviar()">Ver Reporte</button>
                        </div>
                    </div>
                </div>

                    <!--<button id="footerButton" class="btn btn-success" ng-click="toggleFooter()">Toggle Grid Footer</button>
                    <button class="btn btn-success" ng-click="toggleColumnFooter()">Toggle Column Footer</button>-->
                <div><strong class="light-blue-text">{{message_front}}</strong></div>
            </form>
            <div id="grid1" ui-grid="gridOptions" class="grid"></div>
        </div>

    </section>


</div>
<jsp:include page="partials/footer.jsp"/>