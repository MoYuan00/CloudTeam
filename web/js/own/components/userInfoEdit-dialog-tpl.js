Vue.component('user-info-edit-dialog-tpl', {
    template: `
    <div>
        <el-dialog center title="编辑资料" :visible.sync="visible" width="280px">
            <el-form :model="user">
                <form-item-mini-tpl>
                    <span slot="label">用户名</span>
                    <el-input maxlength="20" show-word-limit   v-model="user.name" slot="item" autocomplete="off"></el-input>
                </form-item-mini-tpl>
                 <form-item-mini-tpl>
                    <span slot="label">个人简介</span>
                    <el-input type="textarea" slot="item" v-model="user.detail" autocomplete="off"></el-input>
                </form-item-mini-tpl>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="click_save">保 存</el-button>
            </div>
        </el-dialog>
    </div>
    `,
    data() {
        return {
            user: {},
            visible: false,
            _user: null
        }
    },
    mounted() {
        Event.$on('show-user-info-edit-dialog', this.showDialog);
    },
    methods: {
        showDialog(user) {
            logger.debug('show-user-info-edit-dialog showDialog 接收到显示事件');
            this.visible = true;
            this._user = clone(user);
            this.user = user;
        },
        click_save() {
            if (!this.user || !this.user.name || this.user.name == '' || this.user.name.length < 2) {
                this.$alert('用户名不可以为空！并且长度至少为2！', 'tip', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
                return;
            }
            http.updateUserInfo(this.user)
                .then(res => {
                    const data = res.data;
                    logger.debug('updateUserInfo', data);
                    if(data.errNo == 0){

                    }
                });
            this.visible = false;
        }
    }
});