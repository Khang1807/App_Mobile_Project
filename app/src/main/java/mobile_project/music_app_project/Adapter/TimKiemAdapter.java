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

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder> {

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
//        holder.txtcasitimkiem.setText(baiHat.getNghesi());
        String name = baiHat.getImgUrl();
        int resID  =context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.imganhtimkiem.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem, iconlove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.txttennhac);
//            txtcasitimkiem = itemView.findViewById(R.id.txtcasinhac);
            imganhtimkiem = itemView.findViewById(R.id.imgnhac);
            iconlove = itemView.findViewById(R.id.icon_love);

        }
    }
}
