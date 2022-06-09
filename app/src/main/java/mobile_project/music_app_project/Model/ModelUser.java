package mobile_project.music_app_project.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelUser implements Serializable {
    @SerializedName("UserName")
    @Expose
    private String userName;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("IdUser")
    @Expose
    private String idUser;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("HinhDaiDien")
    @Expose
    private String hinhDaiDien;

    @SerializedName("idBaiHat")
    @Expose
    private String idBaiHat;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) { this.hinhDaiDien = hinhDaiDien;
    }
    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }
}
