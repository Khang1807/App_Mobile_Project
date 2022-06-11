package mobile_project.music_app_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile_project.music_app_project.Adapter.MusicInfo_Adapter;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ModelNgheSi;
import mobile_project.music_app_project.Model.ModelPlayList;
import mobile_project.music_app_project.Model.ModelTheLoai;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicList_Info_Activity extends AppCompatActivity {
    ModelNgheSi artist;
    ModelTheLoai category;
    ModelPlayList playList;
    ArrayList<ModelBaiHat> dataSource;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv ;
    MusicInfo_Adapter myrv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);
        rv = (RecyclerView) findViewById(R.id.music_list);
        DataIntent();
        if(artist!=null && !artist.getArtistId().equals("")){
            GetData(artist.getArtistId());}
        else if(category!=null && !category.getCategoryId().equals("")){
            GetData(category.getCategoryId());}
        else{
            GetData(playList.getIdPlaylist());
        }
    }



    private void DataIntent() {
        TextView artist_name = findViewById(R.id.name_music_list);
        Intent i = getIntent();
        if(i!=null){
            if(i.hasExtra("Artist")){
                artist = (ModelNgheSi) i.getSerializableExtra("Artist");
                artist_name.setText(artist.getArtistName());
                Toast.makeText(this,artist.getArtistName(),Toast.LENGTH_SHORT).show();
            }
            else if(i.hasExtra("Category")){
                category = (ModelTheLoai) i.getSerializableExtra("Category");
                artist_name.setText(category.getCategoryName());
                Toast.makeText(this,category.getCategoryName(),Toast.LENGTH_SHORT).show();
            }
            else{
                playList = (ModelPlayList) i.getSerializableExtra("Playlist");
                artist_name.setText(playList.getTenPlayList());
                Toast.makeText(this,playList.getTenPlayList(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void GetData(String id){
        Intent i = getIntent();

        if(i.hasExtra("Artist")){
            DataService dataservice = APIService.getService();
            Call<ResponseModel> getInfo = dataservice.getArtistInfo(id);
            getInfo.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                    ResponseModel responseBody = response.body();

                    if (responseBody != null) {
                        Log.i("has result", "has result");
                        // ko xoa doan nay, code get data
                        Gson gson = new Gson();

                        assert response.body() != null;
                        String jsonResult = gson.toJson(response.body().getContent());

                        dataSource = new ArrayList<ModelBaiHat>();

                        JSONArray artistInfo;

                        JSONObject resultGetData = null;

                        try {
                            resultGetData = new JSONObject(jsonResult);

                            JSONObject datas = resultGetData.getJSONObject("datas");

                            artistInfo = datas.getJSONArray("artistInfo");

                            for (int i=0;i<artistInfo.length();i++){

                                String musicId = artistInfo.getJSONObject(i).optString("musicId");
                                String musicName = artistInfo.getJSONObject(i).optString("musicName");
                                String urlImg = artistInfo.getJSONObject(i).optString("imgUrl");
                                ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg);
                                dataSource.add(music);
                            }

                            for(int i = 0; i<dataSource.size();i++){
                                Log.i(dataSource.get(i).getImgUrl(),"songname");
                            }
                            linearLayoutManager = new LinearLayoutManager(MusicList_Info_Activity.this);
                            myrv = new MusicInfo_Adapter(MusicList_Info_Activity.this,dataSource);
                            if(MusicList_Info_Activity.this==null){
                                Log.i("yes","NULL");
                            }
                            else{
                                rv.setLayoutManager(linearLayoutManager);
                                rv.setAdapter(myrv);}

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
        else if(i.hasExtra("Category")){
            DataService dataservice = APIService.getService();
            Call<ResponseModel> getInfo = dataservice.getCategoryInfo(id);
            getInfo.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                    ResponseModel responseBody = response.body();

                    if (responseBody != null) {
                        Log.i("has result", "has result");
                        // ko xoa doan nay, code get data
                        Gson gson = new Gson();

                        assert response.body() != null;
                        String jsonResult = gson.toJson(response.body().getContent());

                        dataSource = new ArrayList<ModelBaiHat>();

                        JSONArray artistInfo;

                        JSONObject resultGetData = null;

                        try {
                            resultGetData = new JSONObject(jsonResult);

                            JSONObject datas = resultGetData.getJSONObject("datas");

                            artistInfo = datas.getJSONArray("categoryInfo");

                            for (int i=0;i<artistInfo.length();i++){

                                String musicId = artistInfo.getJSONObject(i).optString("musicId");
                                String musicName = artistInfo.getJSONObject(i).optString("musicName");
                                String urlImg = artistInfo.getJSONObject(i).optString("imgUrl");
                                ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg);
                                dataSource.add(music);
                            }

                            for(int i = 0; i<dataSource.size();i++){
                                Log.i(dataSource.get(i).getImgUrl(),"songname");
                            }
                            linearLayoutManager = new LinearLayoutManager(MusicList_Info_Activity.this);
                            myrv = new MusicInfo_Adapter(MusicList_Info_Activity.this,dataSource);
                            if(MusicList_Info_Activity.this==null){
                                Log.i("yes","NULL");
                            }
                            else{
                                rv.setLayoutManager(linearLayoutManager);
                                rv.setAdapter(myrv);}

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
        else{
            DataService dataservice = APIService.getService();
            Call<ResponseModel> getInfo = dataservice.getPlaylistInfo(id);
            getInfo.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                    ResponseModel responseBody = response.body();

                    if (responseBody != null) {
                        Log.i("has result", "has result");
                        // ko xoa doan nay, code get data
                        Gson gson = new Gson();

                        assert response.body() != null;
                        String jsonResult = gson.toJson(response.body().getContent());

                        dataSource = new ArrayList<ModelBaiHat>();

                        JSONArray artistInfo;

                        JSONObject resultGetData = null;

                        try {
                            resultGetData = new JSONObject(jsonResult);

                            JSONObject datas = resultGetData.getJSONObject("datas");

                            artistInfo = datas.getJSONArray("playlistInfo");

                            for (int i=0;i<artistInfo.length();i++){

                                String musicId = artistInfo.getJSONObject(i).optString("musicId");
                                String musicName = artistInfo.getJSONObject(i).optString("musicName");
                                String urlImg = artistInfo.getJSONObject(i).optString("imgUrl");
                                ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg);
                                dataSource.add(music);
                            }

                            for(int i = 0; i<dataSource.size();i++){
                                Log.i(dataSource.get(i).getImgUrl(),"songname");
                            }
                            linearLayoutManager = new LinearLayoutManager(MusicList_Info_Activity.this);
                            myrv = new MusicInfo_Adapter(MusicList_Info_Activity.this,dataSource);
                            if(MusicList_Info_Activity.this==null){
                                Log.i("yes","NULL");
                            }
                            else{
                                rv.setLayoutManager(linearLayoutManager);
                                rv.setAdapter(myrv);}

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



    }
}
