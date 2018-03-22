package com.mj.utils.fragment.cp;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mj.utils.R;
import com.mj.utils.activity.cp.HistoryDetailActivity;
import com.mj.utils.bean.BaseJson;
import com.mj.utils.bean.CaiPiaoBean;
import com.mj.utils.bean.DeitalBean;
import com.mj.utils.call.ResultCallback;
import com.mj.utils.tools.FastjsonUtils;
import com.mj.utils.tools.NetUtils;
import com.mj.utils.tools.SPUtils;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;
import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlf.basic.uikit.linearlistview.LinearListView;
import com.tlf.basic.uikit.roundview.RoundTextView;
import com.tlf.basic.uikit.roundview.RoundViewDelegate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.okhttp3.Response;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * 今天
 */
public class TrendFragment extends Fragment {

    public static final String TAG = TrendFragment.class.getSimpleName();
    private static final String url = "http://m.cp.360.cn/kaijiang/qkaijiang?r=1458219747840";


    ListView listView;
    AbsCommonAdapter<CaiPiaoBean> adapter;
    List<CaiPiaoBean> list;
    private List<String> nameList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_trend, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        list = new ArrayList<>();
        adapter = new AbsCommonAdapter<CaiPiaoBean>(getActivity(), R.layout.fragment_trend_list_item, list) {
            @Override
            protected void convert(AbsViewHolder holder, final CaiPiaoBean bean, int position) {
                holder.setText(R.id.name, bean.getLotName());
                holder.setText(R.id.date, "开奖日期：" + bean.getDate());
                holder.setText(R.id.qishu, "第：" + bean.getIssue() + "期");
                final LineChartView lineChart = holder.getView(R.id.line_chart);
                final List<PointValue> mPointValues = new ArrayList<>();
                String[] split = bean.getBalls().replace("+", " ").split(" ");
                final List<String> numList = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    String str = split[i];
                    numList.add(str);
                    mPointValues.add(new PointValue(i, Integer.parseInt(str)));
                }
                holder.setOnClickListener(R.id.itme_1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id()) || "120029".equals(bean.getTag_id())) {
                            deital(bean);
                        }
                    }
                });
                holder.setOnClickListener(R.id.line_chart, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id()) || "120029".equals(bean.getTag_id())) {
                            deital(bean);
                        }
                    }

                });
                initLineChart(lineChart, mPointValues);//初始化
                LinearListView numListView = holder.getView(R.id.num_list);


                AbsCommonAdapter adapter1 = new AbsCommonAdapter<String>(getActivity(), R.layout.fragment_num_list_item, numList) {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    protected void convert(AbsViewHolder holder, String s, int position) {
                        RoundTextView name = holder.getView(R.id.num);
                        RoundViewDelegate delegate = name.getDelegate();
                        name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id()) || "120029".equals(bean.getTag_id())) {
                                    deital(bean);
                                }
                            }
                        });
                        name.setText(s);
                        if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id())) {
                            if (position >= numList.size() - 1) {
                                delegate.setBackgroundColor(mContext.getColor(R.color.blue));
                            } else {
                                delegate.setBackgroundColor(mContext.getColor(R.color.red));
                            }

                        } else if ("120029".equals(bean.getTag_id())) {
                            if (position >= numList.size() - 2) {
                                delegate.setBackgroundColor(mContext.getColor(R.color.blue));
                            } else {
                                delegate.setBackgroundColor(mContext.getColor(R.color.red));
                            }

                        } else {
                            delegate.setBackgroundColor(mContext.getColor(R.color.red));
                        }
                    }
                };
                numListView.setAdapter(adapter1);
            }
        };
        listView.setAdapter(adapter);
        getDate();
        return view;
    }

    DeitalBean jsonList;

    public void deital(final CaiPiaoBean bean) {
        OkHttpUtils.get().url("http://m.cp.360.cn/kaijiang/qkj?lotId=" + bean.getTag_id() + "&issue=" + bean.getIssue()).build().execute(new ResultCallback(getActivity()) {
            @Override
            public void onCusResponse(BaseJson response) {
            }

            @Override
            public BaseJson parseNetworkResponse(Response response) throws Exception {
                String string = response.body().string();
                BaseJson jsonBean = new BaseJson();
                try {
                    jsonList = FastjsonUtils.parseToObjectBean(string, DeitalBean.class);

                } catch (Exception var5) {
                    var5.printStackTrace();
                }
                return jsonBean;
            }

            @Override
            public void onResponse(BaseJson response) {
                Intent intent = new Intent(getActivity(), HistoryDetailActivity.class);
                intent.putExtra("bean", (Serializable) jsonList);
                getActivity().startActivity(intent);
            }
        });
    }


    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(LineChartView lineChart, List<PointValue> mPointValues) {
        Line line = new Line(mPointValues).setColor(R.color.text).setCubic(false);  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(true);//曲线是否平滑
        line.setFilled(true);//是否填充曲线的面积
//      line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(R.color.text);  //设置字体颜色
        axisX.setName("走势");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7);  //最多几个X轴坐标
        data.setAxisXBottom(axisX); //x 轴在底部
//      data.setAxisXTop(axisX);  //x 轴在顶部

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        axisY.setTextColor(R.color.text);
        axisY.setTextSize(9);//设置字体大小

        data.setAxisYLeft(axisY);  //Y轴设置在左边
//      data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }


    public void getDate() {
        nameList = new ArrayList<>();
        nameList.add("220051");
        nameList.add("120029");
        nameList.add("220028");
        nameList.add("166406");
        nameList.add("168009");
        nameList.add("165707");
        nameList.add("223515");
        nameList.add("166507");
        nameList.add("165207");
        nameList.add("166907");
        nameList.add("167607");
        nameList.add("165407");
        String string = getDefultJson();
        try {
            list.clear();
            for (String str : nameList) {
                list.add(setCaiPiaoBean(string, str));
            }
            if (!"".equals(string)) {
                SPUtils.putString(getActivity(), "json", string);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        if (NetUtils.isConnected(getActivity())) {
            OkHttpUtils.get().url(url).build().execute(new ResultCallback(getActivity()) {
                @Override
                public void onCusResponse(BaseJson response) {
                }

                @Override
                public BaseJson parseNetworkResponse(Response response) throws Exception {
                    String string = response.body().string();
                    BaseJson jsonBean = new BaseJson();
                    try {
                        list.clear();
                        for (String str : nameList) {
                            list.add(setCaiPiaoBean(string, str));
                        }
                        if (!"".equals(string)) {
                            SPUtils.putString(getActivity(), "json", string);
                        }
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                    return jsonBean;
                }

                @Override
                public void onResponse(BaseJson response) {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    public CaiPiaoBean setCaiPiaoBean(String json, String name) {
        String jsonStr = FastjsonUtils.getKeyResult(json, name);
        CaiPiaoBean caiPiaoBean = FastjsonUtils.parseToObjectBean(jsonStr, CaiPiaoBean.class);
        if (null != caiPiaoBean) {
            caiPiaoBean.setTag_id(name);
        }
        return caiPiaoBean;
    }

    public String getDefultJson() {
        return "{\n" +
                "\t\"220051\": {\n" +
                "\t\t\"issue\": \"2018031\",\n" +
                "\t\t\"lotName\": \"双色球\",\n" +
                "\t\t\"balls\": \"02 16 18 19 27 30+14\",\n" +
                "\t\t\"date\": \"2018-03-20\",\n" +
                "\t\t\"index\": \"1\"\n" +
                "\t},\n" +
                "\t\"120029\": {\n" +
                "\t\t\"issue\": \"2018032\",\n" +
                "\t\t\"lotName\": \"大乐透\",\n" +
                "\t\t\"balls\": \"07 10 17 32 35+05 12\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"2\"\n" +
                "\t},\n" +
                "\t\"220028\": {\n" +
                "\t\t\"issue\": \"2018032\",\n" +
                "\t\t\"lotName\": \"七乐彩\",\n" +
                "\t\t\"balls\": \"13 15 16 18 21 26 29+28\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"20\"\n" +
                "\t},\n" +
                "\t\"110022\": {\n" +
                "\t\t\"issue\": \"2018031\",\n" +
                "\t\t\"lotName\": \"七星彩\",\n" +
                "\t\t\"balls\": \"3708681\",\n" +
                "\t\t\"date\": \"2018-03-20\",\n" +
                "\t\t\"index\": \"19\"\n" +
                "\t},\n" +
                "\t\"210053\": {\n" +
                "\t\t\"issue\": \"2018073\",\n" +
                "\t\t\"lotName\": \"福彩3D\",\n" +
                "\t\t\"balls\": \"275+676\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"5\"\n" +
                "\t},\n" +
                "\t\"110033\": {\n" +
                "\t\t\"issue\": \"2018073\",\n" +
                "\t\t\"lotName\": \"排列三\",\n" +
                "\t\t\"balls\": \"748\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"6\"\n" +
                "\t},\n" +
                "\t\"110035\": {\n" +
                "\t\t\"issue\": \"2018073\",\n" +
                "\t\t\"lotName\": \"排列五\",\n" +
                "\t\t\"balls\": \"74819\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"7\"\n" +
                "\t},\n" +
                "\t\"166406\": {\n" +
                "\t\t\"issue\": \"2018032243\",\n" +
                "\t\t\"lotName\": \"11选5\",\n" +
                "\t\t\"balls\": \"06 04 03 08 11\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"32\"\n" +
                "\t},\n" +
                "\t\"168009\": {\n" +
                "\t\t\"issue\": \"2018032239\",\n" +
                "\t\t\"lotName\": \"新11选5\",\n" +
                "\t\t\"balls\": \"07 05 10 01 08\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"33\"\n" +
                "\t},\n" +
                "\t\"255401\": {\n" +
                "\t\t\"issue\": \"180322057\",\n" +
                "\t\t\"lotName\": \"老时时彩\",\n" +
                "\t\t\"balls\": \"97929\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"21\"\n" +
                "\t},\n" +
                "\t\"258203\": {\n" +
                "\t\t\"issue\": \"2018032247\",\n" +
                "\t\t\"lotName\": \"新快3\",\n" +
                "\t\t\"balls\": \"226\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"42\"\n" +
                "\t},\n" +
                "\t\"130011\": {\n" +
                "\t\t\"issue\": \"2018039\",\n" +
                "\t\t\"lotName\": \"胜负彩\",\n" +
                "\t\t\"balls\": \"13303000133133\",\n" +
                "\t\t\"date\": \"2018-03-18\",\n" +
                "\t\t\"index\": \"15\"\n" +
                "\t},\n" +
                "\t\"165707\": {\n" +
                "\t\t\"issue\": \"2018032239\",\n" +
                "\t\t\"lotName\": \"粤11选5\",\n" +
                "\t\t\"balls\": \"11 02 04 06 10\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"34\"\n" +
                "\t},\n" +
                "\t\"257503\": {\n" +
                "\t\t\"issue\": \"2018032235\",\n" +
                "\t\t\"lotName\": \"快3\",\n" +
                "\t\t\"balls\": \"136\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"40\"\n" +
                "\t},\n" +
                "\t\"255903\": {\n" +
                "\t\t\"issue\": \"2018032242\",\n" +
                "\t\t\"lotName\": \"老快3\",\n" +
                "\t\t\"balls\": \"122\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"41\"\n" +
                "\t},\n" +
                "\t\"255803\": {\n" +
                "\t\t\"issue\": \"2018032225\",\n" +
                "\t\t\"lotName\": \"好运快3\",\n" +
                "\t\t\"balls\": \"256\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"43\"\n" +
                "\t},\n" +
                "\t\"257703\": {\n" +
                "\t\t\"issue\": \"2018032239\",\n" +
                "\t\t\"lotName\": \"湖北快3\",\n" +
                "\t\t\"balls\": \"256\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"44\"\n" +
                "\t},\n" +
                "\t\"223515\": {\n" +
                "\t\t\"issue\": \"2018073\",\n" +
                "\t\t\"lotName\": \"15选5\",\n" +
                "\t\t\"balls\": \"03 04 09 12 14+05 06\",\n" +
                "\t\t\"date\": \"2018-03-21\",\n" +
                "\t\t\"index\": \"39\"\n" +
                "\t},\n" +
                "\t\"166407\": {\n" +
                "\t\t\"issue\": \"2018032243\",\n" +
                "\t\t\"lotName\": \"快乐扑克\",\n" +
                "\t\t\"balls\": \"27 40 3K\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"47\"\n" +
                "\t},\n" +
                "\t\"166507\": {\n" +
                "\t\t\"issue\": \"2018032246\",\n" +
                "\t\t\"lotName\": \"幸运11选5\",\n" +
                "\t\t\"balls\": \"06 03 05 08 04\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"36\"\n" +
                "\t},\n" +
                "\t\"165207\": {\n" +
                "\t\t\"issue\": \"2018032240\",\n" +
                "\t\t\"lotName\": \"上海11选5\",\n" +
                "\t\t\"balls\": \"04 01 05 11 03\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"38\"\n" +
                "\t},\n" +
                "\t\"166907\": {\n" +
                "\t\t\"issue\": \"2018032241\",\n" +
                "\t\t\"lotName\": \"辽宁11选5\",\n" +
                "\t\t\"balls\": \"06 02 05 04 11\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"37\"\n" +
                "\t},\n" +
                "\t\"265108\": {\n" +
                "\t\t\"issue\": \"878347\",\n" +
                "\t\t\"lotName\": \"快乐8\",\n" +
                "\t\t\"balls\": \"02 09 18 21 24 33 36 38 46 47 51 57 58 64 67 70 71 74 76 78+03\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"46\"\n" +
                "\t},\n" +
                "\t\"167607\": {\n" +
                "\t\t\"issue\": \"2018032234\",\n" +
                "\t\t\"lotName\": \"快乐11选5\",\n" +
                "\t\t\"balls\": \"04 08 02 06 10\",\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"index\": \"35\"\n" +
                "\t},\n" +
                "\t\"165407\": {\n" +
                "\t\t\"issue\": \"2015020558\",\n" +
                "\t\t\"lotName\": \"重庆11选5\",\n" +
                "\t\t\"balls\": \"05 09 06 01 02\",\n" +
                "\t\t\"date\": \"2015-02-05\",\n" +
                "\t\t\"index\": \"\"\n" +
                "\t},\n" +
                "\t\"130042\": {\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"lotName\": \"竞彩足球\",\n" +
                "\t\t\"index\": \"3\"\n" +
                "\t},\n" +
                "\t\"130041\": {\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"lotName\": \"北京单场\",\n" +
                "\t\t\"index\": \"17\"\n" +
                "\t},\n" +
                "\t\"130043\": {\n" +
                "\t\t\"date\": \"2018-03-22\",\n" +
                "\t\t\"lotName\": \"竞彩篮球\",\n" +
                "\t\t\"index\": \"12\"\n" +
                "\t}\n" +
                "}";
    }
}
