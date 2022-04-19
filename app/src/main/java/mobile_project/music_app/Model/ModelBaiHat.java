package mobile_project.music_app.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelBaiHat implements Parcelable {
    @SerializedName("IdBaiHat")
    @Expose
    private int idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("TenCaSi")
    @Expose
    private String tenCaSi;

    public ModelBaiHat (int idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi) {
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.tenCaSi = tenCaSi;
    }


    protected ModelBaiHat(Parcel in) {
        idBaiHat = in.readInt();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenCaSi = in.readString();
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

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idBaiHat);
        parcel.writeString(tenBaiHat);
        parcel.writeString(hinhBaiHat);
        parcel.writeString(tenCaSi);
    }

}
