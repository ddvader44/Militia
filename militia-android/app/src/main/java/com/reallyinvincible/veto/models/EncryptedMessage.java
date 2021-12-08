package com.reallyinvincible.veto.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EncryptedMessage implements Parcelable {

    String userName;
    String encryptedText;
    String messageDate;
    String messageTime;

    public EncryptedMessage(String userName, String encryptedText, String messageDate, String messageTime) {
        this.userName = userName;
        this.encryptedText = encryptedText;
        this.messageDate = messageDate;
        this.messageTime = messageTime;
    }

    public EncryptedMessage() {
    }

    protected EncryptedMessage(Parcel in) {
        userName = in.readString();
        encryptedText = in.readString();
        messageDate = in.readString();
        messageTime = in.readString();
    }

    public static final Creator<EncryptedMessage> CREATOR = new Creator<EncryptedMessage>() {
        @Override
        public EncryptedMessage createFromParcel(Parcel in) {
            return new EncryptedMessage(in);
        }

        @Override
        public EncryptedMessage[] newArray(int size) {
            return new EncryptedMessage[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(encryptedText);
        parcel.writeString(messageDate);
        parcel.writeString(messageTime);
    }
}