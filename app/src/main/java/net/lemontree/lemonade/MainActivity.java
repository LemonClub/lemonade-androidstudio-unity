package net.lemontree.lemonade;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {
    String toastMessage;
    String accessToken = "";

    private static final int SEND_THREAD_TOAST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Handler handle = new Handler() {
        public  void handleMessage (Message msg) {
            switch (msg.what) {
                case SEND_THREAD_TOAST:
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    };

    public void init (String token) {
        accessToken = token;
        UnityPlayer.UnitySendMessage("_Lemonade", "ReceiveInit", "FAIL");
        // 유저 로그인 성공에 성공했다면 로그인된 유저의 토큰을 보내줌
        // UnityPlayer.UnitySendMessage("_Lemonade", "ReceiveInit", playerToken);
    }

    /**
     * @brief 토스트 메시지를 화면에 띄워줌
     * @param message 메시지 내용
     */
    public void ToastMessage (String message) {
        toastMessage = message;
        handle.sendEmptyMessage(SEND_THREAD_TOAST);
    }

    /**
     * @brief Splash Activity 로 이동, 로그인 인증함
     * @param activity 현재 액티비티 ( UnityNativeActivity "currentActivity" )
     */
    public void Connect(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifi.isConnected() || mobile.isConnected()) {
            Intent intent = new Intent(activity, Splash.class);
            UnityPlayer.currentActivity.startActivity(intent);
        } else {
            ToastMessage("INTERNET DISCONNECT");
        }
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
}
