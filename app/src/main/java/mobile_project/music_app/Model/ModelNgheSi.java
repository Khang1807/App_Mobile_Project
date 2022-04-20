package mobile_project.music_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelNgheSi implements Serializable {
    @SerializedName("IdNgheSi")
    @Expose
    private String idNgheSi;
    @SerializedName("TenNgheSi")
    @Expose
    private String tenNgheSi;
    @SerializedName("HinhNgheSi")
    @Expose
    private String hinhNgheSi;


    public String getIdNgheSi() {
        return idNgheSi;
    }

    public void setIdNgheSi(String idNgheSi) {
        this.idNgheSi = idNgheSi;
    }

    public String getTenNgheSi() {
        return tenNgheSi;
    }

    public void setTenNgheSi(String tenNgheSi) {
        this.tenNgheSi = tenNgheSi;
    }

    public String getHinhNgheSi() {
        return hinhNgheSi;
    }

    public void setHinhNgheSi(String hinhNgheSi) {
        this.hinhNgheSi = hinhNgheSi;
    }
}

