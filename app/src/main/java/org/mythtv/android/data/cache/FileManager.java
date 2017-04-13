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

package org.mythtv.android.data.cache;

/**
 *
 *
 *
 * @author dmfrey
 *
 * Created on 8/26/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Helper class to do operations on regular files/directories.
 */
@Singleton
public class FileManager {

    private static final String TAG = FileManager.class.getSimpleName();

    @Inject
    public FileManager() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     * Writes a file to Disk.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform this operation using another thread.
     *
     * @param file The file to write to Disk.
     */
    public void writeToFile( File file, String fileContent ) {

        if( !file.exists() ) {

            OutputStreamWriter writer = null;
            try {

//                Requires min API 19
//                writer = new OutputStreamWriter( new FileOutputStream( file ), StandardCharsets.UTF_8 );
                writer = new OutputStreamWriter( new FileOutputStream( file ) );
                writer.write( fileContent );

            } catch( IOException e ) {

                Log.e(TAG, "writeToFile : error, io", e);

            } finally {

                if( null != writer ) {

                    try {

                        writer.close();

                    } catch( IOException e ) {
                        Log.w( TAG, e.getLocalizedMessage(), e );
                    }

                }
            }

        }

    }

    /**
     * Reads a content from a file.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.
     *
     * @param file The file to read from.
     * @return A string with the content of the file.
     */
    public String readFileContent( File file ) {

        StringBuilder fileContentBuilder = new StringBuilder();
        if( file.exists() ) {

            String stringLine;
            InputStream in = null;
            Reader reader = null;
            BufferedReader bufferedReader = null;
            try {

                in = new FileInputStream( file );

//                Requires min API 19
//                reader = new InputStreamReader( in, StandardCharsets.UTF_8 );
                reader = new InputStreamReader( in );

                bufferedReader = new BufferedReader( reader );

                while( ( stringLine = bufferedReader.readLine() ) != null ) {
                    fileContentBuilder.append(stringLine).append('\n');
                }

            } catch( IOException e ) {

                Log.e( TAG, "readFileContent : error, io", e );

            } finally {

                if( null != bufferedReader ) {

                    try {

                        bufferedReader.close();

                    } catch( IOException e ) {
                        Log.w( TAG, e.getLocalizedMessage(), e );
                    }

                }

                if( null != reader ) {

                    try {

                        reader.close();

                    } catch( IOException e ) {
                        Log.w( TAG, e.getLocalizedMessage(), e );
                    }

                }

                if( null != in ) {

                    try {

                        in.close();

                    } catch( IOException e ) {
                        Log.w( TAG, e.getLocalizedMessage(), e );
                    }

                }

            }

        }

        return fileContentBuilder.toString();
    }

    /**
     * Returns a boolean indicating whether this file can be found on the underlying file system.
     *
     * @param file The file to check existence.
     * @return true if this file exists, false otherwise.
     */
    public boolean exists( File file ) {
        return file.exists();
    }

    /**
     * Warning: Deletes the content of a directory.
     * This is an I/O operation and this method executes in the main thread, so it is recommended to
     * perform the operation using another thread.
     *
     * @param directory The directory which its content will be deleted.
     */
    public void clearDirectory( File directory ) {

        if( directory.exists() ) {

            try {

                for (File file : directory.listFiles()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        Log.w(TAG, "clearDirectory : directory not deleted");
                    }
                }

            } catch( NullPointerException e ) {
                Log.e( TAG, "clearDirectory : failed to delete directory" );
            }

        }

    }

    /**
     * Write a value to a user preferences file.
     *
     * @param context            {@link android.content.Context} to retrieve android user preferences.
     * @param preferenceFileName A file name reprensenting where data will be written to.
     * @param key                A string for the key that will be used to retrieve the value in the future.
     * @param value              A long representing the value to be inserted.
     */
    public void writeToPreferences( Context context, String preferenceFileName, String key, long value ) {

        SharedPreferences sharedPreferences = context.getSharedPreferences( preferenceFileName, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong( key, value );
        editor.apply();

    }

    /**
     * Get a value from a user preferences file.
     *
     * @param context            {@link android.content.Context} to retrieve android user preferences.
     * @param preferenceFileName A file name representing where data will be get from.
     * @param key                A key that will be used to retrieve the value from the preference file.
     * @return A long representing the value retrieved from the preferences file.
     */
    public long getFromPreferences( Context context, String preferenceFileName, String key ) {

        SharedPreferences sharedPreferences = context.getSharedPreferences( preferenceFileName, Context.MODE_PRIVATE );

        return sharedPreferences.getLong( key, 0 );
    }

}