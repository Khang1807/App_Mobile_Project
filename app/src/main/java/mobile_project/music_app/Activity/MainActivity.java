package mobile_project.music_app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import mobile_project.music_app.Adapter.MainViewPagerAdapter;
import mobile_project.music_app.Fragment.Fragment_TimKiem;
import mobile_project.music_app.Fragment.Fragment_TrangChu;
import mobile_project.music_app.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"Trang chu");
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(),"Tim kiem");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
    }
    private void anhxa() {
        tabLayout= findViewById(R.id.myTabLayout);
        viewPager= findViewById(R.id.myViewPager);
    }
}