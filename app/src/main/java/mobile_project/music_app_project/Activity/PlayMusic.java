package mobile_project.music_app_project.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import mobile_project.music_app_project.Model.ModelBaiHat;
import mobile_project.music_app_project.Model.MyMediaPlayer;
import mobile_project.music_app_project.Model.ResponseModel;
import mobile_project.music_app_project.R;
import mobile_project.music_app_project.Service_API.APIService;
import mobile_project.music_app_project.Service_API.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusic extends AppCompatActivity {

    TextView titleTv, currentTimeTv, totalTimeTv,artistName;
    SeekBar seekBar;
    ImageView pausePlay, nextBtn, previousBtn, musicIcon, refresh_music;
    ArrayList<ModelBaiHat> songsList;
    View view;
    String msname;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    NotificationManager notificationManager;

    ModelBaiHat currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    int x = 0;
    boolean refreshFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        artistName = findViewById(R.id.artist_title);
        titleTv = findViewById(R.id.song_title);
        currentTimeTv = findViewById(R.id.current_time);
        totalTimeTv = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        nextBtn = findViewById(R.id.next);
        previousBtn = findViewById(R.id.previous);
        refresh_music = findViewById(R.id.refresh_music);
        musicIcon = findViewById(R.id.music_icon_big);


        titleTv.setSelected(true);

        getMusicFromServer();
        setResourcesWithMusic();


        PlayMusic.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        Log.i("complete song", "complete Song");
                        if (refreshFlag) {
                            playMusic();
                        } else {
                            playNextSong();
                        }

                    }
                });
                if (mediaPlayer.isPlaying())
                    pausePlay.setOnClickListener(v -> pausePlay());
                else {
                    pausePlay.setOnClickListener(v -> Play());
                }
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTv.setText(convertToMMSS(mediaPlayer.getCurrentPosition() + ""));

                    if (mediaPlayer.isPlaying()) {
                        pausePlay.setImageResource(R.drawable.iconpause);
                        musicIcon.setRotation(x++);
                    } else {
                        pausePlay.setImageResource(R.drawable.iconplay);
                        musicIcon.setRotation(0);
                    }

                }
                new Handler().postDelayed(this, 100);
            }
        });

        refresh_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                refreshFlag = !refreshFlag ? true : false;
                if (refreshFlag) {
                    refresh_music.setImageResource(R.drawable.ic_replay_2);
                } else {
                    refresh_music.setImageResource(R.drawable.ic_replay_1);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
                if (mediaPlayer.isPlaying()) {
                    pausePlay.setImageResource(R.drawable.iconpause);
                    musicIcon.setRotation(x++);
                } else {
                    pausePlay.setImageResource(R.drawable.iconplay);
                    musicIcon.setRotation(0);
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_play_music, container, false);
        circleImageView = view.findViewById(R.id.music_icon_big);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlay();
    }

    void getMusicFromServer() {
//        Intent intent = getIntent();
        songsList = (ArrayList<ModelBaiHat>) getIntent().getSerializableExtra("musicListPlay");
        for (int i = 0; i < songsList.size(); i++) {
            Log.i(songsList.get(i).getMusicName(), "songname123");
        }


    }


    void setResourcesWithMusic() {
        currentSong = songsList.get(MyMediaPlayer.currentIndex);
        Log.i(currentSong.getLinkUrl(), "currentSongName");
        Log.i(currentSong.getImgUrl(), "currentSongName123");
        titleTv.setText(currentSong.getMusicName());
        artistName.setText(currentSong.getArtistName());
        totalTimeTv.setText(currentSong.getDuration());
        nextBtn.setOnClickListener(v -> playNextSong());
        previousBtn.setOnClickListener(v -> playPreviousSong());
        playMusic();
    }

    private void playMusic(){


        String currentDate = getCurrentTime();
        addMusicIntoHistoryUser(currentDate);


        mediaPlayer.reset();
        String name = currentSong.getLinkUrl();
        int imgID  = getResources().getIdentifier(currentSong.getImgUrl(),"drawable", getApplicationContext().getPackageName());
        musicIcon.setImageResource(imgID);
        int resID  = getResources().getIdentifier(name, "raw", getApplicationContext().getPackageName());
        Log.i(String.valueOf(resID),"resId");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), resID);
        mediaPlayer.start();
        seekBar.setProgress(0);
        seekBar.setMax(mediaPlayer.getDuration());


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
    }
    private void Play(){
        if(!mediaPlayer.isPlaying())
        mediaPlayer.start();
    }


    public static String convertToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    public String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    public void addMusicIntoHistoryUser(String currentDate){
        DataService networkService = APIService.getService();
        Call<ResponseModel> addHistoryUser = networkService.addHistoryUser(SignIn.id_user,currentSong.getMusicId(),currentDate);
        addHistoryUser.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    Log.i("insert successfull","insert successfull");
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.i(t.getMessage(),"error server");
            }
        });
    }
}