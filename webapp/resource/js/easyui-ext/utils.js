/**
 * @author 探路者
 */
$.utils = $.extend({},$);
//定义表单工具
$.utils.serializeFrom = function(form){
    var o = {};
    $.each(form.serializeArray(), function(index) {
        console.info(this['value']);
        if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
            } else {
                o[this['name']] = this['value'];
            }
        }
    });
    return o;
}