package com.judy.emano0o87.training4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 3/20/2018.
 */

public class viewHolder{
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
