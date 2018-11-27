package com.okayfan.jdareaselect;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * author: FYx
 * date:   On 2018/11/14
 */
public class AreaAdapter extends BaseQuickAdapter<Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean, BaseViewHolder> {

    public int selectPosition = -1;



    public AreaAdapter() {
        super(R.layout.item_area);
    }

    @Override
    protected void convert(BaseViewHolder helper, Area.DataBean.AreasBeanXX.AreasBeanX.AreasBean item) {
        helper.setText(R.id.tv_area,item.getAreaName());
        if (selectPosition == helper.getAdapterPosition()){
            helper.setTextColor(R.id.tv_area,mContext.getResources().getColor(R.color.tb_select));
        }else {
            helper.setTextColor(R.id.tv_area,mContext.getResources().getColor(R.color.color_font_333333));

        }
    }





}
