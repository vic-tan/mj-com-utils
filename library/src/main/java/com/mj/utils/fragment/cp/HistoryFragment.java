package com.mj.utils.fragment.cp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mj.utils.R;
import com.mj.utils.activity.cp.HistoryActivity;
import com.mj.utils.bean.BaseJson;
import com.mj.utils.bean.CaiPiaoBean;
import com.mj.utils.bean.HistoryBean;
import com.mj.utils.call.ResultCallback;
import com.mj.utils.tools.FastjsonUtils;
import com.mj.utils.tools.SPUtils;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;
import com.tlf.basic.http.okhttp.OkHttpUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.okhttp3.Response;


/**
 * 今天
 */
public class HistoryFragment extends Fragment {

    public static final String TAG = HistoryFragment.class.getSimpleName();
    private static final String url = "http://m.cp.360.cn/kaijiang/qkjlist?lotId=";
    ListView listView;
    AbsCommonAdapter<CaiPiaoBean> adapter;
    List<CaiPiaoBean> list;
    List<HistoryBean> liSiList;
    private List<String> nameList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_history, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        list = new ArrayList<>();
        liSiList = new ArrayList<>();
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
        String string = SPUtils.getString(getActivity(),"json");
        for (String str : nameList) {
            list.add(setCaiPiaoBean(string, str));
        }
        adapter = new AbsCommonAdapter<CaiPiaoBean>(getActivity(), R.layout.fragment_history_list_item, list) {
            @Override
            protected void convert(AbsViewHolder holder, final CaiPiaoBean bean, int position) {
                holder.setText(R.id.name, bean.getLotName());
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OkHttpUtils.get().url(url + bean.getTag_id() + "&page=1").build().execute(new ResultCallback(getActivity()) {
                            @Override
                            public void onCusResponse(BaseJson response) {
                            }

                            @Override
                            public BaseJson parseNetworkResponse(Response response) throws Exception {
                                String string = response.body().string();
                                BaseJson jsonBean = new BaseJson();
                                try {
                                    liSiList.clear();
                                    String listStr = FastjsonUtils.getKeyResult(string, "list");
                                    List<HistoryBean> jsonList = FastjsonUtils.parseToObjectList(listStr, HistoryBean.class);
                                    if (null != jsonList && jsonList.size() >= 0) {
                                        liSiList.addAll(jsonList);
                                    }
                                } catch (Exception var5) {
                                    var5.printStackTrace();
                                }
                                return jsonBean;
                            }

                            @Override
                            public void onResponse(BaseJson response) {
                                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                                intent.putExtra("list", (Serializable) liSiList);
                                intent.putExtra("bean", (Serializable) bean);
                                getActivity().startActivity(intent);
                            }
                        });
                    }
                });
            }
        };
        listView.setAdapter(adapter);
        return view;
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
