package mobile_project.music_app_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Activity.PlayMusic;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ModelNgheSi;
import mobile_project.music_app_project.Model.ModelPlayList;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.artistTitle.setText(baihat.getArtistName());
        holder.nameSong.setText(baihat.getMusicName());
        String name = baihat.getImgUrl();
        int resID  =context.getResources().getIdentifier(name, "drawable", context.getPackageName());
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
            song_img = view.findViewById(R.id.imgMusicList);
            divMusicList = view.findViewById(R.id.divMusicList);
            artistTitle = view.findViewById(R.id.artistTitle);
            icLove = view.findViewById(R.id.ic_love);

            icLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id =SignIn.id_user;
                    //Log.i(id,"userId");
                    //Log.i(data.get(getPosition()).getMusicId(),"MusicId");
                    icLove.setImageResource(R.drawable.ic_loved);
                    DataService dataService = APIService.getService();
                    Call<ResponseModel> addPlaylist = dataService.addplaylist_user(id,data.get(getPosition()).getMusicId());

                    addPlaylist.enqueue(new Callback<ResponseModel>(){
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            ResponseModel result = response.body();
                            if(result!=null){

                                Gson gson = new Gson();
                                String jsonResult = gson.toJson(response.body().getContent());

                                JSONArray listPlaylist;
                                JSONObject resultGetData = null;
                                try {
                                    resultGetData = new JSONObject(jsonResult);
                                    String message = resultGetData.getString("message");

                                    if(message.equals("Already liked")){
                                        Log.e(message,"Message");
                                        icLove.setImageResource(R.drawable.ic_love);
                                        Toast.makeText(context, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Log.i("Result", "Success");
                                        Toast.makeText(context, "Đã thích bài hát", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
//                            else{
//                                icLove.setImageResource(R.drawable.ic_love);
//                                Toast.makeText(context, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
//                            }
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
