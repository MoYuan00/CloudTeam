Vue.component('card-team-tpl', {
    template: `
<div>

        <el-card @click.native="onclick"
        class="team-card" shadow="hover" style="margin-bottom: 10px;height: 160px;cursor: pointer">
            <div slot="header">
                <span class="font-weight-bold">{{team.team_name}}</span>
                <el-button @click="on_click_team_more"  icon="el-icon-setting" class="float-right"
                   style="padding: 3px 0" type="text">
                   编辑
                </el-button>
            </div>
            <div class="text-truncate">
                <p class="el-icon-s-order">
                {{team.team_detail ? team.team_detail : '此团队的人都很懒，没有简介！'}}  
                </p>
            </div>
             <div class="text-truncate el-icon-date">
               <small>
               {{team.team_build_time}}
               </small>
            </div>
            <div type="text" class="float-right">
                <small>
                    进入<span class="el-icon-arrow-right"></span>
                </small>
            </div>
        </el-card>
       
</div>
    `,
    props: ['team'],
    methods: {
        onclick: function (team) {
            // 当团队卡片被点击的时候触发
            Event.$emit('team_card_clicked', this.team);
        },
        on_click_team_more(event){
            event.stopPropagation();
            Event.$emit('show-more-team-dialog', this.team);
        }
    }

});
