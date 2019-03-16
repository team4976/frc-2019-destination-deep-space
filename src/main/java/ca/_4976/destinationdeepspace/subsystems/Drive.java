package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import javafx.geometry.Pos;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Position;

// The DriveTrain subsystem controls the robot's chassis and reads in
// information about it's speed and posit ion.

public class Drive extends Subsystem {
    // Left drive motor controllers
    private TalonSRX LF = new TalonSRX(49);
    private TalonSRX RF = new TalonSRX(44);
    // Right drive motor controllers
    private VictorSPX LB = new VictorSPX(46);
    private VictorSPX RB = new VictorSPX(45);
    // Gear shift solonid
    public Solenoid gearShift = new Solenoid(40, 4);
    private double rightPos;
    private double leftPos;
    // Control flags
    private boolean userControlEnabled = true;

    // Applies the deadband to the joystick outputs
    private double applyDeadband(double x) {

        // The deadband percentage value
        double deadband = 0.10;
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
    private double regularize(double x) {
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
            // Variables used in the drive calculations
            double throttle = applyDeadband(joy.getRawAxis(2) - joy.getRawAxis(3));
            // Save the left stick value
            double turn = applyDeadband(joy.getRawAxis(0));

            // Saves the left and right outputs as the throttle and turn values combined
            double leftOutput = regularize(throttle + turn);
            double rightOutput = regularize(-throttle + turn);

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
        LF.set(Position, LeftPos);
        RB.set(Position, RightPos);
        LB.set(Position, LeftPos);
        rightPos = RightPos;
        leftPos = LeftPos;
    }
    // Checks to see if bot is at encoder target
    public boolean isAtTarget(){
        double errorRange = 0.05;
        if (RF.getClosedLoopError() >= rightPos * (1 - errorRange) && RF.getClosedLoopError() <= rightPos * (1 + errorRange)
                && RF.getClosedLoopError() >= leftPos * (1 - errorRange) && RF.getClosedLoopError() <= leftPos) {
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