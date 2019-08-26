package com.codingblocks.readwriteinfile;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button btnWrite, btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        textView = findViewById(R.id.textView);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                File dataDir = ContextCompat.getDataDir(MainActivity.this);
                File myFile = new File(dataDir, "File.txt");
                try{
                    FileOutputStream fos = new FileOutputStream(myFile , true);
                    fos.write(text.getBytes());
                }
                catch (FileNotFoundException fnfe){
                    Toast.makeText(MainActivity.this, "file not found", Toast.LENGTH_SHORT).show();
                }
                catch (IOException ioe){
                    Toast.makeText(MainActivity.this, "Error while writing file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dataDir = ContextCompat.getDataDir(MainActivity.this);
                File myFile = new File(dataDir, "File.txt");

                try {
                    FileInputStream fis = new FileInputStream(myFile);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    StringBuilder sb = new StringBuilder();
                    String buffer = br.readLine();
                    while(buffer != null){
                        sb.append(buffer);
                        buffer = br.readLine();
                    }
                    String text = sb.toString();
                    textView.setText(text);

                } catch (FileNotFoundException fnfe) {
                    Toast.makeText(MainActivity.this, "file not found", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ioe){
                    Toast.makeText(MainActivity.this, "Error while reading file", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
