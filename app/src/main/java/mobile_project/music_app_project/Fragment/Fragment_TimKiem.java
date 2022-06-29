package mobile_project.music_app_project.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Activity.PlayMusic;
import mobile_project.music_app_project.Adapter.MusicInfo_Adapter;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {

    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
    ArrayList<ModelBaiHat> dataSource;
    LinearLayoutManager linearLayoutManager;
    MusicInfo_Adapter myrv;
//

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem, container, false);
        toolbar = view.findViewById(R.id.toilbartimkiem);
        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem);
        textViewnull = view.findViewById(R.id.textviewtimkiemnull);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ImageView playAllMusic;
        playAllMusic=view.findViewById(R.id.playAllMusic);
        playAllMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PlayMusic.class);
//                    Log.i(data.get(position).get)
                i.putExtra("musicListPlay",dataSource);

                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.getMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchMusic(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void SearchMusic(String query){
        DataService dataService = APIService.getService();
        Call<ResponseModel> callback = dataService.findMusic(query);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {
                    Log.i("has result", "has result");
                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    assert response.body() != null;
                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelBaiHat>();

                    JSONArray resultFind;

                    JSONObject resultGetData = null;

                    try {
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        resultFind = datas.getJSONArray("resultFind");

                        for (int i=0;i<resultFind.length();i++){

                            String musicId = resultFind.getJSONObject(i).optString("musicId");
                            String musicName = resultFind.getJSONObject(i).optString("musicName");
                            String urlImg = resultFind.getJSONObject(i).optString("musicImg");
                            String linkUrl = resultFind.getJSONObject(i).optString("linkUrl");
                            String playlistId = resultFind.getJSONObject(i).optString("playlistId");
                            String categoryId = resultFind.getJSONObject(i).optString("categoryId");
                            String artistId = resultFind.getJSONObject(i).optString("artistId");
                            String duration = resultFind.getJSONObject(i).optString("duration");
                            String artistName = resultFind.getJSONObject(i).optString("artistName");

                            ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg,linkUrl,playlistId,categoryId,artistId,duration,artistName);
                            dataSource.add(music);
                        }

                        for(int i = 0; i<dataSource.size();i++){
                            Log.i(dataSource.get(i).getImgUrl(),"songname");
                        }
                        linearLayoutManager = new LinearLayoutManager(getContext());
                        myrv = new MusicInfo_Adapter(getContext(), dataSource);
                        if(getContext()==null){
                            Log.i("yes","NULL");
                        }
                        else{
                            recyclerViewtim.setLayoutManager(linearLayoutManager);
                            recyclerViewtim.setAdapter(myrv);}

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}
