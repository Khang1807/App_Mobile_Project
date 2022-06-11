package mobile_project.music_app_project.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import mobile_project.music_app_project.Activity.MainActivity;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Activity.User_Setting_Activity;
import mobile_project.music_app_project.R;

public class Fragment_UserPlayer extends Fragment {
    View view;
    TextView txt_name;
    Button btn_dangxuat,btn_setting_user;
    Intent intent;
    Intent intent1;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_user_player,container,false);
        anhxa();

        txt_name = view.findViewById(R.id.tennguoidung);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        txt_name.setText(sharedPreferences.getString("name_user",""));

        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });

        btn_setting_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent1 = new Intent(getActivity(), User_Setting_Activity.class);
                startActivity(intent1);
            }
        });
        return view;
    }
    private void anhxa(){
        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
        btn_setting_user = view.findViewById(R.id.next_setting);
    }
}
