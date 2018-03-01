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
//        OkHttpUtils.post().url(url).build().execute(new ResultCallback(getActivity()) {
//            @Override
//            public void onCusResponse(BaseJson baseJson) {
//
//            }
//            @Override
//            public BaseJson parseNetworkResponse(Response response) throws Exception {
//                String string = response.body().string();
//                BaseJson jsonBean = new BaseJson();
//                try {
//                    jsonBean.setData(string);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return jsonBean;
//            }
//
//            @Override
//            public void onResponse(BaseJson response) {
//                list.clear();
//                String jsonStr = FastjsonUtils.getKeyResult(response.getData().toString(), "T1356600029035");
//                List<ZiXuBean> caiPiaoBean = FastjsonUtils.parseToObjectList(jsonStr, ZiXuBean.class);
//                if (null != caiPiaoBean && caiPiaoBean.size() > 0) {
//                    list.addAll(caiPiaoBean);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Call call, Exception e) {
//                super.onError(call, e);
//                e.printStackTrace();
//            }
//        });
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
        return "{\n" +
                "    \"T1356600029035\": [\n" +
                "        {\n" +
                "            \"template\": \"normal1\",\n" +
                "            \"lmodify\": \"2018-01-16 11:02:27\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NQPUJ000597U8\",\n" +
                "            \"title\": \"佛山彩民戴面具火速领走920万  照常上下班\",\n" +
                "            \"mtime\": \"2018-01-16 07:51:59\",\n" +
                "            \"hasImg\": 1,\n" +
                "            \"topic_background\": \"http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348648727071.jpg\",\n" +
                "            \"digest\": \"2018年1月3日，广东佛山体彩“大乐透”第17152期头奖得主郭先生变身“面具侠”火速领走920万元。2017年12月27日“体彩大乐透”第17152期其中1\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"alias\": \"Lottery\",\n" +
                "            \"hasAD\": 1,\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/02421e677c584b5e99566217bd42577320180116075156.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:46:04\",\n" +
                "            \"daynum\": \"17547\",\n" +
                "            \"hasHead\": 1,\n" +
                "            \"order\": 1,\n" +
                "            \"wap_pluginfo\": [\n" +
                "                {\n" +
                "                    \"subtitle\": \"Winning Numbers\",\n" +
                "                    \"title\": \"开奖号码\",\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/7ce5ba9155e8434aa9e59188dd71d73a20161207120415.png\",\n" +
                "                    \"animation_icon\": \"\",\n" +
                "                    \"url\": \"http://caipiao.163.com/t/award/?from=newscp\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"subtitle\": \"Lottery Ticket\",\n" +
                "                    \"title\": \"购彩大厅\",\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/0debc4d3cb5a45f5a150397392c6301620161207115844.png\",\n" +
                "                    \"animation_icon\": \"\",\n" +
                "                    \"url\": \"http://game.cp.163.com/redEnvelopeActiveIndex.html\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"votecount\": 129,\n" +
                "            \"hasCover\": false,\n" +
                "            \"docid\": \"D88NQPUJ000597U8\",\n" +
                "            \"tname\": \"彩票\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NQPUJ000597U8.html\",\n" +
                "            \"priority\": 150,\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NQPUJ000597U8.html\",\n" +
                "            \"ename\": \"caipiao\",\n" +
                "            \"replyCount\": 168,\n" +
                "            \"ltitle\": \"佛山彩民戴面具火速领走920万  照常上下班\",\n" +
                "            \"hasIcon\": false,\n" +
                "            \"subtitle\": \"佛山彩民戴面具火速领走920万\",\n" +
                "            \"cid\": \"C1348648727071\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 113,\n" +
                "            \"docid\": \"D88NNN44000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:02:35\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NNN44000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NNN44000597U8\",\n" +
                "            \"priority\": 149,\n" +
                "            \"title\": \"昨夜大乐透开出2注1600万 奖池45.04亿再创新高\",\n" +
                "            \"mtime\": \"2018-01-16 07:52:34\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NNN44000597U8.html\",\n" +
                "            \"replyCount\": 157,\n" +
                "            \"ltitle\": \"昨夜大乐透开出2注1600万 奖池45.04亿再创新高\",\n" +
                "            \"subtitle\": \"昨夜大乐透开出2注1600万\",\n" +
                "            \"digest\": \"1月15日晚，体彩大乐透第18007期开奖，开奖号码前区为“03、04、28、31、34”；后区号码为“04、11”。本期一等奖全国共开出2注，单注奖金1000\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/a0d979c573df4b58912a315fc5e861d020180116075231.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:44:23\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 1,\n" +
                "            \"docid\": \"D88NU59D000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:02:45\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NU59D000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NU59D000597U8\",\n" +
                "            \"priority\": 148,\n" +
                "            \"title\": \"1246万得主领奖:单次花费超200 分别票打十余张\",\n" +
                "            \"mtime\": \"2018-01-16 07:51:39\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NU59D000597U8.html\",\n" +
                "            \"replyCount\": 6,\n" +
                "            \"ltitle\": \"1246万得主领奖:单次花费超200 分别票打十余张\",\n" +
                "            \"subtitle\": \"1246万得主:单次花费超200\",\n" +
                "            \"digest\": \"1月10日晚，体彩大乐透第18005期开奖。本期全国中出6注头奖。其中，1注为1241万元(含465万余元追加奖金)追加投注头奖，出自上海静安区虬江路911号，\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/17233bc73dbe4fdbb840fb373e552d2020180116075134.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:47:54\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"imgextra\": [\n" +
                "                {\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/62bd45f0bb0b4c71a6e136eb3dbf0ecc20171106163906.jpeg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/19152b0589b746cfa2e7ead376ad4afc20171106163906.jpeg\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"votecount\": 0,\n" +
                "            \"docid\": \"D2IS2D7H000597UK\",\n" +
                "            \"lmodify\": \"2018-01-16 11:01:44\",\n" +
                "            \"url_3w\": \"http://sports.163.com/17/1106/16/D2IS2D7H000597UK.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D2IS2D7H000597UK\",\n" +
                "            \"priority\": 148,\n" +
                "            \"title\": \"花2元就中了74万大奖 原来竟然是靠它\",\n" +
                "            \"mtime\": \"2018-01-16 11:01:44\",\n" +
                "            \"url\": \"http://3g.163.com/sports/17/1106/16/D2IS2D7H000597UK.html\",\n" +
                "            \"replyCount\": 0,\n" +
                "            \"ltitle\": \"花2元就中了74万大奖 原来竟然是靠它\",\n" +
                "            \"articleType\": \"webview\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"点击继续查看使用安卓和iPhone最新版本客户端可获得更流畅体验，下载地址：安卓用户点这里iPhone用户点这里\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/707fa10f9fc542e09a17c3c115cd7f2c20171106163906.jpeg\",\n" +
                "            \"ptime\": \"2017-11-06 16:38:21\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 38,\n" +
                "            \"docid\": \"D8949F6V000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:25:49\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/11/D8949F6V000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D8949F6V000597U8\",\n" +
                "            \"priority\": 147,\n" +
                "            \"title\": \"昨夜大乐透奖池超45亿后 我们发现背后三大规律\",\n" +
                "            \"mtime\": \"2018-01-16 11:25:49\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/11/D8949F6V000597U8.html\",\n" +
                "            \"replyCount\": 54,\n" +
                "            \"ltitle\": \"昨夜大乐透奖池超45亿后 我们发现背后三大规律\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"昨夜大乐透奖池突破45亿元，创历史新高。本期《网易数说》我们将探讨一个问题：就是大乐透奖池突破5亿、10亿、15亿&hellip;&hellip;都是那些号码，\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/283e904f764d4d2087cc52e17cd6fde620180116112542.png\",\n" +
                "            \"ptime\": \"2018-01-16 11:23:48\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 10,\n" +
                "            \"docid\": \"D89468CR000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:26:02\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/11/D89468CR000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D89468CR000597U8\",\n" +
                "            \"priority\": 147,\n" +
                "            \"title\": \"彩民只有2元就中1000万 称中大奖真没想到！\",\n" +
                "            \"mtime\": \"2018-01-16 11:26:02\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/11/D89468CR000597U8.html\",\n" +
                "            \"replyCount\": 11,\n" +
                "            \"ltitle\": \"彩民只有2元就中1000万 称中大奖真没想到！\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"销售员打错彩票助他赢得1092万张先生玩彩有自己的“套路”，他每天独来独往，从不和投注站销售员、其他彩友交流、切磋；天天看报纸，却从不借鉴别人的中奖经验，更不照\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/eefa55fdc37e4e85836a78709602d50c20180116112557.png\",\n" +
                "            \"ptime\": \"2018-01-16 11:22:02\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 77,\n" +
                "            \"docid\": \"D88NV9LV000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:02:53\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NV9LV000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NV9LV000597U8\",\n" +
                "            \"priority\": 147,\n" +
                "            \"title\": \"得主中593万心里有落差 称倒霉赶上头奖井喷17注\",\n" +
                "            \"mtime\": \"2018-01-16 07:50:48\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NV9LV000597U8.html\",\n" +
                "            \"replyCount\": 84,\n" +
                "            \"ltitle\": \"得主中593万心里有落差 称倒霉赶上头奖井喷17注\",\n" +
                "            \"subtitle\": \"得主中593万心里有落差\",\n" +
                "            \"digest\": \"因为购买复式票中2+0能获得一定金额的赠票，平日喜欢买单式票的85后小伙赖先生趁机玩起了复式票。不曾想到的是，本想趁这个活动体验一下中2+0得赠票的实惠，结果却\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/69e3dad557b74283a532d6102513fa5120180116075043.png\",\n" +
                "            \"ptime\": \"2018-01-16 07:48:31\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 90,\n" +
                "            \"docid\": \"D88NM411000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:03:05\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NM411000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NM411000597U8\",\n" +
                "            \"priority\": 147,\n" +
                "            \"title\": \"63元追加投注中奖1037万 得主:走势图有规律\",\n" +
                "            \"mtime\": \"2018-01-16 07:52:46\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NM411000597U8.html\",\n" +
                "            \"replyCount\": 117,\n" +
                "            \"ltitle\": \"63元追加投注中奖1037万 得主:走势图有规律\",\n" +
                "            \"subtitle\": \"中奖1037万称走势有规律\",\n" +
                "            \"digest\": \"领奖图1月3日，大乐透新年第2期开奖，全国开出一等奖11注，单注基本奖金648万余元。其中，唯一1注追加一等奖被武汉彩民中得，追加后，一等奖单注奖金达1037万\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/4f54dda8e8b9418aad247a25d4ea82df20180116075242.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:43:31\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 0,\n" +
                "            \"docid\": \"D88RTDNI000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:03:14\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/08/D88RTDNI000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88RTDNI000597U8\",\n" +
                "            \"priority\": 146,\n" +
                "            \"title\": \"今晚谁能中1000万头奖?老黄历已道破天机\",\n" +
                "            \"mtime\": \"2018-01-16 09:41:53\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/08/D88RTDNI000597U8.html\",\n" +
                "            \"replyCount\": 3,\n" +
                "            \"ltitle\": \"今晚谁能中1000万头奖?老黄历已道破天机\",\n" +
                "            \"subtitle\": \"老黄历道破1000万得主人选\",\n" +
                "            \"digest\": \"天地玄黄，宇宙洪荒；日月盈昃，辰宿列张。网易彩票黄历宜忌事项，推定每日吉时、吉位、红运生肖，爱好购彩而时难中奖者可多多参考。正所谓：困龙得水好运交，不由喜气上眉\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/4480be7615f34e9eacda2216c9a1e2de20180116094147.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 08:57:24\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 64,\n" +
                "            \"docid\": \"D88QBEN5000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:03:26\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/08/D88QBEN5000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88QBEN5000597U8\",\n" +
                "            \"priority\": 146,\n" +
                "            \"title\": \"彩民一人独中2.81亿元 老板:对中奖者没印象\",\n" +
                "            \"mtime\": \"2018-01-16 08:30:33\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/08/D88QBEN5000597U8.html\",\n" +
                "            \"replyCount\": 78,\n" +
                "            \"ltitle\": \"彩民一人独中2.81亿元 老板:对中奖者没印象\",\n" +
                "            \"subtitle\": \"彩民一人独中2.81亿元\",\n" +
                "            \"digest\": \"网易彩票16日讯据澳大利亚媒体报道，在上周四国家乐透彩票的开奖中，一位彩民独中5500万澳元（约合人民币2.81亿元），澳洲的媒体称这是近一年来最大的一笔奖金。\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/c223825d1f664b7a8ba85c70f3a8f7d820180116083028.png\",\n" +
                "            \"ptime\": \"2018-01-16 08:30:07\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 2,\n" +
                "            \"docid\": \"D88NQMG1000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 07:52:18\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NQMG1000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NQMG1000597U8\",\n" +
                "            \"priority\": 146,\n" +
                "            \"title\": \"\\\"偶尔玩玩\\\"中头奖889万 女彩民表示不懂彩票\",\n" +
                "            \"mtime\": \"2018-01-16 07:52:18\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NQMG1000597U8.html\",\n" +
                "            \"replyCount\": 12,\n" +
                "            \"ltitle\": \"\\\"偶尔玩玩\\\"中头奖889万 女彩民表示不懂彩票\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"2017年12月24日晚，双色球第2017151期开奖，全国中出一等奖6注，单注奖金889万元，被江苏、辽宁、安徽、福建等共5位幸运彩民共同分享。江苏的3注一等\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/bdb13987a54f455b8dfa2fc0be2fa50320180116075214.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:46:01\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 4,\n" +
                "            \"docid\": \"D88NENJ0000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:03:42\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88NENJ0000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88NENJ0000597U8\",\n" +
                "            \"priority\": 146,\n" +
                "            \"title\": \"工薪阶层4元中奖582万 \\\"懒\\\"反而带来好运气\",\n" +
                "            \"mtime\": \"2018-01-16 07:52:57\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88NENJ0000597U8.html\",\n" +
                "            \"replyCount\": 7,\n" +
                "            \"ltitle\": \"工薪阶层4元中奖582万 \\\"懒\\\"反而带来好运气\",\n" +
                "            \"subtitle\": \"工薪阶层4元中奖582万\",\n" +
                "            \"digest\": \"1月2日，在福彩双色球第2018001期开奖中，宁夏银川市一彩民凭借一张4元单式票独揽当期头奖1注，单注奖金582万余元。1月8日，巨奖得主现身宁夏福彩中心兑取\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/6a981338d152475dad8cf6c3bc9c351520180116075254.png\",\n" +
                "            \"ptime\": \"2018-01-16 07:39:28\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 0,\n" +
                "            \"docid\": \"D88O258M000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 11:03:52\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0116/07/D88O258M000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D88O258M000597U8\",\n" +
                "            \"priority\": 145,\n" +
                "            \"title\": \"为何说彩民是最大慈善家?公益捐款献爱心\",\n" +
                "            \"mtime\": \"2018-01-16 07:51:13\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0116/07/D88O258M000597U8.html\",\n" +
                "            \"replyCount\": 3,\n" +
                "            \"ltitle\": \"为何说彩民是最大慈善家?公益捐款献爱心\",\n" +
                "            \"subtitle\": \"为何说彩民是最大慈善家?\",\n" +
                "            \"digest\": \"不少彩民常说，没中奖就当是作公益了。其实这句话不完全对，彩民购彩，不管中奖与否，都是作公益。以体彩大乐透为例，彩民每买一张2元的彩票，就有0.72元进入体彩公益\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/0ac2dd272b8d422b81d0e0b36566670d20180116075109.jpeg\",\n" +
                "            \"ptime\": \"2018-01-16 07:50:05\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"docid\": \"9IG74V5H00963VRO_D1MJGL0AzhangwandongupdateDoc\",\n" +
                "            \"skipID\": \"S1509008835125\",\n" +
                "            \"lmodify\": \"2018-01-16 07:53:03\",\n" +
                "            \"source\": \"网易原创\",\n" +
                "            \"title\": \"竞彩专家预测-最精准的分析助你红单收米!\",\n" +
                "            \"mtime\": \"2018-01-16 07:53:03\",\n" +
                "            \"priority\": 142,\n" +
                "            \"specialID\": \"S1509008835125\",\n" +
                "            \"skipType\": \"special\",\n" +
                "            \"digest\": \"\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/713311f357374d21a41a1d5c6314dbf920171026170827.jpeg\",\n" +
                "            \"ptime\": \"2017-10-26 17:10:06\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"docid\": \"9IG74V5H00963VRO_D1MESL4Nzhangzhe1updateDoc\",\n" +
                "            \"skipID\": \"S1509007262461\",\n" +
                "            \"lmodify\": \"2018-01-16 07:53:05\",\n" +
                "            \"source\": \"网易原创\",\n" +
                "            \"title\": \"大乐透专家预测-拿下1600万大奖更轻松\",\n" +
                "            \"mtime\": \"2018-01-16 07:53:05\",\n" +
                "            \"priority\": 142,\n" +
                "            \"specialID\": \"S1509007262461\",\n" +
                "            \"skipType\": \"special\",\n" +
                "            \"digest\": \"\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/b7c453c1feaa459dbd5603edc471596e20171026154812.jpeg\",\n" +
                "            \"ptime\": \"2017-10-26 15:49:17\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"imgextra\": [\n" +
                "                {\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/d35ba9b2fc7c41b1aad7aa3254d32da620171026152125.jpeg\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imgsrc\": \"http://cms-bucket.nosdn.127.net/6ae099bb3c684bbeb594f5fafce62ff020171026152125.jpeg\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"docid\": \"9IG74V5H00963VRO_D1MD3O3LshenxinghuiupdateDoc\",\n" +
                "            \"skipID\": \"S1509001788330\",\n" +
                "            \"lmodify\": \"2018-01-16 07:53:07\",\n" +
                "            \"source\": \"网易原创\",\n" +
                "            \"title\": \"双色球专家预测\",\n" +
                "            \"mtime\": \"2018-01-16 07:53:07\",\n" +
                "            \"priority\": 142,\n" +
                "            \"specialID\": \"S1509001788330\",\n" +
                "            \"skipType\": \"special\",\n" +
                "            \"digest\": \"\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/b417afdcf00a40219308e4527e67f41720171026152125.jpeg\",\n" +
                "            \"ptime\": \"2017-10-26 15:18:12\",\n" +
                "            \"daynum\": \"17547\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 81,\n" +
                "            \"docid\": \"D86IBD22000597U8\",\n" +
                "            \"lmodify\": \"2018-01-15 11:36:30\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0115/11/D86IBD22000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D86IBD22000597U8\",\n" +
                "            \"priority\": 150,\n" +
                "            \"title\": \"一号之差错失6850万 新年前6期此省狂爆18注头奖\",\n" +
                "            \"mtime\": \"2018-01-15 11:36:30\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0115/11/D86IBD22000597U8.html\",\n" +
                "            \"replyCount\": 101,\n" +
                "            \"ltitle\": \"一号之差错失6850万 新年前6期此省狂爆18注头奖\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"昨晚是双色球新年第6期开奖，全国中出11注单注奖金为685万元的一等奖，浙江省彩民虽然暂时与一等奖无缘，但中得了14注二等奖，其中杭州一彩民独中10注，获148\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/1a755c2802104c459f766afbfbe13df420180115113629.jpeg\",\n" +
                "            \"ptime\": \"2018-01-15 11:31:48\",\n" +
                "            \"daynum\": \"17546\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"votecount\": 480,\n" +
                "            \"docid\": \"D87J4U4U000597U8\",\n" +
                "            \"lmodify\": \"2018-01-16 10:07:03\",\n" +
                "            \"url_3w\": \"http://sports.163.com/18/0115/21/D87J4U4U000597U8.html\",\n" +
                "            \"source\": \"网易彩票\",\n" +
                "            \"postid\": \"D87J4U4U000597U8\",\n" +
                "            \"priority\": 149,\n" +
                "            \"title\": \"刚刚 云南彩民或独中3200万 奖池终突破45亿元\",\n" +
                "            \"mtime\": \"2018-01-15 21:05:20\",\n" +
                "            \"url\": \"http://3g.163.com/sports/18/0115/21/D87J4U4U000597U8.html\",\n" +
                "            \"replyCount\": 569,\n" +
                "            \"ltitle\": \"刚刚 云南彩民或独中3200万 奖池终突破45亿元\",\n" +
                "            \"subtitle\": \"\",\n" +
                "            \"digest\": \"1月15日晚，中国体育彩票超级大乐透游戏进行了第18007期开奖，网易彩票第一时间带来本期开奖快讯：大乐透18007期开奖号码：0304283134+0411本\",\n" +
                "            \"boardid\": \"sports2_bbs\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/57be79c3a2b9482da47f0dc9398712a320180115210515.png\",\n" +
                "            \"ptime\": \"2018-01-15 21:04:59\",\n" +
                "            \"daynum\": \"17546\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"docid\": \"9IG74V5H00963VRO_D86SSVL2bjzhanglong1updateDoc\",\n" +
                "            \"skipID\": \"168681\",\n" +
                "            \"lmodify\": \"2018-01-15 14:36:10\",\n" +
                "            \"source\": \"\",\n" +
                "            \"title\": \" 20:30直播体彩1月15日开奖\",\n" +
                "            \"mtime\": \"2018-01-15 14:36:10\",\n" +
                "            \"priority\": 149,\n" +
                "            \"skipType\": \"live\",\n" +
                "            \"digest\": \"\",\n" +
                "            \"TAG\": \"直播回顾\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/07fc62040ebd492497b1aff8752cd37e20180115143543.jpeg\",\n" +
                "            \"ptime\": \"2018-01-15 14:36:10\",\n" +
                "            \"TAGS\": \"直播回顾 视频\",\n" +
                "            \"live_info\": {\n" +
                "                \"start_time\": \"2018-01-15 20:28:00\",\n" +
                "                \"user_count\": 146580,\n" +
                "                \"pano\": false,\n" +
                "                \"mutilVideo\": false,\n" +
                "                \"end_time\": \"2018-01-15 21:00:00\",\n" +
                "                \"video\": true,\n" +
                "                \"type\": 0,\n" +
                "                \"roomName\": \"【网易彩票】1月15日体彩开奖直播\",\n" +
                "                \"roomId\": 168681\n" +
                "            },\n" +
                "            \"daynum\": \"17546\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"docid\": \"9IG74V5H00963VRO_D86SQVLUbjzhanglong1updateDoc\",\n" +
                "            \"skipID\": \"168680\",\n" +
                "            \"lmodify\": \"2018-01-15 14:35:04\",\n" +
                "            \"source\": \"\",\n" +
                "            \"title\": \" 21:15直播福彩1月15日开奖\",\n" +
                "            \"mtime\": \"2018-01-15 14:35:04\",\n" +
                "            \"priority\": 149,\n" +
                "            \"skipType\": \"live\",\n" +
                "            \"digest\": \"\",\n" +
                "            \"TAG\": \"直播回顾\",\n" +
                "            \"imgsrc\": \"http://cms-bucket.nosdn.127.net/6cd00fad60214bfa9ee0dd92d8c0dc5420180115143444.jpeg\",\n" +
                "            \"ptime\": \"2018-01-15 14:35:04\",\n" +
                "            \"TAGS\": \"直播回顾 视频\",\n" +
                "            \"live_info\": {\n" +
                "                \"start_time\": \"2018-01-15 21:15:00\",\n" +
                "                \"user_count\": 63633,\n" +
                "                \"pano\": false,\n" +
                "                \"mutilVideo\": false,\n" +
                "                \"end_time\": \"2018-01-15 23:55:00\",\n" +
                "                \"video\": true,\n" +
                "                \"type\": 0,\n" +
                "                \"roomName\": \"【网易彩票】1月15日福彩开奖直播\",\n" +
                "                \"roomId\": 168680\n" +
                "            },\n" +
                "            \"daynum\": \"17546\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }


}
