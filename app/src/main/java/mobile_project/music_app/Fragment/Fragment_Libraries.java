package mobile_project.music_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobile_project.music_app.R;
import mobile_project.music_app.Fragment.Fragment_Lib_NgheSi;
import mobile_project.music_app.Fragment.Fragment_Lib_Playlist;
import mobile_project.music_app.Fragment.Fragment_Lib_YeuThich;


public class Fragment_Libraries extends Fragment {
    View view;
    ViewPager viewPager;

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_libaries, container, false);
//        init();
//        imgAddThuVien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//            }
//        });
        return  view;
    }

//    private void init() {
//        ViewPagerThuVien viewPagerThuVien = new ViewPagerThuVien(getChildFragmentManager());
//        viewPagerThuVien.addFragment(new Fragment_Lib_Playlist(), "Playlist");
//        viewPagerThuVien.addFragment(new Fragment_Lib_NgheSi(), "Nghệ sĩ");
//        viewPagerThuVien.addFragment(new Fragment_Lib_YeuThich(), "Yêu thích");
//        viewPager.setAdapter(viewPagerThuVien);
//        tabLayout.setupWithViewPager(viewPager);
//        Picasso.get().load(hm.getUrl()).into(imguser);
//    }
}

