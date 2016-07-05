package com.heartblood.heartgit.common.HttpTools;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import org.json.JSONException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Tangerine on 16/7/5.
 */
public class UserID {
    /**
     *
     * @param context
     * @param accid
     * @param name
     * @param token
     */
    public static void CreateID(Context context, String accid, String name, String token){
        String appKey = "3b120569699fff06d95365705aad8262";
        String appSecret = "77d4056a53ee";
        String nonce =  "asdasdasda";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        String e = "accid="+accid+"&"+"name="+name+"&"+"token="+token;
        StringEntity entity =null;
        try {
            entity = new StringEntity(e);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        HttpClient.clearHeader();
        HttpClient.addOneHeader("AppKey",appKey);
        HttpClient.addOneHeader("Nonce",nonce);
        HttpClient.addOneHeader("CurTime",curTime);
        HttpClient.addOneHeader("CheckSum",checkSum);
        HttpClient.post(context,"https://api.netease.im/nimserver/user/create.action", entity, "application/x-www-form-urlencoded",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("123", response.toString());
                try {
                    Log.e("123",response.getJSONObject("info").getString("accid"));
                } catch (JSONException e) {

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    /**
     *
     * @param sessionId String,CustomId,接收方帐户ID
     * @param sessionType SessionTypeEnum,sessionType,单聊或群聊类型
     * @param content String,content,要发送的信息文本
     */
    public static void sendTextMessage(String sessionId, SessionTypeEnum sessionType, String content) {
        IMMessage message = MessageBuilder.createTextMessage(
                sessionId,
                sessionType,
                content
        );
        NIMClient.getService(MsgService.class).sendMessage(message,false);
    }

    /**
     *
     * @param sessionId String,CustomId 接收方帐户ID
     * @param sessionType SessionTypeEnum,sessionType 单聊或群聊类型
     * @param imageFile File,imageFile 要发送的图片文件
     */

    public void sendImageMessage(String sessionId, SessionTypeEnum sessionType, File imageFile) {

        IMMessage message = MessageBuilder.createImageMessage(
                sessionId,
                sessionType,
                imageFile,
                null);
        NIMClient.getService(MsgService.class).sendMessage(message,false);
    }

    /**
     *
     * @param sessionId  String,CustomId 接收方帐户ID
     * @param sessionType SessionTypeEnum,sessionType 单聊或群聊类型
     */
    public void sendTipMessage(String sessionId,SessionTypeEnum sessionType){
        IMMessage message = MessageBuilder.createTipMessage(
                sessionId,
                sessionType);
        NIMClient.getService(MsgService.class).sendMessage(message,false);

        CustomMessageConfig config = new CustomMessageConfig();
        config.enableUnreadCount = false;
    }



    /**
     *
     * @return UnreadNum,int,未读信息数目
     */
    public int getUnreadNum(){
        return  NIMClient.getService(MsgService.class).getTotalUnreadCount();
    }




    /**
     *
     * @param accid
     * @param token
     * @param loginListener
     */
    public static void LogIn(String accid, String token, final LoginListener loginListener){
        LoginInfo loginInfo = new LoginInfo(accid,token);
        RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {

            @Override
            public void onSuccess(LoginInfo loginInfo) {
                loginListener.loginSuccess();

            }

            @Override
            public void onFailed(int i) {
                loginListener.loginFailed();
            }

            @Override
            public void onException(Throwable throwable) {

            }
        };
        NIMClient.getService(AuthService.class).login(loginInfo).setCallback(callback);
    }





    interface LoginListener{

        void loginSuccess();
        void loginFailed();
    }
}