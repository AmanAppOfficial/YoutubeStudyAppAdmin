package e.aman.adminstudyapp.service;

import com.google.firebase.database.DataSnapshot;

public interface MyFirebaseListener {
    void onSuccess(DataSnapshot snapshot);
    void onSuccess(String e);
    void onFailure(String e);
}