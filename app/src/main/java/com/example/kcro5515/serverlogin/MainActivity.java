package com.example.kcro5515.serverlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {
    boolean logging_in = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {
        EditText editText_id = (EditText) findViewById(R.id.userID);
        String userid = editText_id.getText().toString();

        EditText editText_psk = (EditText) findViewById(R.id.PSK);
        String psk = editText_psk.getText().toString();

        if(logging_in){
            logging_in = false;

            String sftp_host = "192.43.239.21";
            int    sftp_port = 22;
            String dir_location = "~/photos/";



            Session session     = null;
            Channel channel     = null;
            ChannelSftp channelSftp = null;
            if (userid != null){
                try{
                    Log.d("TAG","User ID: "+userid+" Password: "+psk);
                    JSch jsch = new JSch();
                    session = jsch.getSession(userid,sftp_host,sftp_port);
                    session.setPassword(psk);
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.connect();
                    Log.d("TAG","(Permission granted) :)");
                    //channel = session.openChannel("sftp");
                    //channel.connect();
                    //channelSftp = (ChannelSftp)channel;
                    //channelSftp.cd(dir_location);
                    //File f = new File(FILETOTRANSFER);
                    //channelSftp.put(new FileInputStream(f), f.getName());

                }
                catch (Exception e){
                    Log.d("TAG","Message: " + e);
                    logging_in = true;
                }

        }
            else{
                Log.d("TAG","Please enter password...");
                //Out message to GUI

            }
        }
    }


}
