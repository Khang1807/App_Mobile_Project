package mobile_project.music_app_project.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.Category_Info_Activity;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.R;

public class CategoryInfo_Adapter extends RecyclerView.Adapter<CategoryInfo_Adapter.ViewHolder> {
    Context context;
    ArrayList<ModelBaiHat> data;

    public CategoryInfo_Adapter(Context context, ArrayList<ModelBaiHat> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.music_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelBaiHat baihat = data.get(position);
        holder.category_song.setText(baihat.getMusicName());
        String name = baihat.getImgUrl();
        int resID  =context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.song_img.setImageResource(resID);

        Log.i(data.get(position).getCategoryId(),"MA");
//            holder.divCategory_Music.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i(data.get(positionOfData).getMusicId(),"abc");
//                    GetDataCategory(data.get(positionOfData).getMusicId());
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_song;
        ImageView song_img;
        LinearLayout divCategory_Music;
        public ViewHolder(@NonNull View view) {
            super(view);
            category_song = view.findViewById(R.id.musicTitle);
            song_img = view.findViewById(R.id.imgMusicList);
            divCategory_Music = view.findViewById(R.id.divMusicList);
        }
    }


}
