package com.mymoneycontrol.android.addmoney;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.mymoneycontrol.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jrizvan on 1/28/17.
 */

public class DailyEntryActivity extends AppCompatActivity {

    Activity activity;
    @Bind(R.id.currencycount1_layout)
    LinearLayout currencyCount1Layout;
    @Bind(R.id.currencycount2_layout)
    LinearLayout currencyCount2Layout;
    @Bind(R.id.currencycount3_layout)
    LinearLayout currencyCount3Layout;

    @OnClick(R.id.currency_img_1)
    public void currencyOneClicked() {
        currencyCount1Layout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.currency_img_2)
    public void currencyTwoClicked() {
        currencyCount2Layout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.currency_img_3)
    public void currencyThreeClicked() {
        currencyCount3Layout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyentry_main);
        activity = DailyEntryActivity.this;
        ButterKnife.bind(activity);
    }

}
