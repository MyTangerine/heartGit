package com.heartblood.heartgit.common.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.heartblood.heartgit.MainActivity;
import com.heartblood.heartgit.R;
import com.heartblood.heartgit.common.Global;
import com.heartblood.heartgit.news.NewsActivity;
import com.heartblood.heartgit.news.NewsDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by heartblood on 16/5/31.
 */
public class NewsItemHolder extends RecyclerView.ViewHolder{
    public TextView newsTitle;
    private int Position;
    private NewsActivity mContext;
    private JSONObject mdataObject;
    public NewsItemHolder(View itemView) {
        super(itemView);
        newsTitle = (TextView) itemView.findViewById(R.id.news_card_label_title);
        itemView.findViewById(R.id.news_card_label_container).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showNewsDetail(mContext, getItemPosition());
            }
        });
    }

    private void showNewsDetail(NewsActivity mContext, int position) {
        Global.setNewsData(mdataObject);
        NewsDetailActivity.startActivity(mContext);

    }
    public void initData(NewsActivity mContext, JSONObject mdataObject) {
        this.mContext = mContext;
        this.mdataObject = mdataObject;
        try {
            newsTitle.setText(mdataObject.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void setItemPosition(int Position) {
        this.Position = Position;
    }
    public int getItemPosition() {
        return Position;
    }
}
