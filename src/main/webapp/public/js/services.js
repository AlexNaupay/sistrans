(function(){
    var services = angular.module("sistrans.services",[]);

    // Archivo Service
    services.factory('archivoService', ['$http', '$q', function ($http, $q) {

    }]);


    // Plantilla Service
    services.factory('plantillaService',['$http','$q',function($http, $q){

        function getCampos(){
            var deferred = $q.defer();
            $http.get("/plantilla/campos")
                .success(function(data){
                    deferred.resolve(data);
                });
            return deferred.promise;
        }

        function updateCampos(campos){
            var deferred = $q.defer();
            $http.put("/plantilla/campos",campos)
                .success(function(data){
                    deferred.resolve(data);
                });

            return deferred.promise;
        }

        return {
            getCampos: getCampos,
            updateCampos: updateCampos
        };
    }]);

    // Archivo Service
    services.factory('reporteService', ['$http', '$q', function ($http, $q) {
        function getAutorizadores(){
            var deferred = $q.defer();
            $http.get("/api/autorizadores")
                .success(function(data){
                    deferred.resolve(data);
                });
            return deferred.promise;
        }

        function getAdquirientes(){
            var deferred = $q.defer();
            $http.get("/api/adquirientes")
                .success(function(data){
                    deferred.resolve(data);
                });
            return deferred.promise;
        }

        function getCanales(){
            var deferred = $q.defer();
            $http.get("/api/canales")
                .success(function(data){
                    deferred.resolve(data);
                });
            return deferred.promise;
        }

        function getReporte1(datos){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/api/reporte1',
                params : datos
            }).success(function(data){
                deferred.resolve(data);
            });

            return deferred.promise;
        }
        function getReporte2(datos){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/api/reporte2',
                params : datos
            }).success(function(data){
                deferred.resolve(data);
            });

            return deferred.promise;
        }
        function getReporte3(datos){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/api/reporte3',
                params : datos
            }).success(function(data){
                deferred.resolve(data);
            });

            return deferred.promise;
        }

        return{
            getAutorizadores : getAutorizadores,
            getAdquirientes : getAdquirientes,
            getCanales : getCanales,
            getReporte1 : getReporte1,
            getReporte2 : getReporte2,
            getReporte3 : getReporte3
        };
    }]);

})();
