package com.mymoneycontrol.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mymoneycontrol.android.R;
import com.mymoneycontrol.android.database.model.DataItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jrizvan on 1/29/17.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ListViewHolder> {

    private List<DataItem> dataItems = new ArrayList<DataItem>();
    private Context context;

    public MainListAdapter(Context context, List<DataItem> userDataList) {
        this.context = context;
        this.dataItems = userDataList;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        DataItem item = this.dataItems.get(position);
        holder.totalCountView.setText(item.getTotalAmount().toString());
        try {
            getFormatData(item.getUpdatedTime(), holder.dateTimeView);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_layout_item1, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.dataItems.size();
    }

    public void getFormatData(Long date, TextView textView) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date givenDate = simpleDateFormat.parse(date.toString());
        Log.d("TIME", givenDate.toString());
        textView.setText(givenDate.toString());
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView totalCountView;
        public TextView dateTimeView;

        public ListViewHolder(View view) {
            super(view);
            totalCountView = (TextView) view.findViewById(R.id.main_layout_item1_total);
            dateTimeView = (TextView) view.findViewById(R.id.main_layout_item1_date);
        }
    }

}
