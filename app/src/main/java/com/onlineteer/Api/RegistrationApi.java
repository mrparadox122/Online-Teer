package com.onlineteer.Api;

import android.util.Log;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegistrationApi {

    private OnRegistrationApiHitListner mlistner;
    private final String username;
    private final String password;
    private final String name;
    private final String phonenumber;
    private final String refcode;
    private final String otp;

    public RegistrationApi(String username, String password, OnRegistrationApiHitListner mlistner, String name, String phonenumber, String refcode, String otp)
    {
        this.username = username;
        this.password = password;
        this.mlistner = mlistner;
        this.name = name;
        this.phonenumber = phonenumber;
        this.refcode = refcode;
        this.otp = otp;
    }

    public void HitApi()
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new RegistrationApiHit());
    }

    private class RegistrationApiHit implements Runnable
    {
        @Override
        public void run() {
            FetchData fetchData = new FetchData("https://teerofficial.teer-counter.in/API5023/Register.php?a="+name+"&b="+phonenumber+"&c="+username+"&d="+password+"&e="+refcode+"&f="+otp);
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
                            mlistner.OnRegistrationSuccessful(result);
                        }
                        else mlistner.OnRegistrationFailure(status);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                mlistner.OnRegistrationFailure("Internal Error");
            }
        }
    }
    public interface OnRegistrationApiHitListner
    {
        void OnRegistrationSuccessful(String Message);

        void OnRegistrationFailure(String Error);
    }


}
