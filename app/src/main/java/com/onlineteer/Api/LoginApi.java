package com.onlineteer.Api;

import android.util.Log;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginApi {
    private OnLoginApiHitListner mlistner;
   private final String username;
    private final String password;

    public LoginApi(String username,String password,OnLoginApiHitListner mlistner)
    {
        this.username = username;
        this.password = password;
        this.mlistner = mlistner;
    }

    public void HitApi()
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new LoginApiHit());
    }

    private class LoginApiHit implements Runnable
    {
        @Override
        public void run() {
            FetchData fetchData = new FetchData("https://teerofficial.teer-counter.in/API5023/Login.php?a="+username+"&b="+password);
            if (fetchData.startFetch()) {
                if (fetchData.onComplete()) {
                    String result = fetchData.getResult();
                    //End ProgressBar (Set visibility to GONE)
                    Log.i("FetchData", result);
                    Log.e("APi00", username+password  );
                    try {
                        JSONObject resultData = new JSONObject(result);
                        String status = resultData.getString("status");
                        if (status.equals("true"))
                        {
                            mlistner.OnLoginSuccessful(result);
                        }
                        else mlistner.OnLoginFailure(status);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                mlistner.OnLoginFailure("Internal Error");
            }
        }
    }
    public interface OnLoginApiHitListner
    {
        void OnLoginSuccessful(String Message);

        void OnLoginFailure(String Error);
    }

}
