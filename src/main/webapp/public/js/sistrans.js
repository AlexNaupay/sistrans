
(function(){
    var app = angular.module('sistrans', [
        'ngFileUpload', 'ui.sortable', 'sistrans.services', 'sistrans.controllers',
        'sistrans.directives', 'ui.materialize', 'ui.grid'
    ]);

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


})();