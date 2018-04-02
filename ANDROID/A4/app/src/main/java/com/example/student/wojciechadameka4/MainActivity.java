package com.example.student.wojciechadameka4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Intent messagesActivity = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "", null));
    Intent mapsActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pl/maps/place/Wroc%C5%82aw/@51.1267432,16.7116858,10z/data=!3m1!4b1!4m5!3m4!1s0x470fe9c2d4b58abf:0xb70956aec205e0f5!8m2!3d51.1078852!4d17.0385376"));


    Button contactsButton;
    Button messagesButton;
    Button mapsButton;
    EditText contactsTelephoneNumber;
    EditText messagesTelephoneNumber;
    EditText messagesBody;
    EditText mapsPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsButton = (Button) findViewById(R.id.contacts_button);
        messagesButton = (Button) findViewById(R.id.messages_button);
        mapsButton = (Button) findViewById(R.id.maps_button);
        contactsTelephoneNumber = (EditText) findViewById(R.id.telephone_number_textedit);
        messagesTelephoneNumber = (EditText) findViewById(R.id.message_telephone_number_textedit);
        messagesBody = (EditText) findViewById(R.id.message_body_textedit);
        mapsPlace = (EditText) findViewById(R.id.maps_body_textedit);

        contactsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "tel://"+contactsTelephoneNumber.getText().toString();
                Intent contactsActivity = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
                startActivity(contactsActivity);
            }
        });

        messagesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String sms = "sms://";
                startActivity(messagesActivity);
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(mapsActivity);
            }
        });
    }

}
