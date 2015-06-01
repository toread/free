/**
 * @author 探路者
 */
//定义jquery扩展的命名空间
$.toread =$.extend({},$);;
//定义全局规范
var GLB = {};
GLB.ROOT = "root";
(function($){
    //获取最新的地址
    $.toread.url={
        root:root,
        targetUrl:targetUrl
    }

    /**
     * 获取当前项目路径
     * @returns {*}
     */
    function root(){
        return $.data(GLB,GLB.ROOT);
    }

    /**
     *
     * @param relativePath 相对路径
     * @returns {*}
     */
    function targetUrl(relativePath){
        return root()+relativePath;
    }

})($)

