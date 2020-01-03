$.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

$.fn.load = function (obj,callBack) {
    var $form=$(this);
    var key,value,tagName,type,arr;
    for(x in obj){
        key = x;
        value = obj[x];
        $("[name='"+key+"']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                    arr = value.split(',');
                    for(var i =0;i<arr.length;i++){
                        if($(this).val()==arr[i]){
                            $(this).attr('checked',true);
                            break;
                        }
                    }
                }else{
                    $(this).val(value);
                }
            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
                $(this).val(value).trigger("change");
            }
        });
    }
    if(callBack){
        callBack();
    }
};
$.fn.clear = function (callBack) {
    var $form=$(this);

    var $eles = $form.find("input");
    $.each($eles,function(index,item){
        $(item).val("");
    })
    $eles = $form.find("select");
    $.each($eles,function(index,item){
        $(item).val("");
    })
    return $form;


    callBack();

};