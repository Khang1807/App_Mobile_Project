package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelBaiHat implements Serializable {
    @SerializedName("musicId")
    @Expose
    private String musicId;
    @SerializedName("musicName")
    @Expose
    private String musicName;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("linkUrl")
    @Expose
    private String linkUrl;
    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("NgayPhatHanh")
    @Expose
    private String ngayPhatHanh;
    @SerializedName("playlistId")
    @Expose
    private String playlistId;
    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;

    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("artistId")
    @Expose
    private String artistId;

    @SerializedName("duration")
    @Expose
    private String duration;

    public ModelBaiHat(String musicId, String musicName, String imgUrl, String linkUrl, String playlistId, String categoryId, String artistId, String duration) {
        this.musicId = musicId;
        this.musicName = musicName;
        this.imgUrl = imgUrl;
        this.linkUrl = linkUrl;
        this.playlistId = playlistId;
        this.categoryId = categoryId;
        this.artistId = artistId;
        this.duration=duration;
    }


    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

