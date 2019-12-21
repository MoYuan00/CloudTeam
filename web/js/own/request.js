let cloud_host = 'http://39.105.111.19/';
cloud_host = '';
// cloud_host = 'http://zhengquan.55555.io:22036/SoftwareDemo_war_exploded/';
function getURL(url){
    return cloud_host + url;
}
/**
 * 克隆一个对象的值
 **/
const clone = function (o) {
    const str = JSON.stringify(o);
    return JSON.parse(str);
}
/**
 * 封装请求
 */
// const request = {
//     /**
//      * get请求 - 上传的值为url字符串 有长度限制
//      * 使用情况：spring mvc接收的是多个基本类型（Integer，String等）
//      * @param urlAndParams url?pram1=value&pram2=value2
//      * @param successCallBack 成功时调用
//      * @param errorCallBack 错误时调用
//      */
//     getData(urlAndParams, successCallBack, errorCallBack) {
//         const me = this;
//         $.ajax({
//             url: cloud_host + urlAndParams,
//             type: 'GET',
//             async: true,
//             // 下面的两行代码,就是解决跨域的关键
//             xhrFields: {withCredentials: true},
//             crossDomain: true,
//             contentType: 'application/json;charset=UTF-8',
//             success: successCallBack,
//             error: errorCallBack
//         })
//     },
//     /**
//      * get请求 - 上传的值为url字符串 有长度限制
//      * 使用情况：spring mvc接收的是多个基本类型（Integer，String等）
//      * @param url url
//      * @param jsonData json对象 - 实际上还是使用的url传递
//      * @param successCallBack 成功时调用
//      * @param errorCallBack 错误时调用
//      */
//     getDataJson: function (url, jsonData, successCallBack, errorCallBack) {
//         jsonData = clone(jsonData);
//         $.ajax({
//             url: cloud_host + url,
//             type: 'GET',
//             data: jsonData,
//             async: true,
//             contentType: 'application/json;charset=UTF-8',
//             success: successCallBack,
//             error: errorCallBack
//         })
//     },
//     /**
//      * post请求 - 上传的值为json字符串 -
//      * 使用情况：spring mvc接收的是一个对象或者Map,并且必须加上@RequestBody
//      * @param postUrl url
//      * @param jsonData json对象
//      * @param successCallBack 成功时调用
//      * @param errorCallBack 错误时调用
//      */
//     postData: function (url, jsonData, successCallBack, errorCallBack) {
//         jsonData = clone(jsonData);
//         $.ajax({
//             url: cloud_host + url,
//             type: 'post',
//             async: true,
//             data: JSON.stringify(jsonData),// 通过对象传递需要转化成字符串，mvc会解析字符串
//             contentType: 'application/json;charset=UTF-8',
//             success: successCallBack,
//             error: errorCallBack
//         })
//     },
//     /**
//      * post请求 - 上传的值为json对象 有长度限制
//      * @param postUrl url
//      * @param jsonData json对象 - 相当于给url带参
//      * @param successCallBack 成功时调用
//      * @param errorCallBack 错误时调用
//      */
//     postDataJson: function (url, jsonData, successCallBack, errorCallBack) {
//         jsonData = clone(jsonData)
//         $.ajax({
//             url: cloud_host + url,
//             type: 'post',
//             async: true,
//             data: jsonData,
//             // contentType: 'application/json;charset=UTF-8',// 不能写，写了接收不到
//             success: successCallBack,
//             error: errorCallBack
//         })
//     }
// };


const instance = axios.create({
    timeout: 3000,
});
// 给请求添加拦截器，在请求前和响应前添加 加载层显示。
instance.interceptors.request.use(config=>{
    if(config.method ==='post'){
        config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    }
    Event.$emit('start-loading');
    return config;
}, error=>{
    // 对请求错误做些什么
    Event.$emit('finish-loading');
    Event.$emit('on-show-msg', '请求失败，请重试！', 'warning');
    return Promise.reject(error);
});
instance.interceptors.response.use(res=>{
    const data = res.data;
    Event.$emit('finish-loading');
    logger.debug('响应信息：', data);
    if(data.type && data.message){
        Event.$emit('on-show-msg', data.message, data.type);
    }
    return res;
},
error=>{
    Event.$emit('finish-loading');
    Event.$emit('on-show-msg', '加载失败，请重试！', 'error');
    return Promise.reject(error);
});


// 定义所有的请求服务器函数
const http = {

    getUserInfoByUserId(userId) {
        const url = "getUserInfoByUserId.do";
        return instance.get(getURL(url), {params: {userId, userId}});
    },
    getCreateTeamsByUserId(userId) {
        const url = 'getCreateTeamsByUserId.do';
        return instance.get(getURL(url), {params: {userId, userId}});
    },
    getJoinTeamsByUserId(userId) {
        const url = 'getJoinTeamsByUserId.do';
        return instance.get(getURL(url), {params: {userId: userId}});
    },
    updateUserInfo(user) {
        const url = 'user/updateUserInfoByUserId.do';
        // OK 对应服务器使用@RequestBody接收
        return instance.post(getURL(url), JSON.stringify(user));
    },
    login(name, password) {
        const url = 'login.do';
        return instance.post(getURL(url), {name: name, password: password});
    },
    getLoginUser(){
      const url = 'getLoginUser.do';
      return instance.post(getURL(url));
    },
    getUserByTeamId(teamId){
        const url = 'getUserInfoByTeamId.do';
        return instance.get(getURL(url),
            {
                params:{teamId: teamId}
            });
    },
    getWorkStatus(){
        const url = 'getWorkStatus.do';
        return instance.get(getURL(url));
    },
    getTeamInfo(teamId){
        const url ='getTeamInfoByTeamId.do';
        return instance.get(getURL(url), {params:{teamId: teamId}});
    },
    getWorks(userId, teamId){
        const url = 'getWorks.do';
        return instance.get(getURL(url), {params:{userId:userId, teamId: teamId}});
    },
    reportWorkMsg(work_id, user_id, datetime, content_text){
        const url = 'appendWorkMsg.do';
        return instance.post(getURL(url),
            JSON.stringify(
                {
                work_id: work_id,
                report_user_id:user_id,
                report_time: datetime,
                content_text: content_text
                })
            );
    },
    registerUser(name, password){// 注册一个用户
        const url = 'registerUser.do';
        return instance.post(getURL(url),
            JSON.stringify(
                {
                    name: name, password:password
                    })  );
    },
    deleteWorkByWorkId(workId){// 删除一个任务
        const url = 'deleteWorkByWorkId.do';
        return instance.get(getURL(url), {params: {workId: workId}});
    },
    appendTeam(team){// 创建一个团队
        const url = 'team/appendTeam.do';
        return instance.post(getURL(url), JSON.stringify(team));
    },
    appendWork(work){// 创建一个任务
        const url = 'work/appendWork.do';
        return instance.post(getURL(url), JSON.stringify(work));
    },
    updateTeamInfo(team){
        const url = 'team/updateTeam.do';
        return instance.post(getURL(url), JSON.stringify(team));
    },
    updateWorkWorkStatusId(workId, workStatusId){
        const url = 'work/updateWorkWorkStatusId.do';
        return instance.get(getURL(url), {params:{workId:workId, workStatusId: workStatusId}});
    },
    updateWorkExecUserId(workId, userId){
        const url = 'work/updateWorkExecUserId.do';
        return instance.get(getURL(url), {params:{workId:workId, userId: userId}});
    },
    updateWorkName(workId, name){
        const url = 'work/updateWorkName.do';
        return instance.get(getURL(url), {params:{workId:workId, name: name}});
    },
    logout(){
        const url = 'user/logout.do';
        return instance.post(getURL(url));
    },
    isCanUseName(name){
        const url = 'user/isCanUseName.do';
        return instance.get(getURL(url), {params: {name: name}});
    },

    userOutTeam(teamId, userId){
        const url = 'joinTeam/outUser.do';
        return instance.get(getURL(url), {params:{teamId:teamId,userId:userId}});
    },

    joinTeamByName(teamId, name){
        const url = 'joinTeam/joinTeamByName.do';
        return instance.get(getURL(url), {params:{name:name,teamId:teamId}});
    },
    deleteTeam(teamId){
        const url = 'team/deleteTeam.do';
        return instance.get(getURL(url), {params:{teamId:teamId}});
    }
};
// OK
function inputFilter(input) {
    let re =  input.replace(
        /[`~!@#$%^&*()_\-+=<>?:'{}|,./;\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/g, '')
        .replace(/\s/g, '');
    if(re != input){
        Event.$emit("on-show-msg", "不要尝试输入特殊字符！ " +
            "~!@#$%^&*()_\\-+=<>?:'{}|,./;\\\\[\\]·~！@#￥%……&*（）——\\-+={}|《》？：“”【】、；‘’，。、", "warning");
    }
    return re;
}
// OK
function inputSpaceFilter(input) {
    let re =  input.replace(/\s/g, '');
    if(re != input){
        Event.$emit("on-show-msg", "不要尝试输入空字符！ ", "warning");
    }
    return re;
}


