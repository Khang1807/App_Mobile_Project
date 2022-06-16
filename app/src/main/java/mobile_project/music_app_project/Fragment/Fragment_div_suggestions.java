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

import mobile_project.music_app_project.Activity.Liked_Song_Activity;
import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Activity.Suggest_Hist_Activity;
import mobile_project.music_app_project.R;

public class Fragment_div_suggestions extends Fragment {
    View view;
    Intent intent,intent2;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_div_suggestions,container,false);

        final Button button = (Button) view.findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(), MusicList_Info_Activity.class);
                intent.putExtra("history","history");
                startActivity(intent);
            }
        });
        final Button button2 = (Button) view.findViewById(R.id.button6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2=new Intent(getActivity(), Liked_Song_Activity.class);
                startActivity(intent2);
            }
        });
        return view;
    }
}
