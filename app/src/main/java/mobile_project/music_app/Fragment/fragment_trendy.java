package mobile_project.music_app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mobile_project.music_app.R;

public class fragment_trendy extends Fragment {
    View view;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_trendy,container,false);
        return view;
    }
}
