package mobile_project.music_app.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelBaiHat implements Parcelable {
    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("Rating")
    @Expose
    private String rating;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("NgayPhatHanh")
    @Expose
    private String ngayPhatHanh;
    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;

    @SerializedName("IdTheLoai")
    @Expose
    private String idTheloai;

    @SerializedName("IdNgheSi")
    @Expose
    private String idNgheSi;

    public ModelBaiHat(String idBaiHat, String tenBaiHat, String hinhBaiHat, String linkBaiHat, String rating, String noidung, String ngayPhatHanh, String idPlaylist, String idAlbum, String idTheloai, String idNgheSi) {
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.linkBaiHat = linkBaiHat;
        this.rating = rating;
        this.ngayPhatHanh= ngayPhatHanh;
        this.noiDung =noidung;
        this.idPlaylist =idPlaylist;
        this.idAlbum = idAlbum;
        this.idTheloai =idTheloai;
        this.idNgheSi = idNgheSi;
    }


    protected ModelBaiHat(Parcel in) {
        idBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        linkBaiHat = in.readString();
        rating = in.readString();
        noiDung = in.readString();
        ngayPhatHanh =in.readString();
        idPlaylist =in.readString();
        idAlbum = in.readString();
        idTheloai = in.readString();
        idNgheSi = in.readString();

    }

    public static final Creator<ModelBaiHat> CREATOR = new Creator<ModelBaiHat>() {
        @Override
        public ModelBaiHat createFromParcel(Parcel in) {
            return new ModelBaiHat(in);
        }

        @Override
        public ModelBaiHat[] newArray(int size) {
            return new ModelBaiHat[size];
        }
    };

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
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

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }
    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getIdTheloai() {
        return idTheloai;
    }

    public void setIdTheloai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    public String getIdNgheSi() {
        return idNgheSi;
    }

    public void setIdNgheSi(String idNgheSi) {
        this.idNgheSi = idNgheSi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idBaiHat);
        parcel.writeString(tenBaiHat);
        parcel.writeString(hinhBaiHat);
        parcel.writeString(linkBaiHat);
        parcel.writeString(rating);
        parcel.writeString(noiDung);
        parcel.writeString(ngayPhatHanh);
        parcel.writeString(idPlaylist);
        parcel.writeString(idAlbum);
        parcel.writeString(idTheloai);
        parcel.writeString(idNgheSi);
    }

}

