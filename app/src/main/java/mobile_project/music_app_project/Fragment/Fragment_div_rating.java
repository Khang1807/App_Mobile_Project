package mobile_project.music_app_project.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_rating extends Fragment {
    RecyclerView rv;
    ArrayList<ModelBaiHat> dataSource;


    LinearLayoutManager linearLayoutManager;
    Fragment_div_rating.MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_rating, container, false);
        rv = view.findViewById(R.id.rating);

        getTop10Music();
        return view;
    }

    private void getTop10Music() {


        DataService networkService = APIService.getService();
        Call<ResponseModel> getTop10Music = networkService.getTop10Music();

        getTop10Music.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {
                    Log.i("has result", "has result");
                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelBaiHat>();

                    JSONArray listMusicTop;

                    JSONObject resultGetData = null;

                    try {
                        Log.i(getResources().getDrawable(R.drawable.album7).toString(),"url img");
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listMusicTop = datas.getJSONArray("musicTop10List");

                        for (int i=0;i<listMusicTop.length();i++){

                            String musicId = listMusicTop.getJSONObject(i).optString("musicId");
                            String musicName = listMusicTop.getJSONObject(i).optString("musicName");
                            String urlImg = listMusicTop.getJSONObject(i).optString("imgUrl");


                            ModelBaiHat music = new ModelBaiHat(musicId,musicName,urlImg);
                            dataSource.add(music);

                        }

                        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        myRvAdapter = new Fragment_div_rating.MyRvAdapter(dataSource);
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


    class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_rating.MyRvAdapter.MyHolder> {
        ArrayList<ModelBaiHat> data;

        public MyRvAdapter(ArrayList<ModelBaiHat> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_rating.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rating_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment_div_rating.MyRvAdapter.MyHolder holder, int position) {
            int positionOfData=position;
            holder.ratingTitle.setText(data.get(positionOfData).getMusicName());

            String name = data.get(positionOfData).getImgUrl();
            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            holder.imgRating.setImageResource(resID);

            Log.i(data.get(positionOfData).getCategoryId(),"abcde");
//            holder.divCategory.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(getContext(), Category_Info_Activity.class);
//                    i.putExtra("Category",data.get(position));
//                    getContext().startActivity(i);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView ratingTitle;
            ImageView imgRating;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                ratingTitle = itemView.findViewById(R.id.ratingTitle);
                imgRating = itemView.findViewById(R.id.imgRating);
            }
        }

    }
}
