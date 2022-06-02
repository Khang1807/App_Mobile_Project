package mobile_project.music_app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.R;

public abstract class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder> {

    Context context;
    ArrayList<ModelBaiHat> mangbaihat;

    public TimKiemAdapter(Context context, ArrayList<ModelBaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.timkiem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelBaiHat baiHat = mangbaihat.get(position);
        holder.txttentimkiem.setText(baiHat.getMusicName());
        holder.txtcasitimkiem.setText(baiHat.getArtistId());
        Picasso.get().load(baiHat.getImgUrl()).into(holder.imganhtimkiem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.txttennhac);
            txtcasitimkiem = itemView.findViewById(R.id.txtcasinhac);
            imganhtimkiem = itemView.findViewById(R.id.imgnhac);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, PlayNhacActivity.class);
//                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
//                    context.startActivity(intent);
//                }
//            });

        }
    }
}
