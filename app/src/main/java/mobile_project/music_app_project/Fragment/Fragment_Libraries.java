package mobile_project.music_app_project.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import mobile_project.music_app_project.Activity.MainActivity;
import mobile_project.music_app_project.Adapter.ViewPagerThuVien;

import mobile_project.music_app_project.R;


public class Fragment_Libraries extends Fragment {
    View view;
    ViewPager viewPager1;
    ImageView imgAddThuVien;
    CircleImageView imguser;
    private MainActivity hm;
    TabLayout tabLayout1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        AnhXa();
//        init();
        return inflater.inflate(R.layout.fragment_libaries, container, false);
    }

//    private void init() {
//        ViewPagerThuVien viewPagerThuVien = new ViewPagerThuVien(getChildFragmentManager());
//        viewPagerThuVien.addFragment(new Fragment_Lib_Playlist(), "Playlist");
//        viewPagerThuVien.addFragment(new Fragment_Lib_NgheSi(), "Nghệ sĩ");
//        viewPager1.setAdapter(viewPagerThuVien);
//        tabLayout1.setupWithViewPager(viewPager1);
//    }




    private void AnhXa() {
        hm = (MainActivity) getActivity();
//        tabLayout1 = view.findViewById(R.id.tabLayouttv);
//        viewPager1 = view.findViewById(R.id.viewPagertv);
        imgAddThuVien = view.findViewById(R.id.idaddthuvien);
        imguser = view.findViewById(R.id.imageviewuserthuvien);
    }


}

