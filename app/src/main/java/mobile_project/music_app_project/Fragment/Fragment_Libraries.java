package mobile_project.music_app_project.Fragment;

import android.app.ProgressDialog;
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
    ViewPager viewPager;
    ImageView imgAddThuVien;
    CircleImageView imguser;
    ProgressDialog progressDialog;
    private String tenThuVien;
    private MainActivity hm;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_libaries, container, false);
        AnhXa();
        init();
//        imgAddThuVien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDialog();
//            }
//        });
        return  view;
    }

    private void init() {
        ViewPagerThuVien viewPagerThuVien = new ViewPagerThuVien(getChildFragmentManager());
        viewPagerThuVien.addFragment(new Fragment_Lib_Playlist(), "Playlist");
        viewPagerThuVien.addFragment(new Fragment_Lib_NgheSi(), "Nghệ sĩ");
        viewPager.setAdapter(viewPagerThuVien);
        tabLayout.setupWithViewPager(viewPager);
//        Picasso.get().load(hm.getUrl()).into(imguser);
    }
    private void AnhXa() {
        hm = (MainActivity) getActivity();
        tabLayout = view.findViewById(R.id.tabLayouttv);
        viewPager = view.findViewById(R.id.viewPagertv);
        imgAddThuVien = view.findViewById(R.id.idaddthuvien);
        imguser = view.findViewById(R.id.imageviewuserthuvien);
    }
//    private void openDialog() {
//        Dialog_insert_thu_vien_playlist exampleDialog = new Dialog_insert_thu_vien_playlist();
//        exampleDialog.show(getFragmentManager(), "Dialog_insert_thu_vien_playlist");
//        exampleDialog.setTargetFragment(Fragment_Libraries.this, 1);
//    }

}

