package mobile_project.music_app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;

import mobile_project.music_app.Activity.MainActivity;
import mobile_project.music_app.Activity.SignIn;
import mobile_project.music_app.Activity.SignUp;
import mobile_project.music_app.Model.ModelPlayList;
import mobile_project.music_app.Model.ResponseModel;
import mobile_project.music_app.R;
import mobile_project.music_app.Service_API.APIService;
import mobile_project.music_app.Service_API.DataService;
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
                        Log.i(getResources().getDrawable(R.drawable.album7).toString(),"url img");
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listPlaylist = datas.getJSONArray("playlist");

                        for (int i=0;i<listPlaylist.length();i++){

                            String id = listPlaylist.getJSONObject(i).optString("playlistId");
                            String nameOfPlaylist = listPlaylist.getJSONObject(i).optString("nameOfPlaylist");
                            String urlImg = listPlaylist.getJSONObject(i).optString("imgUrl");
                            String score = listPlaylist.getJSONObject(i).optString("score");

                            ModelPlayList playlist = new ModelPlayList(id,nameOfPlaylist,urlImg,score);
                            dataSource.add(playlist);

                        }

                        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        myRvAdapter = new MyRvAdapter(dataSource);
                        rv.setLayoutManager(linearLayoutManager);
                        rv.setAdapter(myRvAdapter);

//
//                        json=gson.toJson(result.get("datas"));
//
//                        Object datas= result.get("datas");
//
//                        JSONObject playlist = new JSONObject(datas.toString());
//
//                        JSONArray arrJsonPlaylist=new JSONArray(playlist.get("playlist").toString());
//
//
//
//                        if (arrJsonPlaylist != null) {
//                            for (int i=0;i<arrJsonPlaylist.length();i++){
//                                Log.i(arrJsonPlaylist.getJSONObject(i).toString(),"obj string");
//                                Log.i(arrJsonPlaylist.getJSONObject(i).optString("nameOfPlaylist"),"obj name play list");
//                            }
//                        }


//                        Type playlistListType = new TypeToken<ArrayList<ModelPlayList>>(){}.getType();
//                        ArrayList<ModelPlayList> datas = gson.fromJson(json,playlistListType);

//                        for (int i=0; i <1;i++){
//                            Log.i(datas.get(i).getTenPlayList(),"name of playlist");
//                        }

//                        if (datas.has("playlist")){
//                            Log.i("has playlist","print");
//
//                        }else{
//                            Log.i("don't have playlist","print");
//                        }
//
//                        json=gson.toJson(datas.get("playlist"));
//
//                        JSONObject playlist = new JSONObject(json);
//                        if (playlist.has("nameOfPlaylist")){
//                            Log.i(playlist.getString("nameOfPlaylist"),"nameOfPlaylist");
//                        }else{
//                            Log.i("dont have name","name of playlist");
//                        }

//                        JSONArray playlist=datas.getJSONArray("playlist");
//                        for (int i=0; i < 2; i++){
//                            playlist.getJSONObject(i).getString("nameOfPlaylist");
//                        }

//                        Log.i(result.getJSONObject("datas").toString(),"datas ne");
//                        json = gson.toJson(result.getJSONObject("datas"));
//                        Log.w(json,"result data");
//                        JsonArray listPlaylist=result.getJSONArray("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


//                    if (responseBody.getStatusText().equals("OK")) {
//                        loginStatus = true;
//                    } else {
//                        Log.i("error","erorr");
////                        Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
////                        progressDialog.dismiss();
//                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }


    private void getInfoPlaylist(String id){
        DataService networkService = APIService.getService();
        Call<ResponseModel> getPlaylist = networkService.getPlaylist();

        getPlaylist.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {

//                Intent intent_signin =new Intent(getActivity(), Fragment_UserPlayer.class);
//                startActivity(intent_signin );
                MainActivity.viewPager.setCurrentItem(3);

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
        public void onBindViewHolder(@NonNull Fragment_div_playlist.MyRvAdapter.MyHolder holder, int position) {

            int positionOfData=position;
            holder.plTitle.setText(data.get(positionOfData).getTenPlayList());

            String name = data.get(positionOfData).getImgUrl();
            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            holder.imgPlaylist.setImageResource(resID);

            Log.i(data.get(positionOfData).getIdPlaylist(),"abcde");


            holder.divPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(data.get(positionOfData).getIdPlaylist(),"abc");
                    getInfoPlaylist(data.get(positionOfData).getIdPlaylist());
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
            RelativeLayout divPlaylist;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                plTitle = itemView.findViewById(R.id.plTitle);
                imgPlaylist = itemView.findViewById(R.id.imgPlaylist);
                divPlaylist = itemView.findViewById(R.id.divPlaylist);
            }
        }

    }
}
