package com.reallyinvincible.veto.bottomfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.reallyinvincible.veto.R;
import com.reallyinvincible.veto.MessageInteractionInterface;
import com.reallyinvincible.veto.activities.HomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomSheetSendMessage extends BottomSheetDialogFragment {

    MessageInteractionInterface sendMessageInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_send_message, container, false);
        sendMessageInterface = HomeActivity.getSendMessageInterface();
        final EditText messageEditText = view.findViewById(R.id.et_message_input);
        final EditText lockEditText = view.findViewById(R.id.et_lock_text);
        String lockString = HomeActivity.getLockString();
        if (lockString != null)
            lockEditText.setText(lockString);
        view.findViewById(R.id.btn_encrypt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();
                String lock = lockEditText.getText().toString();
                int keyLength = lock.length();
                if (keyLength == 16 || keyLength == 24 || keyLength == 32) {
                    sendMessageInterface.sendMessage(message, lock);
                }
                else {
                    Toast.makeText(getContext(), "Please Enter A Valid Key\n(Key Length should be 16, 24 or 32)", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
