package com.example.lin.mt.servicebestpractice;

/**
 *
 * @author MT-Lin
 * @date 2017/12/19
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
