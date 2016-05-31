package com.heartblood.heartgit.common;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by heartblood on 16/5/25.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //布局文件ID
    protected abstract int getContentViewId();
    //布局中Fragment的ID
    protected abstract int getFragmentContentId();
}
