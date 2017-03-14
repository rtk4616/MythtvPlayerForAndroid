package org.mythtv.android.presentation.mapper;

import org.mythtv.android.domain.Error;
import org.mythtv.android.domain.MediaItem;
import org.mythtv.android.presentation.internal.di.PerActivity;
import org.mythtv.android.presentation.model.ErrorModel;
import org.mythtv.android.presentation.model.MediaItemModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 *
 *
 *
 * @author dmfrey
 *
 * Created on 7/10/16.
 */

@PerActivity
public class MediaItemModelMapper {

    @Inject
    public MediaItemModelMapper() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public MediaItemModel transform( MediaItem mediaItem ) {

        MediaItemModel mediaItemModel = new MediaItemModel();
        mediaItemModel.setId( mediaItem.getId() );
        mediaItemModel.setMedia( mediaItem.getMedia() );
        mediaItemModel.setTitle( mediaItem.getTitle() );
        mediaItemModel.setSubTitle( mediaItem.getSubTitle() );
        mediaItemModel.setDescription( mediaItem.getDescription() );
        mediaItemModel.setStartDate( mediaItem.getStartDate() );
        mediaItemModel.setSeason( mediaItem.getSeason() );
        mediaItemModel.setEpisode( mediaItem.getEpisode() );
        mediaItemModel.setStudio( mediaItem.getStudio() );
        mediaItemModel.setUrl( mediaItem.getUrl() );
        mediaItemModel.setFanartUrl( mediaItem.getFanartUrl() );
        mediaItemModel.setCoverartUrl( mediaItem.getCoverartUrl() );
        mediaItemModel.setBannerUrl( mediaItem.getBannerUrl() );
        mediaItemModel.setPreviewUrl( mediaItem.getPreviewUrl() );
        mediaItemModel.setContentType( mediaItem.getContentType() );
        mediaItemModel.setDuration( mediaItem.getDuration() );
        mediaItemModel.setPercentComplete( mediaItem.getPercentComplete() );
        mediaItemModel.setRecording( mediaItem.isRecording() );
        mediaItemModel.setLiveStreamId( mediaItem.getLiveStreamId() );
        mediaItemModel.setCreateHttpLiveStreamUrl( mediaItem.getCreateHttpLiveStreamUrl() );
        mediaItemModel.setRemoveHttpLiveStreamUrl( mediaItem.getRemoveHttpLiveStreamUrl() );
        mediaItemModel.setGetHttpLiveStreamUrl( mediaItem.getGetHttpLiveStreamUrl() );
        mediaItemModel.setWatched( mediaItem.isWatched() );
        mediaItemModel.setMarkWatchedUrl( mediaItem.getMarkWatchedUrl() );
        mediaItemModel.setUpdateSavedBookmarkUrl( mediaItem.getUpdateSavedBookmarkUrl() );
        mediaItemModel.setBookmark( mediaItem.getBookmark() );
        mediaItemModel.setInetref( mediaItem.getInetref() );
        mediaItemModel.setRecordingGroup( mediaItem.getRecordingGroup() );

        ArrayList<ErrorModel> errors = new ArrayList<>();
        for( Error error : mediaItem.getValidationErrors() ) {
            errors.add( ErrorModel.fromError( error ) );
        }
        mediaItemModel.setValidationErrors( errors );

        mediaItemModel.setCertification( mediaItem.getCertification() );
        mediaItemModel.setParentalLevel( mediaItem.getParentalLevel() );

        return mediaItemModel;
    }

    public List<MediaItemModel> transform( Collection<MediaItem> mediaItemCollection ) {

        List<MediaItemModel> mediaItemList = new ArrayList<>( mediaItemCollection.size() );

        MediaItemModel mediaItemModel;
        for( MediaItem mediaItem : mediaItemCollection ) {

            mediaItemModel = transform( mediaItem );
            if( null != mediaItemModel ) {

                mediaItemList.add( mediaItemModel );

            }

        }

        return mediaItemList;
    }

}
