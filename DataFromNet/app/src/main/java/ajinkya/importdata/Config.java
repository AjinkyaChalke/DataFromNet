package ajinkya.importdata;

/**
 * Created by Ajinkya on 03/12/16.
 */

public class Config {


    //General configs
    private static int PAGE_SIZE = 10;

    //Meetup API configs
    private static String KEY_MEETUP = "1654194f297f7f4626dc757dc7a1d";
    private static String RECOMMENDED_MEETUP = "https://api.meetup.com/recommended/groups?&sign=true&photo-host=secure";
    private static String SEARCH_MEETUP = "https://api.meetup.com/find/groups?&sign=true&photo-host=secure";

    public static int getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    public static String getKEY_MEETUP() {
        return KEY_MEETUP;
    }

    public static String getRECOMMENDED_MEETUP() {
        return RECOMMENDED_MEETUP;
    }

    public static String getSEARCH_MEETUP() {
        return SEARCH_MEETUP;
    }
}
