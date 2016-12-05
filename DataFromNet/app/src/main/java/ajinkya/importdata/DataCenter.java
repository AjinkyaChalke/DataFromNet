package ajinkya.importdata;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import java.util.ArrayList;


/**
 * Created by Ajinkya on 03/12/16.
 */

public class DataCenter {

    private static final String TAG = "DataCenter";

    private static DataCenter sDatacenter;
    private static ArrayList<Story> sStories;
    private static MainActivity mMainActivity;
    private static WebMessenger sWebMessenger;
    private static JsonArrayRequest request;

    private DataCenter(MainActivity activity){
        mMainActivity = activity;
        sWebMessenger = WebMessenger.getInstance(mMainActivity.getApplicationContext());
        sStories = new ArrayList();
        getRecommendedJASONModel();
    }

    public void getRecommendedJASONModel(){
        int method = Request.Method.GET;
        String url = JSONModel.getRecommended();
        request = new JsonArrayRequest(method, url, null,
                (new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONModel.parseJASON(response,sStories);
                    }
                })
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"DataCenter.getRecommended : Failed to process request");
            }
        });
        sWebMessenger.addToRequestQueue(request);
    }

    public static DataCenter getDatacenter(MainActivity mainActivity) {
        if(sDatacenter == null)  sDatacenter = new DataCenter(mainActivity);
        return sDatacenter;
    }

    public static void updateActivity(){
        mMainActivity.updateUI(sStories);
    }

    public static void getSearch(String query){
        int method = Request.Method.GET;
        String url = JSONModel.getSearch(query);
        request = new JsonArrayRequest(method, url, null,
                (new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONModel.parseJASON(response,sStories);
                    }
                })
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"DataCenter.getSearch : Failed to process request");
            }
        });
        sWebMessenger.addToRequestQueue(request);
    }
}

