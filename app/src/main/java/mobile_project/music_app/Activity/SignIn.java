package mobile_project.music_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import mobile_project.music_app.Fragment.Fragment_TrangChu;
import mobile_project.music_app.R;

public class SignIn extends AppCompatActivity {
    Button signin_button;
    ImageView image;
    TextView logoText, logoText2,click_signup;
    TextInputLayout email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        Button btn_1 = (Button) findViewById(R.id.btn_login);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.home_frg, new Fragment()).commit();
            }
        });
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
                Intent intent_signin = new Intent(SignIn.this, SignUp.class);

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
                    startActivity(intent_signin ,options.toBundle());
                }
            }
        });
    }
}

