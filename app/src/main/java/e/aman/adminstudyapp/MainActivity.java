package e.aman.adminstudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button addTopLecturesButton , addUpcomingLecturesButton , addLectureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addLectureButton = findViewById(R.id.add_lecture_button);
        addTopLecturesButton = findViewById(R.id.add_top_lecture_button);
        addUpcomingLecturesButton = findViewById(R.id.add_upcoming_lecture_button);

        addTopLecturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , AddTopLectureActivity.class);
                startActivity(i);
            }
        });



    }
}
