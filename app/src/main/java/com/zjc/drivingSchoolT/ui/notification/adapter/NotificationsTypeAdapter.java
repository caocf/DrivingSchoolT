package com.zjc.drivingSchoolT.ui.notification.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mobo.mobolibrary.ui.base.adapter.ZBaseRecyclerViewAdapter;
import com.zjc.drivingSchoolT.R;
import com.zjc.drivingSchoolT.db.model.MessageItem;

/**
 * @author Z
 * @Filename NotificationsListAdapter.java
 * @Date 2015.11.25
 * @description 通知类型适配器
 */
public class NotificationsTypeAdapter extends ZBaseRecyclerViewAdapter {

    public NotificationsTypeAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MainServiceViewHolder(parent);
    }

    class MainServiceViewHolder extends BaseViewHolder<MessageItem> {
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;
        private TextView tvNoticeCount;

        private ImageView tvIcon;
        private ImageView ImgIsRead;

        public MainServiceViewHolder(ViewGroup parent) {
            super(parent, R.layout.notification_type_item);
            ImgIsRead = $(R.id.notification_type_item_icon);
            tvTitle = $(R.id.notification_type_item_tv_title);
            tvContent = $(R.id.notification_type_item_tv_content);
            tvTime = $(R.id.notification_type_item_tv_time);
        }

        @Override
        public void setData(final MessageItem service) {
            ImgIsRead.setSelected(!service.getIsread());

            tvTitle.setText(service.getTitle());
            tvTime.setText(service.getCreatdate());
        }
    }
}
