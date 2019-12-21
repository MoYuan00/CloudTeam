/**
 * 一个任务的抽屉
 */
Vue.component('work-msg-tpl', {
    template: `
<div>
        <div>
            <span class="el-icon-user"></span>{{getUserName()}}：
            <small class="float-right">{{msg.report_time}}</small>
        </div>
        <small class="text-wrap text-break text-info" style="margin-left: 2em" >
            {{msg.content_text}}
        </small>
    <hr/>
</div>
    `,
    props: ['msg'],
    data: function () {
        return {
        }
    },
    methods:{
        getUserName(){
            if(this.msg.reportUser && this.msg.reportUser.name){
                return this.msg.reportUser.name;
            }
            return '（已退出）';
        }
    }
});