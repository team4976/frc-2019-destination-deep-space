package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Velocity;

public class Vision extends Subsystem {

    NetworkTable rightCam = NetworkTableInstance.getDefault().getTable("limelight-right");
    NetworkTableEntry txR = rightCam.getEntry("tx");
    NetworkTableEntry tyR = rightCam.getEntry("ty");
    NetworkTableEntry tvR = rightCam.getEntry("tv");
    public NetworkTableEntry ledModeR = rightCam.getEntry("ledMode");

//    NetworkTable leftCam = NetworkTableInstance.getDefault().getTable("limelight-left");
//    NetworkTableEntry txL = leftCam.getEntry("tx");
//    NetworkTableEntry tyL = leftCam.getEntry("ty");
//    NetworkTableEntry tvL = leftCam.getEntry("tv");
//    public NetworkTableEntry ledModeL = leftCam.getEntry("ledMode");

    private double targetVelocity = 0;

    public double xValue(boolean rightSide) {
//        if (rightSide)
            return txR.getDouble(0);
//        else return txL.getDouble(0);
    }

    public double yValue(boolean rightSide) {
//        if (rightSide)
            return tyR.getDouble(0);
//        else return tyL.getDouble(0);
    }

    public boolean hasTarget(boolean rightSide){
//        if (rightSide)
            return ((int) tvR.getDouble(0)) == 1;
//        else return ((int) tvL.getDouble(0)) == 1;
    }

    public void toggleLEDOff(boolean rightSide) {
        if (rightSide) ledModeR.setDouble(1);
//        else  ledModeL.setDouble(1);
    }

    public void toggleLEDOn(boolean rightSide) {
        if (rightSide) ledModeR.setDouble(0);
//        else  ledModeL.setDouble(0);
    }

    public void centerShooterPID(boolean rightSide) {
        double velocityScaler = 40;
        if (hasTarget(rightSide)) {
            if (rightSide) targetVelocity = -velocityScaler * xValue(true);
            else targetVelocity = velocityScaler * xValue(false);
        } else {
            targetVelocity = 200;
        }
        Robot.drive.RF.set(Velocity, targetVelocity);
        Robot.drive.LF.set(PercentOutput, -Robot.drive.RF.getMotorOutputPercent());
//        Robot.drive.LF.set(Velocity, -targetVelocity);
        Robot.drive.RB.set(PercentOutput, Robot.drive.RF.getMotorOutputPercent());
        Robot.drive.LB.set(PercentOutput, Robot.drive.LF.getMotorOutputPercent());
    }

    @Override
    protected void initDefaultCommand() {
    }
}
