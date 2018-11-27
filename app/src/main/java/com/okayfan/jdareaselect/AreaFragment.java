package com.okayfan.jdareaselect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * author: FYx
 * date:   On 2018/11/14
 */
public class AreaFragment extends Fragment {

    @BindView(R.id.rv_area)
    RecyclerView rvArea;

    public RxBusUtil rxBus;

    private int type;
    public static final int PROVINCE = 0x1;//省
    public static final int CITY = 0x2;//市
    public static final int AREA = 0x3;//区
    private AreaAdapter areaAdapter;
    MainActivity personInfoActivity;



    public static AreaFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        AreaFragment fragment = new AreaFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_area, container, false);
        Unbinder unbinder = ButterKnife.bind(this, mContentView);
        initView();
        initListener();
        return mContentView;
    }









    protected void initView() {
        type = getArguments().getInt("type");
        personInfoActivity = (MainActivity) getActivity();
        rvArea.setLayoutManager(new LinearLayoutManager(getActivity()));
        areaAdapter = new AreaAdapter();
        rvArea.setAdapter(areaAdapter);
        List<Area.DataBean.AreasBeanXX> provinceList = MainActivity.area.getData().getAreas();
        areaAdapter.setNewData(changeToAreasBean1(MainActivity.area.getData().getAreas()));
    }






    protected void initListener() {
        registerRxBus(rxBusMessage -> {
            if (type == AREA){
                if (rxBusMessage.what == RxBusMessage.GET_AREA){
                    for (Area.DataBean.AreasBeanXX areasBeanXX:MainActivity.area.getData().getAreas()){
                        if (areasBeanXX.getAreaId().equals(rxBusMessage.obj)){
                            for (Area.DataBean.AreasBeanXX.AreasBeanX areasBeanX:areasBeanXX.getAreas()){
                                if (areasBeanX.getAreaId().equals(rxBusMessage.msg)){
                                    areaAdapter.setNewData(areasBeanX.getAreas());
                                }
                            }
                        }
                    }
                }
            }else if (type == CITY){
                if (rxBusMessage.what == RxBusMessage.GET_CITY){
                    for (Area.DataBean.AreasBeanXX areasBeanXX:MainActivity.area.getData().getAreas()){
                        if (areasBeanXX.getAreaId().equals(rxBusMessage.msg)){
                            areaAdapter.setNewData(changeToAreasBean2(areasBeanXX.getAreas(),areasBeanXX.getAreaId()));
                        }
                    }
                }else if (rxBusMessage.what == RxBusMessage.CLEAR_CITY_SELECT){
                    areaAdapter.selectPosition = -1;
                    areaAdapter.notifyDataSetChanged();
                }
            }
        });
        areaAdapter.setOnItemClickListener((adapter, view, position) -> {
            Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean areaBean = areaAdapter.getData().get(position);
            switch (type){
                case PROVINCE:
                    if (!TextUtils.isEmpty(personInfoActivity.mProvince)){
                        personInfoActivity.tabLayout.getTabAt(2).setText("");
                        personInfoActivity.tabStrip.getChildAt(2).setClickable(false);
                        RxBusUtil.getIntanceBus().post(new RxBusMessage(RxBusMessage.CLEAR_CITY_SELECT));
                    }
                    RxBusUtil.getIntanceBus().post(new RxBusMessage(RxBusMessage.GET_CITY,areaBean.getAreaId()));
                    personInfoActivity.mProvince = areaBean.getAreaName();
                    if (areaBean.getAreaName().length() > 3){
                        personInfoActivity.tabLayout.getTabAt(0).setText(areaBean.getAreaName().substring(0,3)+"...");
                    }else {
                        personInfoActivity.tabLayout.getTabAt(0).setText(areaBean.getAreaName());
                    }
                    personInfoActivity.tabLayout.getTabAt(1).setText("请选择");
                    personInfoActivity.tabLayout.getTabAt(1).select();
                    personInfoActivity.tabStrip.getChildAt(1).setClickable(true);
                    personInfoActivity.vp_area.setCurrentItem(1);
                    areaAdapter.selectPosition = position;
                    adapter.notifyDataSetChanged();
                    break;
                case CITY:
                    RxBusUtil.getIntanceBus().post(new RxBusMessage(RxBusMessage.GET_AREA,areaBean.getAreaId(),areaBean.getParentId()));
                    personInfoActivity.mCity = areaBean.getAreaName();
                    if (areaBean.getAreaName().length() > 3){
                        personInfoActivity.tabLayout.getTabAt(1).setText(areaBean.getAreaName().substring(0,3)+"...");
                    }else {
                        personInfoActivity.tabLayout.getTabAt(1).setText(areaBean.getAreaName());
                    }
                    personInfoActivity.tabLayout.getTabAt(2).setText("请选择");
                    personInfoActivity.tabLayout.getTabAt(2).select();
                    personInfoActivity.tabStrip.getChildAt(2).setClickable(true);
                    personInfoActivity.vp_area.setCurrentItem(2);
                    areaAdapter.selectPosition = position;
                    adapter.notifyDataSetChanged();
                    break;
                case AREA:
                    personInfoActivity.mArea = areaBean.getAreaName();
                    personInfoActivity.updateArea();
                    break;
            }
        });
    }



    /**
     * 转省
     * @param list
     * @return
     */
    private ArrayList<Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean> changeToAreasBean1(List<Area.DataBean.AreasBeanXX> list){
        ArrayList<Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean> areasBeans = new ArrayList<>();
        for (Object o:list){
            Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean areasBean = new Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean();
            areasBean.setAreaName(((Area.DataBean.AreasBeanXX) o).getAreaName());
            areasBean.setAreaId(((Area.DataBean.AreasBeanXX) o).getAreaId());
            areasBeans.add(areasBean);
        }
        return areasBeans;
    }



    /**
     * 转市
     * @param list
     * @return
     */
    private ArrayList<Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean> changeToAreasBean2(List<Area.DataBean.AreasBeanXX.AreasBeanX> list,String parentId){
        ArrayList<Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean> areasBeans = new ArrayList<>();
        for (Object o:list){
            Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean areasBean = new Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean();
            areasBean.setAreaName(((Area.DataBean.AreasBeanXX.AreasBeanX) o).getAreaName());
            areasBean.setAreaId(((Area.DataBean.AreasBeanXX.AreasBeanX) o).getAreaId());
            areasBean.setParentId(parentId);
            areasBeans.add(areasBean);
        }
        return areasBeans;
    }




    //注册
    public <T> void registerRxBus(Consumer<RxBusMessage> action) {
        if (rxBus == null) {
            rxBus = RxBusUtil.getIntanceBus();
        }
        Disposable disposable = rxBus.doSubscribe(RxBusMessage.class, action, throwable -> {
//                Log.e("NewsMainPresenter", throwable.toString());
        });
        rxBus.addSubscription(this, disposable);
    }







}
