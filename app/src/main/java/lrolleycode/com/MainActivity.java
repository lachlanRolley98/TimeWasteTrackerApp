package lrolleycode.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Get an editor to modify SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //init buttons and text
        TextView totalSaved = (TextView) findViewById((R.id.bTotalSaved));
        Button workButton = (Button) findViewById(R.id.bWork);
        Button pauseButton = (Button) findViewById(R.id.bPause);
        Button playButton = (Button) findViewById(R.id.bPlay);

        //
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");





        // This guy just grabs the current day, hour and second and saves it
        workButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //grab time when pressed
                Date currentDate = new Date();
                // Format the date as a string and put it in SharedPreferences
                String dateString = dateFormat.format(currentDate);
                //update workstart
                editor.putString("workStart", dateString);
                //update what mode we are in
                editor.putInt("curr",1);
                //push
                editor.apply();
            }
        });
        // he will pause the time and calculate. Also gota wipe start times
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            int curr = sharedPreferences.getString("curr",0);
            String runningTotal = sharedPreferences.getString("totalSaved", "None");
            int mul = sharedPreferences.getInt("multiplier",1);
            Date currentDate = new Date();
            String dateNow = dateFormat.format(currentDate);

            // AIGHT HERE WE BASICALLY HAVE TO DO ANNOYING STUFF AND CONVERT DATES AND STRINGS AND WHATEVER AND THE END GOAL IS HAVING HOURS AND MINUTES
            // TOO MAKE LIFE EASIER HAVE AN HOURS AND MINUTES TEXT BOX AND CAN EDIT THEM

            // If has been counting work
            if (curr == 1){
                String retrievedTime = sharedPreferences.getString("workStart","None");

            //if counting play
            } else if (curr == 3) {
                String retrievedTime = sharedPreferences.getString("workStart","None");
            } else {
                // we hit pause and somethings fucked
            }

            //totalSaved.setText(retrievedTime);


            }
        });

        // This guy just grabs the current day, hour and second and saves it
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //grab time when pressed
                Date currentDate = new Date();
                // Format the date as a string and put it in SharedPreferences
                String dateString = dateFormat.format(currentDate);
                //update workstart
                editor.putString("playStart", dateString);
                //update what mode we are in
                editor.putInt("curr",3);
                //push
                editor.apply();
            }
        });
























    }
}