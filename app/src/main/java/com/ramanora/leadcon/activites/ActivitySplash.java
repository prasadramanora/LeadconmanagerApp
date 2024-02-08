package com.ramanora.leadcon.activites;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ramanora.leadcon.MainActivity;
import com.ramanora.leadcon.R;


import java.util.Random;
import java.util.Set;



@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class ActivitySplash extends AppCompatActivity {

    public static String MyPREFERENCES = "myprefe";
    private int timeoutMillis = 3000;
    private long startTimeMillis = 0;
    private static final int PERMISSIONS_REQUEST = 1234;
    private static final Random random = new Random();
    private TextView textView = null;
    private ImageView imageView;
    String Facility, CompanyName, androidversion, chk_android_version="";


    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAPTURE_AUDIO_OUTPUT,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    private static final int textViewID = View.generateViewId();

    public int getTimeoutMillis() {
        return timeoutMillis;
    }


    public String[] getRequiredPermissions() {
        String[] permissions = null;
        try {
            permissions = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (permissions == null) {
            return new String[0];
        } else {
            return permissions.clone();
        }
    }


    //shared preference

    public static String email;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;


    @TargetApi(23)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.lay_splash);

        // here initializing the shared preference

        sh = getSharedPreferences("myprefe", 0);
        email = sh.getString("email", null);



        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }


        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        textView = new TextView(this);
        textView.setTextSize(20);
        textView.setId(textViewID);
        textView.setGravity(30);

        mainLayout.addView(textView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        /** Set the background color. */
        int off = 128;
        int rest = 256 - off;
        int color = Color.argb(255, off + random.nextInt(rest), off + random.nextInt(rest), off + random.nextInt(rest));
        mainLayout.setBackgroundResource(R.drawable.splashscreen);

        setContentView(mainLayout);
        startTimeMillis = System.currentTimeMillis();

        startNextActivity();
/*
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        } else {

        }
*/


    }
/*
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST) {
            checkPermissions();
        }
    }*/


    private void startNextActivity() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //        textView.setText("Permissions granted...");
            }
        });
        long delayMillis = getTimeoutMillis() - (System.currentTimeMillis() - startTimeMillis);
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                finish();

                //   Log.d("test", "onCreate: " + email + " \n Companyname :" + CompanyName);

                if (email != null && !email.toString().trim().equals("") ) {


                    Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
                    startActivity(intent);
                }

                else {
                    Intent intent = new Intent(ActivitySplash.this, Activity_Login.class);
                    startActivity(intent);
                }

                /*if (email != null && !email.toString().trim().equals("") && login != null && !login.toString().trim().equals("")) {




*//*                    if (chk_android_version != "chk_android_version" || chk_android_version.equalsIgnoreCase("") || chk_android_version.equalsIgnoreCase(null) ||
                            chk_android_version.equalsIgnoreCase("null")) {
                        Intent intent = new Intent(ActivitySplash.this, Login_Fragment.class);
                        startActivity(intent);
                        finish();
                    } else {*//*
                        Intent intent = new Intent(ActivitySplash.this, ActivityConverting.class);
                        intent.putExtra("Company_key", email);
                        intent.putExtra("CompanyName", CompanyName);
                        startActivity(intent);
                        finish();
                   }


             //   }
                else {
                    *//*Intent intent = new Intent(ActivitySplash.this, Login_Fragment.class);
                    startActivity(intent);
                    finish();*//*


                    Intent intent = new Intent(ActivitySplash.this, ActivityEnquiryRegForm.class);
                    startActivity(intent);
                    finish();
                }
*/
            }
        }, delayMillis);
    }

/*    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        String[] ungrantedPermissions = requiredPermissionsStillNeeded();
        if (ungrantedPermissions.length == 0) {
            startNextActivity();
        } else {
            requestPermissions(ungrantedPermissions, PERMISSIONS_REQUEST);
        }
    }*/

    /**
     * Convert the array of required permissions to a {@link Set} to remove
     * redundant elements. Then remove already granted permissions, and return
     * an array of ungranted permissions.
     */
/*    @TargetApi(23)
    private String[] requiredPermissionsStillNeeded() {

        Set<String> permissions = new HashSet<String>();
        for (String permission : getRequiredPermissions()) {
            permissions.add(permission);
        }
        for (Iterator<String> i = permissions.iterator(); i.hasNext(); ) {
            String permission = i.next();
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                Log.d(ActivitySplash.class.getSimpleName(),
                        "Permission: " + permission + " already granted.");
                i.remove();
            } else {
                Log.d(ActivitySplash.class.getSimpleName(),
                        "Permission: " + permission + " not yet granted.");
            }
        }
        return permissions.toArray(new String[permissions.size()]);
    }*/

}





































