package ca._4976.destinationdeepspace.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Vision extends Subsystem implements Sendable {

    double distance, angA2, nx, ny, vpw, vph, actualX, actualY;

    NetworkTable visionValues = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = visionValues.getEntry("tx");
    NetworkTableEntry ty = visionValues.getEntry("ty");
    NetworkTableEntry ta = visionValues.getEntry("ta");

    @Override
    protected void initDefaultCommand() {}

    public void periodicRead(){
        double area = ta.getDouble(0);
        //Most accurate value so far 1.365839252
        //Most second accurate value so far 1.340580252
        distance = 1.408963807/Math.sqrt(area);

        System.out.println("Distance: " + distance);
    }

    public double readXValue(){
        double x = tx.getDouble(0);
        return x;
    }

}