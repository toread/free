/**
 * @author 探路者
 */

//数据表格覆盖
$.fn.datagrid.defaults=$.extend({},$.fn.datagrid.defaults,{
    fit: true,
    method:'get',
    singleSelect:true,
    pagePosition:'bottom',
    idField:'uuid',
    striped:true,
    pagination:true,
    //处理排序方式
    onBeforeLoad:function(data){
        data["page"] =data.page-1;
        data["size"]=data["rows"];
        if(data["sort"]){
            data["sort"]=data["sort"]+","+data["order"]
        }
        delete data["rows"];
        delete data["order"];
    },
    //处理返回结果集
    loadFilter: function(data){
        if(data.success){
            var result = data.result;
            result["rows"]=result["content"];
            delete result["content"];
            result["total"] =result["totalElements"];
            delete result["totalElements"];
            return result;
        }else{
            $.messager.alert('提示信息',data.msg);
        }
    }
})

//设置tree默认设置
$.fn.tree.defaults = $.extend({},$.fn.tree.defaults,{
    loadFilter: function(data){
        if(data.success){
            return data.result;
        }else{
            $.messager.alert('提示信息',data.msg);
        }
    }
})