package com.example.android.vlad.baking;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Vlad
 */

public class StepFragment extends Fragment {

    Step step;
    TextView shortDescriptionTextView;
    TextView descriptionTextView;
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView playerView;

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

        shortDescriptionTextView.setText(this.step.getShortDescription());
        descriptionTextView.setText(this.step.getDescription());
        playerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        startPlayback();

        return rootView;
    }

    private void startPlayback(){

        String video = step.getVideoURL();

        if (video.isEmpty()){
            video = step.getThumbnailURL();
            if (video.isEmpty()){
                return;
            }
        }

        if (!video.endsWith("mp4")){
            return;
        }

        Uri videoUri = Uri.parse(step.getVideoURL());
        initializePlayer(videoUri);
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
            exoPlayer.setPlayWhenReady(true);

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
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }
}
