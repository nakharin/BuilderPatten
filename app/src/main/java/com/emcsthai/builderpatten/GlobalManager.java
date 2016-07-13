package com.emcsthai.builderpatten;

import java.util.ArrayList;

/**
 * Created by nakarin on 7/13/2016 AD.
 */
public class GlobalManager {

    private static GlobalManager instance = null;

    private GlobalManager() {
    }

    public static GlobalManager getInstance() {
        if (instance == null) {
            instance = new GlobalManager();
        }
        return instance;
    }

    private ArrayList<User> userList = new ArrayList<>();

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }
}
