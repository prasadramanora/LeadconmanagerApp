package com.ramanora.leadcon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.ramanora.leadcon.activites.Activity_Login;

public class MainActivity extends AppCompatActivity {

    WebView mwebviewvirtualexhibition;
    String url;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;
    private ProgressBar progressBar;
    String email, password;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sh = getSharedPreferences("myprefe", 0);
        email = sh.getString("email", null);
        password = sh.getString("password", null);


        if (password.equalsIgnoreCase("demo")) {

            //url = "https://leadcon.co/LCManager/applogin.php?email="+email+"&password="+password;

            url = "https://leadcon.co/LCManager/demoapplogin.php?email=" + email + "&password=" + password;

        } else {
            url = "https://leadcon.co/LCManager/applogin.php?email=" + email + "&password=" + password;
        }

        Log.e("WebUrl", url);


        spinner = (ProgressBar) findViewById(R.id.progressBar1);
        mwebviewvirtualexhibition = (WebView) findViewById(R.id.webviewleadcon);

        mwebviewvirtualexhibition.setWebViewClient(new WebViewClient() {

        });
        mwebviewvirtualexhibition.loadUrl(url);

        mwebviewvirtualexhibition.getSettings().setLoadWithOverviewMode(true);
        mwebviewvirtualexhibition.getSettings().setUseWideViewPort(true);
        WebSettings webSettings = mwebviewvirtualexhibition.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // webSettings.setBuiltInZoomControls(true);
        // webSettings .setCacheMode( WebSettings.LOAD_DEFAULT );
        mwebviewvirtualexhibition.setWebViewClient(new CustomWebViewClient());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click

        int id = item.getItemId();


        switch (item.getItemId()) {

            case R.id.action_logout:

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(getSpannedText("Are you sure want to Logout"))
                        .setCancelable(false)
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                sh = getSharedPreferences("myprefe", 0);
                                editor = sh.edit();
                                editor.putString("email", "");
                                editor.putString("password", "");
                                editor.commit();

                                Intent intent = new Intent(MainActivity.this, Activity_Login.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                            }
                        })
                        .show();


                break;

            case R.id.action_refresh:

           /*     mwebviewvirtualexhibition.setWebViewClient(new WebViewClient() {

                });
                mwebviewvirtualexhibition.loadUrl("https://leadcon.co/LCManager/applogin.php?email="+email+"&password="+password);
                mwebviewvirtualexhibition.getSettings().setLoadWithOverviewMode(true);
                mwebviewvirtualexhibition.getSettings().setUseWideViewPort(true);
                WebSettings webSettings = mwebviewvirtualexhibition.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);
                // webSettings.setBuiltInZoomControls(true);
                // webSettings .setCacheMode( WebSettings.LOAD_DEFAULT );
                mwebviewvirtualexhibition.setWebViewClient(new CustomWebViewClient());*/

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private Spanned getSpannedText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(text);
        }
    }

    public class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(webview.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            spinner.setVisibility(View.GONE);

            view.setVisibility(mwebviewvirtualexhibition.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mwebviewvirtualexhibition.canGoBack()) {
                        mwebviewvirtualexhibition.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
