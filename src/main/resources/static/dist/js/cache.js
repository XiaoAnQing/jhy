var cache = function(){};

//定义静态方法
cache.data={
    1:{
        0 : "克",
        1 : "千克"
    },
    2:{
        0 : "塑胶",
        1 : "板材"
    },
    3:{
        0 : "审核中",
        1 : "审核通过",
        2 : "审核未通过",
        3 : "异常",
        4 : "已完成",
        5 : "无效"
    },
    4:{
        0 : "审核中",
        1 : "审核通过",
        2 : "审核未通过",
        3 : "异常",
        4 : "已完成",
        5 : "无效"
    }
};

/**
 * 获取缓存
 * @param type
 */
cache.get = function(type){
    return cache.data[type];
}

/**
 * 获取缓存明细值
 * @param type
 * @param cacheVal
 * @returns
 */
cache.getName = function(type,cacheVal){
    try {
        return cache.data[type][cacheVal];
    } catch (e) {
        return "--";
    }
}