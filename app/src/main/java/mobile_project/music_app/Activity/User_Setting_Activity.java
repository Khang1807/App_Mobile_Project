package mobile_project.music_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import mobile_project.music_app.Fragment.Fragment_UserPlayer;
import mobile_project.music_app.R;

public class User_Setting_Activity extends AppCompatActivity {
    View view;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_setting_activity);
        button = findViewById(R.id.back_user_player);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frag_user,new Fragment_UserPlayer()).commit();
            }
        });
    }
}
