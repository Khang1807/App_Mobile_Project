package mobile_project.music_app_project.Service_API;


import java.util.List;

import mobile_project.music_app_project.Activity.Const;
import mobile_project.music_app_project.Model.ModelUser;
import mobile_project.music_app_project.Model.ResponseModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("auth/register")
    Call<ResponseModel> register(@Part(Const.KEY_USERNAME) RequestBody userName,
                                 @Part(Const.KEY_EMAIL)RequestBody email,
                                 @Part(Const.KEY_PASSWORD)RequestBody password,
                                 @Part MultipartBody.Part avt);

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
    @GET("playlist/get-top10-playlist")
    Call<ResponseModel> getTop10Playlist();

    @FormUrlEncoded
    @POST("category/get-category-info")
    Call<ResponseModel> getCategoryInfo(@Field("categoryId")String id);

    @FormUrlEncoded
    @POST("playlist/get-playlist-info")
    Call<ResponseModel> getPlaylistInfo(@Field("playlistId")String id);

    @FormUrlEncoded
    @POST("artist/get-artist-info")
    Call<ResponseModel> getArtistInfo(@Field("artistId")String id);

    @FormUrlEncoded
    @POST("music/find-music")
    Call<ResponseModel> findMusic(@Field("keyword") String keyword);

    @FormUrlEncoded
    @POST("playlistofuser/add-playlist-of-user")
    Call<ResponseModel> addplaylist_user(@Field("userId") String userId,@Field("musicId") String musicId);

    @FormUrlEncoded
    @POST("playlistofuser/get-playlist-user")
    Call<ResponseModel> getplaylist_user(@Field("userId") String userId);


    @DELETE("playlist/delete-playlist-of-user")
    Call<ResponseModel> deletePlaylist_User(@Query("userId") String userId,@Query("musicId")String musicId);
    @FormUrlEncoded
    @POST("history/add-history-of-user")
    Call<ResponseModel> addHistoryUser(@Field("userId") String userId,@Field("musicId") String musicId, @Field("time") String time);

    @GET("history/get-history-of-user")
    Call<ResponseModel> getHistoryUser(@Query("userId") String userId);
}
