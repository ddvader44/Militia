package com.reallyinvincible.veto.bottomfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.reallyinvincible.veto.MessageInteractionInterface;
import com.reallyinvincible.veto.R;
import com.reallyinvincible.veto.activities.DemoActivity;
import com.reallyinvincible.veto.activities.HomeActivity;
import com.reallyinvincible.veto.utilities.EncryptionUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomSheetKeyFragment extends BottomSheetDialogFragment {

    MessageInteractionInterface addKeyInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_key_fragment, container, false);
        addKeyInterface = HomeActivity.getSendMessageInterface();
        final EditText keyEditText = view.findViewById(R.id.et_key_string);
        view.findViewById(R.id.btn_add_key).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyString = keyEditText.getText().toString();
                int keyLength = keyString.length();
                if (keyLength == 16 || keyLength == 24 || keyLength == 32) {
                    addKeyInterface.addKey(keyString);
                }
                else {
                    Toast.makeText(getContext(), "Please Enter A Valid Key\n(Key Length should be 16, 24 or 32)", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
