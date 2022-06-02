package mobile_project.music_app_project.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;


public class MainViewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle= new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm,@NonNull Lifecycle lifecycle) {
        super(fm,lifecycle);
    }
    public void addFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);

    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayFragment.size();
    }
}
