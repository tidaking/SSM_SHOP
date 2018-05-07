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
            $scope.selectIds.splice(idx,1)
        }
    }


    $scope.reloadList=function(){
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    /**
     *
     * @param list  [{"attributeName":"网络制式","attributeValue":["移动3G"]}]
     * @param key  attributeName
     * @param keyvalue 网络制式
     */
    $scope.searchObjectByKey=function (list,key,keyvalue) {
        for (var i=0;i<list.length;i++){
            var object = list[i];
            if(object[key]==keyvalue){//表示找到
                return object;
            }
        }
        return null;
    }

    /**
     *
     * @param jsonString 被传递过来的JSON字符串  [{"id":1,"text":"联想"},{"id":3,"text":"三星"},{"id":7,"text":"中兴"}]
     * @param key 要提取的某一个key的值对应的KEY ---》  text
     */
    //var o = {key:1}
    // o.key=1
    //o['key']=1
    $scope.jsonToString=function(jsonString,key){
        var fromJson = angular.fromJson(jsonString);
        var str="";
        for (var i=0;i<fromJson.length;i++){
            str+= fromJson[i][key]+","
        }

        if(str.length>=1){
            str= str.substring(0,str.length-1);
        }
        return str;

    }

})