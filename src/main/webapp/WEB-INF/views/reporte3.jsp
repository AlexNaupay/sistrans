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

    <h3 class="teal-text">Reporte Detallado</h3>

    <section>
        <div ng-controller="ReportDetController">

            <form >

                <materialize-date-range></materialize-date-range>

                <div class="row">
                    <div class="input-field col s12 m9">
                        <select-autorizador></select-autorizador>
                    </div>
                    <div class="input-field col s12 m9">
                        <select-adquiriente></select-adquiriente>
                    </div>
                    <div class="input-field col s12 m9">
                        <select-canal></select-canal>
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
                <div id="grid1" ui-grid="gridOptions" class="grid"></div>
            </form>
        </div>

    </section>


</div>
<jsp:include page="partials/footer.jsp"/>