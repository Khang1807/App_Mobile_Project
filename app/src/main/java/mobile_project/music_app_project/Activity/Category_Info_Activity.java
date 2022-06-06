package mobile_project.music_app_project.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mobile_project.music_app_project.Adapter.CategoryInfo_Adapter;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ModelTheLoai;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Info_Activity extends AppCompatActivity {
    private static Context context;
    ModelTheLoai category;
    ArrayList<ModelBaiHat> dataSource;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv ;
    CategoryInfo_Adapter myrv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_info);
        rv = (RecyclerView) findViewById(R.id.category_music_list);
        DataIntent();
        if(category!=null && !category.getCategoryName().equals("")){
        GetDataCategory(category.getCategoryId());}
    }



    private void DataIntent() {
        Intent i = getIntent();
        if(i!=null){
            if(i.hasExtra("Category")){
                category = (ModelTheLoai) i.getSerializableExtra("Category");
                Toast.makeText(this,category.getCategoryId(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void GetDataCategory(String id){

        DataService dataservice = APIService.getService();
        Call<ResponseModel> getCategoryInfo = dataservice.getCategoryInfo(id);

        getCategoryInfo.enqueue(new Callback<ResponseModel>() {
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

                    JSONArray catgoryInfo;

                    JSONObject resultGetData = null;

                    try {
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        catgoryInfo = datas.getJSONArray("categoryInfo");

                        for (int i=0;i<catgoryInfo.length();i++){

                            String musicId = catgoryInfo.getJSONObject(i).optString("musicId");
                            String musicName = catgoryInfo.getJSONObject(i).optString("musicName");
                            String urlImg = catgoryInfo.getJSONObject(i).optString("imgUrl");
                            ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg);
                            dataSource.add(music);
                        }

                        for(int i = 0; i<dataSource.size();i++){
                            Log.i(dataSource.get(i).getImgUrl(),"songname");
                        }
                        linearLayoutManager = new LinearLayoutManager(Category_Info_Activity.this);
                        myrv = new CategoryInfo_Adapter(Category_Info_Activity.this,dataSource);
                        if(Category_Info_Activity.this==null){
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



//    class MyRvAdapter extends RecyclerView.Adapter<Category_Info_Activity.MyRvAdapter.MyHolder> {
//        ArrayList<ModelBaiHat> data;
//
//        public MyRvAdapter(ArrayList<ModelBaiHat> data) {
//            this.data = data;
//        }
//
//        @NonNull
//        @Override
//        public Category_Info_Activity.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.category_music_item, parent, false);
//            return new Category_Info_Activity.MyRvAdapter.MyHolder(view);
//        }
//
//
//
//        @Override
//        public void onBindViewHolder(@NonNull Category_Info_Activity.MyRvAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
//
//            int positionOfData=position;
//            holder.category_song.setText(data.get(positionOfData).getMusicName());
//
//            String name = data.get(positionOfData).getImgUrl();
//            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
//            holder.song_img.setImageResource(resID);
//
//            Log.i(data.get(positionOfData).getCategoryId(),"abcde");
////            holder.divCategory_Music.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////                    Log.i(data.get(positionOfData).getMusicId(),"abc");
////                    GetDataCategory(data.get(positionOfData).getMusicId());
////                }
////            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//
//        class MyHolder extends RecyclerView.ViewHolder {
//            TextView category_song;
//            ImageView song_img;
//            LinearLayout divCategory_Music;
//            public MyHolder(@NonNull View itemView) {
//                super(itemView);
//                category_song = itemView.findViewById(R.id.category_song_name);
//                song_img = itemView.findViewById(R.id.imgMusic_Category);
//                divCategory_Music = itemView.findViewById(R.id.divCategory_Music);
//            }
//        }
//
//    }
    public static Context getContext() {
        return context;
    }
}
