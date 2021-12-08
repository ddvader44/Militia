package com.reallyinvincible.veto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reallyinvincible.veto.utilities.EncryptionUtils;
import com.reallyinvincible.veto.R;

public class DemoActivity extends AppCompatActivity {

    private final String KEY = "ThisIsASecretKey";
    private Context context;
    EditText keyEdiText, messageEditText;
    TextView encyptedTextView, decryptedTextView;
    Button generateKeyButton, encryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        keyEdiText = findViewById(R.id.et_key);
        messageEditText = findViewById(R.id.et_message);
        encyptedTextView = findViewById(R.id.tv_encrypted_text);
        decryptedTextView = findViewById(R.id.tv_decrypted_text);
        generateKeyButton = findViewById(R.id.btn_generate_key);
        encryptButton = findViewById(R.id.btn_encrypt);

        context = this;

        generateKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyEdiText.setText(KEY);
                EncryptionUtils.createKey(KEY, context);
            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();
                String key = keyEdiText.getText().toString();
                int keyLength = key.length();
                if (keyLength == 16 || keyLength == 24 || keyLength == 32) {
                    EncryptionUtils.createKey(key, getApplicationContext());
                    String encryptedText = EncryptionUtils.encrypt(message);
                    String decryptedText = EncryptionUtils.decrypt(encryptedText);
                    encyptedTextView.setText(encryptedText);
                    decryptedTextView.setText(decryptedText);
                }
                else {
                    Toast.makeText(DemoActivity.this, "Please Enter A Valid Key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
