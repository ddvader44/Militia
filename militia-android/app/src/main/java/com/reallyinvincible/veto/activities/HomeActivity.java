package com.reallyinvincible.veto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.reallyinvincible.veto.EncryptedMessageAdapter;
import com.reallyinvincible.veto.bottomfragments.BottomSheetSendMessage;
import com.reallyinvincible.veto.MessageInteractionInterface;
import com.reallyinvincible.veto.bottomfragments.BottomSheetKeyFragment;
import com.reallyinvincible.veto.R;
import com.reallyinvincible.veto.models.EncryptedMessage;
import com.reallyinvincible.veto.models.SecureRoom;
import com.reallyinvincible.veto.utilities.EncryptionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    FirebaseDatabase mDatabase;
    DatabaseReference mRoomReference;
    public static MessageInteractionInterface sendMessageInterface;
    public static String keyString = null;
    private static String lockString = null;
    private SecureRoom secureRoom;
    private List<EncryptedMessage> messagesLocalCopy;
    private String username, roomId;
    private BottomSheetSendMessage bottomSheetSendMessage;
    private BottomSheetKeyFragment bottomSheetKeyFragment;
    private RecyclerView messageRecyclerView;
    private DatabaseReference mDateReference;
    private DatabaseReference mTimeReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("Details", MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "Anonymous");
        secureRoom = gson.fromJson(sharedPreferences.getString("SecureRoom", null), SecureRoom.class);
        messagesLocalCopy = secureRoom.getMessages();
        roomId = secureRoom.getRoomId();
        messageRecyclerView = findViewById(R.id.rv_messages);

        mDatabase = FirebaseDatabase.getInstance();
        mRoomReference = mDatabase.getReference().child("Rooms");
        mTimeReference = mDatabase.getReference().child("Time");
        mDateReference = mDatabase.getReference().child("Date");


        bottomAppBar = findViewById(R.id.bottom_bar);
        bottomAppBar.replaceMenu(R.menu.bottom_bar_menu);
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKeyDialogue();
            }
        });
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_item_share_key){
                    startActivity(new Intent(HomeActivity.this, SoundShareActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_item_receive_key){
                    startActivity(new Intent(HomeActivity.this, DemoActivity.class));
                    return true;
                }
                return false;
            }
        });

        sendMessageInterface = new MessageInteractionInterface() {
            @Override
            public void sendMessage(String message, String lock) {
                bottomSheetSendMessage.dismiss();
                addMessage(message, lock);
            }

            @Override
            public void addKey(String key) {
                setKeyString(key);
                bottomSheetKeyFragment.dismiss();
                messageRecyclerView.setAdapter(new EncryptedMessageAdapter(messagesLocalCopy, getKeyString()));
            }
        };

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMessageDialogue();
            }
        });

        mRoomReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SecureRoom room = dataSnapshot.child(roomId).getValue(SecureRoom.class);
                List<EncryptedMessage> encryptedMessages = room.getMessages();
                messagesLocalCopy = encryptedMessages;
                if (encryptedMessages != null)
                    messageRecyclerView.setAdapter(new EncryptedMessageAdapter(encryptedMessages, getKeyString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void addMessage(String message, String lock){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String uuid =pref.getString("unique", "23059d04-4f23-4bb7-8cbc-e54cc9becd24");         // getting String
        EncryptionUtils.createKey(lock, getApplicationContext());
        lockString = lock;
        String cipherText = EncryptionUtils.encrypt(message);
        String dateStamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String timeStamp = new SimpleDateFormat("HH:mm").format(new Date());
        mTimeReference.child(uuid).setValue(generalization(timeStamp));
        mDateReference.child(uuid).setValue(supress(dateStamp));
        EncryptedMessage encryptedMessage = new EncryptedMessage(username, cipherText, dateStamp, timeStamp);
        if (messagesLocalCopy == null)
            messagesLocalCopy = new ArrayList<EncryptedMessage>();
        messagesLocalCopy.add(encryptedMessage);
        secureRoom.setMessages(messagesLocalCopy);
        mRoomReference.child(roomId).setValue(secureRoom);
    }

    private String supress(String dateStamp) {
        return "*";
    }

    private String generalization(String timeStamp) {
        return timeStamp.replace(timeStamp.substring(0,2),"20");
    }


    void openMessageDialogue(){
        bottomSheetSendMessage = new BottomSheetSendMessage();
        bottomSheetSendMessage.show(getSupportFragmentManager(), "SendMessage");
    }

    void openKeyDialogue(){
        bottomSheetKeyFragment = new BottomSheetKeyFragment();
        bottomSheetKeyFragment.show(getSupportFragmentManager(), "AddKey");
    }

    public static MessageInteractionInterface getSendMessageInterface() {
        return sendMessageInterface;
    }

    public static String getKeyString() {
        return keyString;
    }

    public static void setKeyString(String keyString) {
        HomeActivity.keyString = keyString;
    }

    public static String getLockString() {
        return lockString;
    }

    public static void setLockString(String lockString) {
        HomeActivity.lockString = lockString;
    }
}
