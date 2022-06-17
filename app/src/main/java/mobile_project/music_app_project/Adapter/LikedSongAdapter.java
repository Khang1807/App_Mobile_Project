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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.PlayMusic;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Fragment.Fragment_div_likedsong;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedSongAdapter extends RecyclerView.Adapter<LikedSongAdapter.ViewHolder>{
    Context context;
    ArrayList<ModelBaiHat> data;

    public LikedSongAdapter(Context context,ArrayList<ModelBaiHat> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.music_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelBaiHat baihat = data.get(position);
        holder.nameSong.setText(baihat.getMusicName());
        holder.artistTitle.setText(baihat.getArtistName());
        //Log.i(baihat.getMusicName(),"Name song");
        String name = baihat.getImgUrl();
        int resID  =getContext().getResources().getIdentifier(name, "drawable", getContext().getPackageName());
        holder.song_img.setImageResource(resID);
        holder.divMusicList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PlayMusic.class);
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
        TextView nameSong,artistTitle;
        ImageView song_img,icLove;
        LinearLayout divMusicList;
        public ViewHolder(@NonNull View view) {
            super(view);
            nameSong = view.findViewById(R.id.musicTitle);
            artistTitle = view.findViewById(R.id.artistTitle);
            song_img = view.findViewById(R.id.imgMusicList);
            divMusicList = view.findViewById(R.id.divMusicList);
            icLove = view.findViewById(R.id.ic_love);
            icLove.setImageResource(R.drawable.ic_loved);
            icLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id =SignIn.id_user;

                    icLove.setImageResource(R.drawable.ic_love);
                    DataService dataService = APIService.getService();
                    Call<ResponseModel> deleteLikedSong = dataService.deletePlaylist_User(id,data.get(getPosition()).getMusicId());
                    deleteLikedSong.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            ResponseModel result = response.body();
                            if(result!=null){
                                Log.i("Result","Success");
                                Toast.makeText(getContext(), "Bạn đã bỏ yêu thích", Toast.LENGTH_SHORT).show();
                                //divMusicList.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Log.i(t.getMessage(),"error server");
                        }
                    });
                    icLove.setEnabled(false);
                }
            });
        }
    }
}
