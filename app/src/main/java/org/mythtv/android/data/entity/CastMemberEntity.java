/*
 * MythTV Player An application for Android users to play MythTV Recordings and Videos
 * Copyright (c) 2015. Daniel Frey
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
 * Created on 11/12/14.
 */
@AutoValue
public abstract class CastMemberEntity {

    @SerializedName( "Name" )
    public abstract String name();

    @SerializedName( "CharacterName" )
    public abstract String characterName();

    @SerializedName( "Role" )
    public abstract String role();

    @SerializedName( "TranslatedRole" )
    public abstract String translatedRole();

    public static CastMemberEntity create( String name, String characterName, String role, String translatedRole ) {

        return new AutoValue_CastMemberEntity( name, characterName, role, translatedRole );
    }

    public static TypeAdapter<CastMemberEntity> typeAdapter(Gson gson ) {

        return new AutoValue_CastMemberEntity.GsonTypeAdapter( gson );
    }

}
