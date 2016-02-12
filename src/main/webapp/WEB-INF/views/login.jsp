<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="partials/header.jsp"/>

<jsp:include page="partials/navbar-empty.jsp"/>

<div class="container">

  <div class="section">

  </div>

  <div id="login-page" class="row">
    <div class="col s12 m8 offset-m2  l6 offset-l3 z-depth-3 card-panel ">
      <form class="login-form" id="login-form" ng-submit="login()" ng-controller="LoginController">
        <div class="row">
          <div class="input-field col s12 center">
            <img src="<c:url value="/public/image/first-data.jpg"/>" alt="" class="responsive-img valign profile-image-login">
            <%--<p class="center login-form-text">Sistema de Transacciones </p>--%>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input ng-model="username" ng-change="inputChangue()" id="username" type="text">
            <label for="username" class="center-align">usuario</label>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-action-lock-outline prefix"></i>
            <input ng-model="password" ng-change="inputChangue()" id="password" type="password">
            <label for="password" class="">clave</label>
            <div ng-show="is_show_error" class="red-text text-darken-3 center-align center-block">
              <i class="mdi-alert-error"></i> &nbsp; {{error_message}}
            </div>
          </div>

        </div>
        <div class="row">
          <div class="input-field col s12">
            <button ng-disabled="!submit_ready" type="submit" class="btn col s12 waves-effect deep-orange"
                    value="Iniciar Sesión">Iniciar Sesión</button>
          </div>
        </div>

        <!--<span>{{login.submit_ready}}</span>-->

        <%--<div class="row hide">
          <div class="input-field col s6 m6 l6">
            <p class="margin medium-small"><a href="page-register.html">Register Now!</a></p>
          </div>
          <div class="input-field col s6 m6 l6">
            <p class="margin right-align medium-small"><a href="page-forgot-password.html">Forgot password ?</a></p>
          </div>
        </div>--%>

      </form>
    </div>
  </div>

  <div class="section">

  </div>


</div>

<jsp:include page="partials/footer.jsp"/>
