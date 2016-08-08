package com.sh1r0.caffe_android_lib;

/**
 * Created by zerry on 2/24/16.
 */
public class PredictScore {
    public int idx;
    public float scr;

    public PredictScore(int idx, float scr) {
        this.idx = idx;
        this.scr = scr;
    }
}
