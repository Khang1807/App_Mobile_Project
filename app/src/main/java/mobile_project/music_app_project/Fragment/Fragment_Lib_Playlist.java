package mobile_project.music_app_project.Fragment;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import mobile_project.music_app_project.Activity.MainActivity;
import mobile_project.music_app_project.Model.ModelTheLoai;
import mobile_project.music_app_project.R;

public class Fragment_Lib_Playlist extends Fragment{
    View view;
    ModelTheLoai category;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewThuVienPlayList;
    TextView tenThuVienPlayList;
    MainActivity hm;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lib_playlist, container, false);
        recyclerViewThuVienPlayList = view.findViewById(R.id.recyclerviewthuvienplaylist);
        tenThuVienPlayList = view.findViewById(R.id.textView);
//        hm = (MainActivity) getActivity();
        swipeRefreshLayout = view.findViewById(R.id.swipethuvien);
        return view;
    }

}