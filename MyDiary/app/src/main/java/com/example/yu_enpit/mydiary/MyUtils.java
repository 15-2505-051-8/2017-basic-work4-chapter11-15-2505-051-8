package com.example.yu_enpit.mydiary;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import io.realm.internal.IOException;

public class MyUtils{
    public static Bitmap getImageFromByte(byte[] bytes){
        BitmapFactory.Options opt=new BitmapFactory.Options();
        opt.inJustDecodeBounds=true;
        int bitmapSize=1;
        if((opt.outHeight*opt.outWidth)>500000){
            double outSize=(double)(opt.outHeight*opt.outWidth)/500000;
            bitmapSize=(int)(Math.sqrt(outSize)+1);
        }

        opt.inJustDecodeBounds=false;
        opt.inSampleSize=bitmapSize;
        Bitmap bmp=BitmapFactory.decodeByteArray(bytes,0,bytes.length,opt);
        return bmp;
    }

    public static byte[] getByteFromImage(Bitmap bmp){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
    public static Bitmap getImageFromStream(ContentResolver resolver, Uri uri) throws IOException, java.io.IOException {
        InputStream in;
        BitmapFactory.Options opt=new BitmapFactory.Options();
        opt.inJustDecodeBounds=true;
        in=resolver.openInputStream(uri);
        BitmapFactory.decodeStream(in,null,opt);
        in.close();
        int bitmapSize=1;
        if((opt.outHeight*opt.outWidth)>500000){
            double outSize=(double)(opt.outHeight*opt.outWidth)/500000;
            bitmapSize=(int)(Math.sqrt(outSize)+1);
        }

        opt.inJustDecodeBounds=false;
        opt.inSampleSize=bitmapSize;
        in=resolver.openInputStream(uri);
        Bitmap bmp=BitmapFactory.decodeStream(in,null,opt);
        in.close();
        return bmp;
    }

    public static void tintMenuIcon(android.content.Context context, MenuItem item, @ColorRes int color){
        Drawable normalDrawable=item.getIcon();
        Drawable wrapDrawable= DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context,color));
        item.setIcon(wrapDrawable);
    }

}