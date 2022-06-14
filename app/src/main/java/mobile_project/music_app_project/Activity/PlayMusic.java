package mobile_project.music_app_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.ModelNgheSi;
import mobile_project.music_app_project.Model.ModelPlayList;
import mobile_project.music_app_project.Model.ModelTheLoai;
import mobile_project.music_app_project.Model.MyMediaPlayer;
import mobile_project.music_app_project.R;

public class PlayMusic extends AppCompatActivity {
//    MediaPlayer mediaPlayer;
//    ImageButton btnPlayMusic;
//    ImageButton btnPauseMusic;
////    img_Buttonplaypause
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_play_music);
//        btnPlayMusic = findViewById(R.id.img_Buttonplaypause);
//        btnPauseMusic = findViewById(R.id.img_Buttonpause);
//
//        btnPauseMusic.setVisibility(View.GONE);
//        btnPlayMusic.setVisibility(View.VISIBLE);
//
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tungthuong);
//
//        btnPlayMusic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                mediaPlayer.start();
//                btnPlayMusic.setVisibility(View.GONE);
//                btnPauseMusic.setVisibility(View.VISIBLE);
//            }
//        });
//
//        btnPauseMusic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                btnPauseMusic.setVisibility(View.GONE);
//                btnPlayMusic.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mediaPlayer.stop();
//        mediaPlayer.release();
//
//    }
    TextView titleTv,currentTimeTv,totalTimeTv;
    SeekBar seekBar;
    ImageView pausePlay,nextBtn,previousBtn,musicIcon;
    ArrayList<ModelBaiHat> songsList;

    ModelBaiHat currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
//    MediaPlayer mediaPlayer;
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);

        titleTv.setSelected(true);

        getMusicFromServer();
        setResourcesWithMusic();

        PlayMusic.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        Log.i("complete song","compelte Song");
                        playNextSong();
                    }
                });
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.iconpause);
                        musicIcon.setRotation(x++);
                    }else{
                        pausePlay.setImageResource(R.drawable.iconplay);
                        musicIcon.setRotation(0);
                    }

                }
                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    void getMusicFromServer(){
//        Intent intent = getIntent();
        songsList = (ArrayList<ModelBaiHat>) getIntent().getSerializableExtra("musicListPlay");
        for ( int i = 0; i < songsList.size(); i++){
            Log.i(songsList.get(i).getMusicName(),"songname123");
        }


    }




    void setResourcesWithMusic(){
        currentSong = songsList.get(MyMediaPlayer.currentIndex);
        Log.i(currentSong.getLinkUrl(),"currentSongName");
        Log.i(currentSong.getImgUrl(),"currentSongName123");
        titleTv.setText(currentSong.getMusicName());

        totalTimeTv.setText(currentSong.getDuration());
//        totalTimeTv.setText(convertToMMSS(String.valueOf("0410")));

        pausePlay.setOnClickListener(v-> pausePlay());
        nextBtn.setOnClickListener(v-> playNextSong());
        previousBtn.setOnClickListener(v-> playPreviousSong());

//
//        titleTv.setText("Tung Thuong");
//
//        totalTimeTv.setText(convertToMMSS(String.valueOf("0410")));
//
//        pausePlay.setOnClickListener(v-> pausePlay());


        playMusic();


    }


    private void playMusic(){

//        mediaPlayer.reset();
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tungthuong);
//        mediaPlayer.start();
//        seekBar.setProgress(0);
//        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.reset();
        String name = currentSong.getLinkUrl();
        int resID  = getResources().getIdentifier(name, "raw", getApplicationContext().getPackageName());
        Log.i(String.valueOf(resID),"resId");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resID);
        mediaPlayer.start();
        seekBar.setProgress(0);
        seekBar.setMax(mediaPlayer.getDuration());
//        try {
//            String name = currentSong.getLinkUrl();
//            int resID  = getResources().getIdentifier(name, "raw", getApplicationContext().getPackageName());
//            Log.i(String.valueOf(resID),"resId");
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), resID);
////            holder.imgPlaylist.setImageResource(resID);
////            mediaPlayer.setDataSource(currentSong.getPath());
////            mediaPlayer.prepare();
//            mediaPlayer.start();
//            seekBar.setProgress(0);
//            seekBar.setMax(mediaPlayer.getDuration());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private void playNextSong(){
        Log.i("next song button","click on");
        if(MyMediaPlayer.currentIndex== songsList.size()-1)
            return;
        MyMediaPlayer.currentIndex +=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }

    private void playPreviousSong(){
        if(MyMediaPlayer.currentIndex== 0)
            return;
        MyMediaPlayer.currentIndex -=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
    }


    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}