package fun.qianxiao.mvpdemo.main.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import fun.qianxiao.mvpdemo.tool.EncryptUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Create by QianXiao
 * On 2020/4/15
 */
public class MainModel implements IMainModel {
    Context context;
    RefreshCallback callback;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    callback.RefreshError((String) msg.obj);
                    break;
                case 1:
                    callback.RefreshSuccess((String) msg.obj);
                    break;
            }
        }
    };
    public MainModel(Context context) {
        this.context = context;
    }

    @Override
    public void OnRefreshData(final RefreshCallback callback) {
        this.callback = callback;
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .build();
        final String url = "http://api.qianxiao.fun/prattleoflover/get.php";
        String nowtimestamp = String.valueOf(new Date().getTime()/1000);
        RequestBody body = new FormBody.Builder()
                .add("timestamp",nowtimestamp)
                .add("parsign",EncryptUtils.encryptSHA1ToString("timestamp="+nowtimestamp+"prattleoflover"))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = 0;
                message.obj = e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsestr = response.body().string();
                try{
                    JSONObject jsonObject = new JSONObject(responsestr);
                    Message message = new Message();
                    message.obj = jsonObject.getString("msg");
                    if(jsonObject.getInt("code")==1){
                        message.what = 1;
                        handler.sendMessage(message);
                    }else{
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
