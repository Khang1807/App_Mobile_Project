package mobile_project.music_app.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile_project.music_app.R;

public class Fragment_div_playlist extends Fragment {
    RecyclerView rv;
    ArrayList<String> dataSource;

    LinearLayoutManager linearLayoutManager;
    Fragment_div_playlist.MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_playlist, container, false);
        rv = view.findViewById(R.id.playlist);

        dataSource = new ArrayList<>();
        dataSource.add("USUK Are & Be");
        dataSource.add("Pop Việt ngày nay");
        dataSource.add("Chill Hits");
        dataSource.add("VPOP");


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        return view;
    }



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_playlist.MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_playlist.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_div_playlist.MyRvAdapter.MyHolder holder, int position) {
            holder.plTitle.setText(data.get(position));
            holder.imgPlaylist.setImageDrawable(getResources().getDrawable(R.drawable.album7));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView plTitle;
            ImageView imgPlaylist;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                plTitle = itemView.findViewById(R.id.plTitle);
                imgPlaylist = itemView.findViewById(R.id.imgPlaylist);
            }
        }

    }
}
