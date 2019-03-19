package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.Robot;
import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Position;

// The DriveTrain subsystem controls the robot's chassis and reads in
// information about it's speed and posit ion.

public class Drive extends Subsystem {
    // Making a network table
    NetworkTable drive = NetworkTableInstance.getDefault().getTable("Drive");
    // Left drive motor controllers
    TalonSRX LF = new TalonSRX(49);
    TalonSRX RF = new TalonSRX(44);
    // Right drive motor controllers
    VictorSPX LB = new VictorSPX(46);
    VictorSPX RB = new VictorSPX(45);
    // Gear shift solonid
    public Solenoid gearShift = new Solenoid(40, 4);
    // The deadband percentage value
    double deadband = 0.10;
    // Variables used in the drive calculations
    double throttle, turn, leftOutput, rightOutput, errorRange = 0.05, RightPos, LeftPos;
    // Control flags
    public boolean userControlEnabled = true;

    // Applies the deadband to the joystick outputs
    public double applyDeadband(double x) {

        if (Math.abs(x) > deadband) {
            if (x > 0.0) {
                return (x - deadband) / (1.0 - deadband);
            } else {
                return (x + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
    // Checks if a value is above 1 or below -1 and sets them to 1 and -1 respectively
    public double regularize(double x) {
        if (x > 1.0) {
            return 1.0;
        } else if (x < -1.0) {
            return -1.0;
        } else {
            return x;
        }
    }
    // Sets the motor controllers to the calculated outputs
    public void drive(double leftOutput, double rightOutput) {
        LF.set(PercentOutput, leftOutput);
        LB.set(PercentOutput, leftOutput);
        RF.set(PercentOutput, rightOutput);
        RB.set(PercentOutput, rightOutput);
    }
    // Drive output calculations
    public void arcadeDrive(Joystick joy) {
        if (userControlEnabled) {
            // Save the left and right trigger values as a combined value
            throttle = applyDeadband(joy.getRawAxis(2) - joy.getRawAxis(3))*Math.abs(applyDeadband(joy.getRawAxis(2) - joy.getRawAxis(3)));
            // Save the left stick value
            turn = applyDeadband(joy.getRawAxis(0))*Math.abs(applyDeadband(joy.getRawAxis(0)));

            // Saves the left and right outputs as the throttle and turn values combined
            leftOutput = regularize(throttle + turn);
            rightOutput = regularize(-throttle + turn);

            drive(leftOutput, rightOutput);
        }
    }
    // Sets the drive outputs to zero
    public void stop() {
        drive(0, 0);
    }
    //Set user control enabled or disabled
    public void setUserControlEnabled(boolean enabled) {
        userControlEnabled = enabled;
    }
    //Drives to an encoder position
    public void driveToEncoderPos(double RightPos, double LeftPos) {
        //disables user control
        setUserControlEnabled(false);
        RF.set(Position, RightPos);
        RB.set(Position, RightPos);
        LF.set(Position, LeftPos);
        LB.set(Position, LeftPos);
    }
    // Checks to see if bot is at encoder target
    public boolean isAtTarget(){
        if (RF.getClosedLoopError() >= RightPos * (1 - errorRange) && RF.getClosedLoopError() <= RightPos * (1 + errorRange)
                && RF.getClosedLoopError() >= LeftPos * (1 - errorRange) && RF.getClosedLoopError() <= LeftPos) {
            setUserControlEnabled(true);
            RF.set(PercentOutput, 0);
            RB.set(PercentOutput, 0);
            LF.set(PercentOutput, 0);
            LB.set(PercentOutput, 0);
            return true;
        }
        else {
            return false;
        }
    }
    // Changes the gear
    public void gearShift() {
        gearShift.set(!gearShift.get());
    }
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}