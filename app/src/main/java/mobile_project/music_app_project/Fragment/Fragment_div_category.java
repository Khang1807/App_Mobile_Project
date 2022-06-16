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
import mobile_project.music_app_project.Model.ModelTheLoai;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_category extends Fragment {
    RecyclerView rv;
    ArrayList<ModelTheLoai> dataSource;

    LinearLayoutManager linearLayoutManager;
    Fragment_div_category.MyRvAdapter myRvAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_category, container, false);
        rv = view.findViewById(R.id.category_list);

        getCategoryList();
        return view;
    }



    private void getCategoryList() {
        Log.i("run function artist", "run function");

        DataService networkService = APIService.getService();
        Call<ResponseModel> getCategoryList = networkService.getCategoryList();

        getCategoryList.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();

                if (responseBody != null) {
                    Log.i("has result", "has result");
                    // ko xoa doan nay, code get data
                    Gson gson = new Gson();

                    assert response.body() != null;
                    String jsonResult = gson.toJson(response.body().getContent());

                    dataSource = new ArrayList<ModelTheLoai>();

                    JSONArray listCategory;

                    JSONObject resultGetData = null;

                    try {
                        Log.i(getResources().getDrawable(R.drawable.album7).toString(),"url img");
                        resultGetData = new JSONObject(jsonResult);

                        JSONObject datas = resultGetData.getJSONObject("datas");

                        listCategory = datas.getJSONArray("categoryList");

                        for (int i=0;i<listCategory.length();i++){

                            String categoryId = listCategory.getJSONObject(i).optString("categoryId");
                            String categoryName = listCategory.getJSONObject(i).optString("categoryName");
                            String urlImg = listCategory.getJSONObject(i).optString("categoryImg");


                            ModelTheLoai category = new ModelTheLoai(categoryId,categoryName,urlImg);
                            dataSource.add(category);

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






    public class MyRvAdapter extends RecyclerView.Adapter<Fragment_div_category.MyRvAdapter.MyHolder> {
        ArrayList<ModelTheLoai> data;

        public MyRvAdapter(ArrayList<ModelTheLoai> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Fragment_div_category.MyRvAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
            return new Fragment_div_category.MyRvAdapter.MyHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull Fragment_div_category.MyRvAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {

            int positionOfData=position;
            holder.categoryTitle.setText(data.get(positionOfData).getCategoryName());

            String name = data.get(positionOfData).getImgUrl();
            int resID  = getResources().getIdentifier(name, "drawable", getContext().getPackageName());
            holder.imgCategory.setImageResource(resID);

            Log.i(data.get(positionOfData).getCategoryId(),"abcde");
            holder.divCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), MusicList_Info_Activity.class);
                    i.putExtra("Category",data.get(position));
                    getContext().startActivity(i);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            TextView categoryTitle;
            ImageView imgCategory;
            LinearLayout divCategory;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                categoryTitle = itemView.findViewById(R.id.categoryTitle);
                imgCategory = itemView.findViewById(R.id.imgCategory);
                divCategory = itemView.findViewById(R.id.divCategory);
            }
        }

    }
}
