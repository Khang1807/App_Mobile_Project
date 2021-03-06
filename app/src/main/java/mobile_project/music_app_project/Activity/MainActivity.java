package mobile_project.music_app_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mobile_project.music_app_project.Adapter.MainViewPagerAdapter;
import mobile_project.music_app_project.Fragment.Fragment_Libraries;
import mobile_project.music_app_project.Fragment.Fragment_TimKiem;
import mobile_project.music_app_project.Fragment.Fragment_TrangChu;
import mobile_project.music_app_project.Fragment.Fragment_UserPlayer;
import mobile_project.music_app_project.Model.ModelUser;
import mobile_project.music_app_project.R;
public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ModelUser user;
    public static ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.fragment_trangchu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init(){
        FragmentManager fm = getSupportFragmentManager();
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(fm,getLifecycle());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"Trang chu");
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(),"Tim kiem");
//        mainViewPagerAdapter.addFragment(new Fragment_Libraries(),"Thu vien");
        mainViewPagerAdapter.addFragment(new Fragment_UserPlayer(),"Ca nhan");
        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setUserInputEnabled(false);

//        viewPager. setRotationY(180);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if(position==0)
                    tab.setIcon(R.drawable.home);
                if(position==1)
                    tab.setIcon(R.drawable.icontimkiem);
//                if(position==2)
//                    tab.setIcon(R.drawable.playlist);
                if(position==2)
                    tab.setIcon(R.drawable.music_headphones);
            }
        });
        tabLayoutMediator.attach();
    }
    private void anhxa() {
        tabLayout= findViewById(R.id.myTabLayout);
        viewPager= findViewById(R.id.myViewPager);
    }
}
