package ucf.knightsdealistic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import android.app.Activity;
import android.util.Log;


import java.net.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ucf.knightsdealistic.database.datasource.StoreDataSource;
import ucf.knightsdealistic.database.model.Deal;
import ucf.knightsdealistic.database.model.Store;


public class ForgotPassword extends ActionBarActivity {

    EditText txtemailid;
    private String user;
    private String password;
    StoreDataSource storeDataSource;
    long storeId;
    Store store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgotpassword);
        storeDataSource = new StoreDataSource(getApplicationContext());
        txtemailid = (EditText) findViewById(R.id.txtEmailId);
        final Button submitBtn = (Button) findViewById(R.id.btnSubmit);
        final Button btnCancel = (Button) findViewById(R.id.btnCancel);
        final Button btnBack = (Button) findViewById(R.id.btnBack);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailAddress = txtemailid.getText().toString();
                Store store = null;
                try {
                    store = storeDataSource.getStoreByEmailAddress(emailAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (store.getEmailId().equals(emailAddress)) {
                    try {
                        Mail m = new Mail();
                        m.sendMail(emailAddress, "Knights Dealistic:Retrieve Password", "Your password is:" + store.getStorePassword());
                        Toast.makeText(getApplicationContext(), "Please check your email to see the password.", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Error occurred while sending mail", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtemailid.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ForgotPassword.this, FirstScreen.class);
                                        intent.putExtra("selectedTab",new String("1"));
                                        startActivity(intent);
                                        finish();
                                    }
                                }
        );

    }




}






