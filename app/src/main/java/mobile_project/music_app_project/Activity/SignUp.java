package mobile_project.music_app_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    Button btn_signup;
    TextView click_signin;
    TextInputLayout email, password,userName;
    boolean email_check = false;
    private String name_user,password_user,email_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        anhxa();
        click_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(TextUtils.isEmpty(userName.getEditText().getText().toString().trim())){
                   Toast.makeText(SignUp.this, "Bạn hãy nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
               }else{
                   if (TextUtils.isEmpty(email.getEditText().getText().toString().trim())){
                       Toast.makeText(SignUp.this, "Bạn hãy nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                   }else{
                       if(TextUtils.isEmpty(password.getEditText().getText().toString().trim())){
                           Toast.makeText(SignUp.this, "Bạn hãy nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                       }else{
                           checkEmail(email);
                           if(email_check == true){
                               register();
                           }
                       }
                   }
               }
            }
        });
    }
    private void register(){
        name_user = userName.getEditText().getText().toString().trim();
        email_user = email.getEditText().getText().toString().trim();
        password_user = password.getEditText().getText().toString().trim();
        DataService networkService = APIService.getService();
        Call<ResponseModel> register = networkService.register(email_user,password_user,name_user);
        register.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getStatusText().equals("OK")) {
                        Toast.makeText(SignUp.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                        Intent intent_signup = new Intent( SignUp.this, SignIn.class);
                        startActivity(intent_signup);
                    } else {
                        Log.i("error","error");
                        Toast.makeText(SignUp.this, "Email đã tồn tại!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }

    private void checkEmail(TextInputLayout email){
        email_user = email.getEditText().getText().toString().trim();

        if(!email_user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email_user).matches()){
            email_check = true;
        }else{
            Toast.makeText(SignUp.this, "Email không đúng định dạng!", Toast.LENGTH_LONG).show();
        }
    }
    private void anhxa() {
       click_signin = findViewById(R.id.txt_signin);
       btn_signup = findViewById(R.id.btn_signup);
       email = findViewById(R.id.txt_email_signup);
       password = findViewById(R.id.txt_password_signup);
       userName = findViewById(R.id.txt_username_signup);
    };
 }
