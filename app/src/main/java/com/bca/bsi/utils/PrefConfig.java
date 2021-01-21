package com.bca.bsi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bca.bsi.R;
import com.bca.bsi.model.User;
import com.bca.bsi.utils.constant.Constant;

public class PrefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void setTokenAccess(String tokenAccess) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token_access), tokenAccess);
        editor.apply();
    }

    public void setTipsOfTheWeek(boolean tipsActivated) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_tips_of_the_week), tipsActivated);
        editor.apply();
    }

    public void removeTokenAccess() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token_access), Constant.EMPTY);
        editor.apply();
    }

    public void setUser(User.ForumUser user, User.WelmaUser welmaUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_bca_id), user.getBcaID());
        editor.putString(context.getString(R.string.pref_name), welmaUser.getName());
        editor.putString(context.getString(R.string.pref_profile_risiko), welmaUser.getProfileRisiko());
        editor.putString(context.getString(R.string.pref_account_number), welmaUser.getAccountNumber());
        editor.putString(context.getString(R.string.pref_email), welmaUser.getEmail());
        editor.putString(context.getString(R.string.pref_profile_id), user.getProfileID());
        editor.putString(context.getString(R.string.pref_username), user.getUsername());
        editor.apply();
    }

    public void logOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_bca_id), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_name), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_profile_risiko), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_email), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_account_number), Constant.EMPTY);
        editor.putString(context.getString(R.string.pref_username), Constant.EMPTY);
        editor.apply();
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name), name);
        editor.apply();
    }


    public void setAccountNumber(String accountNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_account_number), accountNumber);
        editor.apply();
    }

    public void setTokenFCM(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token_fcm), token);
        editor.apply();
    }

    public String getBCAID() {
        return sharedPreferences.getString(context.getString(R.string.pref_bca_id), Constant.EMPTY);
    }

    public String getProfileRisiko() {
        return sharedPreferences.getString(context.getString(R.string.pref_profile_risiko), Constant.EMPTY);
    }

    public String getName() {
        return sharedPreferences.getString(context.getString(R.string.pref_name), Constant.EMPTY);
    }

    public String getEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_email), Constant.EMPTY);
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString(context.getString(R.string.pref_profile_risiko), Constant.EMPTY);
    }

    public String getTokenFCM() {
        return sharedPreferences.getString(context.getString(R.string.pref_token_fcm), Constant.EMPTY);
    }

    public String getAccountNumber() {
        return sharedPreferences.getString(context.getString(R.string.pref_account_number), Constant.EMPTY);
    }

    public String getTokenAccess() {
        return sharedPreferences.getString(context.getString(R.string.pref_token_access), Constant.EMPTY);
    }

    public String getUsername() {
        return sharedPreferences.getString(context.getString(R.string.pref_username), Constant.EMPTY);
    }

    public Boolean getTipsActivated() {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_tips_of_the_week),true);
    }

}
