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

package org.mythtv.android.domain.repository;

import org.joda.time.DateTime;
import org.mythtv.android.domain.LiveStreamInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by dmfrey on 8/26/15.
 */
public interface ContentRepository {

    Observable<LiveStreamInfo> addliveStream( final String storageGroup, final String filename, final String hostname );

    Observable<LiveStreamInfo> addRecordingliveStream( final int recordedId, final int chanId, final DateTime startTime );

    Observable<LiveStreamInfo> addVideoliveStream( final int id );

    Observable<List<LiveStreamInfo>> liveStreamInfos( String filename );

    Observable<LiveStreamInfo> liveStreamInfo( int id );

    Observable<Boolean> removeLiveStream( final int id );

    Observable<Boolean> stopLiveStream( final int id );

}