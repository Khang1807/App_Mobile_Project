package mobile_project.music_app_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import mobile_project.music_app_project.R;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation top_anim,bottom_anim;
    ImageView logo;
    TextView name,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Animations
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Gan ID
        logo = findViewById(R.id.img_logo_only);
        name = findViewById(R.id.txt_logoname);
        slogan = findViewById(R.id.txt_slogan);

        //Gan Animation cho img,txt
        logo.setAnimation(top_anim);
        name.setAnimation(bottom_anim);
        slogan.setAnimation(bottom_anim);

        //Hàm tự động chuyển từ màn hình splash sang login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_splash = new Intent(Splash.this, SignIn.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(name, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splash.this,pairs);
                    startActivity(intent_splash,options.toBundle());
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}