package com.example.sagar.attendance;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Student extends AppCompatActivity {

    TextView coursename, studentname, studentattend, studenttotal, studentpercent;
    boolean present, absent;
    ArrayList<String> studentName;
    ArrayList<Integer> studentAttended,studentTotal;
    int index;
    int total;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        coursename = (TextView)findViewById(R.id.coursename);
        studentname = (TextView)findViewById(R.id.studentname);
        studentattend = (TextView)findViewById(R.id.studentattend);
        studenttotal = (TextView)findViewById(R.id.studenttotal);
        studentpercent = (TextView)findViewById(R.id.studentpercent);

        present = false;
        absent = false;
        studentName = new ArrayList<String>();
        studentAttended = new ArrayList<>();
        studentTotal = new ArrayList<>();
        index=0;
        total=0;

        Bundle data = getIntent().getExtras();
        if(data==null)
            return;
        number = data.getInt("number");
        String courName = "COURSE"+number;
        coursename.setText(courName);

        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath()+"/Attendance");
        File file = new File(dir, "course"+number+".txt");
        String message;

        try {
            FileInputStream in = new FileInputStream(file);
            InputStreamReader inreader = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inreader);
            StringBuffer strin = new StringBuffer();

            try {
                while((message = buf.readLine())!=null)
                {
                    total++;
                    studentName.add(message);
                    message = buf.readLine();
                    studentAttended.add(Integer.parseInt(message));
                    message = buf.readLine();
                    studentTotal.add(Integer.parseInt(message));
                }
                buf.close();
                inreader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        studentname.setText(studentName.get(index));
        studentattend.setText("Class Attended : "+studentAttended.get(index));
        studenttotal.setText("Total Classes : "+studentTotal.get(index));
        float percent = (float)(studentAttended.get(index))/(float)(studentTotal.get(index));
        percent=percent*100;
        studentpercent.setText("Percentage : "+percent);
    }

    public void markAbsent(View view)
    {
        if(present)
            present=false;
        absent=true;
        studentattend.setText("Class Attended"+studentAttended.get(index));
        studenttotal.setText("Total Classes"+(studentTotal.get(index)+1));
        float percent = (float)(studentAttended.get(index))/(float)(studentTotal.get(index)+1);
        percent=percent*100;
        studentpercent.setText("Percentage : "+percent);

    }

    public void next(View view)
    {
        if(present)
        {
            studentAttended.set(index,studentAttended.get(index)+1);
            studentTotal.set(index,studentTotal.get(index)+1);
            present=false;
        }
        if(absent)
        {
            studentTotal.set(index,studentTotal.get(index)+1);
            absent=false;
        }



        String state;
        state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"/Attendance");
            if(!dir.exists())
            {
                dir.mkdir();
            }
            File file = new File(dir, "course"+number+".txt");
            try{
                String updated="";
                FileOutputStream out = new FileOutputStream(file);
                for(int i=0;i<total;i++)
                {
                    updated= updated + studentName.get(i) + System.lineSeparator() + studentAttended.get(i)+System.lineSeparator()+studentTotal.get(i)+System.lineSeparator();
                }
                out.write(updated.getBytes());
                out.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        if(index==total-1)
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        else {
            index++;
            studentname.setText(studentName.get(index));
            studentattend.setText("Class Attended : " + studentAttended.get(index));
            studenttotal.setText("Total Classes : " + studentTotal.get(index));
            float percent = (float)(studentAttended.get(index))/(float)(studentTotal.get(index));
            percent=percent*100;
            studentpercent.setText("Percentage : "+percent);
        }
    }

    public void previous(View view)
    {

        if(present)
        {
            studentAttended.set(index,studentAttended.get(index)+1);
            studentTotal.set(index,studentTotal.get(index)+1);
            present=false;
        }
        if(absent)
        {
            studentTotal.set(index,studentTotal.get(index)+1);
            absent=false;
        }


        String state;
        state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"/Attendance");
            if(!dir.exists())
            {
                dir.mkdir();
            }
            File file = new File(dir, "course"+number+".txt");
            try{
                String updated="";
                FileOutputStream out = new FileOutputStream(file);
                for(int i=0;i<total;i++)
                {
                    updated= updated + studentName.get(i) + System.lineSeparator() + studentAttended.get(i)+System.lineSeparator()+studentTotal.get(i)+System.lineSeparator();
                }
                out.write(updated.getBytes());
                out.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        if(index==0)
        {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        else {
            index--;
            studentname.setText(studentName.get(index));
            studentattend.setText("Class Attended : " + studentAttended.get(index));
            studenttotal.setText("Total Classes : " + studentTotal.get(index));
            float percent = (float)(studentAttended.get(index))/(float)(studentTotal.get(index));
            percent=percent*100;
            studentpercent.setText("Percentage : "+percent);
        }
    }

    public void markPresent(View view)
    {
        if(absent)
            absent=false;
        present=true;
        studentattend.setText("Class Attended"+(studentAttended.get(index)+1));
        studenttotal.setText("Total Classes"+(studentTotal.get(index)+1));
        float percent = (float)(studentAttended.get(index)+1)/(float)(studentTotal.get(index)+1);
        percent=percent*100;
        studentpercent.setText("Percentage : "+percent);
    }

}



