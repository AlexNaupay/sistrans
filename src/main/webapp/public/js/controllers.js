(function () {
    // private function
    function _renderResponse(data, scope){
        if (data.errorCod == 0) {
            data.data.forEach(function (row) {
                row.fecha = moment(row.fecha, "YYYYMMDD").format("DD/MM/YYYY");
            });
            scope.gridOptions.data = data.data;
            if (data.data.length == 0) {
                scope.message_front = "No hay datos para este reporte";
            }
        } else {
            $scope.message_front = data.errorDesc;
        }
    }

    // sistrans.controllers module
    angular.module('sistrans.controllers', [])
        .controller('ReportFirstController', [
            '$scope', 'uiGridConstants', '$http', 'i18nService', 'reporteService',
            function ($scope, uiGridConstants, $http, i18nService, reporteService) {
                i18nService.setCurrentLang('es');
                var data = [];
                $scope.dateRange = {};
                $scope.selectAutorizador = {};
                $scope.selectCanal = {};

                reporteService.getAutorizadores().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectAutorizador.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });

                reporteService.getCanales().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectCanal.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });

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
                            name: "Número de Transacciones",
                            field: 'numeroTx', width: '20%',
                            aggregationType: uiGridConstants.aggregationTypes.sum
                        },
                        {
                            name: "Monto Total",
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
                    // get date range object
                    var range = $scope.dateRange.getData();

                    var params = {
                        date_prev: range.prev.format("YYYYMMDD"),
                        date_next: range.next.format("YYYYMMDD"),
                        adq_filters: $scope.selectAutorizador.getFilters(),
                        canal_filters: $scope.selectCanal.getFilters()
                        //adq_filters :
                    };

                    reporteService.getReporte1(params).then(function (data) {
                        _renderResponse(data, $scope);
                    });

                };


            }])

        .controller('ReportSecondController', ['$scope', 'uiGridConstants', '$http', 'i18nService', 'reporteService',
            function ($scope, uiGridConstants, $http, i18nService, reporteService) {
                i18nService.setCurrentLang('es');
                var data = [];
                $scope.dateRange = {};
                $scope.selectAutorizador = {};

                reporteService.getAutorizadores().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectAutorizador.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });

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
                            field: 'autorizador', width: '30%'
                        },
                        {
                            name: "Número de Transacciones",
                            field: 'numeroTx', width: '25%',
                            aggregationType: uiGridConstants.aggregationTypes.sum
                        },
                        {
                            name: "Monto Total",
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
                    var range = $scope.dateRange.getData();

                    var params = {
                        date_prev: range.prev.format("YYYYMMDD"),
                        date_next: range.next.format("YYYYMMDD"),
                        aut_filters: $scope.selectAutorizador.getFilters()
                    };

                    reporteService.getReporte2(params).then(function (data) {
                        _renderResponse(data, $scope);
                    });

                };


            }])


        .controller('ReportDetController', ['$scope', 'uiGridConstants', '$http', 'i18nService', 'reporteService',
            function ($scope, uiGridConstants, $http, i18nService, reporteService) {
                i18nService.setCurrentLang('es');
                var data = [];
                $scope.dateRange = {};
                $scope.selectAutorizador = {};
                $scope.selectAdquiriente = {};
                $scope.selectCanal = {};

                reporteService.getAutorizadores().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectAutorizador.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });

                reporteService.getAdquirientes().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectAdquiriente.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });

                reporteService.getCanales().then(function(data){
                    if(data.errorCod == 0){
                        $scope.selectCanal.values = data.data;
                    }else{
                        console.error("Error en el servidor");
                    }
                });


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
                            name: "Monto",
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
                    var range = $scope.dateRange.getData();

                    var params = {
                        date_prev: range.prev.format("YYYYMMDD"),
                        date_next: range.next.format("YYYYMMDD"),
                        adq_filters: $scope.selectAdquiriente.getFilters(),
                        canal_filters: $scope.selectCanal.getFilters(),
                        aut_filters: $scope.selectAutorizador.getFilters()
                    };

                    reporteService.getReporte3(params).then(function (data) {
                        _renderResponse(data, $scope);

                    });

                };


            }])

})();
