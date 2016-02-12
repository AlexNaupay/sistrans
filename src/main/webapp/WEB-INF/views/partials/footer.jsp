<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer class="page-footer blue-grey darken-3">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">SISTRANS</h5>

                <blockquote>
                    <p class="grey-text text-lighten-4">
                        Configuración de plantilla, Carga de datos y reportes
                    </p>
                </blockquote>



            </div>
            <div class="col l3 s12">
                <p style="opacity: 0;">Algo oculto</p>

                <%--<h5 class="white-text">Developers</h5>
                <ul>
                    <li><a class="white-text" href="#!">@alexh</a></li>
                    <li><a class="white-text" href="#!">@josef</a></li>
                    <li><a class="white-text" href="#!">@ruddy</a></li>
                </ul>--%>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Developers</h5>
                <ul>
                    <li><a class="white-text" href="#!">@alexh</a></li>
                    <li><a class="white-text" href="#!">@josef</a></li>
                    <li><a class="white-text" href="#!">@ruddy</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Powered by <a class="orange-text text-lighten-3" href="http://developers.com">Ruddy Vertiz</a>
        </div>
    </div>
</footer>

<%-- jQuery --%>
<script src="<c:url value='/public/bower_components/jquery/dist/jquery.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/underscore/underscore-min.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/moment/moment.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/Materialize/dist/js/materialize.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/jquery-ui/jquery-ui.min.js' />" type="text/javascript"></script>

<script src="<c:url value='/public/js/sisproa-init.js' />" type="text/javascript"></script>


<%-- Angular --%>
<script src="<c:url value='/public/bower_components/angular/angular.min.js' />" type="text/javascript"></script>
<%--<script src="<c:url value='/public/bower_components/angular-ui-router/release/angular-ui-router.min.js' />" type="text/javascript"></script>--%>
<script src="<c:url value='/public/js/vendor/angular-materialize.js' />" type="text/javascript"></script>

<script src="<c:url value='/public/bower_components/ng-file-upload/ng-file-upload.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/angular-ui-sortable/sortable.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/bower_components/angular-ui-grid/ui-grid.min.js' />" type="text/javascript"></script>

<%--<script src="<c:url value='/public/bower_components/angular-route/angular-route.min.js' />" type="text/javascript"></script>--%>
<script src="<c:url value='/public/js/sistrans.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/js/services.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/js/directives.js' />" type="text/javascript"></script>
<script src="<c:url value='/public/js/controllers.js' />" type="text/javascript"></script>


</body>
</html>
