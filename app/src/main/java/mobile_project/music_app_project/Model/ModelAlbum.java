package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelAlbum implements Serializable {
    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("HinhAlbum")
    @Expose
    private String hinhAlbum;
    @SerializedName("TenNgheSiAlbum")
    @Expose
    private String tenNgheSiAlbum;



    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

    public String getTenNgheSiAlbum() { return tenNgheSiAlbum; }

    public void setTenNgheSiAlbum(String tenNgheSiAlbum) {
        this.tenNgheSiAlbum = tenNgheSiAlbum;
    }

}
