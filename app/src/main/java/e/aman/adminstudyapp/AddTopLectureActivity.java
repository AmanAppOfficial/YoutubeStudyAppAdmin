package e.aman.adminstudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import e.aman.adminstudyapp.service.AddTopLectureService;
import e.aman.adminstudyapp.service.MyFirebaseListener;
import e.aman.adminstudyapp.util.Constants;

public class AddTopLectureActivity extends AppCompatActivity  implements MyFirebaseListener {

    private LinearLayout verifiedLayout;
    private EditText videoIDTextView , titleTextView , descTextView , authorTextView;
    private Button verifyButton , addButton;
    private ImageView imageView;
    private MyFirebaseListener myFirebaseListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_top_lecture);


        verifiedLayout = findViewById(R.id.verified_layout);
        videoIDTextView = findViewById(R.id.video_id_textview);
        titleTextView = findViewById(R.id.title_textview);
        descTextView = findViewById(R.id.description_textview);
        authorTextView = findViewById(R.id.author_textview);
        verifyButton = findViewById(R.id.verify_id_button);
        addButton = findViewById(R.id.add_video_button);
        imageView = findViewById(R.id.imageview);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Uri.parse("https://img.youtube.com/vi/" + videoIDTextView.getText().toString().trim() + "/0.jpg")).into(imageView);
                verifiedLayout.setVisibility(View.VISIBLE);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = videoIDTextView.getText().toString().trim();
                String title = titleTextView.getText().toString().trim();
                String desc = descTextView.getText().toString().trim();
                String author = authorTextView.getText().toString().trim();
                String imagepath = "https://img.youtube.com/vi/" + videoIDTextView.getText().toString().trim() + "/0.jpg";

                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(author))
                {
                    saveDetailsToDatabase(id , imagepath , title , desc , author);
                }
                else
                {
                    Toast.makeText(getApplicationContext() , "Empty Fields!" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void saveDetailsToDatabase(String id ,  String imagepath , String title, String desc, String author) {
        new AddTopLectureService(id , imagepath , title , desc , author , this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        verifiedLayout.setVisibility(View.GONE);

    }

    @Override
    public void onSuccess(DataSnapshot snapshot) {

    }

    @Override
    public void onSuccess(String e) {
        Toast.makeText(getApplicationContext() , e , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String e) {
        Toast.makeText(getApplicationContext() , e , Toast.LENGTH_SHORT).show();
    }
}
