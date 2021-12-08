package com.reallyinvincible.veto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.models.ConnectState;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reallyinvincible.veto.R;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SoundShareActivity extends AppCompatActivity {

    private ChirpConnect chirpConnect;

    private static final int RESULT_REQUEST_RECORD_AUDIO = 1;

    String APP_KEY = "ee895EaBC34D87BCB77Db3a2b";
    String APP_SECRET = "21B74a053facc4EAe2AdEB980CB0050cb94AABB53dFa7c320b";
    String APP_CONFIG = "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";

    View parentLayout;
    TextView statusTextView;
    TextView lastChirpTextView;
    Button startStopSdkBtn;
    Button startStopSendingBtn;

    Boolean startStopSdkBtnPressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_share);

        parentLayout = findViewById(R.id.cl_parent);
        statusTextView = findViewById(R.id.tv_status);
        lastChirpTextView = findViewById(R.id.tv_received_key);
        startStopSdkBtn = findViewById(R.id.btn_start_stop);
        startStopSendingBtn = findViewById(R.id.btn_send_key);

        startStopSdkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStopSdk(view);
            }
        });

        startStopSendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPayload(view);
            }
        });

        chirpConnect = new ChirpConnect(this, APP_KEY, APP_SECRET);
        chirpConnect.setConfig(APP_CONFIG, new ConnectSetConfigListener() {

            @Override
            public void onSuccess() {
                //Set-up the connect callbacks
                chirpConnect.setListener(connectEventListener);
                //Enable Start/Stop button
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startStopSdkBtn.setAlpha(1f);
                        startStopSdkBtn.setClickable(true);
                    }
                });
            }

            @Override
            public void onError(ChirpError setConfigError) {
                Log.e("setConfigError", setConfigError.getMessage());
            }
        });
    }


    ConnectEventListener connectEventListener = new ConnectEventListener() {

        @Override
        public void onSending(byte[] data, byte channel) {

            int intData = 0;
            if (data != null) {
                intData = fromByteArray(data);
            }
            Log.v("Veto", "ConnectCallback: onSending: " + String.valueOf(intData) + " on channel: " + channel);
            updateLastPayload(String.valueOf(intData));
        }

        @Override
        public void onSent(byte[] data, byte channel) {
            /**
             * onSent is called when a send event has completed.
             * The data argument contains the payload that was sent.
             */
            int intData = 0;
            if (data != null) {
                intData = fromByteArray(data);
            }
            Log.v("Veto", "ConnectCallback: onSent: " + String.valueOf(intData) + " on channel: " + channel);
            updateLastPayload(String.valueOf(intData));
        }

        @Override
        public void onReceiving(byte channel) {

            Log.v("Veto", "ConnectCallback: onReceiving on channel: " + channel);
        }

        @Override
        public void onReceived(byte[] data, byte channel) {

            int intData = 0;
            if (data != null) {
                intData = fromByteArray(data);
            }
            Log.v("Veto", "ConnectCallback: onReceived: " + String.valueOf(intData) + " on channel: " + channel);
            updateLastPayload(String.valueOf(intData));
        }

        @Override
        public void onStateChanged(byte oldState, byte newState) {
            /**
             * onStateChanged is called when the SDK changes state.
             */
            ConnectState state = ConnectState.createConnectState(newState);
            Log.v("Veto", "ConnectCallback: onStateChanged " + oldState + " -> " + newState);
            if (state == ConnectState.ConnectNotCreated) {
                updateStatus("NotCreated");
            } else if (state == ConnectState.AudioStateStopped) {
                updateStatus("Stopped");
            } else if (state == ConnectState.AudioStatePaused) {
                updateStatus("Paused");
            } else if (state == ConnectState.AudioStateRunning) {
                updateStatus("Running");
            } else if (state == ConnectState.AudioStateSending) {
                updateStatus("Sending");
            } else if (state == ConnectState.AudioStateReceiving) {
                updateStatus("Receiving");
            } else {
                updateStatus(newState + "");
            }

        }

        @Override
        public void onSystemVolumeChanged(int oldVolume, int newVolume) {
            /**
             * onSystemVolumeChanged is called when the system volume is changed.
             */
            Snackbar snackbar = Snackbar.make(parentLayout, "System volume has been changed to: " + newVolume, Snackbar.LENGTH_LONG);
            snackbar.setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
            Log.v("Veto", "System volume has been changed, notify user to increase the volume when sending data");
        }

    };


    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {
            if (startStopSdkBtnPressed) startSdk();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (startStopSdkBtnPressed) stopSdk();
                }
                return;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        chirpConnect.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        stopSdk();
    }

    public void stopSdk() {
        ChirpError error = chirpConnect.stop();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
            return;
        }
        startStopSendingBtn.setAlpha(.4f);
        startStopSendingBtn.setClickable(false);
        startStopSdkBtn.setText("START LISTENING");
    }

    public void startSdk() {
        ChirpError error = chirpConnect.start();
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
            return;
        }
        startStopSendingBtn.setAlpha(1f);
        startStopSendingBtn.setClickable(true);
        startStopSdkBtn.setText("STOP LISTENING");
    }

    public void startStopSdk(View view) {
        /**
         * Start or stop the SDK.
         * Audio is only processed when the SDK is running.
         */
        startStopSdkBtnPressed = true;
        if (chirpConnect.getConnectState() == ConnectState.AudioStateStopped) {
            startSdk();
        } else {
            stopSdk();
        }
    }

    public void updateStatus(final String newStatus) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusTextView.setText(newStatus);
            }
        });
    }
    public void updateLastPayload(final String newPayload) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lastChirpTextView.setText(newPayload);
            }
        });
    }

    public void sendPayload(View view) {

        byte[] payload = toByteArray(48);
        long maxSize = chirpConnect.getMaxPayloadLength();
        if (maxSize < payload.length) {
            Log.e("ConnectError: ", "Invalid Payload");
            Toast.makeText(this, "Length Invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        ChirpError error = chirpConnect.send(payload);
        if (error.getCode() > 0) {
            Log.e("ConnectError: ", error.getMessage());
        }
    }

    byte[] toByteArray(int value) {
        return  ByteBuffer.allocate(4).putInt(value).array();
    }


    int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

}