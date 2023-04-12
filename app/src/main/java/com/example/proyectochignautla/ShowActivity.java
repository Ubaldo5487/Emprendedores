package com.example.proyectochignautla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectochignautla.Class.Reports;


public class ShowActivity extends AppCompatActivity {
    private TextView sName;
    private TextView sFatherLastName;
    private TextView sMotherLastName;
    private TextView sPhonenumber;
    private TextView sAge;
    private TextView sStreet1;
    private TextView sStreet2;
    private TextView sArea;
    private TextView sProblem;
    private TextView sDescription;
    private TextView sCreated_at;
    private TextView sStatus;
    private Reports reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initViews();
        initValues();
    }

    private void initViews() {
        sName = findViewById(R.id.sName);
        sFatherLastName = findViewById(R.id.sFatherLastName);
        sMotherLastName = findViewById(R.id.sMotherLastName);
        sPhonenumber = findViewById(R.id.sPhonenumber);
        sAge = findViewById(R.id.sAge);
        sStreet1 = findViewById(R.id.sStreet1);
        sStreet2 = findViewById(R.id.sStreet2);
        sArea = findViewById(R.id.sArea);
        sProblem = findViewById(R.id.sProblem);
        sDescription = findViewById(R.id.sDescription);
        sCreated_at = findViewById(R.id.sCreated_at);
        sStatus = findViewById(R.id.sStatus);
    }

    public void initValues() {
        reports = (Reports) getIntent().getExtras().getSerializable("itemDetail");
        sName.setText(reports.getName());
        sFatherLastName.setText(reports.getFatherlastname());
        sMotherLastName.setText(reports.getMotherlatsname());
        sPhonenumber.setText(reports.getPhonenumber());
        sAge.setText(reports.getAge());
        sStreet1.setText(reports.getStreet1());
        sStreet2.setText(reports.getStreet2());
        sArea.setText(reports.getArea());
        sProblem.setText(reports.getProblem());
        sDescription.setText(reports.getDescription());
        sCreated_at.setText(reports.getCreated_at());
        sStatus.setText(reports.getStatus());
    }
}