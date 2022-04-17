package mobile_project.music_app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import mobile_project.music_app.R;
public class fragment_ActivityHorizontalRV extends Fragment {
    RecyclerView rv;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_horizontal_rv, container, false);
        rv = view.findViewById(R.id.horizontalRv);

        dataSource = new ArrayList<>();
        dataSource.add("Thiều Bảo Trâm");
        dataSource.add("Sơn Tùng MTP");
        dataSource.add("Lệ Rơi");
        dataSource.add("Hari Won");
        dataSource.add("Trịnh Thăng Bình");
        dataSource.add("Jack 5tr");
        dataSource.add("Lê Tuấn Vinh");
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        return view;
    }


//    @Override
//    protected void onCreate(Bundle savedInstance){
//        super.onCreate(savedInstance);
//        setContentView(R.layout.activity_horizontal_rv);
//        rv=findViewById(R.id.horizontalRv);
//        dataSource = new ArrayList<>();
//        dataSource.add("Hello");
//        dataSource.add("World");
//        dataSource.add("To");
//        dataSource.add("The");
//        dataSource.add("Code");
//        dataSource.add("City");
//        dataSource.add("******");
//        linearLayoutManager = new LinearLayoutManager(fragment_ActivityHorizontalRV.this, LinearLayoutManager.HORIZONTAL, false);
//        myRvAdapter = new MyRvAdapter(dataSource);
//        rv.setLayoutManager(linearLayoutManager);
//        rv.setAdapter(myRvAdapter);
//    }
    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvTitle.setText(data.get(position));
            holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.album3));

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);



        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            ImageView imgView;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                imgView = itemView.findViewById(R.id.imgView);
            }
        }

    }
}
