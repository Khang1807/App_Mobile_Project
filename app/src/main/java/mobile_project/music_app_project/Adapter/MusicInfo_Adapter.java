package mobile_project.music_app_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Activity.PlayMusic;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.R;

public class MusicInfo_Adapter extends RecyclerView.Adapter<MusicInfo_Adapter.ViewHolder>{
    Context context;
    ArrayList<ModelBaiHat> data;

    public MusicInfo_Adapter(Context context, ArrayList<ModelBaiHat> data) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelBaiHat baihat = data.get(position);
        holder.nameSong.setText(baihat.getMusicName());
        String name = baihat.getImgUrl();
        int resID  =context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.song_img.setImageResource(resID);

//        Log.i(data.get(position).getCategoryId(),"MA");
            holder.divMusicList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), PlayMusic.class);
//                    Log.i(data.get(position).get)
                    ArrayList<ModelBaiHat> songList = new ArrayList<ModelBaiHat>();
                    songList.add(data.get(position));
                    i.putExtra("musicListPlay",songList);
                    getContext().startActivity(i);
                }
            });
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
        TextView nameSong;
        ImageView song_img;
        LinearLayout divMusicList;
        public ViewHolder(@NonNull View view) {
            super(view);
            nameSong = view.findViewById(R.id.musicTitle);
            song_img = view.findViewById(R.id.imgMusicList);
            divMusicList = view.findViewById(R.id.divMusicList);
        }
    }
}
