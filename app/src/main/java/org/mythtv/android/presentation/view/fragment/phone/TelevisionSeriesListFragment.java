/*
 * MythtvPlayerForAndroid. An application for Android users to play MythTV Recordings and Videos
 * Copyright (c) 2016. Daniel Frey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mythtv.android.presentation.view.fragment.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.mythtv.android.R;
import org.mythtv.android.presentation.internal.di.components.VideoComponent;
import org.mythtv.android.presentation.internal.di.modules.VideosModule;
import org.mythtv.android.presentation.model.VideoMetadataInfoModel;
import org.mythtv.android.presentation.presenter.phone.TelevisionSeriesListPresenter;
import org.mythtv.android.presentation.view.VideoListView;
import org.mythtv.android.presentation.view.adapter.phone.VideosLayoutManager;
import org.mythtv.android.presentation.view.adapter.phone.VideoSeriesAdapter;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmfrey on 8/31/15.
 */
public class TelevisionSeriesListFragment extends AbstractBaseFragment implements VideoListView {

    private static final String TAG = TelevisionSeriesListFragment.class.getSimpleName();

    private static final String ARGUMENT_KEY_SERIES = "org.mythtv.android.ARGUMENT_SERIES";

    private String series;

    /**
     * Interface for listening videoMetadataInfo list events.
     */
    public interface VideoMetadataInfoListListener {

        void onVideoMetadataInfoClicked( final VideoMetadataInfoModel videoMetadataInfoModel );

    }

    @Inject
    TelevisionSeriesListPresenter televisionSeriesListPresenter;

    @Bind( R.id.rv_videoMetadataInfos )
    RecyclerView rv_videoMetadataInfos;

    @Bind( R.id.rl_progress )
    RelativeLayout rl_progress;

    private VideoSeriesAdapter videoSeriesAdapter;

    private VideoMetadataInfoListListener videoMetadataInfoListListener;

    public TelevisionSeriesListFragment() { super(); }

    public static TelevisionSeriesListFragment newInstance(String series ) {

        TelevisionSeriesListFragment fragment = new TelevisionSeriesListFragment();

        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString( ARGUMENT_KEY_SERIES, series );
        fragment.setArguments( argumentsBundle );

        return fragment;
    }

    @Override public void onAttach( Activity activity ) {
        super.onAttach( activity );
        Log.d( TAG, "onAttach : enter" );

        if( activity instanceof VideoMetadataInfoListListener ) {
            this.videoMetadataInfoListListener = (VideoMetadataInfoListListener) activity;
        }

        Log.d( TAG, "onAttach : exit" );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        Log.d( TAG, "onCreateView : enter" );

        View fragmentView = inflater.inflate( R.layout.fragment_phone_video_list, container, false );
        ButterKnife.bind( this, fragmentView );
        setupUI();

        Log.d( TAG, "onCreateView : exit" );
        return fragmentView;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {
        Log.d( TAG, "onActivityCreated : enter" );
        super.onActivityCreated( savedInstanceState );

        this.initialize();
        this.loadTelevisionSeriesList();

        Log.d( TAG, "onActivityCreated : exit" );
    }

    @Override
    public void onResume() {
        Log.d( TAG, "onResume : enter" );
        super.onResume();

        this.televisionSeriesListPresenter.resume();

        Log.d( TAG, "onResume : exit" );
    }

    @Override
    public void onPause() {
        Log.d( TAG, "onPause : enter" );
        super.onPause();

        this.televisionSeriesListPresenter.pause();

        Log.d( TAG, "onPause : exit" );
    }

    @Override
    public void onDestroy() {
        Log.d( TAG, "onDestroy : enter" );
        super.onDestroy();

        this.televisionSeriesListPresenter.destroy();

        Log.d( TAG, "onDestroy : exit" );
    }

    @Override
    public void onDestroyView() {
        Log.d( TAG, "onDestroyView : enter" );
        super.onDestroyView();

        ButterKnife.unbind( this );

        Log.d( TAG, "onDestroyView : exit" );
    }

    private void initialize() {
        Log.d( TAG, "initialize : enter" );

        this.getComponent( VideoComponent.class ).inject( this );
        this.televisionSeriesListPresenter.setView( this );
        this.series = getArguments().getString( ARGUMENT_KEY_SERIES );
        this.getComponent( VideoComponent.class ).plus( new VideosModule() );

        this.televisionSeriesListPresenter.initialize( this.series );

        Log.d( TAG, "initialize : exit" );
    }

    private void setupUI() {
        Log.d( TAG, "setupUI : enter" );

        this.rv_videoMetadataInfos.setLayoutManager( new VideosLayoutManager( getActivity() ) );

        this.videoSeriesAdapter = new VideoSeriesAdapter( getActivity(), new ArrayList<VideoMetadataInfoModel>() );
        this.videoSeriesAdapter.setOnItemClickListener( onItemClickListener );
        this.rv_videoMetadataInfos.setAdapter( videoSeriesAdapter );

        Log.d( TAG, "setupUI : exit" );
    }

    @Override
    public void showLoading() {
        Log.d( TAG, "showLoading : enter" );

        this.rl_progress.setVisibility( View.VISIBLE );
        this.getActivity().setProgressBarIndeterminateVisibility( true );

        Log.d( TAG, "showLoading : exit" );
    }

    @Override
    public void hideLoading() {
        Log.d( TAG, "hideLoading : enter" );

        this.rl_progress.setVisibility( View.GONE );
        this.getActivity().setProgressBarIndeterminateVisibility( false );

        Log.d( TAG, "hideLoading : exit" );
    }

    @Override
    public void showRetry() {
        Log.d( TAG, "showRetry : enter" );

        Log.d( TAG, "showRetry : exit" );
    }

    @Override
    public void hideRetry() {
        Log.d( TAG, "hideRetry : enter" );

        Log.d( TAG, "hideRetry : exit" );
    }

    @Override
    public void renderVideoList(Collection<VideoMetadataInfoModel> videoMetadataInfoModelCollection ) {
        Log.d( TAG, "renderVideoList : enter" );

        if( null != videoMetadataInfoModelCollection ) {
            Log.d( TAG, "renderVideoList : videoMetadataInfoModelCollection is not null, videoMetadataInfoModelCollection=" + videoMetadataInfoModelCollection );

            this.videoSeriesAdapter.setVideoSeriesCollection( videoMetadataInfoModelCollection );

        }

        Log.d( TAG, "renderVideoList : exit" );
    }

    @Override
    public void viewVideo(VideoMetadataInfoModel videoMetadataInfoModel ) {
        Log.d( TAG, "viewVideo : enter" );

        if( null != this.videoMetadataInfoListListener ) {

            this.videoMetadataInfoListListener.onVideoMetadataInfoClicked( videoMetadataInfoModel );

        }

        Log.d( TAG, "viewVideo : exit" );
    }

    @Override
    public void showError( String message ) {
        Log.d( TAG, "showError : enter" );

        this.showToastMessage( message, getResources().getString( R.string.retry ), new View.OnClickListener() {

            @Override
            public void onClick( View v ) {

                TelevisionSeriesListFragment.this.loadTelevisionSeriesList();

            }

        });

        Log.d( TAG, "showError : exit" );
    }

    @Override
    public void showMessage( String message ) {
        Log.d( TAG, "showMessage : enter" );

        this.showToastMessage( message, null, null );

        Log.d( TAG, "showMessage : exit" );
    }

    @Override
    public Context getContext() {
        Log.d( TAG, "getContext : enter" );

        Log.d( TAG, "getContext : exit" );
        return this.getActivity().getApplicationContext();
    }

    /**
     * Loads all tv series.
     */
    private void loadTelevisionSeriesList() {
        Log.d( TAG, "loadTelevisionSeriesList : enter" );

        if( null != this.televisionSeriesListPresenter ) {

            this.televisionSeriesListPresenter.initialize( series );

        }

        Log.d( TAG, "loadTelevisionSeriesList : exit" );
    }

    private VideoSeriesAdapter.OnItemClickListener onItemClickListener = new VideoSeriesAdapter.OnItemClickListener() {

                @Override
                public void onVideoMetadataInfoItemClicked( VideoMetadataInfoModel videoMetadataInfoModel ) {

                    if( null != TelevisionSeriesListFragment.this.televisionSeriesListPresenter && null != videoMetadataInfoModel ) {

                        TelevisionSeriesListFragment.this.televisionSeriesListPresenter.onVideoClicked( videoMetadataInfoModel );

                    }

                }

    };

}