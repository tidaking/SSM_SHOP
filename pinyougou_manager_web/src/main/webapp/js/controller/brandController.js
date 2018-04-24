//在brand上创建新的controller
app.controller("brandController",function ($scope,$controller,brandService) {

    //$controller也是angular提供的一个服务，可以实现伪继承，实际上就是与baseController共享$scope
    $controller('baseController',{$scope:$scope})

    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.brandList = response
            })
    }


    $scope.findPage = function () {
        brandService.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(
            function (response) {
                //PageResult
//                            private Long total;//总记录数
//                            private List rows;//分组后数据

                $scope.brandList = response.rows
                $scope.paginationConf.totalItems = response.total
            }
        )
    }


    $scope.save = function () {
        brandService.save($scope.entity.id,$scope.entity).success(function (response) {
            if(response.success) {
                //$scope.findPage()
                $scope.reloadList();//重新加载
            }else{
                alert(response.message)
            }
        })
    }

    $scope.findOne = function (id) {
        //alert(id)
        brandService.findOne(id).success(function (response) {
            //alert(response.name)
            //alert(response.firstChar)
            $scope.entity = response
            //$scope.entity.name = response.name
            //$scope.entity.firstChar = response.firstChar
        })
    }

    $scope.searchEntity = {}

    $scope.dele = function () {
        alert($scope.selectIds)
        var json = {'ids':$scope.selectIds}
        brandService.dele($scope.selectIds).success(function (response) {
            //alert(response.success)
            alert(response.message)
        })

    }

    $scope.search = function () {
        brandService.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage,$scope.searchEntity).success(function (response) {
            $scope.brandList = response.rows
            $scope.paginationConf.totalItems = response.total
        })
    }



});