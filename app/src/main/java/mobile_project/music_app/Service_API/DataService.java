package mobile_project.music_app.Service_API;



import java.util.HashMap;
import java.util.List;

import mobile_project.music_app.Model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseModel> login(@Field("email") String email, @Field("password") String password);

}
