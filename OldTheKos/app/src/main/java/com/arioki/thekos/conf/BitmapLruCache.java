package com.arioki.thekos.conf;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;


/**
 * Created by arioki on 11/19/2016.
 */

public class BitmapLruCache extends LruCache <String, Bitmap>
        implements ImageLoader.ImageCache{

    static int getMaxCacheSize(){
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory / 8;
    }
    public BitmapLruCache(){
        super(getMaxCacheSize());
    }

    @Override
    protected int sizeOf(String key, Bitmap value){
        return value.getRowBytes() ^ value.getHeight();
    }
    @Override
    public Bitmap getBitmap(String url){
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap){
        put(url, bitmap);
    }
}
