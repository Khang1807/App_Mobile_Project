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


import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Model.ModelPlayList;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_playlist extends Fragment {
    RecyclerView rv;
    ArrayList<ModelPlayList> dataSource;

    LinearLayoutManager linearLayoutManager;
    Fragment_div_playlist.MyRvAdapter myRvAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_playlist, container, false);
        rv = view.findViewById(R.id.playlist);
        getPlaylist();

        return view;
    }


    private void getPlaylist() {


        DataService networkService = APIService.getService();
        Call<ResponseModel> getPlaylist = networkService.getPlaylist();

        getPlaylist.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {

                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelPlayList>();

                    JSONArray listPlaylist;

                    JSONObject resultGetData = null;

                    try {

                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listPlaylist = datas.getJSONArray("playlist");

                        for (int i=0;i<listPlaylist.length();i++){

                            String id = listPlaylist.getJSONObject(i).optString("playlistId");

                            String nameOfPlaylist = listPlaylist.getJSONObject(i).optString("nameOfPlaylist");
                            String urlImg = listPlaylist.getJSONObject(i).optString("playlistImg");
                            String score = listPlaylist.getJSONObject(i).optString("score");

                            ModelPlayList playlist = new ModelPlayList(id,nameOfPlaylist,urlImg,score);
                            dataSource.add(playlist);

                        }

                        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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


    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_playlist.MyRvAdapter.MyHolder> {
        ArrayList<ModelPlayList> data;

        public MyRvAdapter(ArrayList<ModelPlayList> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_playlist.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
            return new MyHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull Fragment_div_playlist.MyRvAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {

            int positionOfData=position;
            holder.plTitle.setText(data.get(positionOfData).getTenPlayList());

            String name = data.get(positionOfData).getImgUrl();
            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            holder.imgPlaylist.setImageResource(resID);




            holder.divPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), MusicList_Info_Activity.class);
                    i.putExtra("Playlist",data.get(position));
                    //Log.i(data.get(position).getIdPlaylist(),"Position Playlist");
                    getContext().startActivity(i);
                }
            });
//            holder.imgPlaylist.setImageDrawable(getResources().getDrawable(R.drawable.album7));

//            Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/200px-PNG_transparency_demonstration_1.png").into(holder.imgPlaylist);

//            Picasso.with(getContext()).load(data.get(position)).into(holder.imgView);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView plTitle;
            ImageView imgPlaylist;
            LinearLayout divPlaylist;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                plTitle = itemView.findViewById(R.id.plTitle);
                imgPlaylist = itemView.findViewById(R.id.imgPlaylist);
                divPlaylist = itemView.findViewById(R.id.divPlaylist);
            }
        }

    }
}
