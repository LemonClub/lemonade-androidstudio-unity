package net.lemontree.lemonade;

/**
 * Created by kyech on 2017-01-06.
 */
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();

        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        //sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("LA_USER_TOKEN", token)
                .add("LA_USER_NAME", "Jason")
                .add("LA_USER_ID", "id")
                .add("LA_USER_PW", "pass")
                .add("LA_USER_EMAIL", "lemon@gmail.com")
                .add("LA_USER_SEX", "0")
                .add("LA_USER_AGE", "19")
                .add("LA_USER_PHONE", "01012345678")
                .add("LA_USER_EXP", "0")
                .add("LA_USER_PROFILE", "basic_profile.png")
                .add("LA_USER_FRIENDS", "")
                .add("LA_USER_GAMES", "")
                .add("LA_USER_ACHIEVEMENT", "")
                .build();

        //request
        Request request = new Request.Builder()
                .url("http://lemontree.dothome.co.kr/user_register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}