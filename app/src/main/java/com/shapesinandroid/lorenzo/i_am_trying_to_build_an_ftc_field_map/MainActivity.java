package com.shapesinandroid.lorenzo.i_am_trying_to_build_an_ftc_field_map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
/**
 * <Summary>
 * @author Lorenzo Pedroza
 * Working on code to rotate rectangle
 * <Summary/>
 * <Version_History>
 *     v 0.1 Inaccurate field 11/22/18
 *     v 0.2 Movable Robot using Seekbars as proof of concept 11/23/18
 * </Version_History>
 */


public class MainActivity extends AppCompatActivity {
    FTCFieldMapView ftcFieldMapView;
    ToggleButton showRRFieldToggleButton;
    SeekBar moveRobotXSeekBar;
    SeekBar moveRobotYSeekbar;
    SeekBar rotateRobotSeekbar;
    TextView mineralTouchingRobotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftcFieldMapView = findViewById(R.id.ftcFieldMapViewID);
        ftcFieldMapView.inputRobotDimensions(24, 18);
        showRRFieldToggleButton = findViewById(R.id.showRRFieldToggleButtonID);
        moveRobotXSeekBar = findViewById(R.id.moveXRobotSeekbarID);
        moveRobotYSeekbar = findViewById(R.id.moveRobotYSeekBarID);
        rotateRobotSeekbar = findViewById(R.id.rotateRobotSeekBarID);
        mineralTouchingRobotTextView = findViewById(R.id.showIfRobotIsTouchingMineralTextViewID);


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

        moveRobotXSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ftcFieldMapView.updateRobotCoordinate(progress, true);
                mineralTouchingRobotTextView.setText(ftcFieldMapView.robotTouchingLanderString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        moveRobotYSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ftcFieldMapView.updateRobotCoordinate(progress, false);
                mineralTouchingRobotTextView.setText(ftcFieldMapView.robotTouchingLanderString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // updateRobotMienralTouchingTextBox();

            }
        });

        rotateRobotSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ftcFieldMapView.updateRobotBearing(progress*2);
                mineralTouchingRobotTextView.setText(ftcFieldMapView.robotTouchingLanderString());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //updateRobotMienralTouchingTextBox();

            }
        });










    }



    /*private void updateRobotMienralTouchingTextBox() {
        mineralTouchingRobotTextView.setText("Touching Gold Mineral: " + ftcFieldMapView.robotTouchingLander() +
                "\n" + "Touching Silver Mineral" + ftcFieldMapView.robotTouchingSilverMineral());
    }
    */


}
