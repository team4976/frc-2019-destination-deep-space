package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Vision extends Subsystem implements Sendable {

    double distance, angA2, nx, ny, vpw, vph, actualX, actualY;

    NetworkTable visionValues = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = visionValues.getEntry("tx");
    NetworkTableEntry ty = visionValues.getEntry("ty");
    NetworkTableEntry ta = visionValues.getEntry("ta");

    Servo camera = new Servo(1);

    @Override
    protected void initDefaultCommand() {}

    public void periodicRead(){
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        nx = (1/160.0) * (x - 159.5);
        ny = (1/120.0) * (119.5 - y);

        vpw = 2.0*Math.tan(27);
        vph = 2.0*Math.tan(20.5);

        actualX = vpw/2 * nx;
        actualY = vph/2 * ny;

        angA2 = Math.atan2(1,x);

        distance = ( - 1)/Math.tan(0+angA2);

        System.out.println("Y Value: " + y);
        System.out.println("Distance: " + distance);
    }

    public double readXValue(){
        double x = tx.getDouble(0);
        return x;
    }

    public void cameraLeft() {
        camera.setAngle(0);
    }

    public void cameraForwards() {
        camera.setAngle(90);
    }

    public void cameraRight() {
        camera.setAngle(180);
    }

}