package mobile_project.music_app_project.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import mobile_project.music_app.Adapter.TimKiemAdapter;
import mobile_project.music_app_project.R;

public class Fragment_TimKiem extends Fragment {

    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
//

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem, container, false);
//        toolbar = view.findViewById(R.id.toilbartimkiem);
//        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem);
//        textViewnull = view.findViewById(R.id.textviewtimkiemnull);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }
}
