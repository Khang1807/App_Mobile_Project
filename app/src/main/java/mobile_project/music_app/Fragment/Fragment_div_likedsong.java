package mobile_project.music_app.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile_project.music_app.Activity.MainActivity;
import mobile_project.music_app.R;

public class Fragment_div_likedsong extends Fragment {

    RecyclerView rv;
    ArrayList<String> dataSource;

    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_likedsong, container, false);
        rv = view.findViewById(R.id.liked_list);

        dataSource = new ArrayList<>();
        dataSource.add("The Weeknd");
        dataSource.add("Justin Bieber");
        dataSource.add("Snoop Dog");
        dataSource.add("Maroon 5 ");
        dataSource.add("Obito ");
        dataSource.add("BigBang ");
        dataSource.add("BTS ");


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myRvAdapter = new MyRvAdapter(dataSource) ;
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        return view;
    }



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_likedsong.MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_likedsong.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.liked_list_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_div_likedsong.MyRvAdapter.MyHolder holder, int position) {
            holder.liked_song_name.setText(data.get(position));
            holder.imgLikedList.setImageDrawable(getResources().getDrawable(R.drawable.album4));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView liked_song_name;
            ImageView imgLikedList;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                liked_song_name = itemView.findViewById(R.id.liked_song_name);
                imgLikedList = itemView.findViewById(R.id.imgLikedList);
            }
        }

    }
}