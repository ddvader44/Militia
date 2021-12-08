package com.reallyinvincible.veto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.reallyinvincible.veto.R;
import com.reallyinvincible.veto.models.EncryptedMessage;
import com.reallyinvincible.veto.models.SecureRoom;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class OnboardingActivity extends AppCompatActivity {

    private EditText usernameEditText, roomIdEditText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRoomIdReference;
    private DatabaseReference mDataPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        mDatabase = FirebaseDatabase.getInstance();
        mRoomIdReference = mDatabase.getReference().child("Rooms");
        mDataPrivacy = mDatabase.getReference().child("Names");
        usernameEditText = findViewById(R.id.et_user_name);
        roomIdEditText = findViewById(R.id.et_room_id);
        findViewById(R.id.btn_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterRoom();
            }
        });
    }

    void enterRoom(){
        final String username = usernameEditText.getText().toString();
        final String roomId = roomIdEditText.getText().toString();
        mDataPrivacy.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uniqueID = UUID.randomUUID().toString();
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("unique", uniqueID);
                editor.apply();
                mDataPrivacy.child(uniqueID).setValue(mask(username));
//                if (dataSnapshot.hasChild(roomId)){
//                    SecureRoom secureRoom = dataSnapshot.child(roomId).getValue(SecureRoom.class);
//                    Toast.makeText(OnboardingActivity.this, "Entering " + roomId + "!", Toast.LENGTH_SHORT).show();
//                    saveDetails(username, secureRoom);
//                } else {
//                    SecureRoom secureRoom = new SecureRoom(roomId, roomId, new ArrayList<EncryptedMessage>());
//                    mRoomIdReference.child(roomId).setValue(secureRoom);
//                    Toast.makeText(OnboardingActivity.this, "Creating a new Room For You!", Toast.LENGTH_SHORT).show();
//                    saveDetails(username, secureRoom);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mRoomIdReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(roomId)){
                    SecureRoom secureRoom = dataSnapshot.child(roomId).getValue(SecureRoom.class);
                    Toast.makeText(OnboardingActivity.this, "Entering " + roomId + "!", Toast.LENGTH_SHORT).show();
                    saveDetails(username, secureRoom);
                } else {
                    SecureRoom secureRoom = new SecureRoom(roomId, roomId, new ArrayList<EncryptedMessage>());
                    mRoomIdReference.child(roomId).setValue(secureRoom);
                    Toast.makeText(OnboardingActivity.this, "Creating a new Room For You!", Toast.LENGTH_SHORT).show();
                    saveDetails(username, secureRoom);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private char randomChar(Random r, String cs, boolean b) {
        char c = cs.charAt(r.nextInt(cs.length()));
        return b ? Character.toUpperCase(c) : c;
    }

    private String mask(String username) {
        final String cons = "bcdfghjklmnpqrstvwxz";
        final String vowel = "aeiouy";
        final String digit = "0123456789";

        Random r = new Random(0);
        char[] data = username.toCharArray();

        for (int n = 0; n < data.length; ++n) {
            char ln = Character.toLowerCase(data[n]);
            if (cons.indexOf(ln) >= 0)
                data[n] = randomChar(r, cons, ln != data[n]);
            else if (vowel.indexOf(ln) >= 0)
                data[n] = randomChar(r, vowel, ln != data[n]);
            else if (digit.indexOf(ln) >= 0)
                data[n] = randomChar(r, digit, ln != data[n]);
        }
        return new String(data);
    }

    void saveDetails(String userName, SecureRoom secureRoom){
        Gson gson = new Gson();
        String secureRoomString = gson.toJson(secureRoom);
        SharedPreferences sharedPreferences = getSharedPreferences("Details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", userName);
        editor.putString("SecureRoom", secureRoomString);
        editor.apply();
        launchHome();
    }

    void launchHome(){
        Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    }
