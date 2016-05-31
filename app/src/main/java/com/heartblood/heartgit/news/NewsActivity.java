package com.heartblood.heartgit.news;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.heartblood.heartgit.R;
import com.heartblood.heartgit.common.AppActivity;
import com.heartblood.heartgit.common.adapter.NewsListAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by heartblood on 16/5/31.
 */
public class NewsActivity extends AppActivity {

    @BindView(R.id.news_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.news_list)
    RecyclerView mRecyclerView;
    private JSONArray mJsonData;
    private NewsListAdapter mNewsListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Fresco.initialize(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getNews("http://119.29.58.43/api/getSfBlog/getPage=1", this);
    }

    private void getNews(String url, final NewsActivity mContext) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                mJsonData = response;
                mRecyclerView.setAdapter(mNewsListAdapter = new NewsListAdapter(mContext, mJsonData));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                // TODO: 16/5/9 获取json失败
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("测试", "报错报错啦");
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

}
