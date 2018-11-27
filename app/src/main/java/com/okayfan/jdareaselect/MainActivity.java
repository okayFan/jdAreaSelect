package com.okayfan.jdareaselect;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.pedaily.yc.ycdialoglib.dialogFragment.BottomDialogFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <pre>
 *     author : fanYangXiao
 *     time   : 2018/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainActivity extends AppCompatActivity {

    public static Area  area;
    public BottomDialogFragment dialogFragment = null;


    public XTabLayout tabLayout;
    public LimitSlideDirectionViewPager vp_area;
    public String mProvince;
    public String mCity;
    public String mArea;
    public LinearLayout tabStrip;

    private Button bAreaSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAreaSelect = findViewById(R.id.b_area_select);
        getJsonFromAssets();
        bAreaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAreaDialog();
            }
        });
    }





    private void getJsonFromAssets(){
        StringBuilder newstringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = getResources().getAssets().open("area.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                newstringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result =  newstringBuilder .toString();
        Log.d("json",result);
        Gson gson = new Gson();
        area = gson.fromJson(result, Area.class);
    }



    public void updateArea(){
        bAreaSelect.setText(mProvince + mCity + mArea);
        dialogFragment.dismiss();
    }



    private void showAreaDialog(){
        dialogFragment = BottomDialogFragment.create(getSupportFragmentManager())
                .setViewListener(v -> initAreaView(v))
                .setLayoutRes(R.layout.dialog_jd_area)
                .setDimAmount(0.5f)
                .setTag("BottomDialog")
                .setCancelOutside(true)
                .setHeight(DensityUtil.dip2px(this, 500));
        dialogFragment.show();
    }



    private void initAreaView(View v){
        vp_area = v.findViewById(R.id.vp_area);
        vp_area.setAllowedSwipeDirection(SwipeDirection.LEFT);
        tabLayout= v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("请选择"));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        tabStrip.getChildAt(1).setClickable(false);
        tabStrip.getChildAt(2).setClickable(false);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AreaFragment.newInstance(AreaFragment.PROVINCE));
        fragmentList.add(AreaFragment.newInstance(AreaFragment.CITY));
        fragmentList.add(AreaFragment.newInstance(AreaFragment.AREA));
        AreaViewPageAdapter areaViewPageAdapter = new AreaViewPageAdapter(dialogFragment.getChildFragmentManager(),fragmentList);
        vp_area.setAdapter(areaViewPageAdapter);
        vp_area.setOffscreenPageLimit(3);
        tabLayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        vp_area.setCurrentItem(0);
                        break;
                    case 1:
                        vp_area.setCurrentItem(1);
                        break;
                    case 2:
                        vp_area.setCurrentItem(2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
        vp_area.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        tabLayout.getTabAt(0).select();
                        if (tabLayout.getTabAt(0).getText().equals("请选择")){
                            vp_area.setAllowedSwipeDirection(SwipeDirection.LEFT);
                        }else {
                            vp_area.setAllowedSwipeDirection(SwipeDirection.ALL);
                        }
                        break;
                    case 1:
                        tabLayout.getTabAt(1).select();
                        if (tabLayout.getTabAt(1).getText().equals("请选择")){
                            vp_area.setAllowedSwipeDirection(SwipeDirection.LEFT);
                        }else {
                            vp_area.setAllowedSwipeDirection(SwipeDirection.ALL);
                        }
                        break;
                    case 2:
                        tabLayout.getTabAt(2).select();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



}
