package com.example.android.vlad.baking;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.vlad.baking.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.example.android.vlad.baking.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad
 */

public class StepFragment extends Fragment {

    Step step;
    TextView shortDescriptionTextView;
    TextView descriptionTextView;
    ImageView thumbnailImageView;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView playerView;
    private long playerPosition;
    private boolean isPlayWhenReady;

    public StepFragment(){
    }

    public void setStep(Step step){
        this.step = step;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        shortDescriptionTextView = (TextView) rootView.findViewById(R.id.recipe_step_short_description);
        descriptionTextView = (TextView) rootView.findViewById(R.id.recipe_step_description);
        thumbnailImageView = (ImageView) rootView.findViewById(R.id.videoThumbnail);
        playerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        if (savedInstanceState != null){
            this.step = savedInstanceState.getParcelable(getString(R.string.step_key));
            this.playerPosition = savedInstanceState.getLong(getString(R.string.player_position_key));
            this.isPlayWhenReady = savedInstanceState.getBoolean(getString(R.string.play_state_key));
        }
        else{
            this.isPlayWhenReady = true;
            this.playerPosition = 0L;
        }
        shortDescriptionTextView.setText(this.step.getShortDescription());
        descriptionTextView.setText(this.step.getDescription());

        startPlayback();

        return rootView;
    }

    private void startPlayback(){

        String video = step.getVideoURL();

        if (video.isEmpty()){
            String thumbnail = step.getThumbnailURL();
            if (!ImageUtils.thumbnailIsValid(thumbnail)){
                return;
            }
            initializePictureViewer(thumbnail);
        }

        if (!video.endsWith("mp4")){
            return;
        }
        Uri videoUri = Uri.parse(step.getVideoURL());
        initializePlayer(videoUri);
    }

    @Override
    public void onResume() {
        super.onResume();
        startPlayback();
    }

    private void initializePictureViewer(String thumbnail){
        thumbnailImageView.setVisibility(View.VISIBLE);
        ImageUtils.setImage(this.getContext(), thumbnail, thumbnailImageView);
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);

            playerView.setPlayer(exoPlayer);
            playerView.setVisibility(View.VISIBLE);

            String userAgent = Util.getUserAgent(this.getContext(), "Baking");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    this.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);

            exoPlayer.prepare(mediaSource);
            exoPlayer.seekTo(this.playerPosition);
            exoPlayer.setPlayWhenReady(isPlayWhenReady);

        }
    }

    private void releasePlayer() {
        if (exoPlayer != null){
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        long position = 0L;
        boolean isPlayWhenReady;

        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
            isPlayWhenReady = exoPlayer.getPlayWhenReady();
        }
        else {
            isPlayWhenReady = true;
        }
        outState.putParcelable(getString(R.string.step_key), step);
        outState.putLong(getString(R.string.player_position_key), position);
        outState.putBoolean(getString(R.string.play_state_key), isPlayWhenReady);
        super.onSaveInstanceState(outState);
    }
}
