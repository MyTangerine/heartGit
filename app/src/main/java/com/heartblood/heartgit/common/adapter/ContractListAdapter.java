package com.heartblood.heartgit.common.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.heartblood.heartgit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;


/**
 * Created by Tangerine on 16/7/6.
 */
public class ContractListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private JSONArray dataList;
    private Activity context;
    private LayoutInflater layoutInflater;

    public ContractListAdapter(Activity context,LayoutInflater layoutInflater , JSONArray jsonArray){
        this.dataList = jsonArray;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_preview,parent,false);
        return new CodePreviewHodler(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        JSONObject json = null;
        String uriStr = null;
        String titleStr = null;
        String profileStr = null;
        String meStr = null;
        try {
             json = dataList.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            uriStr = json.getJSONObject("json").getString("imgurl");
            titleStr = json.getJSONObject("json").getString("title");
            profileStr = json.getJSONObject("json").getString("profile");
            meStr = json.getJSONObject("json").getString("author");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.parse(uriStr);
        ((CodePreviewHodler)holder).frescoImage.setImageURI(uri);
        ((CodePreviewHodler)holder).textMe.setText(meStr);
        ((CodePreviewHodler)holder).textProfile.setText(profileStr);
        ((CodePreviewHodler)holder).textTitle.setText(titleStr);
    }





    @Override
    public int getItemCount() {
        return dataList.length();
    }




    private class CodePreviewHodler extends RecyclerView.ViewHolder {
        private SimpleDraweeView frescoImage;
        private TextView textTitle;
        private TextView textProfile;
        private TextView textMe;

        public CodePreviewHodler(View itemView) {
            super(itemView);
            frescoImage = (SimpleDraweeView) itemView.findViewById(R.id.code_img_item);
            textTitle = (TextView) itemView.findViewById(R.id.code_title_item);
            textProfile = (TextView) itemView.findViewById(R.id.code_profile_item);
            textMe = (TextView) itemView.findViewById(R.id.code_me_item);

        }
    }
}
