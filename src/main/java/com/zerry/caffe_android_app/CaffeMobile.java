package com.sh1r0.caffe_android_lib;

public class CaffeMobile {

    private long nativeCaffeModel = 0;

    public CaffeMobile(String modelPath, String weightsPath) {
        nativeCaffeModel = create(modelPath, weightsPath);
    }

    public void setMean(float[] meanValues) {
        setMeanWithMeanValues(nativeCaffeModel, meanValues);
    }

    public int[] predictImage(String imgPath) {
        return predictImage(nativeCaffeModel, imgPath, 10);
    }

    public PredictScore[] predictFrame(int width, int height, int front1back0, int orientCase, byte[] frmdata, int k, String clsname) {
        return predictFrame(nativeCaffeModel, width, height, front1back0, orientCase, frmdata, k, clsname);
    }

    public PredictScore[] predictJPEG(int front1back0, int orientCase, byte[] frmdata, int k, String clsname) {
        return predictJPEG(nativeCaffeModel, front1back0, orientCase, frmdata, k, clsname);
    }

    public float[][] extractFeaturesCVMat(long cvMatAddr, String blobnames) {
        return extractFeaturesCVMat(nativeCaffeModel, cvMatAddr, blobnames);
    }

    public float[][] extractFeaturesCVBatch(long cvBatchImgsAddr, String blobname) {
        return extractFeaturesCVBatch(nativeCaffeModel, cvBatchImgsAddr, blobname, 2);
    }

    public native void setNumThreads(int numThreads);

    public native long create(String modelPath, String weightsPath);  // required

    private native void setMeanWithMeanValues(long thiz, float[] meanValues);

    public native int[] predictImage(long thiz, String imgPath, int k);

    public native PredictScore[] predictFrame(long thiz, int width, int height, int front1back0, int orientCase, byte[] frmdata, int k, String clsname);

    public native PredictScore[] predictJPEG(long thiz, int front1back0, int orientCase, byte[] frmdata, int k, String clsname);

    public native float[][] extractFeaturesCVMat(long thiz, long cvMatAddr, String blobnames);

    public native float[][] extractFeaturesCVBatch(long thiz, long cvBatchImgsAddr, String blobname, int endLayerOffset);

}
