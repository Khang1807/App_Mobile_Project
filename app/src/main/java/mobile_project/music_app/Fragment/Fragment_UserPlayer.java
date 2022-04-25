package mobile_project.music_app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mobile_project.music_app.Activity.SignIn;
import mobile_project.music_app.R;

public class Fragment_UserPlayer extends Fragment {
    View view;
    public Button button = null;
    Intent intent;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_user_player,container,false);
        intent = new Intent(getActivity(), SignIn.class);
        View view = inflater.inflate(R.layout.fragment_user_player, container, false);
        button = (Button) view.findViewById(R.id.btndangxuat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        return view;
    }
}
