 //控制层 
app.controller('goodsController' ,function($scope,$controller,goodsService,uploadService,itemCatService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

	$scope.add=function () {
        $scope.entity.goodsDesc.introduction=editor.html();
		goodsService.add($scope.entity).success(
			function (response) {
				if(response.success){
                    $scope.entity={};
                    editor.html('');//清空富文本编辑器
                    alert("添加成功")
				}else{
					alert(response.message)
				}
            }
		)
    }


    /**
     *上传图片
     */
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(function(response) {
            if(response.success){//如果上传成功，取出url
                $scope.image_entity.url=response.message;//设置文件地址
				//alert(response.message)
            }else{
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });
    };


    //$scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构
    $scope.entity={goods:{},goodsDesc:{itemImages:[],specificationItems:[]},itemList:[]};

//添加图片列表
    $scope.add_image_entity=function(){
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }

    //查询一级分类的列表
    $scope.selectCat1List=function () {
        itemCatService.findByParentId(0).success(
            function (response) {//List<tbitemcat>
                $scope.itemCat1List=response;
            }
        )
    }

    /**
     * 监控一级分类的id的值的变化，触发以下逻辑   根据一级分类的ID 查询二级分类的列表展示
     */
    $scope.$watch('entity.goods.category1Id',function (newValue,oldValue) {

        if(newValue!=undefined){
            itemCatService.findByParentId(newValue).success(
                function (response) {//List<tbitemcat>
                    $scope.itemCat2List=response;
                }
            )
        }
    })


    /**
     * 监控二级级分类的id的值的变化，触发以下逻辑 根据二级分类的ID 查询三级分类的列表展示
     */
    $scope.$watch('entity.goods.category2Id',function (newValue,oldValue) {

        if(newValue!=undefined){
            itemCatService.findByParentId(newValue).success(
                function (response) {//List<tbitemcat>
                    $scope.itemCat3List=response;
                }
            )
        }
    })

    /**
     * 监控三级分类的id的值的变化，触发以下逻辑 根据三级分类的ID 自己的对象的数据 获取到模板的ID 展示到页面
     */
    $scope.$watch('entity.goods.category3Id',function (newValue,oldValue) {

        if(newValue!=undefined){
            itemCatService.findOne(newValue).success(
                function (response) {//tbitemcat
                    $scope.entity.goods.typeTemplateId=response.typeId;//展示模板的id
                }
            );
        }
    })

    $scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue){
        if(newValue!=undefined){
            typeTemplateService.findOne(newValue).success(
            	function (response) {
                    $scope.typeTemplate=response
                    $scope.typeTemplate.brandIds=JSON.parse(response.brandIds)

                    $scope.typeTemplate.customAttributeItems=JSON.parse(response.customAttributeItems)
            	}
			)
            //查询的模板对应的规格列表
            typeTemplateService.findSpecList(newValue).success(
                function (response) {//response就是List<map>
                    $scope.specList = response;
                }
            )
		}
    })


    /**
     * [{"attributeName":"网络制式","attributeValue":["移动3G"]}]
     * 当点击复选框的时候调用去影响变量：$scope.entity.goodsDesc.specificationItems的值
     * specName：就是你点击的选项所对应的规格名称  网路
     * specValue:就是你点击的选项的值   4G
     */
    $scope.updateSpecAttribute=function ($event,specName,specValue) {
        var object = $scope.searchObjectByKey( $scope.entity.goodsDesc.specificationItems,'attributeName',specName);

        if(object!=null){
            //如果有对象
            if($event.target.checked){
                object.attributeValue.push(specValue);
            }else{
                object.attributeValue.splice(object.attributeValue.indexOf(specValue),1);//

                // 判断这个对象中的数组属性attributeValue的长度如果是0 删除该对象
                if( object.attributeValue.length==0){
                    $scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else {
            //如果没有对象
            $scope.entity.goodsDesc.specificationItems.push({"attributeName":specName,"attributeValue":[specValue]});
        }
    }




    /**
     * 重新构建SKU列表(item表)  深克隆
	 * spec就是一个规格
	 * $scope.entity.itemList就是
     */
    /*
	* 源数据:[{name:网络,value:[2G,3G,4G]},{name:屏幕,value:[3寸/4寸]}]
	*目标数据:[{网络:2G,屏幕:3寸},{网络:3G,屏幕:3寸},{网络:4G,屏幕:3寸},{网络:2G,屏幕:4寸},{网络:3G,屏幕:4寸},{网络:4G,屏幕:4寸}]
	* */
    $scope.createItemList=function () {

    	//test
		//var test = "[{'id':1,'text':'联想'},{'id':3,'text':'三星'},{'id':7,'text':'中兴'}]"
		// var test = "[{id:1,text:'联想'}]"
        // var after = $scope.jsonToString(test,"text")
		// alert(after)


        $scope.entity.itemList=[{spec:{},price:0,num:9999,status:'0',isDefault:'0'}];

        //循环遍历$scope.entity.goodsDesc.specificationItems ---》 [{"attributeName":"网络制式","attributeValue":["移动3G"]}]

		//获取选中的所有规格,并遍历它
		//items就是选中的规格
        var items = $scope.entity.goodsDesc.specificationItems;

        for (var i=0;i<items.length;i++){
            var object = items[i];
            //往$scope.entity.itemList里面添加item
            $scope.entity.itemList=addColumn($scope.entity.itemList,object.attributeName,object.attributeValue);
        }
    }

    /**
     *
     * @param list   [{spec:{},price:0,num:9999,status:'0',isDefault:'0'}];
     * @param column 网络制式
     * @param clumnValues ["移动3G"]
     * @returns {Array}
     */
    addColumn=function (list,column,clumnValues) {
        var newList = [];
        //遍历传进来的老的scope.entity.itemList
        for(var i=0;i<list.length;i++){
            var oldRow =  list[i];//{spec:{'网路制式'“：3g},price:0,num:9999,status:'0',isDefault:'0'}
            //attributeValue有很多个的,比如说name:网络知识,value:2G/3G/4G
			for (var j = 0;j<clumnValues.length;j++){
                var newRow = angular.fromJson(angular.toJson(oldRow));
                newRow.spec[column]=clumnValues[j];//加规格属性
                newList.push(newRow);
            }
        }
        return newList;
    }




});	
