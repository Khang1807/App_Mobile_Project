package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ModelPlayList implements Serializable {

    @SerializedName("playlistId")
    @Expose
    private String playlistId;
    @SerializedName("nameOfPlaylist")
    @Expose
    private String nameOfPlaylist;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("score")
    @Expose
    private String score;

    public ModelPlayList(){}
    public ModelPlayList(String playlistId, String nameOfPlaylist, String urlImg, String score) {
        this.playlistId = playlistId;
        this.nameOfPlaylist = nameOfPlaylist;
        this.imgUrl = urlImg;
        this.score = score;
    }

    public String getIdPlaylist() {
        return playlistId;
    }

    public void setIdPlaylist(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getTenPlayList() {
        return nameOfPlaylist;
    }

    public void setTenPlayList(String tenPlayList) {
        this.nameOfPlaylist = tenPlayList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String hinhNen) {
        this.imgUrl = hinhNen;
    }


}
