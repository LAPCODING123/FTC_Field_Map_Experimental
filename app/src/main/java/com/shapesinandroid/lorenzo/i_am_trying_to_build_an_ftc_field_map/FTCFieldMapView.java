package com.shapesinandroid.lorenzo.i_am_trying_to_build_an_ftc_field_map;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.View;

public class FTCFieldMapView extends View {
    private static final int SCALE = 4;
    private int robotxPos = 0 ;
    private int robotyPos = 0 ;
    private int robotBearingInDegrees = 45; //always start with a reference angle beta of 45 degrees.

    //Need to quadruple everything as field is twice the size in pixels two increase resoultion as pixels are integers.
    //I multiply field by
    //RRGameElements.GoldMineral goldMineralToSampleBlueCrater;
    Paint green = new Paint(Color.GREEN);
    RectShape goldMineral = new RectShape();
    OvalShape silverMineral = new OvalShape();
    float[] craterOuterCornerRadii = new float[]{30f, 30f, 30f, 30f, 30f, 30f, 30f, 30f};
    RoundRectShape crater = new RoundRectShape(craterOuterCornerRadii, new RectF(), null);
    RectShape landerArea = new RectShape();
    ShapeDrawable mDrawable;
    ShapeDrawable silverMineralDrawable;
    ShapeDrawable landerAreaDrawable;
    ShapeDrawable craterDrawable;

    public ValueAnimator robotLocationUpdater;
    RectShape robot = new RectShape();
    ShapeDrawable robotDrawable;
    PropertyValuesHolder robotXHolder;
    PropertyValuesHolder robotYHolder;
    PropertyValuesHolder robotBearingHolder;
    private int robotLengthInches;
    private int robotWidthInches;

    private static final int leftGapOfOuterRightMineral = 45*SCALE; //TODO Veirfy all mineral distances.
    private static final int topGapOfMineral = 94; //(23.5*4)

    //private static final float SCALE = //Field In inches. Can get to quarter of an inch accuracy by making



    public FTCFieldMapView(Context context){
        super(context);
    }

    //TODO Figure out how to make a field an XML file that is easy to build.
    public FTCFieldMapView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public FTCFieldMapView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setScaleY((float) ((1.0/SCALE)*2.0)); //Shrink it to actual size that will fit.
        setScaleX((float) ((1.0/SCALE)*2.0));

        canvas.drawColor(Color.LTGRAY);
        mDrawable = new ShapeDrawable(goldMineral);
        landerAreaDrawable = new ShapeDrawable(landerArea);
        //
        mDrawable.setBounds(leftGapOfOuterRightMineral, topGapOfMineral, leftGapOfOuterRightMineral+(2*SCALE), topGapOfMineral+(2*SCALE)); //gold mineral side = 2" //off by half an inch. Need to increase scale then scale by half.
        mDrawable.getPaint().setColor(Color.YELLOW);
        mDrawable.draw(canvas);
        canvas.save();

        silverMineralDrawable = new ShapeDrawable(silverMineral);
        silverMineralDrawable.setBounds(51, 137, 51 + (2*SCALE), 137 + (2*SCALE));
        silverMineralDrawable.getPaint().setColor(Color.WHITE);
        silverMineralDrawable.draw(canvas);
        canvas.save();

        craterDrawable = new ShapeDrawable(crater);
        craterDrawable.setBounds(-80, -70, 60, 120);
        canvas.rotate(45, craterDrawable.getBounds().centerX(), craterDrawable.getBounds().centerY());
        craterDrawable.getPaint().setColor(Color.BLACK);
        craterDrawable.draw(canvas);
        canvas.restore();
        canvas.save();


        //draw the lander.
        canvas.rotate(45, canvas.getWidth()/2, canvas.getHeight()/2);
        landerAreaDrawable.setBounds(384, 384, canvas.getWidth()-384, canvas.getHeight()-384); //44.8 is about 45 and (564-364 = 200).45*4 = 180 Rather have a little more to compensate for tape area.
        landerAreaDrawable.getPaint().setColor(Color.GRAY);
        landerAreaDrawable.draw(canvas);
        canvas.restore();
        canvas.save();
        Rect landerAreaDrawableBounds = landerAreaDrawable.getBounds();
        canvas.drawText(landerAreaDrawableBounds.toString(), 30, 30, new Paint(Color.GREEN));

        //Rect rect = new Rect(20, 20, 0, 40);
        robotDrawable = new ShapeDrawable(robot);
        robotDrawable.setBounds(robotxPos-(int)(0.5*robotLengthInches), robotyPos-(int)(0.5*robotWidthInches), robotxPos+(int)(0.5*robotLengthInches), robotyPos+(int)(0.5*robotWidthInches));
        robotDrawable.getPaint().setColor(Color.BLUE);
        canvas.rotate(robotBearingInDegrees);
        robotDrawable.draw(canvas);
        canvas.restore();
        canvas.save();


    }

    public void updateRobotCoordinates(double robotxPos, double robotyPos, int robotBearingInDegrees){
        this.robotxPos = (int)(robotxPos*SCALE); //TODO check for weird casting bugs here.
        this.robotyPos = (int)(robotyPos*SCALE);
        this.robotBearingInDegrees = (robotBearingInDegrees);
        invalidate(); //now redraw robot location.
    }

    public void inputRobotDimensions(double robotLengthInches, double robotWidthInches){
        this.robotLengthInches = (int)(robotLengthInches*SCALE);
        this.robotWidthInches = (int)(robotWidthInches*SCALE);
    }

    public int getRobotXCoordingates(){
        return robotDrawable.getBounds().centerX();  //get Midpoint of Robot (Might need to make engine to convert field coordinates to graphics coordinates)=
    }

    public int getRobotYCoordinates(){
        return robotDrawable.getBounds().centerY();
    }

}
