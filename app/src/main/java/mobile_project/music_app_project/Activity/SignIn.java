package mobile_project.music_app_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.os.Bundle;

import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;



import mobile_project.music_app_project.Model.ModelUser;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    Button signin_button;
    ImageView image;
    TextView logoText, logoText2,click_signup;
    TextInputLayout email, password;
    private String name_user,password_user,email_user,imgurl;
    ArrayList<ModelUser> dataSource;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        click_signup = findViewById(R.id.txt_signup);
        image = findViewById(R.id.img_logo_only_login);
        logoText = findViewById(R.id.txt_welcom_login);
        logoText2 = findViewById(R.id.txt_logintocontinue);
        email = findViewById(R.id.txt_email_signin);
        password = findViewById(R.id.txt_password_signin);
        signin_button = findViewById(R.id.btn_login);

        click_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_signin1 = new Intent(SignIn.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image, "logo_image");
                pairs[1] = new Pair<View,String>(logoText, "logo_text");
                pairs[2] = new Pair<View,String>(logoText2, "logo_text_2");
                pairs[3] = new Pair<View,String>(email, "email_trans");
                pairs[4] = new Pair<View,String>(password, "password_trans");
                pairs[5] = new Pair<View,String>(signin_button, "button_signin_trans");
                pairs[6] = new Pair<View,String>(click_signup, "signin_signup_trans");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignIn.this,pairs);
                    startActivity(intent_signin1 ,options.toBundle());
                }
            }
        });
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                get_info_user(email_user);
            }
        });
    }
    private void login() {
        email_user = email.getEditText().getText().toString().trim();
        password_user = password.getEditText().getText().toString().trim();
        DataService networkService = APIService.getService();
        Call<ResponseModel> login = networkService.login(email_user,password_user);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getStatusText().equals("OK")) {
                        Intent intent_signin2 = new Intent( SignIn.this, MainActivity.class);
                        startActivity(intent_signin2);
                    } else {
                        Log.i("error","error");
                        Toast.makeText(SignIn.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }
    private void get_info_user(String email){
        DataService networkService = APIService.getService();
        Call<ResponseModel> get_info_user = networkService.getUserInfo(email);
        get_info_user.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getStatusText().equals("OK")) {
                        Gson gson = new Gson();
                        String json = gson.toJson(response.body().getContent());

                        JSONObject result = null;

                        JSONArray info_user;

                        try {
                            result = new JSONObject(json);

                            JSONObject datas = result.getJSONObject("datas");

                            info_user = datas.getJSONArray("userInfo");

                            for(int i = 0; i < info_user.length(); i++) {
                                JSONObject info = info_user.getJSONObject(i);

                                name_user = info.getString("userName");
                            }
                            SharedPreferences cached_login = getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = cached_login.edit();
                            editor.putString("name_user",name_user);
                            editor.commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }} else {
                        Log.i("error","erorr");
                        Toast.makeText(SignIn.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }
}

