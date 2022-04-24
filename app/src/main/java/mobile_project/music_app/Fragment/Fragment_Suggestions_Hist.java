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

public class Fragment_Suggestions_Hist extends Fragment {

    RecyclerView rv;
    ArrayList<String> dataSource;

    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_suggestions_hist, container, false);
        rv = view.findViewById(R.id.hist_list);

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



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_Suggestions_Hist.MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_Suggestions_Hist.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.hist_list_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_Suggestions_Hist.MyRvAdapter.MyHolder holder, int position) {
            holder.hist_song.setText(data.get(position));
            holder.imgHistList.setImageDrawable(getResources().getDrawable(R.drawable.album6));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView hist_song;
            ImageView imgHistList;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                hist_song = itemView.findViewById(R.id.hist_song);
                imgHistList = itemView.findViewById(R.id.imgHistList);
            }
        }

    }
}