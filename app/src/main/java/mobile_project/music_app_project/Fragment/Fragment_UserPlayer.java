package mobile_project.music_app_project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Activity.User_Setting_Activity;
import mobile_project.music_app_project.R;

public class Fragment_UserPlayer extends Fragment {
    View view;
    public Button button = null;
    Intent intent;
    Intent intent1;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_user_player,container,false);

        View view = inflater.inflate(R.layout.fragment_user_player, container, false);

        button = (Button) view.findViewById(R.id.btn_dangxuat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });
        final Button button1 = (Button) view.findViewById(R.id.next_setting);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent1=new Intent(getActivity(), User_Setting_Activity.class);
                startActivity(intent1);
            }
        });
        return view;
    }
}
