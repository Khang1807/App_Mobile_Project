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

import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.R;

public class PlaylistInfo_Adapter extends RecyclerView.Adapter<PlaylistInfo_Adapter.ViewHolder> {
    Context context;
    ArrayList<ModelBaiHat> data;

    public PlaylistInfo_Adapter(Context context, ArrayList<ModelBaiHat> data) {
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
    public void onBindViewHolder(@NonNull PlaylistInfo_Adapter.ViewHolder holder, int position) {
        ModelBaiHat baihat = data.get(position);
        holder.playlist_song.setText(baihat.getMusicName());
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
        TextView playlist_song;
        ImageView song_img;
        LinearLayout divPlaylist_Music;
        public ViewHolder(@NonNull View view) {
            super(view);
            playlist_song = view.findViewById(R.id.musicTitle);
            song_img = view.findViewById(R.id.imgMusicList);
            divPlaylist_Music = view.findViewById(R.id.divMusicList);
        }
    }
}
