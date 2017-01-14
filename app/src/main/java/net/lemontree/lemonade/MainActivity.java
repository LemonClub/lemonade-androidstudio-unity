package net.lemontree.lemonade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.firebase.messaging.*;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends UnityPlayerActivity {
    String toastMessage;
    Context context;

    private static final int SEND_THREAD_TOAST = 0;
    public  static String pushTitle = "Lemontree";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*try {
            FirebaseMessaging.getInstance().subscribeToTopic("notice");
            String token = FirebaseInstanceId.getInstance().getToken();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }

    public Handler handle = new Handler() {
        public  void handleMessage (Message msg) {
            switch (msg.what) {
                case SEND_THREAD_TOAST:
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    SendString();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * @brief 토스트 메시지를 화면에 띄워줌
     * @param message 메시지 내용
     */
    public void ToastMessage (String message) {
        toastMessage = message;
        handle.sendEmptyMessage(SEND_THREAD_TOAST);
    }

    /**
     * @brief 내용을 받음
     */
    public void GetString() {
        handle.sendEmptyMessage(1);
    }

    /**
     * @brief Splash Activity 로 이동, 로그인 인증함
     * @param activity 현재 액티비티 ( UnityNativeActivity "currentActivity" )
     */
    public void Connect(Activity activity) {
        Intent intent = new Intent(activity,Splash.class);
        UnityPlayer.currentActivity.startActivity(intent);
        /*ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifi.isConnected() || mobile.isConnected()) {
            Intent intent = new Intent(activity,Splash.class);
            UnityPlayer.currentActivity.startActivity(intent);
        } else {
            ToastMessage("INTERNET DISCONNECT");
        }*/
    }

    public void setPushTitle(String arg) {
        pushTitle = arg;
    }

    public void SendString() {
        UnityPlayer.UnitySendMessage("Lemonade", "ReceiveString", "Android Test");
    }

    private void getUserLogin() {
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        String token =  data.getString("LA_USER_TOKEN", "");

        if (token.equals(""))
        {
            // 로그인 되어 있지 않음 || 처음 로그인
            // 서비스 앱 (레몬에이드)로 이동하여 로그인 되있는 유저정보 받아옴
        }
        else
        {
            
        }
    }

    public void checkToken() {
        ToastMessage(FirebaseInstanceId.getInstance().getToken());
    }
}
