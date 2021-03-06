package mobile_project.music_app_project.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;

import mobile_project.music_app_project.Activity.ChinhSach;
import mobile_project.music_app_project.Activity.DieuKhoan;
import mobile_project.music_app_project.Activity.MainActivity;
import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.Activity.User_Setting_Activity;
import mobile_project.music_app_project.R;

public class Fragment_UserPlayer extends Fragment {
    View view;
    TextView txt_name;
    Button btn_dangxuat,btn_setting_user,btn_dieukhoan,btn_chinhsach;
    ImageView img_avatar;
    String img_url = SignIn.img_user;
    String MY_URL_STRING = img_url;
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_player,container,false);
        anhxa();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        txt_name.setText(sharedPreferences.getString("name_user",""));
        new DownloadImageTask((ImageView) view.findViewById(R.id.img_user_profile))
                .execute(MY_URL_STRING);
        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });

        btn_setting_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), User_Setting_Activity.class);
                startActivity(intent1);
            }
        });

        btn_dieukhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), DieuKhoan.class);
                startActivity(intent2);
            }
        });

        btn_chinhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), ChinhSach.class);
                startActivity(intent3);
            }
        });
        return view;
    }
    private void anhxa(){
        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
        btn_setting_user = view.findViewById(R.id.btn_setting);
        img_avatar = view.findViewById(R.id.img_user_profile);
        txt_name = view.findViewById(R.id.tennguoidung);
        btn_dieukhoan = view.findViewById(R.id.btn_term);
        btn_chinhsach = view.findViewById(R.id.btn_policy);
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
