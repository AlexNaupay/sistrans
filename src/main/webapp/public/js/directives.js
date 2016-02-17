(function () {
    angular.module('sistrans.directives', [])
        .directive('materializeDateRange', [/*'$filter', */function (/*$filter*/) {
            return {
                restrict: 'E',
                templateUrl: '/public/partials/materialize-date-range.html',
                scope: {
                    dateModel: '=',  // bidirectional
                    dateFormat: '@'  // unidirectional
                },

                // $scope de la directiva ..
                controller: function ($scope) {
                    $scope.dateFormat = $scope.dateFormat || 'DD/MM/YYYY';
                    var currentTime = moment().format();
                    $scope.dateModel = {
                        prev: currentTime,
                        next: currentTime,

                        getData: function(){
                            // validate before ..
                            var next = moment(this.next, $scope.dateFormat);
                            next = next.isValid()?next:moment(this.next);
                            var prev = moment(this.prev, $scope.dateFormat);
                            prev = prev.isValid()?prev:moment(this.prev);
                            return {
                                prev: prev,
                                next: next
                            };
                        }
                    };

                    /* $scope.dateModel.prev = currentTime; */
                    $scope.month = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];
                    $scope.monthShort = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'];
                    $scope.weekdaysFull = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
                    $scope.weekdaysLetter = ['D', 'L', 'M', 'X', 'J', 'V', 'S'];
                    /* $scope.disable = [false, 1, 7]; // Que días de la semana se desactiva */
                    $scope.today = 'Hoy';
                    $scope.clear = '';
                    $scope.close = 'Cerrar';
                    /*
                        var days = 15;
                        $scope.minDate = (new Date($scope.currentTime.getTime() - ( 1000 * 60 * 60 *24 * days ))).toISOString();
                        $scope.maxDate = (new Date($scope.currentTime.getTime() + ( 1000 * 60 * 60 *24 * days ))).toISOString();
                        $scope.minDatePrev = '';
                    */
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
                        //console.info("onSet",$scope.dateModel.prev);
                        $scope.minDateNext = moment($scope.dateModel.prev, $scope.dateFormat).format();
                    };
                    $scope.onStop = function () {
                        //console.log('onStop');
                    };

                    // --------------------------------------------------------------------------------
                    $scope.minDateNext = currentTime;
                    $scope.maxDateNext = currentTime;

                    $scope.onStartNext = function () {
                        //console.log('onStartNext');
                    };
                    $scope.onRenderNext = function () {
                        //console.log('onRenderNext');
                    };
                    $scope.onOpenNext = function () {
                        //console.log('onOpenNext');
                    };
                    $scope.onCloseNext = function () {
                        //console.log('onCloseNext');
                    };
                    $scope.onSetNext = function () {
                        //console.info("onSetNext",$scope.dateModel.prev,' ... ',$scope.dateModel.next);

                        var final = moment($scope.dateModel.next, $scope.dateFormat);
                        final = final.isValid() ? final : moment($scope.dateModel.next);

                        var inicio = moment($scope.dateModel.prev, $scope.dateFormat);
                        inicio = inicio.isValid() ? inicio : moment($scope.dateModel.prev);

                        if (inicio.isAfter(final, 'day')) {
                            $scope.dateModel.prev = final.format();
                        }
                        $scope.maxDatePrev = final.format();
                    };
                    $scope.onStopNext = function () {
                        //console.log('onStopNext');
                    };


                }
            };
        }])

    .directive('selectAndFilter', [function(){
        return{
            restrict:'E',
            templateUrl:'/public/partials/select-and-filter.html',
            scope: {
                data: '='
            },
            controller: function($scope){
                $scope.data = {
                    values: [],
                    items: [],
                    getFilters: function(){
                        return _.map(this.items, function (item) {
                            return item.codigo
                        }).toString();
                    }
                };
                $scope.sel = '';

                $scope.cambio = function(){
                    if($scope.sel == ''){
                        $scope.data.items = [];
                    }else if(_.findIndex($scope.data.items,{codigo:$scope.sel}) < 0) //Si no está aún
                        $scope.data.items.push(_.findWhere($scope.data.values,{codigo:$scope.sel}));
                    //console.log($scope.values, $scope.items);
                };

                $scope.delete = function(id){
                    $scope.data.items =  // delete item with codigo=id
                        _.without($scope.data.items, _.findWhere($scope.data.items,{codigo:id}));
                };
            }
        };
    }])

})();