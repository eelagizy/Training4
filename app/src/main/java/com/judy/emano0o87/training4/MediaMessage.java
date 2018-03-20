package com.judy.emano0o87.training4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 3/20/2018.
 */

public class MediaMessage extends Message {
    viewHolder holder;
    private String ImagePath;
    private String VideoPath;

    public MediaMessage(String Mtime, String Mtype,int status,String ImgPath) {
        super(Mtime, Mtype,status);
        ImagePath = ImgPath;

    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }

    @Override
    public View setMessageView(Context context, View view, ViewGroup parent) {
        viewHolder holder=null;
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
        holder.ImageID.setVisibility(View.VISIBLE);
        decodeImage(getImagePath());

        return view;

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
