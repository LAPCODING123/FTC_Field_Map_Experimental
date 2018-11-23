package com.shapesinandroid.lorenzo.i_am_trying_to_build_an_ftc_field_map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    FTCFieldMapView ftcFieldMapView;
    ToggleButton showRRFieldToggleButton;
    SeekBar moveRobotSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftcFieldMapView = findViewById(R.id.ftcFieldMapViewID);
        ftcFieldMapView.inputRobotDimensions(24, 18);
        showRRFieldToggleButton = findViewById(R.id.showRRFieldToggleButtonID);
        moveRobotSeekBar = findViewById(R.id.moveRobotSeekbarID);


        showRRFieldToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ftcFieldMapView.setVisibility(View.VISIBLE);
                }
                else {
                    ftcFieldMapView.setVisibility(View.INVISIBLE);
                }
            }
        });

        moveRobotSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ftcFieldMapView.updateRobotCoordinates(progress, 35.25, 45);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
