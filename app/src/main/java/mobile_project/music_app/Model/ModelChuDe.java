package mobile_project.music_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelChuDe implements Serializable {
    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("TenChuDe")
    @Expose
    private String tenChuDe;
    @SerializedName("HinhChuDe")
    @Expose
    private String hinhChuDe;


    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(String hinhChuDe) { this.hinhChuDe = hinhChuDe; }


}
