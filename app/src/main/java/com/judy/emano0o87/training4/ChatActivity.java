package com.judy.emano0o87.training4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    Message newMessage;
    MessageAdapter adapter;
    EditText messagetxt;
    ListView listView;
    String msgTime;
    ImageView messagestatus;
    ImageView imageMsgView ;
    static ArrayList<Message> senddata;
    long start;
    de.hdodenhof.circleimageview.CircleImageView camerChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(savedInstanceState != null)
//        {
//            if(savedInstanceState.containsKey("data")) {
//                senddata = (ArrayList<Message>) savedInstanceState.getSerializable("data");
//            }}
//            else {//            }

        setContentView(R.layout.activity_chat);

        if(senddata == null)
            senddata = new ArrayList<>();
        AndroidThreeTen.init(this);
        start = System.currentTimeMillis();
        adapter = new MessageAdapter(ChatActivity.this, senddata);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.myActionBar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        imageMsgView = (ImageView) findViewById(R.id.imageMsg);
        messagetxt = (EditText) findViewById(R.id.msgEdit);
        messagestatus = (ImageView) findViewById(R.id.messagestatus);
        listView = (ListView) findViewById(R.id.list);
        listView.setStackFromBottom(true);
        listView.setAdapter(adapter);

        insertNewImageMsg();

        messagetxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    findViewById(R.id.camera).setVisibility(View.VISIBLE);
                } else
                    findViewById(R.id.camera).setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        FloatingActionButton sendBtn = (FloatingActionButton) findViewById(R.id.sendMsg);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNewMesg();
            }});

        //camera choice button to show dialog
        camerChoice = (CircleImageView) findViewById(R.id.camera);
        camerChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showDialog = new Intent(ChatActivity.this,DialogActivity.class);
                startActivity(showDialog);
            }
        });

    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if(savedInstanceState != null)
//        {
//            if(savedInstanceState.containsKey("data")) {
//                senddata = (ArrayList<Message>) savedInstanceState.getSerializable("data");
//            }}
//        else {
//            senddata = new ArrayList<>();
//        }
//    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
   //     imageMsgView =(ImageView) findViewById(R.id.imageMsg);
        super.onSaveInstanceState(outState);
    }

    private void insertNewMesg() {
msgTime =  msgTime = TimeUnit.SECONDS.toSeconds((System.currentTimeMillis() - start) / 1000) +
        " seconds ago , " +
        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss a"));

        if (messagetxt.getText().toString().length() == 0) {
            Toast.makeText(ChatActivity.this, "You need to type msg!!", Toast.LENGTH_LONG).show();
        } else {
            newMessage = new Message(messagetxt.getText().toString(),msgTime,
                    R.drawable.msg_status_gray_waiting,"sender");

            senddata.add(newMessage);
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
        messagetxt.setText("");
    }

    private void insertNewImageMsg() {

        msgTime =  msgTime = TimeUnit.SECONDS.toSeconds((System.currentTimeMillis() - start) / 1000) +
                " seconds ago , " +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss a"));

        Intent in = getIntent();
        if (in.hasExtra("imageUrl")) {
            Uri image = Uri.parse(in.getStringExtra("imageUrl"));
            if (image != null) {
                Message msg = new Message(true
                        , R.drawable.msg_status_gray_waiting,
                        "sender",image.getPath(),msgTime);
                senddata.add(msg);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            //one check - sendAll
            case R.id.sendAll:
                sendAll();
                return true;
            //two check - ReadAll
            case R.id.readAll:
                readAll();
                return true;
            //send from other side
            case R.id.partnerMsg:
                resend();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //send from other side
    private void resend() {
        if (senddata.size()>0)
        {
            Message msg = new Message(senddata.get(senddata.size()-1).getMessageText(),
                    senddata.get(senddata.size()-1).getMessageTime(),
                    senddata.get(senddata.size()-1).getMessageStatus(),"reciever");
            senddata.add(msg);
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }else return;
    }

    //two check - ReadAll
    private void readAll() {
        for (int i = 0; i < senddata.size(); i++) {
            senddata.get(i).setMessageStatus(R.drawable.msg_status_client_read);
        }
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    //one check - sendAll
    private void sendAll() {

        for (int i = 0; i < senddata.size(); i++) {
            senddata.get(i).setMessageStatus(R.drawable.msg_status_server_receive);
        }
     ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }
    //    protected void onSaveInstanceState(Bundle outState) {
    //        outState.putSerializable("data", senddata);
    //        super.onSaveInstanceState(outState);
    //    }    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    //   }


}
