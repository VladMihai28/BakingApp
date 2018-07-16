package com.example.android.vlad.baking.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad
 */

public class ImageUtils {

    public static void setImage(Context context, String thumbnail, ImageView imageView){
        Uri thumbnailUri = Uri.parse(thumbnail);
        Picasso.with(context)
        .load(thumbnailUri)
        .into(imageView);
    }

    public static boolean thumbnailIsValid(String thumbnail){
        if (thumbnail.isEmpty() || !thumbnailInSupportedFormats(thumbnail)){
            return false;
        }
        return true;

    }

    private static boolean thumbnailInSupportedFormats(String thumbnail){
        List<String> supportedFormats = new ArrayList<>();
        supportedFormats.add("jpg");
        supportedFormats.add("png");
        supportedFormats.add("bmp");

        for (String format: supportedFormats){
            if (thumbnail.endsWith(format)){
                return true;
            }
        }
        return false;
    }
}
