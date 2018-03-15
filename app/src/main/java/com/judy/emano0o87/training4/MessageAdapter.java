package com.judy.emano0o87.training4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 3/12/2018.
 */

public class MessageAdapter extends BaseAdapter {
    Drawable backResId;
    List<Message> msgData;
   Context context;
    viewHolder holder;

   //  ContextThemeWrapper wrapper;
    MessageAdapter(Context con , List<Message> data)
    {
     context = con;
    // wrapper = new ContextThemeWrapper(con, style); , int style
     msgData = data;
  //   stylee = style;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) //new row
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_item,parent,false);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
           holder=(viewHolder) convertView.getTag();
        }
       //set message data
        holder.MessageText.setText(msgData.get(position).getMessageText());
        holder.MessageTime.setText(msgData.get(position).getMessageTime());
        holder.MessageStatus.setImageResource(msgData.get(position).getMessageStatus());

        LinearLayout root =(LinearLayout) convertView.findViewById(R.id.root);

        if(msgData.get(position).getImageId() == false)
        {
            holder.ImageID.setVisibility(View.GONE);
        }
        else if (msgData.get(position).getImageId() != false ){
                holder.ImageID.setVisibility(View.VISIBLE);
              // holder.MessageText.setVisibility(View.GONE);
               decodeImage(msgData.get(position).getImagePath());

        }else if (msgData.get(position).getMessageText().length() != 0 &&
                msgData.get(position).getImageId() == true )
        {
            holder.ImageID.setVisibility(View.VISIBLE);
            holder.MessageText.setVisibility(View.VISIBLE);
            decodeImage(msgData.get(position).getImagePath());


        }

        if(msgData.get(position).getMessageType() =="sender")
        {
            backResId = ContextCompat.getDrawable(context,R.drawable.balloon_outgoing_normal);
            root.setGravity(Gravity.RIGHT);
           // root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }else if (msgData.get(position).getMessageType() =="reciever")
        {
            backResId   = ContextCompat.getDrawable(context,R.drawable.balloon_incoming_normal);
            root.setGravity(Gravity.LEFT);
           // root.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
        root.setBackground(backResId);


        return convertView;
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

    private class viewHolder{
        TextView MessageText;
        TextView MessageTime;
        ImageView MessageStatus;
        ImageView ImageID;

        viewHolder(View view)
        {
            MessageText = (TextView) view.findViewById(R.id.messageText);
            MessageTime = (TextView) view.findViewById(R.id.messageTime);
            MessageStatus = (ImageView) view.findViewById(R.id.messagestatus);
            ImageID = (ImageView) view.findViewById(R.id.imageMsg);

        }
    }

    void decodeImage(String imagepath)
    {
        // Get the dimensions of the View
         // int targetW = holder.ImageID.getWidth();
       //   int targetH = holder.ImageID.getHeight();
             int targetW =100 , targetH = 100;
        // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imagepath, bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

           //Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

     //    Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

         Bitmap bitmap = BitmapFactory.decodeFile(imagepath, bmOptions);
         holder.ImageID.setImageBitmap(bitmap);
    }

}
