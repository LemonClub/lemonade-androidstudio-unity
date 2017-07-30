package net.lemontree.lemonade;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getResources().getIdentifier("@layout/activity_splash", "id", getPackageName()));

        // Toast.makeText(getApplicationContext(), "LOADING..", Toast.LENGTH_SHORT).show();

        final TextView nameTxt = (TextView)findViewById(R.id.user_name);
        nameTxt.setText("USER_NAME");

        final TextView lvTxt = (TextView)findViewById(R.id.user_name);
        lvTxt.setText("Lv. 1");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }
}
