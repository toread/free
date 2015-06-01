/**
 * @author 探路者
 */
var Model={};
$(function(){
    //获取各种组件
    Model.getModel = function(modelName){
        return {
            window:function(){
                return getComponentById(modelName,'window');
            },
            grid:function(){
                return getComponentById(modelName,'grid');
            },
            queryForm: function () {
                return getComponentById(modelName,'queryForm');
            },
            form:function(){
                return getComponentById(modelName,'form');
            },
            component:function(componentName){
                return getComponentById(modelName,componentName);
            },
            api: function() {
                return api(modelName);
            }
        }
    }

    function getComponentById(modelName,componentName){
        return $("#"+modelName+"-"+componentName);
    }

    //api组件
    var api = function(modelName){
        return{
            add:function(data){
                return simpleAjax({
                    url:$.toread.url.targetUrl("/"+modelName),
                    type:'post',
                    module:modelName,
                    data:data
                })
            },
            delete:function(data){
                return simpleAjax({
                    url:$.toread.url.targetUrl("/"+modelName+"/"+data.uuid),
                    type:'delete',
                    module:modelName,
                    data:data
                })

            },
            update:function(data){
                return simpleAjax({
                    url:$.toread.url.targetUrl("/"+modelName+"/"+data.uuid),
                    type:'post',
                    module:modelName,
                    data:data
                })
            }
        }
    }

    function simpleAjax(config){
        var module = config.module;
        var config = $.extend({},Model.defaults,config);
        $.when($.ajax(config)).then(function(data){config.ok(data,module)},function(data){config.fail(data,module)})

    }

    /**
     * 回调函数后续处理
     * @param model
     */
    function msgClose(moduleName){
        var windows = Model.getModel(moduleName).window();
        var grid = Model.getModel(moduleName).grid();
        windows.window('close');
        grid.datagrid('reload');
    }

    Model.defaults = {
        ok:function(data,moduleName){
            $.messager.alert('提示信息',data.msg,'',function(){
                msgClose(moduleName);
            });
        },
        fail:function(data,moduleName){
            $.messager.alert('提示信息',data.msg,'',function(){
                msgClose(moduleName);
            });
        }
    }
})
