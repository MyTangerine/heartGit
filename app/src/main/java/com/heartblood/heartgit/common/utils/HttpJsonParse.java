package com.heartblood.heartgit.common.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by heartblood on 16/5/27.
 */
public class HttpJsonParse {
    private static JSONArray mJsonArrayData;
    private static JSONObject mJSONObjectData;

    public static JSONArray getHttpJsonArray(String url) {
        mJsonArrayData = null;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                mJsonArrayData = response;
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("测试", "报错报错啦");
            }
        });
        return mJsonArrayData;
    }
    public static JSONObject getHttpJsonObject(String url) {
        mJSONObjectData = null;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                mJSONObjectData = response;
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("测试", "报错报错啦");
            }
        });
        return mJSONObjectData;
    }
}
