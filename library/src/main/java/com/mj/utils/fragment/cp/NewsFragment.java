package com.mj.utils.fragment.cp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.mj.utils.R;
import com.mj.utils.activity.WebViewActivity;
import com.mj.utils.bean.NewsBean;
import com.mj.utils.tools.FastjsonUtils;
import com.mj.utils.tools.GetDateUtils;
import com.squareup.picasso.Picasso;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 今天
 */
public class NewsFragment extends Fragment {

    public static final String TAG = NewsFragment.class.getSimpleName();
    public static final String url = "http://c.m.163.com/nc/article/headline/T1356600029035/0-20.html";


    ListView listView;
    AbsCommonAdapter<NewsBean> adapter;
    List<NewsBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_news, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        list = new ArrayList<>();
        adapter = new AbsCommonAdapter<NewsBean>(getActivity(), R.layout.list_item_news, list) {
            @Override
            protected void convert(AbsViewHolder holder, final NewsBean bean, int position) {
                ImageView cover = holder.getView(R.id.cover);
                Picasso.with(mContext)
                        .load(bean.getImgsrc()).resize(100, 90)
                        .centerCrop()
                        .into(cover);
                holder.setText(R.id.title, bean.getTitle());
                holder.setText(R.id.desc, bean.getDigest());
                holder.setText(R.id.date, bean.getPtime().substring(0, 10));
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra(GetDateUtils.URL,"http://sports.163.com/18/0112/07/D7UE2V33000597U8.html");
                        startActivity(intent);
                    }
                });
            }
        };
        listView.setAdapter(adapter);
        getDate();
        return view;
    }


    public void getDate() {
        list.clear();
        String jsonStr = FastjsonUtils.getKeyResult(getDataStr(), "T1356600029035");
        List<NewsBean> caiPiaoBean = FastjsonUtils.parseToObjectList(jsonStr, NewsBean.class);
        if (null != caiPiaoBean && caiPiaoBean.size() > 0) {
            list.addAll(caiPiaoBean);
        }

        adapter.notifyDataSetChanged();
    }

    public String getDataStr() {
        return "{\"T1356600029035\":[{\"template\":\"normal1\",\"lmodify\":\"2018-03-22 11:23:30\",\"source\":\"网易彩票\",\"postid\":\"DDG55HSN000597U8\",\"title\":\"体彩即开票可自助兑奖 互联网彩票充满无限可能\",\"mtime\":\"2018-03-22 08:12:55\",\"hasImg\":1,\"topic_background\":\"http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348648727071.jpg\",\"digest\":\"体彩顶呱刮是国家体彩中心推出的即开型体育彩票于2008年上市，具有即中即兑、中奖率高、玩法丰富等特点，购彩者可以在所有体彩网点、部分商场、超市和便利店等购买到顶\",\"boardid\":\"sports2_bbs\",\"alias\":\"Lottery\",\"hasAD\":1,\"imgsrc\":\"http://cms-bucket.nosdn.127.net/e36fc1e990d54fadba8fce4f9b5aaffd20180322081254.jpeg\",\"ptime\":\"2018-03-22 08:12:18\",\"daynum\":\"17612\",\"hasHead\":1,\"order\":1,\"votecount\":82,\"hasCover\":false,\"docid\":\"DDG55HSN000597U8\",\"tname\":\"彩票\",\"url_3w\":\"http://sports.163.com/18/0322/08/DDG55HSN000597U8.html\",\"priority\":150,\"url\":\"http://3g.163.com/sports/18/0322/08/DDG55HSN000597U8.html\",\"ename\":\"caipiao\",\"replyCount\":92,\"ltitle\":\"体彩即开票可自助兑奖 互联网彩票充满无限可能\",\"hasIcon\":false,\"subtitle\":\"\",\"cid\":\"C1348648727071\"},{\"votecount\":236,\"docid\":\"DDG37PPG000597U8\",\"lmodify\":\"2018-03-22 08:34:49\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG37PPG000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG37PPG000597U8\",\"priority\":149,\"title\":\"吉林彩民中1000万头奖 大乐透奖池首次破52亿\",\"mtime\":\"2018-03-22 08:13:29\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG37PPG000597U8.html\",\"replyCount\":274,\"ltitle\":\"吉林彩民中1000万头奖 大乐透奖池首次破52亿\",\"subtitle\":\"\",\"digest\":\"3月21日晚，体彩大乐透18032期开出1注头奖，奖金为1000万元，落在吉林省。当期全国销售额为2.11亿元，筹集彩票公益金7610万多元，开奖过后，奖池滚存\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/a6eea120b2424cfcaa187899fde1560b20180322081328.jpeg\",\"ptime\":\"2018-03-22 07:38:34\",\"daynum\":\"17612\"},{\"votecount\":655,\"docid\":\"DDGBQQLT000597U8\",\"lmodify\":\"2018-03-22 10:10:30\",\"url_3w\":\"http://sports.163.com/18/0322/10/DDGBQQLT000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGBQQLT000597U8\",\"priority\":148,\"title\":\"刚刚 吉林彩民中1000万 出票时间距停售仅10分钟\",\"mtime\":\"2018-03-22 10:10:30\",\"url\":\"http://3g.163.com/sports/18/0322/10/DDGBQQLT000597U8.html\",\"replyCount\":708,\"ltitle\":\"刚刚 吉林彩民中1000万 出票时间距停售仅10分钟\",\"subtitle\":\"\",\"digest\":\"3月21日晚，体彩大乐透18032期开出1注头奖，奖金为1000万元，落在吉林省。当期全国销售额为2.11亿元，筹集彩票公益金7610万多元，开奖过后，奖池滚存\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/4a10f223c0ec4f8fb04a6e61a2deb50e20180322101028.jpeg\",\"ptime\":\"2018-03-22 10:08:47\",\"daynum\":\"17612\"},{\"imgextra\":[{\"imgsrc\":\"http://cms-bucket.nosdn.127.net/62bd45f0bb0b4c71a6e136eb3dbf0ecc20171106163906.jpeg\"},{\"imgsrc\":\"http://cms-bucket.nosdn.127.net/19152b0589b746cfa2e7ead376ad4afc20171106163906.jpeg\"}],\"votecount\":0,\"docid\":\"D2IS2D7H000597UK\",\"lmodify\":\"2018-03-22 08:15:01\",\"url_3w\":\"http://sports.163.com/17/1106/16/D2IS2D7H000597UK.html\",\"source\":\"网易彩票\",\"postid\":\"D2IS2D7H000597UK\",\"priority\":148,\"title\":\"花2元就中了74万大奖 原来竟然是靠它\",\"mtime\":\"2018-03-22 08:15:01\",\"url\":\"http://3g.163.com/sports/17/1106/16/D2IS2D7H000597UK.html\",\"replyCount\":0,\"ltitle\":\"花2元就中了74万大奖 原来竟然是靠它\",\"articleType\":\"webview\",\"subtitle\":\"\",\"digest\":\"点击继续查看使用安卓和iPhone最新版本客户端可获得更流畅体验，下载地址：安卓用户点这里iPhone用户点这里\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/707fa10f9fc542e09a17c3c115cd7f2c20171106163906.jpeg\",\"ptime\":\"2017-11-06 16:38:21\",\"daynum\":\"17612\"},{\"votecount\":100,\"docid\":\"DDGPAF3E000597U8\",\"lmodify\":\"2018-03-22 14:05:04\",\"url_3w\":\"http://sports.163.com/18/0322/14/DDGPAF3E000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGPAF3E000597U8\",\"priority\":147,\"title\":\"这类彩票游戏开奖存猫腻?真相:官方可能还赔钱\",\"mtime\":\"2018-03-22 14:05:04\",\"url\":\"http://3g.163.com/sports/18/0322/14/DDGPAF3E000597U8.html\",\"replyCount\":102,\"ltitle\":\"这类彩票游戏开奖存猫腻?真相:官方可能还赔钱\",\"subtitle\":\"\",\"digest\":\"关于彩票开奖有一个奇怪的现象，彩票机构在乐透游戏的开奖方面不遗余力，斥巨资购买了最先进的摇奖机，冒着巨大风险开通了网上摇奖直播，但是质疑的声音却从未间断过。相反\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/7c756c56bf494d94bc16c6fe05baae3420180322140503.jpeg\",\"ptime\":\"2018-03-22 14:04:30\",\"daynum\":\"17612\"},{\"votecount\":0,\"docid\":\"DDGH21IL000597U8\",\"lmodify\":\"2018-03-22 11:43:47\",\"url_3w\":\"http://sports.163.com/18/0322/11/DDGH21IL000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGH21IL000597U8\",\"priority\":147,\"title\":\"18岁厨房学徒领走200000元大奖:之前从未中过\",\"mtime\":\"2018-03-22 11:43:47\",\"url\":\"http://3g.163.com/sports/18/0322/11/DDGH21IL000597U8.html\",\"replyCount\":1,\"ltitle\":\"18岁厨房学徒领走200000元大奖:之前从未中过\",\"subtitle\":\"\",\"digest\":\"????3月19日一早，上班时间刚到，市福彩中心兑奖处就迎来一对稚气的小伙子，其中一位手里扬着一张彩票，兴高采烈地说：“我中奖啦，我中奖啦！”在场的工作人员被他\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/7df70328b23d4b858e7afd99bbae54b520180322114346.jpeg\",\"ptime\":\"2018-03-22 11:40:06\",\"daynum\":\"17612\"},{\"votecount\":136,\"docid\":\"DDGETCVU000597U8\",\"lmodify\":\"2018-03-22 11:03:18\",\"url_3w\":\"http://sports.163.com/18/0322/11/DDGETCVU000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGETCVU000597U8\",\"priority\":147,\"title\":\"中国杯-国足爆冷击败威尔士?博彩公司观点很明确\",\"mtime\":\"2018-03-22 11:03:18\",\"url\":\"http://3g.163.com/sports/18/0322/11/DDGETCVU000597U8.html\",\"replyCount\":176,\"ltitle\":\"中国杯-国足爆冷击败威尔士?博彩公司观点很明确\",\"subtitle\":\"\",\"digest\":\"北京时间3月22日19时35分，2018“中国杯”迎来揭幕战，中国男足将与“欧洲红龙”威尔士队上演焦点对决。此次杯赛是国足自去年12月打完东亚杯之后第一次亮相，\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/9f6eac24cbd0411e9a18931d51bcc3d620180322110317.jpeg\",\"ptime\":\"2018-03-22 11:02:37\",\"daynum\":\"17612\"},{\"votecount\":231,\"docid\":\"DDG9D3R4000597U8\",\"lmodify\":\"2018-03-22 09:26:45\",\"url_3w\":\"http://sports.163.com/18/0322/09/DDG9D3R4000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG9D3R4000597U8\",\"priority\":147,\"title\":\"CBA-北京死磕辽宁扳2-2?博彩公司力挺这一赛果\",\"mtime\":\"2018-03-22 09:26:45\",\"url\":\"http://3g.163.com/sports/18/0322/09/DDG9D3R4000597U8.html\",\"replyCount\":259,\"ltitle\":\"CBA-北京死磕辽宁扳2-2?博彩公司力挺这一赛果\",\"subtitle\":\"\",\"digest\":\"北京时间3月22日19:35，CBA季后赛1/4决赛继续展开争夺，北京首钢男篮回到主场对阵辽宁男篮。北京队上场苦战双加时遗憾落败，今晚对双方来说均是天王山之战，\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/5b8e84d168024f17bef1739c61c6288720180322092643.jpeg\",\"ptime\":\"2018-03-22 09:26:20\",\"daynum\":\"17612\"},{\"votecount\":55,\"docid\":\"DDG43LQO000597U8\",\"lmodify\":\"2018-03-22 08:13:46\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG43LQO000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG43LQO000597U8\",\"priority\":147,\"title\":\"彩民凭感觉选号 结果一不小心就中了1000万\",\"mtime\":\"2018-03-22 08:13:46\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG43LQO000597U8.html\",\"replyCount\":64,\"ltitle\":\"彩民凭感觉选号 结果一不小心就中了1000万\",\"subtitle\":\"\",\"digest\":\"3月5日，体彩大乐透第18025期开奖，北京一位幸运儿喜中1注1000万元头奖。3月9日上午，中奖者房先生来到北京体彩中心办理了兑奖手续。只身前来兑奖的房先生在\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/ecd7c1bd380745db8c2eb62e16d49e1120180322081344.jpeg\",\"ptime\":\"2018-03-22 07:53:48\",\"daynum\":\"17612\"},{\"votecount\":221,\"docid\":\"DDG3VIQ8000597U8\",\"lmodify\":\"2018-03-22 10:10:12\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG3VIQ8000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG3VIQ8000597U8\",\"priority\":147,\"title\":\"救治家人负债数十万 河源大叔中361万如释重负\",\"mtime\":\"2018-03-22 10:10:12\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG3VIQ8000597U8.html\",\"replyCount\":232,\"ltitle\":\"救治家人负债数十万 河源大叔中361万如释重负\",\"subtitle\":\"\",\"digest\":\"新快报讯记者陆妍思通讯员黄振平报道救治家人负债数十万,七星彩大奖送来新希望。3月9日一早,经过几日的等待,七星彩第18024期的2注头奖得主,终于现身河源市体彩\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/f78325337b0545628a90970c1b35338f20180322081403.jpeg\",\"ptime\":\"2018-03-22 07:51:34\",\"daynum\":\"17612\"},{\"votecount\":29,\"docid\":\"DDG3EBKA000597U8\",\"lmodify\":\"2018-03-22 08:14:58\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG3EBKA000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG3EBKA000597U8\",\"priority\":147,\"title\":\"济南30人合买中130000元 称下次要中一等奖\",\"mtime\":\"2018-03-22 08:14:58\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG3EBKA000597U8.html\",\"replyCount\":41,\"ltitle\":\"济南30人合买中130000元 称下次要中一等奖\",\"subtitle\":\"\",\"digest\":\"乐透幸运季，喜讯天天送！随着“买体彩大乐透复式6+3、8+3，你中奖我买单”活动的火爆开展，济南彩民的合买情绪越来越高涨，各个大大小小的合买团如雨后春笋般在泉城\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/24750557e69747c6aacbc3e630ff6ffd20180322081457.jpeg\",\"ptime\":\"2018-03-22 07:42:09\",\"daynum\":\"17612\"},{\"votecount\":0,\"docid\":\"DDGH7289000597U8\",\"lmodify\":\"2018-03-22 11:43:06\",\"url_3w\":\"http://sports.163.com/18/0322/11/DDGH7289000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGH7289000597U8\",\"priority\":146,\"title\":\"1024万元!厦门彩民收获福建今年第一个千万巨奖\",\"mtime\":\"2018-03-22 11:43:06\",\"url\":\"http://3g.163.com/sports/18/0322/11/DDGH7289000597U8.html\",\"replyCount\":2,\"ltitle\":\"1024万元!厦门彩民收获福建今年第一个千万巨奖\",\"subtitle\":\"厦门彩民收获第一个千万巨奖\",\"digest\":\"3月20日晚，中国福利彩票双色球游戏进行第2018031期开奖。当期双色球红球号码为02、16、18、19、27、30，蓝球号码为14。今天是春分，老话说：“吃\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"\",\"ptime\":\"2018-03-22 11:42:50\",\"daynum\":\"17612\"},{\"votecount\":66,\"docid\":\"DDGH5M3N000597U8\",\"lmodify\":\"2018-03-22 11:44:31\",\"url_3w\":\"http://sports.163.com/18/0322/11/DDGH5M3N000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGH5M3N000597U8\",\"priority\":146,\"title\":\"彩民905倍投注揽大奖 占当期全省中奖总额82%\",\"mtime\":\"2018-03-22 11:44:31\",\"url\":\"http://3g.163.com/sports/18/0322/11/DDGH5M3N000597U8.html\",\"replyCount\":82,\"ltitle\":\"彩民905倍投注揽大奖 占当期全省中奖总额82%\",\"subtitle\":\"\",\"digest\":\"3月9日，福彩3D第2018061期开奖，开奖号码为525，位于三明市泰宁县公路大厦一楼二号店的福彩35081206投注站中出905注单选奖，总奖金达94.12\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/728827e9ee6a4dd2a8a29b0e31279d4720180322114430.jpeg\",\"ptime\":\"2018-03-22 11:42:05\",\"daynum\":\"17612\"},{\"votecount\":102,\"docid\":\"DDGH3OVK000597U8\",\"lmodify\":\"2018-03-22 11:44:49\",\"url_3w\":\"http://sports.163.com/18/0322/11/DDGH3OVK000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGH3OVK000597U8\",\"priority\":146,\"title\":\"委托家人买自选号不中 家人自作主张机选反中奖\",\"mtime\":\"2018-03-22 11:44:49\",\"url\":\"http://3g.163.com/sports/18/0322/11/DDGH3OVK000597U8.html\",\"replyCount\":104,\"ltitle\":\"委托家人买自选号不中 家人自作主张机选反中奖\",\"subtitle\":\"\",\"digest\":\"临时被安排出差，无暇购彩，他托家人买了一张10元的双色球彩票，却意外收获了106471元二等奖。3月15日上午，胡先生现身浙江宁波市福彩中心，领走了属于他的意外\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/bf30f8d7cf8e498a8356508ed33d92ce20180322114448.jpeg\",\"ptime\":\"2018-03-22 11:41:03\",\"daynum\":\"17612\"},{\"votecount\":30,\"docid\":\"DDGALN47000597U8\",\"lmodify\":\"2018-03-22 11:24:01\",\"url_3w\":\"http://sports.163.com/18/0322/09/DDGALN47000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDGALN47000597U8\",\"priority\":146,\"title\":\"CBA-\\\"还魂\\\"新疆再灭广东?博彩公司刚给出答案\",\"mtime\":\"2018-03-22 09:49:01\",\"url\":\"http://3g.163.com/sports/18/0322/09/DDGALN47000597U8.html\",\"replyCount\":39,\"ltitle\":\"CBA-\\\"还魂\\\"新疆再灭广东?博彩公司刚给出答案\",\"subtitle\":\"CBA-\\\"还魂\\\"新疆再灭广东?\",\"digest\":\"北京时间3月22日19:35，CBA季后赛1/4决赛第四回合，新疆男篮回到主场迎战广东男篮。在所有人都以为新疆就要被0-3横扫出局时，上场凭借着布拉切最后时刻的\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/ec5eedc5e0e944a9b636245248ea7eb420180322094859.jpeg\",\"ptime\":\"2018-03-22 09:48:30\",\"daynum\":\"17612\"},{\"votecount\":184,\"docid\":\"DDG3R0M7000597U8\",\"lmodify\":\"2018-03-22 08:14:18\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG3R0M7000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG3R0M7000597U8\",\"priority\":146,\"title\":\"彩民独中767万元大奖 称不敢相信自己中奖\",\"mtime\":\"2018-03-22 08:14:18\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG3R0M7000597U8.html\",\"replyCount\":203,\"ltitle\":\"彩民独中767万元大奖 称不敢相信自己中奖\",\"subtitle\":\"\",\"digest\":\"人常说百善孝为先，孝顺的人常行善事，运气都不会太差。一对异地来津打拼的姐弟，因为对父母的一颗孝心而中取了双色球767万一等奖，实现了他们把父母接来天津共同生活的\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/1e1bd4498d5247449ec9a79ca28f996020180322081417.jpeg\",\"ptime\":\"2018-03-22 07:49:04\",\"daynum\":\"17612\"},{\"votecount\":100,\"docid\":\"DDG3KOLN000597U8\",\"lmodify\":\"2018-03-22 08:14:30\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG3KOLN000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG3KOLN000597U8\",\"priority\":146,\"title\":\"男子偷6000多元彩票中2000多元 去兑奖被抓\",\"mtime\":\"2018-03-22 08:14:30\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG3KOLN000597U8.html\",\"replyCount\":102,\"ltitle\":\"男子偷6000多元彩票中2000多元 去兑奖被抓\",\"subtitle\":\"\",\"digest\":\"生活报3月21日讯绥化一男子想通过彩票发横财，可他不是买彩票，而是到彩票站偷走了价值6000多元的即开型“刮刮乐”彩票。中了2000多元倒是如他所愿，可是兑奖时\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/036ae8b13fa047d7851a57ca9446621320180322081429.jpeg\",\"ptime\":\"2018-03-22 07:45:39\",\"daynum\":\"17612\"},{\"votecount\":0,\"docid\":\"DDG3IG2L000597U8\",\"lmodify\":\"2018-03-22 08:14:43\",\"url_3w\":\"http://sports.163.com/18/0322/07/DDG3IG2L000597U8.html\",\"source\":\"网易彩票\",\"postid\":\"DDG3IG2L000597U8\",\"priority\":146,\"title\":\"彩民一人独中福彩3D340注 中奖方法被曝光\",\"mtime\":\"2018-03-22 08:14:43\",\"url\":\"http://3g.163.com/sports/18/0322/07/DDG3IG2L000597U8.html\",\"replyCount\":2,\"ltitle\":\"彩民一人独中福彩3D340注 中奖方法被曝光\",\"subtitle\":\"\",\"digest\":\"3月8日晚，福彩3D开出直选奖号767；我省当期总共中出直选922注；而在这922注中，我省武汉市东西湖区一位老彩民，就独揽了其中的340注，成为当期最大的赢家\",\"boardid\":\"sports2_bbs\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/catchpic/2/25/255dff24947de1df8044308f98ffeaf7.jpg\",\"ptime\":\"2018-03-22 07:44:25\",\"pixel\":\"720*960\",\"daynum\":\"17612\"},{\"docid\":\"9IG74V5H00963VRO_D1MJGL0AzhangwandongupdateDoc\",\"skipID\":\"S1509008835125\",\"lmodify\":\"2018-03-22 08:15:04\",\"source\":\"网易原创\",\"title\":\"竞彩专家预测-最精准的分析助你红单收米!\",\"mtime\":\"2018-03-22 08:15:04\",\"priority\":142,\"specialID\":\"S1509008835125\",\"skipType\":\"special\",\"digest\":\"\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/713311f357374d21a41a1d5c6314dbf920171026170827.jpeg\",\"ptime\":\"2017-10-26 17:10:06\",\"daynum\":\"17612\"},{\"docid\":\"9IG74V5H00963VRO_D1MESL4Nzhangzhe1updateDoc\",\"skipID\":\"S1509007262461\",\"lmodify\":\"2018-03-22 08:15:05\",\"source\":\"网易原创\",\"title\":\"大乐透专家预测-拿下1600万大奖更轻松\",\"mtime\":\"2018-03-22 08:15:05\",\"priority\":142,\"specialID\":\"S1509007262461\",\"skipType\":\"special\",\"digest\":\"\",\"imgsrc\":\"http://cms-bucket.nosdn.127.net/b7c453c1feaa459dbd5603edc471596e20171026154812.jpeg\",\"ptime\":\"2017-10-26 15:49:17\",\"daynum\":\"17612\"}]}";
    }


}
