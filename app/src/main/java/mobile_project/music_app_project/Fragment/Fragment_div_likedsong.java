package mobile_project.music_app_project.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.PlayMusic;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ModelNgheSi;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_likedsong extends Fragment {

    RecyclerView rv;
    ArrayList<ModelBaiHat> dataSource;

    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_music_info, container, false);
        TextView a = view.findViewById(R.id.name_music_list);
        a.setText("Bài hát yêu thích");
        rv = view.findViewById(R.id.music_list);
        String userId = SignIn.id_user;
        getPlaylistList(userId);
        return view;
    }


    private void getPlaylistList(String id) {
        Log.i("run playlist user", "run function");

        DataService networkService = APIService.getService();
        Call<ResponseModel> getPlaylistList = networkService.getplaylist_user(id);

        getPlaylistList.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {
                    Log.i("has result", "has result");
                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelBaiHat>();

                    JSONArray listPL;

                    JSONObject resultGetData = null;

                    try {

                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listPL = datas.getJSONArray("playlistOfUser");

                        for (int i=0;i<listPL.length();i++){

                            String musicId = listPL.getJSONObject(i).optString("musicId");
                            String musicName = listPL.getJSONObject(i).optString("musicName");
                            String urlImg = listPL.getJSONObject(i).optString("musicImg");
                            String linkUrl = listPL.getJSONObject(i).optString("linkUrl");
                            String playlistId = listPL.getJSONObject(i).optString("playlistId");
                            String categoryId = listPL.getJSONObject(i).optString("categoryId");
                            String artistId = listPL.getJSONObject(i).optString("artistId");
                            String duration = listPL.getJSONObject(i).optString("duration");
                            String artistName = listPL.getJSONObject(i).optString("artistName");

                            ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg,linkUrl,playlistId,categoryId,artistId,duration,artistName);
                            dataSource.add(music);

                        }

                        linearLayoutManager = new LinearLayoutManager(getContext());
                        myRvAdapter = new MyRvAdapter(dataSource);
                        rv.setLayoutManager(linearLayoutManager);
                        rv.setAdapter(myRvAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }
    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_likedsong.MyRvAdapter.MyHolder> {
        ArrayList<ModelBaiHat> data;

        public MyRvAdapter(ArrayList<ModelBaiHat> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_likedsong.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.music_list_item, parent, false);
            return new Fragment_div_likedsong.MyRvAdapter.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_div_likedsong.MyRvAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
            ModelBaiHat baihat = data.get(position);
            holder.nameSong.setText(baihat.getMusicName());
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

        class MyHolder extends RecyclerView.ViewHolder {
            TextView nameSong;
            ImageView song_img,icLove;
            LinearLayout divMusicList;
            public MyHolder(@NonNull View view) {
                super(view);
                nameSong = view.findViewById(R.id.musicTitle);
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
                                    Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
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
}