package mcproject.instanotesv1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CheckFirstTimeLaunch extends Activity {

    private int PREF_MODE = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String PREF_NAME = "INSTANOTES_SLIDER";
    private static final String IS_FIRST_LAUNCH = "ISFIRST_LAUNCH";

    @SuppressLint("CommitPrefEdits")
    public CheckFirstTimeLaunch(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(getPrefName(),getPREF_MODE());
        editor = sharedPreferences.edit();
    }

    // Get started prompted only once

    void setFirstLaunch(boolean time){
        editor.putBoolean(IS_FIRST_LAUNCH,time);
        editor.commit();
    }

    public boolean checkFirstLaunch(){
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH,true);
    }

    private String getPrefName(){
        return PREF_NAME;
    }

    private int getPREF_MODE(){
        return PREF_MODE;
    }

}
