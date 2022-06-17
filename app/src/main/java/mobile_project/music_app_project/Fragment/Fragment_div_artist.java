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
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Model.ModelNgheSi;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_artist extends Fragment {
    RecyclerView rv;
    ArrayList<ModelNgheSi> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_artist, container, false);
        rv = view.findViewById(R.id.artist_recycle);

        getArtistList();
        return view;
    }


    private void getArtistList() {
        Log.i("run function artist", "run function");

        DataService networkService = APIService.getService();
        Call<ResponseModel> getArtistList = networkService.getArtistList();

        getArtistList.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {
                    Log.i("has result", "has result");
                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelNgheSi>();

                    JSONArray listArtist;

                    JSONObject resultGetData = null;

                    try {
                        Log.i(getResources().getDrawable(R.drawable.album_us).toString(),"url img");
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listArtist = datas.getJSONArray("artistList");

                        for (int i=0;i<listArtist.length();i++){

                            String artistId = listArtist.getJSONObject(i).optString("artistId");
                            String artistName = listArtist.getJSONObject(i).optString("artistName");
                            String urlImg = listArtist.getJSONObject(i).optString("artistImg");


                            ModelNgheSi artist = new ModelNgheSi(artistId,artistName,urlImg);
                            dataSource.add(artist);

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



    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_artist.MyRvAdapter.MyHolder> {
        ArrayList<ModelNgheSi> data;

        public MyRvAdapter(ArrayList<ModelNgheSi> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_artist.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.artist_music_item, parent, false);
            return new Fragment_div_artist.MyRvAdapter.MyHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull Fragment_div_artist.MyRvAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {

            int positionOfData=position;
            holder.tvTitle.setText(data.get(positionOfData).getArtistName());

            String name = data.get(positionOfData).getImgUrl();
            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            holder.imgView.setImageResource(resID);

            Log.i(data.get(positionOfData).getArtistId(),"abcde");
            holder.divArtist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), MusicList_Info_Activity.class);
                    i.putExtra("Artist",data.get(position));

                    getContext().startActivity(i);

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            ImageView imgView;
            LinearLayout divArtist;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.artist_song_name);
                imgView = itemView.findViewById(R.id.imgMusic_Artist);
                divArtist = itemView.findViewById(R.id.divArtist_Music);
            }
        }

    }

}





