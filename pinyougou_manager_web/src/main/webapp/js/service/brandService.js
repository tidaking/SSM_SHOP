app.service("brandService",function ($http) {
    this.findAll = function () {
        return $http.get("/brand/findAll.do");
    }

    this.findPage = function (pageNum,pageSize) {
        return $http.get("/brand/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize);
    }

    this.save = function (id,entity) {
        var method = "add";
        if(id != null)
        {
            method = "update";
        }
        return $http.post("/brand/"+method+".do",entity);
    }

    this.findOne = function (id) {
        return $http.get("/brand/findOne.do?id="+id);
    }

    this.dele = function (ids) {
        return $http.post("/brand/dele.do",ids);
    }

    this.search = function (pageNum,pageSize,searchEntity) {
        return $http.post("/brand/search.do?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity);
    }

})