package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelNgheSi implements Serializable {

    @SerializedName("artistId")
    @Expose
    private String artistId;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("HinhNgheSi")
    @Expose
    private String artistImg;

    public ModelNgheSi(String artistId) {
        this.artistId = artistId;
    }

    public ModelNgheSi(String artistId, String artistname, String imgUrl) {
        this.artistId = artistId;
        this.artistName = artistname;
        this.artistImg = imgUrl;
    }

    public ModelNgheSi() {

    }

    public String getImgUrl() {
        return artistImg;
    }

    public void setImgUrl(String imgUrl) {
        this.artistImg = imgUrl;
    }
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistname(String artistName) {
        this.artistName = artistName;
    }

}

