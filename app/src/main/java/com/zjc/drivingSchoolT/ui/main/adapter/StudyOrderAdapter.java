package com.zjc.drivingSchoolT.ui.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.ZBaseFragment;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingSchoolT.R;
import com.zjc.drivingSchoolT.api.ApiHttpClient;
import com.zjc.drivingSchoolT.api.ResultResponseHandler;
import com.zjc.drivingSchoolT.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingSchoolT.db.model.OrderItem;
import com.zjc.drivingSchoolT.ui.receive.ReceiveActivity;
import com.zjc.drivingSchoolT.utils.Constants;
import com.zjc.drivingSchoolT.utils.ConstantsParams;

/**
 * Created by Administrator on 2016/8/17.
 */
public class StudyOrderAdapter extends ZBaseRecyclerViewAdapter {
    public StudyOrderAdapter(Context context) {
        super(context);
    }


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new OrderManagerFrgViewHolder(parent);
    }

    class OrderManagerFrgViewHolder extends BaseViewHolder<OrderItem> {
        private TextView tvName;
        private TextView tvStatus;
        private TextView tvNumber;
        private TextView tvTime;
        private TextView tvReceive;
        private TextView tvDistribution;
        private TextView tvMoney;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.study_order_item);
            tvName = $(R.id.order_name);
            tvStatus = $(R.id.order_status);
            tvNumber = $(R.id.order_number);
            tvTime = $(R.id.order_time);
            tvReceive = $(R.id.order_receive);
            tvDistribution = $(R.id.order_distribution);
            tvMoney = $(R.id.order_money);
        }

        @Override
        public void setData(OrderItem service) {
            if (service == null) {
                return;
            }
            tvName.setText(service.getTitle());
            tvNumber.setText(service.getContactsname());
            tvTime.setText(service.getContactsphone());
            tvMoney.setText(service.getTotal() + "");
//            tvStatus.setText(ConstantsParams.getStatus(service.getState()));
            //	1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消
            if (service.getState().equals(ConstantsParams.STUDY_ORDER_ONE)) {
                tvReceive.setVisibility(View.VISIBLE);
                tvDistribution.setVisibility(View.GONE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_TWO)) {
                tvReceive.setVisibility(View.GONE);
                tvDistribution.setVisibility(View.VISIBLE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_SEVEN)) {
                tvReceive.setVisibility(View.GONE);
                tvDistribution.setVisibility(View.GONE);
            } else {
                tvReceive.setVisibility(View.GONE);
                tvDistribution.setVisibility(View.GONE);
            }

            tvReceive.setTag(service);
            tvReceive.setOnClickListener(new ReceiveOnClickListener());
            tvDistribution.setTag(service);
            tvDistribution.setOnClickListener(new DistributionOnClickListener());
        }
    }

    /**
     * 分配订单
     */
    class DistributionOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            startDistributionOrder(item);
        }
    }

    /**
     * 接受订单
     */
    class ReceiveOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            ReceiveStudyOrder(item);
        }
    }

    private void ReceiveStudyOrder(final OrderItem item) {
        ApiHttpClient.getInstance().receiveStudyOrder(SharePreferencesUtil.getInstance().readUser().getUid(), item.getOrid(), new ResultResponseHandler(getContext(), "正在接单") {

            @Override
            public void onResultSuccess(String result) {
                item.setState(ConstantsParams.STUDY_ORDER_TWO);
                StudyOrderAdapter.this.notifyDataSetChanged();
                //跳转到分配界面
                startDistributionOrder(item);
            }
        });
    }

    private void startDistributionOrder(OrderItem item) {
        ZBaseFragment fragment = (ZBaseFragment) ((AppCompatActivity) getContext()).getSupportFragmentManager().getFragments().get(0);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ARGUMENT,item);
        fragment.startActivity(ReceiveActivity.class,bundle);
    }
}
