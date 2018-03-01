package com.mj.utils.activity.cp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mj.utils.R;
import com.mj.utils.bean.DeitalBean;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;
import com.tlf.basic.uikit.linearlistview.LinearListView;
import com.tlf.basic.uikit.roundview.RoundTextView;
import com.tlf.basic.uikit.roundview.RoundViewDelegate;

import java.util.ArrayList;
import java.util.List;


/**
 * 关于
 */
public class HistoryDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    RoundTextView name;
    TextView date;
    TextView qishu;
    LinearListView numListView;
    TextView money;
    TextView sale;
    LinearListView list;
    private DeitalBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        name = (RoundTextView) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        qishu = (TextView) findViewById(R.id.qishu);
        money = (TextView) findViewById(R.id.money);
        sale = (TextView) findViewById(R.id.sale);
        list = (LinearListView) findViewById(R.id.list);
        numListView = (LinearListView) findViewById(R.id.num_list);
        try {
            bean = (DeitalBean) getIntent().getSerializableExtra("bean");
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            name.setText(bean.getLotName());
            date.setText("开奖日期：" + bean.getDate());
            money.setText("奖池金额：" + bean.getMoney() + "元");
            sale.setText("本期销售：" + bean.getSale() + "元");
            qishu.setText("第：" + bean.getIssue() + "期");
            String[] split = bean.getCode().replace("+", " ").split(" ");
            final List<String> numList = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                String str = split[i];
                numList.add(str);
            }
            AbsCommonAdapter adapter1 = new AbsCommonAdapter<String>(this, R.layout.fragment_num_list_item, numList) {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                protected void convert(AbsViewHolder holder, String s, int position) {
                    RoundTextView name = holder.getView(R.id.num);
                    RoundViewDelegate delegate = name.getDelegate();
                    name.setText(s);
                    if ("220051".equals(bean.getLotId()) || "220028".equals(bean.getLotId())) {
                        if (position >= numList.size() - 1) {
                            delegate.setBackgroundColor(mContext.getColor(R.color.blue));
                        } else {
                            delegate.setBackgroundColor(mContext.getColor(R.color.red));
                        }

                    } else if ("120029".equals(bean.getLotId())) {
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

            AbsCommonAdapter adapter2 = new AbsCommonAdapter<DeitalBean.LevelBean>(this, R.layout.list_item_two, bean.getLevel()) {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                protected void convert(AbsViewHolder holder, DeitalBean.LevelBean s, int position) {
                    holder.setText(R.id.name_1, s.getName());
                    holder.setText(R.id.name_2, s.getCount());
                    holder.setText(R.id.name_3, s.getFund());
                }
            };
            list.setAdapter(adapter2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}