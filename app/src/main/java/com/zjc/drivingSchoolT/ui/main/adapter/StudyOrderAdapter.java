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
        private TextView tvStart;
        private TextView tvFinish;
        private TextView tvMoney;

        public OrderManagerFrgViewHolder(ViewGroup parent) {
            super(parent, R.layout.study_order_item);
            tvName = $(R.id.order_name);
            tvStatus = $(R.id.order_status);
            tvNumber = $(R.id.order_number);
            tvTime = $(R.id.order_time);
            tvStart = $(R.id.order_start);
            tvFinish = $(R.id.order_finish);
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
            tvStatus.setText(ConstantsParams.getStatus(service.getState()));
            //	1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消
            if (service.getState().equals(ConstantsParams.STUDY_ORDER_TWO)) {
                tvStart.setVisibility(View.VISIBLE);
                tvFinish.setVisibility(View.GONE);
            } else if (service.getState().equals(ConstantsParams.STUDY_ORDER_FIVE)) {
                tvStart.setVisibility(View.GONE);
                tvFinish.setVisibility(View.VISIBLE);
            } else {
                tvStart.setVisibility(View.GONE);
                tvFinish.setVisibility(View.GONE);
            }

            tvStart.setTag(service);
            tvStart.setOnClickListener(new StartOnClickListener());
            tvFinish.setTag(service);
            tvFinish.setOnClickListener(new FinishOnClickListener());
        }
    }

    /**
     * 开始练车
     */
    class StartOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            startStudyOrder(item);
        }
    }

    private void startStudyOrder(final OrderItem item) {
        ApiHttpClient.getInstance().startStudyOrder(SharePreferencesUtil.getInstance().readUser().getUid(), item.getOrid(), new ResultResponseHandler(getContext(), "开始练车") {

            @Override
            public void onResultSuccess(String result) {
                item.setState(ConstantsParams.STUDY_ORDER_FIVE);
                StudyOrderAdapter.this.notifyDataSetChanged();
            }
        });
    }

    /**
     * 完成练车
     */
    class FinishOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderItem item = (OrderItem) v.getTag();
            finishStudyOrder(item);
        }
    }

    private void finishStudyOrder(final OrderItem item) {
        ApiHttpClient.getInstance().finishStudyOrder(SharePreferencesUtil.getInstance().readUser().getUid(), item.getOrid(), new ResultResponseHandler(getContext(), "结束练车") {

            @Override
            public void onResultSuccess(String result) {
                item.setState(ConstantsParams.STUDY_ORDER_SIX);
                StudyOrderAdapter.this.notifyDataSetChanged();
            }
        });
    }
}
