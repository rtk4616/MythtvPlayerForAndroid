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

package org.mythtv.android.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 *
 *
 *
 * @author dmfrey
 *
 * Created on 11/1/15.
 */
@AutoValue
public abstract class VideoMetadataInfoListWrapperEntity {

    @SerializedName( "VideoMetadataInfoList" )
    public abstract VideoMetadataInfoListEntity videoMetadataInfoListEntity();

    public static VideoMetadataInfoListWrapperEntity create( VideoMetadataInfoListEntity videoMetadataInfoListEntity ) {

        return new AutoValue_VideoMetadataInfoListWrapperEntity( videoMetadataInfoListEntity );
    }

    public static TypeAdapter<VideoMetadataInfoListWrapperEntity> typeAdapter(Gson gson ) {

        return new AutoValue_VideoMetadataInfoListWrapperEntity.GsonTypeAdapter( gson );
    }

}
