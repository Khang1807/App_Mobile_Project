package mobile_project.music_app.Fragment;

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

public class Fragment_div_rating extends Fragment {
    RecyclerView rv;
    ArrayList<String> dataSource;

    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_rating, container, false);
        rv = view.findViewById(R.id.rating);

        dataSource = new ArrayList<>();
        dataSource.add("Top 10 Pop Remixed");
        dataSource.add("2014 Dance Top 10");
        dataSource.add("Top 10 Baseball songs");
        dataSource.add("Mixtape Top 10");


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        return view;
    }



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_rating.MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_rating.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rating_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_div_rating.MyRvAdapter.MyHolder holder, int position) {
            holder.ratingTitle.setText(data.get(position));
            holder.imgRating.setImageDrawable(getResources().getDrawable(R.drawable.top10));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView ratingTitle;
            ImageView imgRating;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                ratingTitle = itemView.findViewById(R.id.ratingTitle);
                imgRating = itemView.findViewById(R.id.imgRating);
            }
        }

    }
}
