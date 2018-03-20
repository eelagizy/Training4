package com.judy.emano0o87.training4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 3/12/2018.
 */
public abstract class Message  {
    private String messageTime;
    private String messageType;

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    private int messageStatus;

    public Message(String Mtime , String Mtype , int status)
    {
        this.messageTime = Mtime;
        this.messageType = Mtype;
        this.messageStatus = status;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

   public abstract View setMessageView(Context context, View view,ViewGroup parent);





}
