package com.judy.emano0o87.training4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 3/12/2018.
 */

public class MessageAdapter extends BaseAdapter {
    Drawable backResId;
    ArrayList<Message> msgData;
    Context context;

    MessageAdapter(Context con , ArrayList<Message> data)
    {
     context = con;
     msgData = data;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      return   convertView = msgData.get(position).setMessageView(context,convertView,parent);
       //set message data
       // holder.MessageStatus.setImageResource(msgData.get(position).getMessageStatus());

//        LinearLayout root =(LinearLayout) convertView.findViewById(R.id.root);
//
//        if(msgData.get(position).getImageId() == false)
//        {
//            holder.ImageID.setVisibility(View.GONE);
//        }
//        else if (msgData.get(position).getImageId() != false ){
//                holder.ImageID.setVisibility(View.VISIBLE);
//              // holder.MessageText.setVisibility(View.GONE);
//               decodeImage(msgData.get(position).getImagePath());
//
//        }else if (msgData.get(position).getMessageText().length() != 0 &&
//                msgData.get(position).getImageId() == true )
//        {
//            holder.ImageID.setVisibility(View.VISIBLE);
//            holder.MessageText.setVisibility(View.VISIBLE);
//            decodeImage(msgData.get(position).getImagePath());
//
//
//        }
//
//        if(msgData.get(position).getMessageType() =="sender")
//        {
//            backResId = ContextCompat.getDrawable(context,R.drawable.balloon_outgoing_normal);
//            root.setGravity(Gravity.RIGHT);
//           // root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//
//        }else if (msgData.get(position).getMessageType() =="reciever")
//        {
//            backResId   = ContextCompat.getDrawable(context,R.drawable.balloon_incoming_normal);
//            root.setGravity(Gravity.LEFT);
//           // root.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        }
//        root.setBackground(backResId);
    }
    @Override
    public int getCount() {
        return msgData.size();
    }

    @Override
    public Object getItem(int position) {
        return msgData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
