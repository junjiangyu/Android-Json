package com.example.johnny.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String fname;
    String lname;
    String dob;
    String gender;
    String email;


    EditText fnameinput;
    EditText lnameinput;
    EditText dobinput;
    EditText genderinput;
    EditText emailinput;


    Button submitbtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //following two line enable the use of network on the main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        fnameinput = (EditText) findViewById(R.id.nameInput);
        lnameinput  = (EditText) findViewById(R.id.editText);
        dobinput  = (EditText) findViewById(R.id.editText2);
        genderinput  = (EditText) findViewById(R.id.editText3);
        emailinput  = (EditText) findViewById(R.id.editText4);

        submitbtn = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.resultview);




        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = fnameinput.getText().toString();
                lname = lnameinput.getText().toString();
                dob = dobinput.getText().toString();
                gender = genderinput.getText().toString();
                email = emailinput.getText().toString();

                TextView outputview = (TextView) findViewById(R.id.resultview);

                //sending the name parameter to internet
                try {


                    URLConnection urlConn;
                    DataOutputStream printout;
                    DataInputStream input;
                    URL url = new URL("https://johnnyuusqlspringboot.cfapps.io/Users");
                    urlConn = url.openConnection();
                    urlConn.setDoInput (true);
                    urlConn.setDoOutput (true);
                    urlConn.setUseCaches (false);
                    urlConn.setRequestProperty("Content-Type","application/json");
                    urlConn.setRequestProperty("Host", "android.schoolportal.gr");
                    urlConn.connect();
                    //Create JSONObject here
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id","2");
                    jsonObject.put("fname", fname);
                    jsonObject.put("lname",lname);
                    jsonObject.put("dob",dob);
                    jsonObject.put("gender",gender );
                    jsonObject.put("email",email);
                    String json = jsonObject.toString();

                    Toast.makeText(MainActivity.this, json, Toast.LENGTH_LONG).show();

                    printout = new DataOutputStream(urlConn.getOutputStream ());
                    printout.writeBytes(URLEncoder.encode(json,"UTF-8"));
                    printout.flush ();
                    printout.close ();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
