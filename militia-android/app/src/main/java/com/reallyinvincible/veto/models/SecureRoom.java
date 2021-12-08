package com.reallyinvincible.veto.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SecureRoom implements Parcelable {

    String roomName;
    String roomId;
    List<EncryptedMessage> messages;

    public SecureRoom(String roomName, String roomId, List<EncryptedMessage> messages) {
        this.roomName = roomName;
        this.roomId = roomId;
        this.messages = messages;
    }

    public SecureRoom() {
    }

    protected SecureRoom(Parcel in) {
        roomName = in.readString();
        roomId = in.readString();
        messages = in.createTypedArrayList(EncryptedMessage.CREATOR);
    }

    public static final Creator<SecureRoom> CREATOR = new Creator<SecureRoom>() {
        @Override
        public SecureRoom createFromParcel(Parcel in) {
            return new SecureRoom(in);
        }

        @Override
        public SecureRoom[] newArray(int size) {
            return new SecureRoom[size];
        }
    };

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<EncryptedMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<EncryptedMessage> messages) {
        this.messages = messages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(roomName);
        parcel.writeString(roomId);
        parcel.writeTypedList(messages);
    }
}
