package com.onlineteer.Api;

import android.util.Log;

import com.onlineteer.ui.home.HomeViewModel;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeAPi {

    private OnHomeAPiHit mlistner;

    public HomeAPi(OnHomeAPiHit mlistner)
    {
        this.mlistner = mlistner;
    }

    public void CallApi()
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new HitApi());
    }

    public class HitApi implements Runnable {

        @Override
        public void run() {
            FetchData fetchData = new FetchData("https://teerofficial.teer-counter.in/API5023/home.php");
            if (fetchData.startFetch()) {
                if (fetchData.onComplete()) {
                    String result = fetchData.getResult();
                    //End ProgressBar (Set visibility to GONE)
                    Log.i("FetchData", result);
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        ArrayList<HomeViewModel> homeViewModels = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.optJSONObject(i);
                            if (data != null) {
                                String Gamename = data.optString("Game_Name");
                                String GameTimeS = data.optString("GameTimeS");
                                String GameTimeE = data.optString("GameTimeE");
                                String ResultTime = data.optString("ResultTime");
                                homeViewModels.add(new HomeViewModel(Gamename, GameTimeS, GameTimeE, ResultTime));
                            }
                        }
                        mlistner.OnHomeApiGivesResult(homeViewModels);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mlistner.OnHomeAPiGivesError("Internal Error");
                    }

                }
            }
        }
    }

    public interface OnHomeAPiHit
    {
        void OnHomeApiGivesResult(ArrayList<HomeViewModel> homeViewModels);
        void OnHomeAPiGivesError(String error);
    }
}
