package mobile_project.music_app_project.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import mobile_project.music_app_project.R;

public class Liked_Song_Activity extends AppCompatActivity {
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_song);
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
