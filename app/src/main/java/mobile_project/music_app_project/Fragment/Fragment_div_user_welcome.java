package mobile_project.music_app_project.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

import mobile_project.music_app_project.Activity.SignIn;
import mobile_project.music_app_project.R;

public class Fragment_div_user_welcome extends Fragment {
    SharedPreferences sharedPreferences;
    View view;
    TextView txt_name;
    String name = SignIn.name_user;
    String img_url = SignIn.img_user;
    String MY_URL_STRING = img_url;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_div_user_welcome,container,false);
        txt_name = view.findViewById(R.id.user_name_welcom);
        txt_name.setText(name);
        new DownloadImageTask((ImageView) view.findViewById(R.id.imageView1))
                .execute(MY_URL_STRING);
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
//        txt_name.setText(sharedPreferences.getString("name_user",""));
        return view;
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
