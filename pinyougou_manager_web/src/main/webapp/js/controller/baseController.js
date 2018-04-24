app.controller("baseController",function ($scope) {

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

    //复选数组
    $scope.selectIds = []

    //更新复选数组
    $scope.updateSelection = function($event,id){
        if($event.target.checked){
            $scope.selectIds.push(id)
        }else{
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx)
        }
    }


    $scope.reloadList=function(){
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

})