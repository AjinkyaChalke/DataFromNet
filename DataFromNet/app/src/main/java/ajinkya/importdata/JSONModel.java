package ajinkya.importdata;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ajinkya on 03/12/16.
 */

public class JSONModel{

    private static final String TAG ="JSONModel";

    public static String getRecommended() {
        StringBuilder recommend = new StringBuilder(Config.getRECOMMENDED_MEETUP());
        recommend.append("&key="+Config.getKEY_MEETUP());
        recommend.append("&page="+Config.getPAGE_SIZE());

        return recommend.toString();
    }

    public static String getSearch(String query) {
        StringBuilder search = new StringBuilder(Config.getSEARCH_MEETUP());
        search.append("&key="+Config.getKEY_MEETUP());
        search.append("&page="+Config.getPAGE_SIZE());
        search.append("&text="+query);

        return search.toString();
    }

    public static void parseJASON(JSONArray jasonData, ArrayList<Story> stories){
        try{
            Story story;
            JSONObject object;
            String image="";
            final int n = jasonData.length();
            for (int i = 0; i < n; ++i) {
                object = jasonData.getJSONObject(i);
                if(object.has("key_photo")){
                    if(((JSONObject)object.get("key_photo")).get("photo_link")!=null) {
                        image = ((JSONObject) object.get("key_photo")).getString("photo_link");
                    }
                }
                else if(object.has("photos")) {
                    if(((JSONArray)object.get("photos")).getJSONObject(0).has("photo_link")) {
                        image = ((JSONArray)object.get("photos")).getJSONObject(0).getString("photo_link");
                    }
                }

                story = new Story(object.getString("name"),object.getString("description"),image);
                stories.add(story);
            }
            DataCenter.updateActivity();
        }
        catch (JSONException e){
            Log.e(TAG, e.getMessage());
        }

    }
}
