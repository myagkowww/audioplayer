package com.dame.VibraVerse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import android.media.MediaPlayer;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button pauseButton;
    MediaPlayer mPlayer;
    MediaPlayer mPlayer2;
    MediaPlayer mPlayer3;
    MediaPlayer mPlayer4;
    MediaPlayer mPlayer5;
    ArrayList<State> states = new ArrayList<State>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = MediaPlayer.create(this, R.raw.reincarnation);
        pauseButton = findViewById(R.id.button);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2(mPlayer);
            }
        });
        mPlayer2 = MediaPlayer.create(this, R.raw.dancincorpse);
        mPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2(mPlayer2);
            }
        });
        mPlayer3 = MediaPlayer.create(this, R.raw.dynamic);
        mPlayer3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2(mPlayer3);
            }
        });
        mPlayer4 = MediaPlayer.create(this, R.raw.massacre);
        mPlayer4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2(mPlayer4);
            }
        });
        mPlayer5 = MediaPlayer.create(this, R.raw.phonkytown);
        mPlayer5.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay2(mPlayer5);
            }
        });
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
        // определяем слушателя нажатия элемента в списке
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(State state, int position) {
                if (state.getName() == "REINCARNATION") {
                    stopPlay2(mPlayer2);
                    stopPlay2(mPlayer3);
                    stopPlay2(mPlayer4);
                    stopPlay2(mPlayer5);
                    mPlayer.start();
                } else if (state.getName() == "DANCING CORPSE") {
                    stopPlay2(mPlayer);
                    stopPlay2(mPlayer3);
                    stopPlay2(mPlayer4);
                    stopPlay2(mPlayer5);
                    mPlayer2.start();
                } else if (state.getName() == "DYNAMIC") {
                    stopPlay2(mPlayer);
                    stopPlay2(mPlayer2);
                    stopPlay2(mPlayer4);
                    stopPlay2(mPlayer5);
                    mPlayer3.start();
                } else if (state.getName() == "MASSACRE") {
                    stopPlay2(mPlayer);
                    stopPlay2(mPlayer2);
                    stopPlay2(mPlayer3);
                    stopPlay2(mPlayer5);
                    mPlayer4.start();
                } else if (state.getName() == "PHONKY TOWN") {
                    stopPlay2(mPlayer);
                    stopPlay2(mPlayer2);
                    stopPlay2(mPlayer3);
                    stopPlay2(mPlayer4);
                    mPlayer5.start();
                }

            }
        };
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this, states, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    public void play(View view) {
        mPlayer.start();
    }

    private void stopPlay2(MediaPlayer mediaPlayer){
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void pause(View view){
        try {
            if (mPlayer.isPlaying()){
                mPlayer.pause();
                Toast.makeText(this, "Pause button clicked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Player is not playing", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error pausing: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            if (mPlayer2.isPlaying()) {
                mPlayer2.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (mPlayer3.isPlaying()) {
                mPlayer3.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (mPlayer4.isPlaying()) {
                mPlayer4.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (mPlayer5.isPlaying()) {
                mPlayer5.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void setInitialData() {

            states.add(new State("REINCARNATION", "HXLXST", R.drawable.phonkimage));
            states.add(new State("DANCING CORPSE", "REDFXRD", R.drawable.phonkimage2));
            states.add(new State("DYNAMIC", "SHADXWBXRN", R.drawable.phonkimage3));
            states.add(new State("MASSACRE", "SHADXWBXRN", R.drawable.phonkimage4));
            states.add(new State("PHONKY TOWN", "PlayaPhonk", R.drawable.phonkimage5));
        }
    }
