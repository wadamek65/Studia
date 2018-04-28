package com.example.wojta_000.a5;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.android.internal.telephony.*;

import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int ARE_PERMISSION_GRANTED;
    TextView mainLabel;
    TextView callLabel;
    TextView smsLabel;
    TextView stateLabel;

    public boolean arePermissionGranted() {
        Boolean permission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
        Boolean permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
        Boolean permission3 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
        Boolean permission4 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
        return (permission1 && permission2 && permission3 && permission4);

    }

    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_PHONE_STATE}, ARE_PERMISSION_GRANTED);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLabel = findViewById(R.id.main_label);
        callLabel = findViewById(R.id.call_label);
        smsLabel = findViewById(R.id.sms_label);
        stateLabel = findViewById(R.id.state_label);

        TelephonyManager telMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        requestPermissions();

        String info = "Network country:"+telMgr.getNetworkCountryIso();
        info += "\n Sim country:"+telMgr.getSimCountryIso();
        info += "\n Sim state:"+telMgr.getSimState();
        mainLabel.setText(info);

        BroadcastReceiver telephoneReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) return;
                else {
                    try {
                        callLabel.setText("Call received and blocked.");
                        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                        Class sampleClass = Class.forName(telephonyManager.getClass().getName());
                        Method method = sampleClass.getDeclaredMethod("getITelephony");
                        method.setAccessible(true);
                        ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
                        telephonyService.endCall(); // nie działa na niektórych urządzeniach?
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        BroadcastReceiver smsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Object[] smsextras = (Object[]) extras.get("pdus");
                    for (int i=0; i < smsextras.length; i++) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) smsextras[i]);
                        String smsBody = "Received message: " + smsMessage.getMessageBody().toString();
                        String smsNumber = smsMessage.getOriginatingAddress();
                        smsLabel.setText(smsBody);
                        SmsManager.getDefault().sendTextMessage(smsNumber, null, "This is an automated response", null, null);
                    }
                }

            }
        };

        PhoneStateListener stateListener = new PhoneStateListener() {

            public void onCellInfoChanged(List<CellInfo> cellInfo) {
                stateLabel.setText(cellInfo.toString());
            }
        };
        IntentFilter smsFilter = new IntentFilter();
        smsFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        IntentFilter filter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        this.registerReceiver(telephoneReceiver, filter);
        this.registerReceiver(smsReceiver, smsFilter);
        //telMgr.listen(stateListener, PhoneStateListener.LISTEN_CELL_INFO); // dodanie listenera powoduje crash,
    }


}