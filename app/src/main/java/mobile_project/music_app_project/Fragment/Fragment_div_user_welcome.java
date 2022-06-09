package mobile_project.music_app_project.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import mobile_project.music_app_project.R;

public class Fragment_div_user_welcome extends Fragment {
    SharedPreferences sharedPreferences;
    View view;
    TextView txt_name;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_user_welcome,container,false);
        txt_name = view.findViewById(R.id.user_name_welcom);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        txt_name.setText(sharedPreferences.getString("name_user",""));
        return view;
    }
}
