package com.zerry.caffe_android_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zerry on 16/8/9.
 */
public class ObjectItem implements Serializable {
    class ItemBrief implements Serializable {
        public String thumbnailPth;
        public String itemName;
        public int featureNum;
    }
    class ItemFeat implements Serializable {
        public int featureLength;
        public List<float[]> features = new ArrayList<>();
    }

    public ItemBrief itemBrief;
    public ItemFeat itemFeat;

    public ObjectItem(String name) {
        this.itemBrief = new ItemBrief();
        this.itemBrief.itemName = name;
    }
}
