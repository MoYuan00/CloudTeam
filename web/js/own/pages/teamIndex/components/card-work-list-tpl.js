// 一组任务卡片的显示组件
Vue.component("card-work-list-tpl", {
    template: `
      <el-row :gutter="10">
        <template v-for="work in works">
            <el-col :lg="6" :md="8" :sm="14" :xs="24" style="max-width: 350px;min-width: 270px;">
                <card-work-tpl @delete-work-success="delete_work"  :work="work">
                </card-work-tpl>
            </el-col>
        </template>
    </el-row>
  `,
    props: ["works"],
    mounted(){
    },
    methods:{
        // 响应式从vue中删除当前删除的工作，避免重新请求
        delete_work(work){

            // this.$forceUpdate();// 刷新vue 的 dom显示。
            this.$emit('delete-work-success', work);
        }
    }
});
