// 踩坑记录，tag-tpl是自己定义的组件。如果要在其他自定义组件中使用，只能采取直接注册的方式
Vue.component('tag-tpl', {
    template: `
    <el-popover center placement="bottom" trigger="click">
        <div class="text-center">
            <el-radio-group  @change="on_change" size="mini" v-model="t_data">
                <div v-for="item in data_list">
                    <el-radio-button :label="item">
                    {{item[data_show_name]}}
                    </el-radio-button>
                </div>
            </el-radio-group>
        </div>
        <el-button slot="reference" type="plain">
            {{getShowName}}
        </el-button>
    </el-popover>
      `,
    props: ["data_list", "data", "data_show_name"],
    data: function () {
        return {
            t_data: this.data
        };
    },
    methods: {
        on_change: function (item) {
            logger.debug("tag-tpl 切换为:", item);
            // 当值发生改变时调用,触发一个事件，此事件由可由父组件监听。
            this.$emit('on_tag_tpl_value_change', item);
        }
    },
    computed:{
        getShowName(){
            if (this.t_data !== undefined
                && this.t_data != null
                && this.t_data !== {}
                && this.t_data[this.data_show_name])
                return this.t_data[this.data_show_name];
            return '（无）';
        }
    }
});