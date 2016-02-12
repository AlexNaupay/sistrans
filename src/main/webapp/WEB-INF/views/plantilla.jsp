<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 29/06/2015
  Time: 01:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="partials/header.jsp"/>
<jsp:include page="partials/navbar-perfil.jsp"/>
<jsp:include page="partials/modal-form-template.jsp"/>
<div class="container">

  <h3 class="teal-text">Modificar Plantilla</h3>
  <ul class="collection" style="margin-bottom: 0; padding-bottom: 0;">
    <li class="collection-item field-item">
      <div class="row">
        <div class="col s1">Orden</div>
        <div class="col s3">Nombre</div>
        <div class="col s3">Tipo de campo</div>
        <div class="col s2">Longitud Actual</div>
        <div class="col s3">
          Acciones
        </div>
      </div>

    </li>
  </ul>

    <div class="main" ng-controller="FieldController">
      <ul class="collection" ui-sortable="sortableOptions" ng-model="campos">
        <li class="collection-item field-item" ng-repeat="campo in campos">
          <div class="row">
            <div class="col s1">{{campo.orden}}</div>
            <div class="col s3">{{campo.nombre}}</div>
            <div class="col s3">{{campo.tipo}}</div>
            <div class="col s2">{{campo.longitud}}</div>
            <div class="col s3">
              <button class="waves-effect waves-orange btn"
                 ng-click="editar(campo.codigo)"  ng-disabled="!campo.editable">
                Editar Tamaño
              </button>
            </div>
          </div>

        </li>
      </ul>

      <div>
          <div>{{updateResult}}</div>
          <div class="chip red lighten-2 white-text" ng-repeat="error in errors">
              Campo {{error.data.nombre}} : {{error.errorDesc}}
              <i class="material-icons mdi-navigation-close"></i>
          </div>
      </div>

      <div>
          <button class="btn orangered right" ng-disabled="!cambios" ng-click="guardar()">Guardar Cambios</button>
      </div>


      <!-- Modal Structure -->
      <div id="modal-edit" class="modal">
        <div class="container">
          <div class="modal-content">
            <h4>Editar campo {{nombreedit}}</h4>
            <p>Tamaño de campo: </p>

            <div class="input-field col s12">
              <input id="longitud" type="number" required class="validate" ng-model="tamanoedit"/>
              <!--<label for="longitud">Longitud de campo</label>-->
            </div>

          </div>
          <div class="modal-footer">
            <!--<a class="modal-action modal-close btn deep-orange waves-effect waves-green"
               href="#!" >Guardar</a>-->
            <button class="btn deep-orange" ng-disabled="tamanoedit==''" ng-click="cambiar()">Cambiar tamaño</button>
          </div>
        </div>
      </div>
      <!--</div>-->
</div>

</div>

<jsp:include page="partials/footer.jsp"/>