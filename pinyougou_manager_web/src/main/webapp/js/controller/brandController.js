//在brand上创建新的controller
app.controller("brandController",function ($scope,$http,brandService) {
    //分页控件配置
    //这是一个对象
    $scope.paginationConf = {
        currentPage: 1,//当前页码
        totalItems: 10,//总记录数
        itemsPerPage: 10,//每页显示记录数
        perPageOptions: [10, 20, 30, 40, 50],//每页显示记录数选择
        //onChange: function(){
        //    $scope.reloadList(); };//重新加载
        onChange:function () {
            //$scope.findPage()
            $scope.reloadList()
        }
    }

    //$scope.findAll表示它是这个作用域下的findAll,也就是brandController下的findAll而不是其他xxxController下的findAll
//                $scope.findAll = function () {
//                    $http.get("/brand/findAll.do").success(
//                        function (response) {
//                            $scope.brandList = response
//                        })
//                }
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.brandList = response
            })
    }

//                $scope.findPage = function () {
//					$http.get("/brand/findPage.do?pageNum="+$scope.paginationConf.currentPage+"&pageSize="+$scope.paginationConf.itemsPerPage).success(
//					    function (response) {
//							//PageResult
////                            private Long total;//总记录数
////                            private List rows;//分组后数据
//
//                            $scope.brandList = response.rows
//                            $scope.paginationConf.totalItems = response.total
//                        }
//					)
//                }
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

//                $scope.save = function () {
//                	var method = "add"
//					if($scope.entity.id != null)
//					{
//                        method = "update"
//					}
//					$http.post("/brand/"+method+".do",$scope.entity).success(function (response) {
//						if(response.success) {
//						    //$scope.findPage()
//                            $scope.reloadList();//重新加载
//						}else{
//						    alert(response.message)
//						}
//                    })
//                }
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


//                $scope.findOne = function (id) {
//                	//alert(id)
//					$http.get("/brand/findOne.do?id="+id).success(function (response) {
//						//alert(response.name)
//                        //alert(response.firstChar)
//                        $scope.entity = response
//                        //$scope.entity.name = response.name
//                        //$scope.entity.firstChar = response.firstChar
//                    })
//                }
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

    $scope.selectIds = []
    $scope.searchEntity = {}

    $scope.updateSelection = function($event,id){
        if($event.target.checked){
            $scope.selectIds.push(id)
        }else{
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx)
        }
    }


//                $scope.dele = function () {
//					alert($scope.selectIds)
//					var json = {'ids':$scope.selectIds}
//					$http.post("/brand/dele.do",$scope.selectIds).success(function (response) {
//						alert(response.success)
//						alert(response.message)
//                    })
//
//                }
    $scope.dele = function () {
        alert($scope.selectIds)
        var json = {'ids':$scope.selectIds}
        brandService.dele($scope.selectIds).success(function (response) {
            //alert(response.success)
            alert(response.message)
        })

    }

//                $scope.search = function () {
//					$http.post("/brand/search.do?pageNum="+$scope.paginationConf.currentPage+"&pageSize="+$scope.paginationConf.itemsPerPage,$scope.searchEntity).success(function (response) {
//                        $scope.brandList = response.rows
//                        $scope.paginationConf.totalItems = response.total
//                    })
//                }
    $scope.search = function () {
        brandService.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage,$scope.searchEntity).success(function (response) {
            $scope.brandList = response.rows
            $scope.paginationConf.totalItems = response.total
        })
    }

    $scope.reloadList=function(){
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


});