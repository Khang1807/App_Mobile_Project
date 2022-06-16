package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelTheLoai implements Serializable{
    @SerializedName("IdTheLoai")
    @Expose
    private String categoryId;
    @SerializedName("TenTheLoai")
    @Expose
    private String categoryName;
    @SerializedName("HinhTheLoai")
    @Expose
    private String categoryImg;

    public ModelTheLoai(String categoryId, String categoryName, String imgUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImg = imgUrl;
    }




    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImgUrl() {
        return categoryImg;
    }

    public void setImgUrl(String imgUrl) {
        this.categoryImg = imgUrl;
    }










}
