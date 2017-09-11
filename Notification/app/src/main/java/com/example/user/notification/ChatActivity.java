package com.example.user.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 2017-09-12.
 */

public class ChatActivity extends AppCompatActivity {

    String chatContent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        chatContent = intent.getStringExtra("Param1");

        TextView text1 = (TextView) findViewById(R.id.texto1);
        text1.setText("This is chat");
        TextView text2 = (TextView) findViewById(R.id.texto2);
        text2.setText("hi there");
        TextView text3 = (TextView) findViewById(R.id.texto3);
        text3.setText("chat");
        setTitle(chatContent);

    }

}
