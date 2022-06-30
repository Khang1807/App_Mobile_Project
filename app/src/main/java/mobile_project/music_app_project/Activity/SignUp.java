package mobile_project.music_app_project.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;

import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private static final String TAG = SignUp.class.getName();
    Button btn_signup,btnSelectImage;
    TextView click_signin;
    TextInputLayout email, password,userName;
    boolean email_check = false;
    private String name_user,password_user,email_user;
    ImageView img_load_avatar;
    private static final int MY_REQUEST_CODE = 10;
    private Uri mUri;
    private ProgressDialog mProgressDialog;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data1 = result.getData();
                        if(data1 == null){
                            return;
                        }
                        Uri uri = data1.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            img_load_avatar.setImageBitmap(bitmap);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        anhxa();

        //Dialog

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Vui lòng đợi ...");

        // Qua trang dang nhap
        click_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Nut dang ky
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
                                if(mUri != null){
                                   register();
                                }
                            }
                        }
                    }
                }
            }
        });
        // Chon anh dai dien
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        SignUp.super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent1,"Chọn ảnh"));
    }

    private void register(){
        mProgressDialog.show();

        name_user = userName.getEditText().getText().toString().trim();
        email_user = email.getEditText().getText().toString().trim();
        password_user = password.getEditText().getText().toString().trim();

        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"), name_user);
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("multipart/form-data"), email_user);
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("multipart/form-data"), password_user);

        String real_path = RealPathUtil.getRealPath(this, mUri);
        Log.e("aaaaa",real_path);
        File file = new File(real_path);
        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part multipartAvt = MultipartBody.Part.createFormData(Const.KEY_AVT,file.getName(),requestBodyAvt);

        DataService networkService = APIService.getService();
        Call<ResponseModel> register = networkService.register(requestBodyUsername,requestBodyEmail,requestBodyPassword,multipartAvt);
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
                        Log.e("error","error");
                        Toast.makeText(SignUp.this, "Email đã tồn tại1!", Toast.LENGTH_LONG).show();
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
       btnSelectImage = findViewById(R.id.btn_loadimg);
       img_load_avatar = findViewById(R.id.img_load_avatar);
    }
 }
