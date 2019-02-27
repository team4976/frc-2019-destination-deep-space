package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Vision extends Subsystem implements Sendable {

    double distance, angA2, nx, ny, vpw, vph, actualX, actualY, x;

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
    public void periodicRead(){
        double area = ta.getDouble(0);
        //Most accurate value so far 1.365839252
        //Most second accurate value so far 1.340580252
        distance = 1.319004642/Math.sqrt(area);

    }

    //distance calculations
    double defaultValue [] = new double[0];

    public String skewValue(){
        double areaOne, areaTwo;
        double area = ta.getDouble(0);
        double canSeeValue = tv.getDouble(0);
        double tCornersX [] = tcornx.getDoubleArray(defaultValue);
        double tCornersY [] = tcorny.getDoubleArray(defaultValue);
        if(canSeeValue == 1 && tCornersX.length >= 7){
            //Calculates area of both targets
            areaOne = lengthOfLine(tCornersX[0], tCornersY[0], tCornersX[1], tCornersY[1]) * lengthOfLine(tCornersX[1], tCornersY[1], tCornersX[2], tCornersY[2]);
            areaTwo = lengthOfLine(tCornersX[4], tCornersY[4], tCornersX[5], tCornersY[5]) * lengthOfLine(tCornersX[5], tCornersY[5], tCornersX[6], tCornersY[6]);
            //Finds the difference between the two areas
            double differenceArea = areaOne - areaTwo;
            int threshold = 500;
            //Prints difference in area and the threshold value
            if((differenceArea > threshold || differenceArea < -threshold) && areaOne > areaTwo){
                return "Turn Right";
            } else if ((differenceArea > threshold || differenceArea < -threshold)  && areaOne < areaTwo){
                return "Turn Left";
            } else {
                return "Threshold met";
            }
        }
        return null;
    }

    //constantly reading x values from the Limelight
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

    //Turns the bot based on its angle in comparison to the target
    public boolean skewCorrection() {
        if (skewValue().equals("Turn Right")){
            Robot.drive.drive(0.5, 0.5);
        }
        else if (skewValue().equals("Turn Left")){
            Robot.drive.drive(-0.5, -0.5);
        }
        if (skewValue().equals("Threshold met")){
            return true;
        }
        return false;
    }

    //Length of a line formula
    public double lengthOfLine(double x1, double y1, double x2, double y2){
        double length = Math.sqrt(Math.pow(x1 - x2, 2) + (Math.pow(y1 - y2, 2)));
        return length;
    }

    public void viewCamera() {
        CameraServer.getInstance().startAutomaticCapture();
    }
}