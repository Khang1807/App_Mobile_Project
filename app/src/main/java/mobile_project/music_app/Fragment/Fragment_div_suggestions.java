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

import mobile_project.music_app.Activity.Suggest_Hist_Activity;
import mobile_project.music_app.R;

public class Fragment_div_suggestions extends Fragment {
    View view;
    Intent intent;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_div_suggestions,container,false);

        final Button button = (Button) view.findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(), Suggest_Hist_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
