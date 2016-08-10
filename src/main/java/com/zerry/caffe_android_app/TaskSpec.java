package com.zerry.caffe_android_app;

import java.io.Serializable;

/**
 * Created by zerry on 16/8/9.
 */
public class TaskSpec implements Serializable {

    class SingleTask implements Serializable {
        public String taskName;
        public String protoPth;
        public String caffeMdlPth;
        public String caffeFeatLayer;
        public String svmMdlPth;
        public String taskDBPth;

        public SingleTask(String taskName, String protoPth,
                          String caffeMdlPth, String caffeFeatLayer,
                          String svmMdlPth, String taskDBPth) {
            this.taskName = taskName;
            this.protoPth = protoPth;
            this.caffeMdlPth = caffeMdlPth;
            this.caffeFeatLayer = caffeFeatLayer;
            this.svmMdlPth = svmMdlPth;
            this.taskDBPth = taskDBPth;
        }
    }

    public SingleTask[] tasks = new SingleTask[]{
            new SingleTask("Object Recognition",
                    "bvlc/deploy.prototxt",
                    "bvlc/mdl.caffemodel",
                    "fc7", "", "objectRec.db"),

            new SingleTask("Face Recognition",
                    "mfm/LightenedCNN_B_deploy.prototxt",
                    "mfm/LightenedCNN_B.caffemodel",
                    "eltwise_fc1", "", "faceRec.db")

    };

}
