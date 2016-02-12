<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="partials/header.jsp"/>

<jsp:include page="partials/navbar-perfil.jsp"/>

<div class="container">
    <section class="main">
        <ul class="collection with-header">
            <li class="collection-header">
                <h4 class="teal-text">Subir Archivo - <span><small class="grey-text darken-2">Estructura actual de plantilla</small></span></h4>
            </li>
            <li class="collection-item">
                <div class="row">
                    <div class="col s2"><strong>Orden</strong></div>
                    <div class="col s4"><strong>Nombre</strong></div>
                    <div class="col s3"><strong>Tipo</strong></div>
                    <div class="col s2"><strong>Longitud Actual</strong></div>
                </div>

            </li>
            <c:forEach var="field" items="${fields}">
                <li class="collection-item field-item-normal">
                    <div class="row">
                        <div class="col s2">${field.orden}</div>
                        <div class="col s4" title="${field.descripcion}">${field.nombre}</div>
                        <div class="col s3">${field.tipo}</div>
                        <div class="col s3">${field.longitud}</div>
                    </div>
                </li>
            </c:forEach>
        </ul>

        <div ng-controller="fileUploadController">
            <form name="myForm">
                <div class="row">
                    <div class="col s12 m9 l10">
                        <div class="file-field input-field">
                            <div class="btn">
                                <span>Seleccionar</span>
                                <input type="file"  ngf-select ng-model="picFile" accept="text/plain" required>
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text" autocomplete="off" placeholder="Selecciona un archivo">
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m3 l2">
                        <button ng-disabled="!myForm.$valid" ng-click="uploadPic(picFile)" class="btn deep-orange waves-effect waves-orange upload-button">
                            Subir <i class="material-icons right mdi-file-cloud-upload"></i>
                        </button>
                    </div>
                    <div class="col s12">
                        <div class="progress" ng-show="picFile.progress > 0">
                            <div class="determinate" style="width: {{picFile.progress}}%" ng-bind="picFile.progress + '%'"></div>
                        </div>
                    </div>
                </div>

                <%--<input type="file" ngf-select ng-model="picFile" name="file"
                       accept="text/plain" ngf-max-size="2MB" required>

                <i ng-show="myForm.file.$error.required">*required</i><br>
                <i ng-show="myForm.file.$error.maxSize">File too large
                    {{picFile.size / 1000000|number:1}}MB: max 2M</i>

                <button ng-disabled="!myForm.$valid" ng-click="uploadPic(picFile)">Subir</button>

                <span class="progress" ng-show="picFile.progress >= 0">
                    <div style="width:{{picFile.progress}}%" ng-bind="picFile.progress + '%'"></div>
                </span>

                <span ng-show="picFile.result">Upload Successful</span>
                <span class="err" ng-show="errorMsg">{{errorMsg}}</span>--%>
            </form>

            <div class="section">
                <!-- Modal Structure -->
                <div id="modal-upload-result" class="modal modal-fixed-footer">
                    <div class="modal-content">
                        <h4 class="blue-grey-text darken-3">Subida de Archivo</h4>
                        <div class="row">
                            <div class="col s6">
                                <div class="badge">{{data.errorDesc}}</div>
                            </div>
                            <div class="col s6">
                                <div class="badge">{{data.data.errorDesc}}</div>
                            </div>
                        </div>

                        <table class="bordered striped">
                            <thead>
                            <tr>
                                <th data-field="">Nº</th>
                                <th data-field="">Fecha</th>
                                <th data-field="">Hora</th>
                                <th data-field="">Tarjeta</th>
                                <th data-field="">Monto</th>
                                <th data-field="">Canal</th>
                                <th data-field="">Adquiriente</th>
                                <th data-field="">Autorizador</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="rowdata in data.data.data" title="{{rowdata.data.raw}}"
                                class="trow" ng-class="{'tr-error':rowdata.errorCod!=0, 'tr-success':rowdata.errorCod==0}">
                                <td>{{$index+1}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.fecha}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.hora}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.tarjeta}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.monto}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.canal}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.adquiriente}}</td>
                                <td ng-hide="rowdata.errorCod!=0">{{rowdata.data.autorizador}}</td>
                                <td ng-show="rowdata.errorCod!=0" colspan="7">{{rowdata.errorDesc}}</td>

                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <a href="#!" class=" modal-action modal-close waves-effect waves-orange btn deep-orange">Cerrar</a>
                    </div>
                </div>
            </div>

        </div>
    </section>


</div>

<jsp:include page="partials/footer.jsp"/>
