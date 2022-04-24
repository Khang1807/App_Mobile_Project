package mobile_project.music_app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mobile_project.music_app.Adapter.MainViewPagerAdapter;
import mobile_project.music_app.Fragment.Fragment_Libraries;
import mobile_project.music_app.Fragment.Fragment_Suggestions_Hist;
import mobile_project.music_app.Fragment.Fragment_TimKiem;
import mobile_project.music_app.Fragment.Fragment_TrangChu;
import mobile_project.music_app.Fragment.Fragment_UserPlayer;
import mobile_project.music_app.R;

public class Suggest_Hist_Activity extends AppCompatActivity {
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_hist);
        //anhxa();
        //init();
    }
//    private void init(){
//        FragmentManager fm = getSupportFragmentManager();
//        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(fm,getLifecycle());
//        mainViewPagerAdapter.addFragment(new Fragment_Suggestions_Hist(),"Lich su bai hat");
//        viewPager.setAdapter(mainViewPagerAdapter);
//        viewPager.setUserInputEnabled(false);
//    }
//    private void anhxa() {
//
//        viewPager= findViewById(R.id.myViewPager2);
//    }
}
