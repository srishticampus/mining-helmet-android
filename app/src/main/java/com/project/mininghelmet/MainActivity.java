package com.project.mininghelmet;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText ipEt;

    private String ipAddress;
    private WebView temperatureWebView;
    private WebView helmetWebView;


    // define the number of times to call the method
    int numTimesToCallMethod = 50;

    // create a new handler
    Handler handler = new Handler();

    // define a boolean flag to control whether the method should continue to be called
    boolean shouldCallMethodHelmet = false;

    boolean shouldCallMethodTemp = false;

    private Button tempStartBt;
    private Button tempStopBt;
    private Button helmetStartBt;
    private Button helmetStopBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        WebSettings webSettings = temperatureWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebSettings webSettingsOne = helmetWebView.getSettings();
        webSettingsOne.setJavaScriptEnabled(true);


        // create a new runnable that calls the method
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                //  myMethod(); // replace "myMethod" with the name of the method you want to call

                // check the flag to determine whether to continue calling the method or not
                if (shouldCallMethodTemp) {
                    handler.postDelayed(this, 10000); // 5000 milliseconds = 5 seconds

                    if (ipEt.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter The Ip Address", Toast.LENGTH_SHORT).show();
                    } else {

                        ipAddress = ipEt.getText().toString();
                        setTemperatureWebView(ipAddress, "temperature");


                    }
                }
            }
        };

        // create a new runnable that calls the method
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {


                //  myMethod(); // replace "myMethod" with the name of the method you want to call

                // check the flag to determine whether to continue calling the method or not
                if (shouldCallMethodHelmet) {
                    handler.postDelayed(this, 10000); // 5000 milliseconds = 5 seconds

                    if (ipEt.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter The Ip Address", Toast.LENGTH_SHORT).show();
                    } else {

                        ipAddress = ipEt.getText().toString();
                        setHelmetStatusWebView(ipAddress, "helmet");

                    }
                }
            }
        };

//        helmetStartBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shouldCallMethodHelmet = true;
//
//                // post the runnable to the handler with a delay of 5 seconds
//                for (int i = 0; i < numTimesToCallMethod; i++) {
//                    handler.postDelayed(runnable1, i * 10000); // 5000 milliseconds = 5 seconds
//                }
//            }
//        });
//        helmetStopBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shouldCallMethodHelmet = false;
//            }
//        });
//
//        tempStartBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shouldCallMethodTemp = true;
//
//                // post the runnable to the handler with a delay of 5 seconds
//                for (int i = 0; i < numTimesToCallMethod; i++) {
//                    handler.postDelayed(runnable, i * 10000); // 5000 milliseconds = 5 seconds
//                }
//            }
//        });
//        tempStopBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shouldCallMethodTemp = false;
//            }
//        });

        helmetStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ipEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter The Ip Address", Toast.LENGTH_SHORT).show();
                } else {

                    ipAddress = ipEt.getText().toString();
                    setHelmetStatusWebView(ipAddress, "helmet");
                    setTemperatureWebView(ipAddress, "temperature");

                }


            }
        });

    }


    public void setTemperatureWebView(String webUrl, String webEndPoint) {
        temperatureWebView.loadUrl("http://" + webUrl + "/" + webEndPoint);
        //webViewOne.loadUrl("https://www.google.com/");
        temperatureWebView.setWebViewClient(new WebViewClient());
        temperatureWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                // show "No data" message in WebView
                String html = "<html><body><h1>No data</h1></body></html>";
                view.loadData(html, "text/html", "UTF-8");

            }
        });

    }

    public void setHelmetStatusWebView(String webUrl, String webEndPoint) {
        helmetWebView.loadUrl("http://" + webUrl + "/" + webEndPoint);
        helmetWebView.setWebViewClient(new WebViewClient());
        helmetWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                // show "No data" message in WebView
                String html = "<html><body><h1>No data</h1></body></html>";
                view.loadData(html, "text/html", "UTF-8");

            }
        });
    }

    private void initView() {
        ipEt = findViewById(R.id.ip_et);
        temperatureWebView = findViewById(R.id.temperature_web_view);
        helmetWebView = findViewById(R.id.helmet_web_view);
        tempStartBt = findViewById(R.id.temp_start_bt);
        tempStopBt = findViewById(R.id.temp_stop_bt);
        helmetStartBt = findViewById(R.id.helmet_start_bt);
        helmetStopBt = findViewById(R.id.helmet_stop_bt);
    }
}