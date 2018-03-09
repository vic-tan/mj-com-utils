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

    public CaiPiaoBean setCaiPiaoBean(String json, String name) {
        String jsonStr = FastjsonUtils.getKeyResult(json, name);
        CaiPiaoBean caiPiaoBean = FastjsonUtils.parseToObjectBean(jsonStr, CaiPiaoBean.class);
        if (null != caiPiaoBean) {
            caiPiaoBean.setTag_id(name);
        }
        return caiPiaoBean;
    }
}
