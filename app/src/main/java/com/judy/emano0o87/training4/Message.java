package com.judy.emano0o87.training4;

import java.io.Serializable;

/**
 * Created by Administrator on 3/12/2018.
 */
public class Message implements Serializable {
    private String messageText;
    private String messageTime;
    private int messageStatus;
    private String MessageType;
    private String ImagePath;

    public boolean getImageId() {
        return imageId;
    }

    public void setImageId(boolean imageId) {
        this.imageId = imageId;
    }

    private boolean imageId = false;


   public Message(String messagetxt,String messagetime,int messagestat,String type)
   {
       this.messageText=messagetxt;
       this.messageTime = messagetime;
       this.messageStatus = messagestat;
       this.setMessageType(type);
   }

   public Message(boolean hasImage ,int messageStatus, String msgType,String imagePath , String msgTime)
   {
       MessageType = msgType;
       imageId = hasImage;
       this.messageStatus= messageStatus ;
       this.ImagePath = imagePath;
       this.messageTime = msgTime;
   }

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
