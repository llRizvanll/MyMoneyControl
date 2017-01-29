package com.mymoneycontrol.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mymoneycontrol.android.R;
import com.mymoneycontrol.android.adapters.MainListAdapter;
import com.mymoneycontrol.android.database.model.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrizvan on 1/28/17.
 */

public class PageFragment extends Fragment {
    public static final String TAG = "PageFragment";
    private List<DataItem> dataItemList = new ArrayList<DataItem>();
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public List<DataItem> getDataItemList() {
        return this.dataItemList;
    }

    public void setDataItemList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listadapter_listview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        MainListAdapter adapter = new MainListAdapter(this.context, getDataItemList());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
