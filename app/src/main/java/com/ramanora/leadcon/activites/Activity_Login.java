package com.ramanora.leadcon.activites;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ramanora.leadcon.AppStatus;
import com.ramanora.leadcon.MainActivity;
import com.ramanora.leadcon.R;
import com.ramanora.leadcon.Utils;
import com.ramanora.leadcon.progressbar.KProgressHUD;
import com.ramanora.leadcon.volley.VolleySingelton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener {
    private static View view;
    private EditText mEdtEmail, mEdtPassword;
    private Button mBtnloginBtn;
    String EmailId, Password;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;

    KProgressHUD hud;
    RequestQueue mRequestQueue;
    //String url;
    private String android_id;
    String CompanyKey;
    String token;
    String login = null;
    public static Boolean shown = false;
    public boolean isFirstTime = false;
    String androidversion;
String url, urlweb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent1 = getIntent();
        initViews();
        mRequestQueue = Volley.newRequestQueue(Activity_Login.this);
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();
      //  token = sh.getString("token", "");

       //     Log.d("MyFirebaseIIDService", "token: " + token);
        setListeners();
    }

    private void initViews() {
        mEdtEmail = (EditText) findViewById(R.id.login_emailid);
        mEdtPassword = (EditText) findViewById(R.id.login_companykey);
        mBtnloginBtn = (Button) findViewById(R.id.loginBtn);
    }


    private void setListeners() {
        mBtnloginBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:

/*                String username = String.valueOf(mEdtEmail.getText());
                if (!username.trim().equals("")){
                    sharedPref();
                    Intent intent = new Intent(Login_Fragment.this, ActivitySelectionList.class);
                    intent.putExtra("Company_key",username);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login_Fragment.this, "Login Unsuccessful!", Toast.LENGTH_SHORT).show();
                }*/

                try {
                    if (AppStatus.getInstance(Activity_Login.this).isOnline()) {

                        getData();
                    } else {

                        Toast.makeText(Activity_Login.this, "No Internet Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public void sharedPref() {

        EmailId = mEdtEmail.getText().toString();
        Password = mEdtPassword.getText().toString();

        Log.d("test", "sharedPref: save showdata");
        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();
        editor.putString("email", String.valueOf(EmailId));
        editor.putString("password", String.valueOf(Password));
        editor.commit();

    }

    public void getData() {
        //progress bar
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.spin_animation);
        final AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        drawable.start();


        hud = KProgressHUD.create(this)
                .setCustomView(imageView)
                .setSize(100, 100)
                .setDimAmount(0.8f)
                .setLabel("Please wait");

        hud.show();

        EmailId = mEdtEmail.getText().toString();
        Password = mEdtPassword.getText().toString();



//leadcon.co/ManagerApp/Api/GET/ManagerLogin.php?email=manager@sales.com&password=password2
        //  url = "https://leadcon.co/GET/validatelogin.php?CompanyKey=" + EmailId + "&deviceid=" + android_id;
        // url = "https://leadcon.co/ManagerApp/Api/GET/ManagerLogin.php?email=manager@ramanoratest.com&password=password2";
        // url = "https://leadcon.co/LCManager/applogin.php?email=sales@leadcon.co&password=qwerty";

        if (Password.equalsIgnoreCase("demo")) {

           url =  "https://leadcon.co/LCManager/DemoFirstAppLogin.php?email="+EmailId+"&password="+Password;

        } else
        {
            url = "https://leadcon.co/LCManager/FirstAppLogin.php?email="+EmailId+"&password="+Password;
        }





        Log.d("test_testnew", "onResponse: url " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                hud.dismiss();
                try {

                    Log.d("test_testnew", "onResponse: Dc response" + response);

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equalsIgnoreCase("false")) {
                        //Toast.makeText(Login_Fragment.this, "Key is Invalid", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Login.this);
                        dialog.setCancelable(false);
                        dialog.setIcon(android.R.drawable.ic_dialog_alert);
                        dialog.setTitle("Leadcon Manager App");
                        dialog.setMessage(message+ "\n" + "Thank you.");
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Action for "Delete".
                                dialog.dismiss();
                            }
                        });

                        final AlertDialog alert = dialog.create();
                        alert.show();
                    }

                    else if(status.equalsIgnoreCase("true"))
                    {
                        sharedPref();

                        urlweb = "https://leadcon.co/LCManager/applogin.php?email="+EmailId+"&password="+Password;
                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);

                        intent.putExtra("url", urlweb);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hud.dismiss();
                Log.d("test", "onErrorResponse: " + error.toString());
                Toast.makeText(Activity_Login.this, "No Internet Connection, please check your internet connection.", Toast.LENGTH_SHORT).show();
                error.getMessage();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingelton.getInstance(Activity_Login.this).getRequestQueue()
                .add(jsonObjectRequest);
    }


    public void UpdateLoginData() {

        String urlupdatelogin = "https://leadcon.co/POST/NewUpdateLoginData.php?token=" + token + "&email=" + EmailId + "&deviceid=" + android_id + "&devicetype=android";

        Log.d("test_testnew", "onResponse: url " + urlupdatelogin);

        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();
        token = sh.getString("token", "");

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlupdatelogin,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try {

                            Log.d("test_testnew", "onResponse: Track Data :" + response);

                            JSONObject jsonObject = new JSONObject(response);

                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");


                            if (status.equalsIgnoreCase("false")) {
                         //       Toast.makeText(Login_Fragment.this, "Device is Registered with another key", Toast.LENGTH_SHORT).show();

                                AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Login.this);
                                dialog.setCancelable(false);
                                dialog.setIcon(android.R.drawable.ic_dialog_alert);
                                dialog.setTitle("Leadcon Manager App");
                                dialog.setMessage(message+ "\n" + "Thank you.");
                                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Action for "Delete".
                                        dialog.dismiss();
                                    }
                                });

                                final AlertDialog alert = dialog.create();
                                alert.show();


                            } else if (status.equalsIgnoreCase("true")) {


                                if (!Utils.LOCAL_ANDROID_VERSION.equals(androidversion)) {

                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Login.this);
                                    dialog.setCancelable(false);
                                    dialog.setIcon(android.R.drawable.ic_dialog_alert);
                                    dialog.setTitle(Utils.LOCAL_ANDROID_VERSION_DAILOG_TITLE);
                                    dialog.setCancelable(false);
                                    dialog.setMessage(Utils.LOCAL_ANDROID_VERSION_MESSAGE);
                                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {


                                            sh = getSharedPreferences("myprefe", 0);
                                            editor = sh.edit();

                                            editor.putString("chk_android_version", "chk_android_version");
                                            startActivity(new Intent(Intent.ACTION_VIEW,
//Uri.parse("http://play.google.com/store/apps/details?id=com.olacabs.customer")));
                                                    Uri.parse("https://play.google.com/store/apps/details?id=app.ramanora.com.callr")));

                                        }
                                    });

                                    final AlertDialog alert = dialog.create();
                                    alert.show();
                                }


                                else {
                                    Intent intent = new Intent(Activity_Login.this, MainActivity.class);

                                    intent.putExtra("company_key", EmailId);
                                    startActivity(intent);

                                    Toast.makeText(Activity_Login.this, "Login Successfully.", Toast.LENGTH_SHORT).show();

                                    sh = getSharedPreferences("myprefe", 0);
                                    editor = sh.edit();

                                    editor.putString("login", "login");
                                    editor.putString("company_key", String.valueOf(CompanyKey));

                                    editor.commit();

                                    finish();

                                }



                             //   }
                            } else {

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Login.this, "No Interner Connection, please check your internet connection ", Toast.LENGTH_SHORT).show();
                        error.getMessage();
                        Log.d("test_testnew", "onErrorResponse: Error " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("token", token);
                params.put("email", EmailId);
                params.put("deviceid", android_id);
                params.put("devicetype", "android");

                Log.d("test_testnew", "getParams: " + params);
                return params;

            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingelton.getInstance(getApplicationContext()).getRequestQueue().add(stringRequest);

    }



}
