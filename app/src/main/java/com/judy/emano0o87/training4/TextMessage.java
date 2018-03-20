package com.judy.emano0o87.training4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 3/20/2018.
 */

public class TextMessage extends Message {

    private String messageText;
    viewHolder holder;

    public TextMessage(String Mesgtxt,String Mtime,String Mtype , int staus)
    {
        super(Mtime,Mtype,staus);
        messageText = Mesgtxt;
    }

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public View setMessageView(Context context, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(view == null) {
            if (getMessageType() == "sender") {
                view = inflater.inflate(R.layout.row_item_sender,parent,false);
                holder = new viewHolder(view);
                view.setTag(holder);

            } else if (getMessageType() == "reciever") {

                view = inflater.inflate(R.layout.row_item_reciever,null,false);
                holder = new viewHolder(view);
                view.setTag(holder);
            }
        }  else
        {
            holder=(viewHolder) view.getTag();
        }

        holder.ImageID.setVisibility(View.GONE);
        holder.MessageText.setText(getMessageText());
        holder.MessageTime.setText(getMessageTime());
        return view;
    }
}
