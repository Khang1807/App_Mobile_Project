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

public class Fragment_trendy extends Fragment {
    RecyclerView rv;
    ArrayList<String> dataSource;

    LinearLayoutManager linearLayoutManager;
    Fragment_trendy.MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trendy, container, false);
        rv = view.findViewById(R.id.trendy_list);

        dataSource = new ArrayList<>();
        dataSource.add("");
        dataSource.add("Pop Việt ngày nay");
        dataSource.add("Chill Hits");
        dataSource.add("VPOP");


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        return view;
    }



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_trendy.MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_trendy.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.trendy_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_trendy.MyRvAdapter.MyHolder holder, int position) {
            holder.trendTitle.setText(data.get(position));
            holder.imgTrendy.setImageDrawable(getResources().getDrawable(R.drawable.album6));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView trendTitle;
            ImageView imgTrendy;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                trendTitle = itemView.findViewById(R.id.trendTitle);
                imgTrendy = itemView.findViewById(R.id.imgtrendy);
            }
        }

    }
}
