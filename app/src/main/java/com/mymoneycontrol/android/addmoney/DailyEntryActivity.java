package com.mymoneycontrol.android.addmoney;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.stetho.Stetho;
import com.mymoneycontrol.android.MainActivity;
import com.mymoneycontrol.android.R;
import com.mymoneycontrol.android.database.model.DataItem;
import io.realm.Realm;
import io.realm.RealmResults;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jrizvan on 1/28/17.
 */

public class DailyEntryActivity extends AppCompatActivity {
    final Integer ACTIONUP = 1;
    final Integer ACTIONDOWN = 2;
    Activity activity;
    DailyEntryModel dailyEntryModel = new DailyEntryModel();

    @Bind(R.id.currencycount1_layout)
    LinearLayout currencyCount1Layout;
    @Bind(R.id.currencycount2_layout)
    LinearLayout currencyCount2Layout;
    @Bind(R.id.currencycount3_layout)
    LinearLayout currencyCount3Layout;
    @Bind(R.id.currencycount4_layout)
    LinearLayout currencyCount4Layout;
    @Bind(R.id.currencycount5_layout)
    LinearLayout currencyCount5Layout;
    @Bind(R.id.currencycount6_layout)
    LinearLayout currencyCount6Layout;


    @Bind(R.id.currencycount1_count)
    TextView curOneCountTextView;
    @Bind(R.id.currencycount2_count)
    TextView curTwoCountTextView;
    @Bind(R.id.currencycount3_count)
    TextView curThreeCountTextView;
    @Bind(R.id.currencycount4_count)
    TextView curFourCountTextView;
    @Bind(R.id.currencycount5_count)
    TextView curFiveCountTextView;
    @Bind(R.id.currencycount6_count)
    TextView curSixCountTextView;

    @Bind(R.id.currency_img_1)
    ImageView currencyImage1;
    @Bind(R.id.currency_img_2)
    ImageView currencyImage2;
    @Bind(R.id.currency_img_3)
    ImageView currencyImage3;
    @Bind(R.id.currency_img_4)
    ImageView currencyImage4;
    @Bind(R.id.currency_img_5)
    ImageView currencyImage5;
    @Bind(R.id.currency_img_6)
    ImageView currencyImage6;


    @Bind(R.id.currency_total_count_view)
    TextView currencyTotalView;
    private Realm realmInstance;

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

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

    @OnClick(R.id.currency_img_4)
    public void currencyFourClicked() {
        currencyCount4Layout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.currency_img_5)
    public void currencyFiveClicked() {
        currencyCount5Layout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.currency_img_6)
    public void currencySixClicked() {
        currencyCount6Layout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyentry_main);
        activity = DailyEntryActivity.this;
        ButterKnife.bind(activity);
        // Obtain a Realm instance
        realmInstance = Realm.getDefaultInstance();
        //Inspect elements & debugger support
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        loadImages();
    }

    private void loadImages() {
        loadBitmap(R.drawable.teninr_new, currencyImage1);
        loadBitmap(R.drawable.inr_new_twenty, currencyImage2);
        loadBitmap(R.drawable.inr_new_fifty, currencyImage3);
        loadBitmap(R.drawable.inr_new_hundred, currencyImage4);
        loadBitmap(R.drawable.fivehinr_new, currencyImage5);
        loadBitmap(R.drawable.twokinr_new, currencyImage6);
    }

    // Count click Validation
    private Boolean countValidation(Integer count) {
        return count > -1;
    }

    @OnClick(R.id.currencycount1_up)
    public void curCountOneUpClicked() {
        setValue(curOneCountTextView, ACTIONUP, DailyEntryModel.one);
    }

    @OnClick(R.id.currencycount1_down)
    public void curCountOneDownClicked() {
        setValue(curOneCountTextView, ACTIONDOWN, DailyEntryModel.one);
    }

    @OnClick(R.id.currencycount2_up)
    public void curCountTwoUpClicked() {
        setValue(curTwoCountTextView, ACTIONUP, DailyEntryModel.two);
    }

    @OnClick(R.id.currencycount2_down)
    public void curCountTwoDownClicked() {
        setValue(curTwoCountTextView, ACTIONDOWN, DailyEntryModel.two);
    }

    @OnClick(R.id.currencycount3_up)
    public void curCountThreeUpClicked() {
        setValue(curThreeCountTextView, ACTIONUP, DailyEntryModel.three);
    }

    @OnClick(R.id.currencycount3_down)
    public void curCountThreeDownClicked() {
        setValue(curThreeCountTextView, ACTIONDOWN, DailyEntryModel.three);
    }

    @OnClick(R.id.currencycount4_up)
    public void curCountFourUpClicked() {
        setValue(curFourCountTextView, ACTIONUP, DailyEntryModel.four);
    }

    @OnClick(R.id.currencycount4_down)
    public void curCountFourDownClicked() {
        setValue(curFourCountTextView, ACTIONDOWN, DailyEntryModel.four);
    }

    @OnClick(R.id.currencycount5_up)
    public void curCountFiveUpClicked() {
        setValue(curFiveCountTextView, ACTIONUP, DailyEntryModel.five);
    }

    @OnClick(R.id.currencycount5_down)
    public void curCountFiveDownClicked() {
        setValue(curFiveCountTextView, ACTIONDOWN, DailyEntryModel.five);
    }

    @OnClick(R.id.currencycount6_up)
    public void curCountSixUpClicked() {
        setValue(curSixCountTextView, ACTIONUP, DailyEntryModel.SIX);
    }

    @OnClick(R.id.currencycount6_down)
    public void curCountSixDownClicked() {
        setValue(curSixCountTextView, ACTIONDOWN, DailyEntryModel.SIX);
    }

    private void setValue(TextView textView, Integer action, Integer type) {

        if (textView.getText().toString() != null && (action == ACTIONUP || Integer.parseInt(textView.getText().toString()) > 0)) {
            Integer count = Integer.parseInt(textView.getText().toString());
            if (action == ACTIONUP) {
                count = count + 1;
            } else
                count = count - 1;

            textView.setText("" + count);

            if (countValidation(count)) {
                dailyEntryModel.setValue(action, type);
            }
            updateTotal();
            Snackbar.make(textView, "Total Amount :  " + dailyEntryModel.getCurInputMap().get(DailyEntryModel.total), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }
    }

    public void updateTotal() {
        currencyTotalView.setText(" " + dailyEntryModel.getCurInputMap().get(DailyEntryModel.total));

    }

    @OnClick(R.id.currency_save_view)
    public void saveAction() {

        try {
            realmInstance.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    HashMap<Integer, Double> map = dailyEntryModel.getCurInputMap();
                    DataItem item = realm.createObject(DataItem.class);
                    for (Integer integer : map.keySet()) {
                        if (integer != DailyEntryModel.total) {
                            item.setAmountType(map.get(integer).toString());
                            item.setQuantity(map.get(integer));
                        }
                    }
                    item.setId(new Random().nextInt(1000));
                    item.setTotalAmount(map.get(DailyEntryModel.total));
                    item.setUpdatedTime(System.currentTimeMillis());
                }
            });
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        RealmResults<DataItem> dataResult = realmInstance.where(DataItem.class).findAll();
        Log.d("TAG", dataResult.size() + " --- " + dataResult.get(0).getTotalAmount());
        /*realmInstance.commitTransaction();*/
        Snackbar.make(currencyCount1Layout, " Total Amount :  " + dailyEntryModel.getCurInputMap().get(DailyEntryModel.total), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        startActivity(new Intent(activity, MainActivity.class));
        finish();
    }

    public void loadBitmap(int resId, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(getResources(), data, 200, 200);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
