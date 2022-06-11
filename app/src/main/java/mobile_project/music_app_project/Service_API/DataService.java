package mobile_project.music_app_project.Service_API;


import java.util.List;

import mobile_project.music_app_project.Model.ModelUser;
import mobile_project.music_app_project.Model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/get-user")
    Call<ResponseModel> getUserInfo(@Field("email") String email);

    @GET("playlist/get-playlist")
    Call<ResponseModel> getPlaylist();

    @GET("artist/get-artist")
    Call<ResponseModel> getArtistList();

    @GET("music/get-music")
    Call<ResponseModel> getMusic();

    @GET("category/get-category")
    Call<ResponseModel> getCategoryList();
    @GET("music/get-music-top10")
    Call<ResponseModel> getTop10Music();

    @FormUrlEncoded
    @POST("category/get-category-info")
    Call<ResponseModel> getCategoryInfo(@Field("categoryId")String id);
}
