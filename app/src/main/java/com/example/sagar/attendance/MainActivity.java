package com.example.sagar.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void course1(View view)
    {
        Intent i = new Intent(this,Student.class);
        int number=1;
        i.putExtra("number",number);

        startActivity(i);
    }
    public void course2(View view)
    {
        Intent i = new Intent(this,Student.class);
        int number=2;
        i.putExtra("number",number);

        startActivity(i);
    }
    public void course3(View view)
    {
        Intent i = new Intent(this,Student.class);
        int number=3;
        i.putExtra("number",number);

        startActivity(i);
    }
    public void course4(View view)
    {
        Intent i = new Intent(this,Student.class);
        int number=4;
        i.putExtra("number",number);

        startActivity(i);
    }
}
