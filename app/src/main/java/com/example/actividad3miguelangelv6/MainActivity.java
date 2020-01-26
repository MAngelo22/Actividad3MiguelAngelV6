package com.example.actividad3miguelangelv6;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {
    private PlayerView playerView;
    private SimpleExoPlayer player;
    String videoURL = "https://www3.gobiernodecanarias.org/medusa/mediateca/cprofesgrancanariasur/wp-content/uploads/sites/24/2017/12/3--creamos-el-cuerpo-del-motor.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.PlayerView);
    }

    @Override
    protected void onStart(){
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        SimpleExoPlayer reproductor = ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        playerView.setPlayer(player);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "Actividad3"));

        ExtractorMediaSource archivoMultimedia = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse("https://www3.gobiernodecanarias.org/medusa/mediateca/cprofesgrancanariasur/wp-content/uploads/sites/24/2017/12/3--creamos-el-cuerpo-del-motor.mp4"));
        player.prepare(archivoMultimedia);
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onStop(){
        super.onStop();

        playerView.setPlayer(null);
        player.release();
        player = null;
    }

}

    /* ------------Prueba 2-----------------
    PlayerView playerView;
    SimpleExoPlayer player;
    String videoURL = "https://www3.gobiernodecanarias.org/medusa/mediateca/iesgeneto/wp-content/uploads/sites/9/2013/11/paseo-virtual-final.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.PlayerView);

        try {

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("Actividad_3");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            playerView.setPlayer(player);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }catch (Exception e) {
            Log.e("MainActivity", " exoplayer error " + e.toString());
        }

    }*/

