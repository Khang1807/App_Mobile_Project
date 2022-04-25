package mobile_project.music_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import mobile_project.music_app.R;

public class Fragment_Lib_NgheSi  extends Fragment {

    View view;
    //    ThuVienNgheSiAdapter thuVienNgheSiAdapter;
    RecyclerView recyclerViewNgheSi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lib_nghesi, container, false);
        recyclerViewNgheSi = view.findViewById(R.id.recyclerviewnthuvienghesi);
//        GetData();
        return view;
    }
}