package e.aman.adminstudyapp.service;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import e.aman.adminstudyapp.util.Constants;

public class AddTopLectureService
{
    private FirebaseDatabase database;
    private DatabaseReference topLecturesRef;

    private static final String TITLE = "title";
    private static final String DESC = "description";
    private static final String ID = "id";
    private static final String AUTHOR = "author";
    private static final String DATE = "date";
    private static final String IMAGE_PATH = "imagepath";

    MyFirebaseListener myFirebaseListener;

    String id;
    String title;
    String desc;
    String author;
    String imagePath;


    public AddTopLectureService(String id, String imagePath , String title, String desc, String author, MyFirebaseListener myFirebaseListener)
    {
        database = FirebaseDatabase.getInstance();
        topLecturesRef = database.getReference().child(Constants.TOP_LECTURES_REF);

        this.id = id;
        this.title = title;
        this.desc = desc;
        this.author = author;
        this.imagePath = imagePath;
        this.myFirebaseListener = myFirebaseListener;

        saveDetails();

    }


    public  void saveDetails()
    {
        HashMap hashMap = new HashMap();
        hashMap.put(ID , id);
        hashMap.put(TITLE , title);
        hashMap.put(DESC , desc);
        hashMap.put(AUTHOR , author);
        hashMap.put(DATE , getDate());
        hashMap.put(IMAGE_PATH , imagePath);


        topLecturesRef.child(id).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                myFirebaseListener.onSuccess("Successful");
            }
        });
    }

    private String getDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        return  formattedDate;
    }


}
