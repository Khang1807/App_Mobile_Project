package mobile_project.music_app_project.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile_project.music_app_project.Activity.Liked_Song_Activity;
import mobile_project.music_app_project.Activity.MusicList_Info_Activity;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Activity.Suggest_Hist_Activity;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_div_suggestions extends Fragment {
    View view;
    Intent intent,intent2;
//    ArrayList<ModelBaiHat> dataSource;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_div_suggestions,container,false);
        String userId = SignIn.id_user;
        //getPlaylistList(userId);
        final Button button = (Button) view.findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(), Suggest_Hist_Activity.class);
                startActivity(intent);
            }
        });
        final Button button2 = (Button) view.findViewById(R.id.button6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent2=new Intent(getActivity(), MusicList_Info_Activity.class);
                //intent2.putExtra("PlaylistUser",userId);
                startActivity(intent2);
            }
        });
        return view;
    }

}
