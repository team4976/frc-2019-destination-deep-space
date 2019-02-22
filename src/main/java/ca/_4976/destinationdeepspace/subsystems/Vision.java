package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Vision extends Subsystem implements Sendable {

    double distance, x;

    NetworkTable visionValues = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = visionValues.getEntry("tx");
    NetworkTableEntry ty = visionValues.getEntry("ty");
    NetworkTableEntry ta = visionValues.getEntry("ta");
    NetworkTableEntry ts = visionValues.getEntry("ts");
    NetworkTableEntry tv = visionValues.getEntry("tv");
    NetworkTableEntry tcornx = visionValues.getEntry("tcornx");
    NetworkTableEntry tcorny = visionValues.getEntry("tcorny");

    Servo camera = new Servo(0);

    @Override
    protected void initDefaultCommand() {}

    //distance calculations
    double defaultValue [] = new double[0];

    public void periodicRead(){
        double areaOne, areaTwo;
        double area = ta.getDouble(0);
        double canSeeValue = tv.getDouble(0);
        double tCornersX [] = tcornx.getDoubleArray(defaultValue);
        double tCornersY [] = tcorny.getDoubleArray(defaultValue);
        //System.out.println("Array Length"+tCornersX.length);
            if(canSeeValue == 1 && tCornersX.length >= 7){
                //System.out.println(tCornersX[0] + " " + tCornersX[1] + " " + tCornersY[0] + " " + tCornersY[1]);
                //Calculates area of both targets
                areaOne = lengthOfLine(tCornersX[0], tCornersY[0], tCornersX[1], tCornersY[1]) * lengthOfLine(tCornersX[1], tCornersY[1], tCornersX[2], tCornersY[2]);
                areaTwo = lengthOfLine(tCornersX[4], tCornersY[4], tCornersX[5], tCornersY[5]) * lengthOfLine(tCornersX[5], tCornersY[5], tCornersX[6], tCornersY[6]);
                double differenceArea = areaOne - areaTwo;
                int threshold = 500;
                System.out.println(differenceArea +"|"+ threshold);
                System.out.println(differenceArea > threshold || differenceArea < -threshold);
                if((differenceArea > threshold || differenceArea < -threshold) && areaOne > areaTwo){
                    System.out.println("Left side closer");
                } else if ((differenceArea > threshold || differenceArea < -threshold)  && areaOne < areaTwo){
                    System.out.println("Right side closer");
                } else {
                    System.out.println("Threshold met");
                }
                //System.out.println(areaOne);
                //System.out.println(areaTwo);
        }
    }

    public double getDistance(){
        double area = ta.getDouble(0);
        distance = 1.408963807/Math.sqrt(area);
        return distance;
    }

    public double readXValue(){
        x = tx.getDouble(0);
        return x;
    }

    public void center() {
        if (readXValue() < 10){
            Robot.drive.drive(0.6, -0.6);
        }
        else {
            Robot.drive.drive(-0.6 , 0.6);
        }
    }

    public boolean isCentered() {
        if (readXValue() > 5 && readXValue() < 15){
            return true;
        }
        else {
            return false;
        }
    }

    //looks for a vision target then returns true
    public boolean stopWithVision(){
        readXValue();
        if (x != 0){
            return true;
        }
        return false;
    }

    // Turns the camera to the left
    public void cameraLeft() {
        camera.setAngle(0);
    }

    // Turns the camera to the forwards position
    public void cameraForwards() {
        camera.setAngle(90);
    }

    // Turns the camera to the right
    public void cameraRight() {
        camera.setAngle(180);
    }

    //Length of a line formula
    public double lengthOfLine(double x1, double y1, double x2, double y2){
        double length = Math.sqrt(Math.pow(x1 - x2, 2) + (Math.pow(y1 - y2, 2)));
        return length;
    }

}