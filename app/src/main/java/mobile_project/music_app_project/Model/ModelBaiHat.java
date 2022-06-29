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
    @SerializedName("musicImg")
    @Expose
    private String musicImg;

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

    public ModelBaiHat(String musicId, String musicName, String urlImg, String linkUrl, String playlistId, String categoryId, String artistId, String duration) {
    }

    public ModelBaiHat(ModelBaiHat currentSong, String songname, String songurl, String songimg) {
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @SerializedName("artistName")
    @Expose
    private String artistName;

    public ModelBaiHat(String musicId, String musicName, String imgUrl, String linkUrl, String playlistId, String categoryId, String artistId, String duration, String artistName) {
        this.musicId = musicId;
        this.musicName = musicName;
        this.musicImg = imgUrl;
        this.linkUrl = linkUrl;
        this.playlistId = playlistId;
        this.categoryId = categoryId;
        this.artistId = artistId;
        this.duration = duration;
        this.artistName = artistName;
    }

    public ModelBaiHat(String musicId, String musicName, String imgUrl) {
        this.musicId = musicId;
        this.musicName = musicName;
        this.musicImg = imgUrl;

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
        return musicImg;
    }

    public void setImgUrl(String imgUrl) {
        this.musicImg = imgUrl;
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

