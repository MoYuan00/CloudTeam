/**
 * 一个团队卡片列表的显示
 */
Vue.component('card-team-list-tpl', {
    template: `
    <el-row :gutter="10">
        <template v-for="team in teams" >
            <el-col :lg="5" :md="8" :sm="10" :xs="24" style="max-width: 350px">
                <card-team-tpl :team="team"></card-team-tpl>
            </el-col>
        </template>
    </el-row>
    `,
    props: ['teams'],
});
