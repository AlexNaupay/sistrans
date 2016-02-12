
(function(){
    var app = angular.module('sistrans', ['ngFileUpload','ui.sortable','sistrans.services','ui.materialize','ui.grid']);

    app.controller("LoginController",['$scope','$http','$location',function($scope,$http,$location){
        $scope.username = "";
        $scope.password = "";
        $scope.submit_ready = false;
        $scope.is_show_error = false;
        $scope.error_message = "";

        $scope.inputChangue = function(){
            if($scope.username === "" || $scope.password == ""){
                $scope.submit_ready = false;
            }else{
                $scope.submit_ready = true;
            }
            $scope.is_show_error = false;

        };

        $scope.login = function(){
            var req = {
                method: 'POST',
                url: 'api/login',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: { username: $scope.username,
                    password: $scope.password
                }
            };


            $http(req).
                then(function(data,status){
                    if(data.data.errorCod == 0){ //Inicio de sesión correcta
                        window.location.href = "/";
                    }else{
                        $scope.is_show_error = true;
                        $scope.error_message = data.data.errorDesc;

                    }

                    //$location.path('/api/login'); //Only Angular working in frontend page

                }, function(data, status){
                    $scope.is_show_error = true;
                    $scope.error_message = "Error de petición";

                });
        }
    }]);

    app.controller('fileUploadController',  ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {
        $scope.data = {}; //Recibe los datos de subida

        $scope.uploadPic = function(file) {
            file.upload = Upload.upload({
                url: '/upload',
                data: {file: file}
            });

            file.upload.then(function (response) {
                console.info("Ok",response);
                $scope.data = response.data;
                $timeout(function () {
                    file.progress = 0;
                    //file.result = response.data;
                    $("#modal-upload-result").openModal();
                });
            }, function (response) {
                console.info("Mal",response);
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function (evt) {
                console.info("Process");
                // Math.min is to fix IE which reports 200% sometimes
                file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            });
        }
    }]);

    app.controller('FieldController', ['$scope','plantillaService',function ($scope,plantillaService) {
        $scope.campos = [];
        $scope.errors = [];
        $scope.updateResult = "";
        $scope.cambios = false;
        $scope.editItem = {};
        $scope.tamanoedit = 0;
        $scope.nombreedit = '';

        plantillaService.getCampos().then(function(data){
             $scope.campos = data.data;
        });


        $scope.editar = function(id){
            $scope.editItem = _.findWhere($scope.campos,{codigo:id});
            $scope.tamanoedit = $scope.editItem.longitud;
            $scope.nombreedit = $scope.editItem.nombre;
            $('#modal-edit').openModal();
        };

        $scope.cambiar = function(){
            $scope.editItem.longitud = $scope.tamanoedit;
            $scope.editItem.nombre = $scope.nombreedit;
            $scope.cambios = true;
            console.info("Click en cambiar");
            $('#modal-edit').closeModal();
        };

        $scope.guardar=function(){
            plantillaService.updateCampos($scope.campos)
                .then(function(data){
                    var list = [];
                    if(data.errorCod == 0){
                        data.data.forEach(function(dto, index){
                            list.push(dto.data);
                        });
                        $scope.campos = list;
                        $scope.updateResult = "Cambio exitoso";
                        $scope.errors = [];
                    }else{
                        $scope.updateResult = "Actualización incorrecta";
                        $scope.errors = _.filter(data.data,function(obj){
                            return obj.errorCod != 0;
                        });
                    }

                    console.log(data);
                    $scope.cambios = false;
                }, function(data){
                    console.log("error",data)
                });
        };

        $scope.sortableOptions = {
            stop: function(){
                $scope.campos.forEach(function(campo,index){
                    campo.orden = index+1;
                });
                $scope.cambios = true;
                $scope.updateResult = "";
            }
        };
    }]);




















    app.controller('ReportFirstController', ['$scope', 'uiGridConstants', '$http', 'i18nService','reporteService',
        function ($scope, uiGridConstants, $http, i18nService,reporteService) {
            i18nService.setCurrentLang('es');
            var data = [];

            $scope.gridOptions = {
                showGridFooter: true,
                showColumnFooter: true,
                enableFiltering: true,
                columnDefs: [
                    {
                        field: 'fecha', width: '15%',
                        cellFilter: 'date',
                        footerCellFilter: 'date'
                    },
                    {
                        field: 'adquiriente', width: '25%'
                    },
                    {
                        field: 'canal', width: '15%'
                    },
                    {
                        name:"Número de Transacciones",
                        field: 'numeroTx', width: '20%',
                        aggregationType: uiGridConstants.aggregationTypes.sum
                    },
                    {
                        name:"Monto Total",
                        field: 'monto', width: '25%',
                        aggregationType: uiGridConstants.aggregationTypes.sum
                    }

                ],
                data: data,
                onRegisterApi: function (gridApi) {
                    $scope.gridApi = gridApi;
                }
            };

            $scope.toggleFooter = function () {
                $scope.gridOptions.showGridFooter = !$scope.gridOptions.showGridFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };

            $scope.toggleColumnFooter = function () {
                $scope.gridOptions.showColumnFooter = !$scope.gridOptions.showColumnFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };



            $scope.enviar = function () {
                var adq_filters = _.map($scope.itemsAdq, function (item) {
                    return item.codigo
                });
                var canal_filters = _.map($scope.itemsCanal, function (item) {
                    return item.codigo
                });

                var final = moment($scope.selectTimeNext,'DD/MM/YYYY');
                final = final.isValid()?final:moment($scope.selectTimeNext);
                var inicio = moment($scope.selectTimePrev,'DD/MM/YYYY');
                inicio = inicio.isValid()?inicio:moment($scope.selectTimePrev);

                var params = {
                    date_prev : inicio.format("YYYYMMDD"),
                    date_next : final.format("YYYYMMDD"),
                    adq_filters:adq_filters.toString(),
                    canal_filters : canal_filters.toString()
                     //adq_filters :
                };

                reporteService.getReporte1(params).then(function(data){

                    if(data.errorCod == 0){
                        data.data.forEach(function (row){
                            row.fecha = moment(row.fecha,"YYYYMMDD").format("DD/MM/YYYY");
                        });
                        $scope.gridOptions.data = data.data;
                        if(data.data.length == 0){
                            $scope.message_front = "No hay datos para este reporte";
                        }
                    }else{
                        $scope.message_front = data.errorDesc;
                    }

                });

            };


        }]);

    app.controller('ReportSecondController', ['$scope', 'uiGridConstants', '$http', 'i18nService','reporteService',
        function ($scope, uiGridConstants, $http, i18nService,reporteService) {
            i18nService.setCurrentLang('es');
            var data = [];

            $scope.gridOptions = {
                showGridFooter: true,
                showColumnFooter: true,
                enableFiltering: true,
                columnDefs: [
                    {
                        field: 'fecha', width: '20%',
                        cellFilter: 'date',
                        footerCellFilter: 'date'
                    },
                    {
                        field: 'autorizador', width: '30%'},
                    {
                        name:"Número de Transacciones",
                        field: 'numeroTx', width: '25%',
                        aggregationType: uiGridConstants.aggregationTypes.sum
                    },
                    {
                        name:"Monto Total",
                        field: 'monto', width: '25%',
                        aggregationType: uiGridConstants.aggregationTypes.sum
                    }
                    /*{field: 'autorizador', aggregationType: uiGridConstants.aggregationTypes.sum, width: '13%'},
                     {
                     field: 'numeroTx',
                     aggregationType: uiGridConstants.aggregationTypes.avg,
                     aggregationHideLabel: true,
                     width: '13%'
                     },
                     {
                     name: 'Monto',
                     field: 'monto',
                     aggregationType: uiGridConstants.aggregationTypes.min,
                     width: '13%',
                     displayName: 'Age for min'
                     },
                     {
                     name: 'ageMax',
                     field: 'age',
                     aggregationType: uiGridConstants.aggregationTypes.max,
                     width: '13%',
                     displayName: 'Age for max'
                     },
                     {
                     name: 'customCellTemplate',
                     field: 'age',
                     width: '14%',
                     footerCellTemplate: '<div class="ui-grid-cell-contents" style="background-color: dodgerblue;color: White">Totalizado</div>'
                     },
                     {
                     name: 'registered',
                     field: 'registered',
                     width: '20%',
                     cellFilter: 'date',
                     footerCellFilter: 'date',
                     aggregationType: uiGridConstants.aggregationTypes.max
                     }*/
                ],
                data: data,
                onRegisterApi: function (gridApi) {
                    $scope.gridApi = gridApi;
                }
            };

            $scope.toggleFooter = function () {
                $scope.gridOptions.showGridFooter = !$scope.gridOptions.showGridFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };

            $scope.toggleColumnFooter = function () {
                $scope.gridOptions.showColumnFooter = !$scope.gridOptions.showColumnFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };



            $scope.enviar = function () {
                var aut_filters = _.map($scope.items, function (item) {
                    return item.codigo
                });

                var final = moment($scope.selectTimeNext,'DD/MM/YYYY');
                final = final.isValid()?final:moment($scope.selectTimeNext);
                var inicio = moment($scope.selectTimePrev,'DD/MM/YYYY');
                inicio = inicio.isValid()?inicio:moment($scope.selectTimePrev);

                var params = {
                    date_prev : inicio.format("YYYYMMDD"),
                    date_next : final.format("YYYYMMDD"),
                    /*adq_filters:'',
                     canalFilters:'',*/
                    aut_filters : aut_filters.toString()
                };

                reporteService.getReporte2(params).then(function(data){
                    if(data.errorCod == 0){
                        data.data.forEach(function (row){
                            row.fecha = moment(row.fecha,"YYYYMMDD").format("DD/MM/YYYY");
                        });
                        $scope.gridOptions.data = data.data;
                        if(data.data.length == 0){
                            $scope.message_front = "No hay datos para este reporte";
                        }
                    }else{
                        $scope.message_front = data.errorDesc;
                    }

                });


                /*$http.get('/public/data/500_complex.json')
                 .success(function (data) {
                 data.forEach(function (row) {
                 row.registered = Date.parse(row.registered);
                 });
                 $scope.gridOptions.data = data;
                 });*/
            };


        }]);


    app.controller('ReportDetController', ['$scope', 'uiGridConstants', '$http', 'i18nService','reporteService',
        function ($scope, uiGridConstants, $http, i18nService,reporteService) {
            i18nService.setCurrentLang('es');
            var data = [];

            $scope.gridOptions = {
                showGridFooter: true,
                showColumnFooter: true,
                enableFiltering: true,
                columnDefs: [
                    {
                        field: 'fecha', width: '10%',
                        cellFilter: 'date',
                        footerCellFilter: 'date'
                    },
                    {
                        field: 'hora', width: '10%'

                    },
                    {
                        field: 'tarjeta', width: '15%'

                    },
                    {
                        field: 'autorizador', width: '20%'
                    },
                    {
                        field: 'adquiriente', width: '20%'
                    },
                    {
                        field: 'canal', width: '15%'
                    },

                    {
                        name:"Monto",
                        field: 'monto', width: '25%',
                        aggregationType: uiGridConstants.aggregationTypes.sum
                    }

                ],
                data: data,
                onRegisterApi: function (gridApi) {
                    $scope.gridApi = gridApi;
                }
            };

            $scope.toggleFooter = function () {
                $scope.gridOptions.showGridFooter = !$scope.gridOptions.showGridFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };

            $scope.toggleColumnFooter = function () {
                $scope.gridOptions.showColumnFooter = !$scope.gridOptions.showColumnFooter;
                $scope.gridApi.core.notifyDataChange(uiGridConstants.dataChange.OPTIONS);
            };



            $scope.enviar = function () {
                var aut_filters = _.map($scope.items, function (item) {
                    return item.codigo
                });
                var adq_filters = _.map($scope.itemsAdq, function (item) {
                    return item.codigo
                });
                var canal_filters = _.map($scope.itemsCanal, function (item) {
                    return item.codigo
                });

                var final = moment($scope.selectTimeNext,'DD/MM/YYYY');
                final = final.isValid()?final:moment($scope.selectTimeNext);
                var inicio = moment($scope.selectTimePrev,'DD/MM/YYYY');
                inicio = inicio.isValid()?inicio:moment($scope.selectTimePrev);

                var params = {
                    date_prev : inicio.format("YYYYMMDD"),
                    date_next : final.format("YYYYMMDD"),
                    adq_filters:adq_filters.toString(),
                    canal_filters : canal_filters.toString(),
                    aut_filters : aut_filters.toString()
                };

                reporteService.getReporte3(params).then(function(data){

                    if(data.errorCod == 0){
                        data.data.forEach(function (row){
                            row.fecha = moment(row.fecha,"YYYYMMDD").format("DD/MM/YYYY");
                        });
                        $scope.gridOptions.data = data.data;
                        if(data.data.length == 0){
                            $scope.message_front = "No hay datos para este reporte";
                        }
                    }else{
                        $scope.message_front = data.errorDesc;
                    }

                });

            };


        }]);


    app.directive('materializeDateRange',['$filter', function($filter){
        return {
            restrict: 'E',
            templateUrl: '/public/partials/materialize-date-range.html',
            controller: function($scope){
                var currentTime = moment().format();
                $scope.selectTimePrev = currentTime;
                $scope.month = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];
                $scope.monthShort = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'];
                $scope.weekdaysFull = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
                $scope.weekdaysLetter = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];
                //$scope.disable = [false, 1, 7]; // Que días de la semana se desactiva
                $scope.today = 'Hoy';
                $scope.clear = '';
                $scope.close = 'Cerrar';
                //var days = 15;
                //$scope.minDate = (new Date($scope.currentTime.getTime() - ( 1000 * 60 * 60 *24 * days ))).toISOString();
                //$scope.maxDate = (new Date($scope.currentTime.getTime() + ( 1000 * 60 * 60 *24 * days ))).toISOString();
                $scope.minDatePrev = '';
                $scope.maxDatePrev = currentTime;

                $scope.onStart = function () {
                    //console.log('onStart');
                };
                $scope.onRender = function () {
                    //console.log('onRender');
                };
                $scope.onOpen = function () {
                    //console.log('onOpen');
                };
                $scope.onClose = function () {
                    //console.log('onClose');
                };
                $scope.onSet = function () {
                    //console.info("onSet",$scope.selectTimePrev);
                    $scope.minDateNext = moment($scope.selectTimePrev,'DD/MM/YYYY').format();
                };
                $scope.onStop = function () {
                    //console.log('onStop');
                };

                // --------------------------------------------------------------------------------

                $scope.selectTimeNext = currentTime;
                /*$scope.month = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];
                 $scope.monthShort = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'];
                 $scope.weekdaysFull = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
                 $scope.weekdaysLetter = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];*/
                //$scope.disable = [false, 1, 7]; // Que días de la semana se desactiva
                /*$scope.today = 'Hoy';
                 $scope.clear = '';
                 $scope.close = 'Cerrar';*/
                //var days = 15;
                //$scope.minDate = (new Date($scope.currentTime.getTime() - ( 1000 * 60 * 60 *24 * days ))).toISOString();
                //$scope.maxDate = (new Date($scope.currentTime.getTime() + ( 1000 * 60 * 60 *24 * days ))).toISOString();

                $scope.minDateNext = currentTime;
                $scope.maxDateNext = currentTime;

                $scope.onStartNext= function () {
                    //console.log('onStartNext');
                };
                $scope.onRenderNext= function () {
                    //console.log('onRenderNext');
                };
                $scope.onOpenNext= function () {
                    //console.log('onOpenNext');
                };
                $scope.onCloseNext= function () {
                    //console.log('onCloseNext');
                };
                $scope.onSetNext = function () {
                    //console.info("onSetNext",$scope.selectTimePrev,' ... ',$scope.selectTimeNext);

                    var final = moment($scope.selectTimeNext,'DD/MM/YYYY');
                    final = final.isValid()?final:moment($scope.selectTimeNext);
                    var inicio = moment($scope.selectTimePrev,'DD/MM/YYYY');
                    inicio = inicio.isValid()?inicio:moment($scope.selectTimePrev);

                    if( inicio.isAfter(final,'day') ){
                        $scope.selectTimePrev = final.format();
                    }
                    $scope.maxDatePrev = final.format();
                };
                $scope.onStopNext= function () {
                    //console.log('onStopNext');
                };


            }
        };
    }]);

    app.directive('selectAutorizador',['reporteService',function(reporteService){
        return{
            restrict:'E',
            templateUrl:'/public/partials/select-autorizador.html',
            controller: function($scope){
                $scope.sel = '';
                $scope.values = [];
                $scope.items = [];

                reporteService.getAutorizadores().then(function(data){
                    if(data.errorCod == 0){
                        $scope.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });


                $scope.cambio = function(){
                    if($scope.sel == ''){
                        $scope.items = [];
                    }else if(_.findIndex($scope.items,{codigo:$scope.sel}) < 0) //Si no está aún
                        $scope.items.push(_.findWhere($scope.values,{codigo:$scope.sel}));
                    //console.log($scope.values, $scope.items);
                };

                $scope.delete = function(id){
                    $scope.items = _.without($scope.items,_.findWhere($scope.items,{codigo:id}));
                    //console.log($scope.values, $scope.items);
                };
            }
        };
    }]);

    app.directive('selectAdquiriente',['reporteService',function(reporteService){
        return{
            restrict:'E',
            templateUrl:'/public/partials/select-adquiriente.html',
            controller: function($scope){
                $scope.selAdq = '';
                $scope.valuesAdq = [];
                $scope.itemsAdq = [];

                reporteService.getAdquirientes().then(function(data){
                    if(data.errorCod == 0){
                        $scope.valuesAdq = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });


                $scope.cambioAdq = function(){
                    if($scope.selAdq == ''){
                        $scope.itemsAdq = [];
                    }else if(_.findIndex($scope.itemsAdq,{codigo:$scope.selAdq}) < 0) //Si no está aún
                        $scope.itemsAdq.push(_.findWhere($scope.valuesAdq,{codigo:$scope.selAdq}));
                    //console.log($scope.values, $scope.items);
                };

                $scope.deleteAdq = function(id){
                    $scope.itemsAdq = _.without($scope.itemsAdq,_.findWhere($scope.itemsAdq,{codigo:id}));
                    //console.log($scope.values, $scope.items);
                };
            }
        };
    }]);

    app.directive('selectCanal',['reporteService',function(reporteService){
        return{
            restrict:'E',
            templateUrl:'/public/partials/select-canal.html',
            controller: function($scope){
                $scope.selCan = '';
                $scope.valuesCan = [];
                $scope.itemsCanal = [];

                reporteService.getCanales().then(function(data){
                    if(data.errorCod == 0){
                        $scope.valuesCan = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });


                $scope.cambioCan = function(){
                    if($scope.selCan == ''){
                        $scope.itemsCanal = [];
                    }else if(_.findIndex($scope.itemsCanal,{codigo:$scope.selCan}) < 0) //Si no está aún
                        $scope.itemsCanal.push(_.findWhere($scope.valuesCan,{codigo:$scope.selCan}));
                    //console.log($scope.values, $scope.items);
                };

                $scope.deleteCan = function(id){
                    $scope.itemsCanal = _.without($scope.itemsCanal,_.findWhere($scope.itemsCanal,{codigo:id}));
                    //console.log($scope.values, $scope.items);
                };
            }
        };
    }]);











})();