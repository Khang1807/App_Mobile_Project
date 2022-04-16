package mobile_project.music_app.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mobile_project.music_app.Adapter.MainViewPagerAdapter;
import mobile_project.music_app.Fragment.Fragment_TimKiem;
import mobile_project.music_app.Fragment.Fragment_TrangChu;
import mobile_project.music_app.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_trangchu);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        anhxa();
//        init();
    }

//    private void init(){
//        FragmentManager fm = getSupportFragmentManager();
//        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(fm,getLifecycle());
//        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"Trang chu");
//        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(),"Tim kiem");
//        viewPager.setAdapter(mainViewPagerAdapter);
//
//
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.OnConfigureTabCallback() {
//            @Override
//            public void onConfigureTab(TabLayout.Tab tab, int position) {
//                if(position==0)
//                    tab.setIcon(R.drawable.icontrangchu);
//                if(position==1)
//                    tab.setIcon(R.drawable.icontimkiem);
//            }
//        });
//        tabLayoutMediator.attach();
//    }
    private void anhxa() {
        tabLayout= findViewById(R.id.myTabLayout);
        viewPager= findViewById(R.id.myViewPager);
    }
}