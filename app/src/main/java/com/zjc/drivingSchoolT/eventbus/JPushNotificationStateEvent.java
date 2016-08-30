package com.zjc.drivingSchoolT.eventbus;


import com.zjc.drivingSchoolT.db.model.MessageItem;

/**
 * Created by Administrator on 2015/7/14.
 */
public class JPushNotificationStateEvent {
    MessageItem messageItem;

    public JPushNotificationStateEvent(MessageItem messageItem) {
        this.messageItem = messageItem;
    }

    public MessageItem getMessageItem() {
        return messageItem;
    }
}
