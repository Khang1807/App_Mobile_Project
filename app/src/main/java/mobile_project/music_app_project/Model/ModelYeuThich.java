package mobile_project.music_app_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelYeuThich implements Serializable {
    @SerializedName("IdYeuThich")
    @Expose
    private int idYeuThich;

    @SerializedName("UserName")
    @Expose
    private String username;

    @SerializedName("IdBaiHat")
    @Expose
    private int idBaiHat;

    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;

    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    @SerializedName("TenNgheSi")
    @Expose
    private String tenNgheSi;


    public ModelYeuThich(int idYeuThich, String username, int idBaiHat, String tenBaiHat, String hinhBaiHat, String tenNgheSi) {
        this.idYeuThich = idYeuThich;
        this.username = username;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.tenNgheSi = tenNgheSi;
    }

    public int getIdYeuThich() {
        return idYeuThich;
    }

    public void setIdYeuThich(int idYeuThich) {
        this.idYeuThich = idYeuThich;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(int idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getTenNgheSi() {
        return tenNgheSi;
    }

    public void setTenNgheSi(String tenCaSi) {
        this.tenNgheSi = tenNgheSi;
    }


    protected ModelYeuThich(Parcel in) {
        idYeuThich = in.readInt();
        username = in.readString();
        idBaiHat = in.readInt();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenNgheSi = in.readString();
    }


}