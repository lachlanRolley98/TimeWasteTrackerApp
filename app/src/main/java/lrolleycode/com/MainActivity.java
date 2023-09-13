package lrolleycode.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.SharedPreferences;
import android.util.Log;
import android.text.TextUtils;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Get an editor to modify SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //init buttons and text   bSettings
        TextView totalSaved = (TextView) findViewById((R.id.bTotalSaved));
        Button workButton = (Button) findViewById(R.id.bWork);
        Button pauseButton = (Button) findViewById(R.id.bPause);
        Button playButton = (Button) findViewById(R.id.bPlay);
        Button settingsButton = (Button) findViewById(R.id.bSettings);

        ImageView TL = findViewById(R.id.imageTL);
        ImageView TR = findViewById(R.id.imageTR);
        ImageView BL = findViewById(R.id.imageBL);
        ImageView BR = findViewById(R.id.imageBR);

        EditText multiText = (EditText) findViewById((R.id.textMultipler));
        EditText resetText = (EditText) findViewById((R.id.textReset));

        // FIRST HAVE TO CHECK WHAT MODE WE ARE IN
        int curr = sharedPreferences.getInt("curr",0);
        if (curr == 1) {
            TL.setImageResource(R.drawable.happy);
            TR.setImageResource(R.drawable.jessica);
            BL.setImageResource(R.drawable.rick);
            BR.setImageResource(R.drawable.rich);
        } else if (curr == 3) {
            TL.setImageResource(R.drawable.tammy);
            TR.setImageResource(R.drawable.poor);
            BL.setImageResource(R.drawable.left);
            BR.setImageResource(R.drawable.weeks);
        } else {
            TL.setImageResource(R.drawable.black);
            TR.setImageResource(R.drawable.black);
            BL.setImageResource(R.drawable.black);
            BR.setImageResource(R.drawable.black);
        }
        //
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int currentStored = sharedPreferences.getInt("minTotal", 0);

        totalSaved.setText(Integer.toString(currentStored));

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
                TL.setImageResource(R.drawable.happy);
                TR.setImageResource(R.drawable.jessica);
                BL.setImageResource(R.drawable.rick);
                BR.setImageResource(R.drawable.rich);

            }
        });
        // he will pause the time and calculate. Also gota wipe start times
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            int curr = sharedPreferences.getInt("curr",0);
            //do everything in mins then convert when displaying
            int minTotal = sharedPreferences.getInt("minTotal", 0);

            int mul = sharedPreferences.getInt("multiplier",1);
            Date currentDate = new Date();
            String dateNow = dateFormat.format(currentDate);
            int[] endTime = TimeConverter.stringToArray(dateNow,"yyyy-MM-dd HH:mm:ss");

            //Log.d("yeet", "endTime: " + endTime[0] +' ' + endTime[1] +' '+ endTime[2] + ' '+endTime[3]);

            // If has been counting work
            if (curr == 1){
                String retrievedTime = sharedPreferences.getString("workStart","None");
                if(retrievedTime != "None"){
                    int[] startTime = TimeConverter.stringToArray(retrievedTime,"yyyy-MM-dd HH:mm:ss");
                    int elapsedTime = TimeConverter.calculateTimeDifference(startTime, endTime );
                    //ok here we have  mins, We want to add this too the total saved after applying multiplier
                    elapsedTime = elapsedTime/mul;
                    minTotal = minTotal + elapsedTime;
                }else{totalSaved.setText("No work time saved");}


            //if counting play
            } else if (curr == 3) {
                String retrievedTime = sharedPreferences.getString("playStart","None");
                if(retrievedTime != "None"){
                    int[] startTime = TimeConverter.stringToArray(retrievedTime,"yyyy-MM-dd HH:mm:ss");
                    int elapsedTime = TimeConverter.calculateTimeDifference(startTime, endTime );
                    minTotal = minTotal - elapsedTime;
                }else{totalSaved.setText("No work time saved");}

            } else {
                totalSaved.setText("Not Counting Stuff");
            }


            totalSaved.setText(Integer.toString(minTotal));
            editor.putInt("minTotal",minTotal);
            editor.putInt("curr",2);
            editor.apply();
            TL.setImageResource(R.drawable.black);
            TR.setImageResource(R.drawable.black);
            BL.setImageResource(R.drawable.black);
            BR.setImageResource(R.drawable.black);


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
                TL.setImageResource(R.drawable.tammy);
                TR.setImageResource(R.drawable.poor);
                BL.setImageResource(R.drawable.left);
                BR.setImageResource(R.drawable.weeks);
            }
        });


// This guy just grabs the current day, hour and second and saves it
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //We wana set minTotal and multiplier
              // also set the text box to the new minTotal
              int minTotal = sharedPreferences.getInt("minTotal", 0);

              int mul = sharedPreferences.getInt("multiplier",1);


              if(TextUtils.isEmpty(multiText.getText().toString()) || TextUtils.isEmpty(resetText.getText().toString())){
                  totalSaved.setText("Fill All Boxes");
              }else{

                    int a = (int) Integer.parseInt(multiText.getText().toString());
                    int b = (int) Integer.parseInt(resetText.getText().toString());

                    editor.putInt("minTotal", b);
                    editor.putInt("multiplier", a);
                    editor.apply();

                    multiText.getText().clear();
                    resetText.getText().clear();
                    totalSaved.setText(Integer.toString(b));
                }
            }
        });





















    }
}