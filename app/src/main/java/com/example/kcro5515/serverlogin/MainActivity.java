package com.example.kcro5515.serverlogin;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class MainActivity extends Activity {
    boolean logging_in = true;
    String username;
    String hostname;
    int port;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {
        Log.d("TAG","Login pressed");
        EditText editText_id = (EditText) findViewById(R.id.userID);
        username = editText_id.getText().toString();

        EditText editText_psk = (EditText) findViewById(R.id.PSK);
        password = editText_psk.getText().toString();

        hostname = "192.43.239.21";
        port = 22;
        String dir_location = "~/photos/";

        if(logging_in){
            logging_in = false;






            Session session     = null;
            Channel channel     = null;
            ChannelSftp channelSftp = null;
            if (username != null){
                try{
                    Log.d("TAG","User ID: "+username+" Password: "+password);
                    //ServerConnect.executeRemoteCommand(userid,psk,sftp_host,sftp_port);
                    new ServerConnect().execute("");


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

    private class ServerConnect extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {


            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(username, hostname, port);
                session.setPassword(password);

                // Avoid asking for key confirmation
                Properties prop = new Properties();
                prop.put("StrictHostKeyChecking", "no");
                session.setConfig(prop);

                session.connect();

                Log.d("TAG", "(Permission granted) :)");

            } catch (JSchException e) {
                e.printStackTrace();
                Log.d("TAG","Message: "+ e);
            }
            //channel = session.openChannel("sftp");
            //channel.connect();
            //channelSftp = (ChannelSftp)channel;
            //channelSftp.cd(dir_location);
            //File f = new File(FILETOTRANSFER);
            //channelSftp.put(new FileInputStream(f), f.getName());
            logging_in = true;
            return null;



        }


    }


}


