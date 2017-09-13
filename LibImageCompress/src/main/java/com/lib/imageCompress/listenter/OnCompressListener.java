package com.lib.imageCompress.listenter;

/**
 * author: heshantao
 * data: 2017/9/11.
 * 压缩监听器
 */

public interface OnCompressListener {
    //开始压缩
    void onCompressStart();

    /*图片压缩成功
    @paramer filePath 压缩以后的图片路径
     */
    void onCompressSuccess(String filePath);

    //图片压缩失败
    void onCompressError(Throwable e);

}
