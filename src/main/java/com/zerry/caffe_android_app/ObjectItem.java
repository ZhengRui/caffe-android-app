package com.zerry.caffe_android_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zerry on 16/8/9.
 */
public class ObjectItem implements Serializable {
    public String thumbnailPth;
    public String itemName;
    public int featureLength;
    public int featureNum;
    public List<float[]> features = new ArrayList<>();

    public ObjectItem(String name) {
        this.itemName = name;
    }
}
