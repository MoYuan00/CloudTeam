/**
 * 一个任务的抽屉
 */
Vue.component('work-msg-list-tpl', {
    template: `
<div >
    消息<span class="badge badge-light el-icon-chat-square">{{msg_list.length}}</span>
    <div style="height: 200px; overflow: auto;">
        <div v-for="msg in msg_list">
             <work-msg-tpl :msg="msg"></work-msg-tpl>
        </div>
    </div>
</div>
    `,
    props: ['msg_list'],
    data: function () {
        return {

        }
    },
});