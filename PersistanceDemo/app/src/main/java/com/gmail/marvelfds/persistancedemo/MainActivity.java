package com.gmail.marvelfds.persistancedemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText etValue;
    private Button btnPersist;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.etValue = (EditText)findViewById(R.id.etValue);
        this.btnPersist =(Button)findViewById(R.id.btnPersist);
        onload();
    }
   //pertist data using  Shared Preferences
    private void onload() {
        String text = pref.getString("text","N/A");
        this.etValue.setText(text);

    }

    public void onPersist1(View v){
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("text",etValue.getText().toString());
        edit.apply();
        Toast.makeText(this,"text saved",Toast.LENGTH_LONG).show();
    }

    // persist data using File

    private void onload2() {
        BufferedReader input = null;
        String text= "N/A";

        try {
            input = new BufferedReader(
                    new InputStreamReader(openFileInput("myfile")));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + "\n");
            }
            text = buffer.toString();
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        this.etValue.setText(text);

    }

    public void onPersist2(View v) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("myfile", MODE_PRIVATE);
            // create a buffer writter
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            try {
                writer.write(this.etValue.getText().toString());
                Toast.makeText(this,"text saved",Toast.LENGTH_LONG).show();
            } catch (IOException ex) {
                ex.printStackTrace();
                Toast.makeText(this,"text did not save",Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
//persist data using sqllite
    private void onLoad3(){
        InputValue val = InputValue.theMostRecentValue();
        etValue.setText(val.text);
    }
    public void onPersist3(View v) {
        InputValue val = new InputValue(etValue.getText().toString());
        val.save();
    }

    //

    public void btnOnclick(View view) {
        onPersist1(etValue);
    }
}
