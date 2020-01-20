package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.DriveTrain.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;

// The DriveTrain subsystem controls the robot's chassis and reads in
// information about it's speed and posit ion.

public class Drive extends Subsystem {
    // Making a network table
    NetworkTable drive = NetworkTableInstance.getDefault().getTable("Drive");
    // Left drive motor controllers
    public TalonSRX LF = new TalonSRX(49);
    public TalonSRX RF = new TalonSRX(44);
    // Right drive motor controllers
    public VictorSPX LB = new VictorSPX(46);
    public VictorSPX RB = new VictorSPX(45);
    // Gear shift solonid
    public Solenoid gearShift = new Solenoid(40, 4);
    // The deadband percentage value
    double deadband = 0.10;
    // Variables used in the drive calculations
    double throttle, turn, leftOutput, rightOutput;
    // Control flags
    public boolean userControlEnabled = true;

    public Drive() {
        RF.setSensorPhase(false);
        LF.setSensorPhase(false);
    }
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
            leftOutput = regularize(throttle - turn);
            rightOutput = regularize(-throttle - turn);

            drive(leftOutput, rightOutput);
        }
    }
    // Sets the drive outputs to zero
    public void stop() {
        drive(0, 0);
    }

    public void setUserControl(boolean enabled) {
        userControlEnabled = enabled;
    }

    public void gearShift() {
        gearShift.set(!gearShift.get());
    }

    @Override
    protected void initDefaultCommand() {
        LF.setInverted(true);
        RF.setInverted(true);
        LB.setInverted(true);
        RB.setInverted(true);
        gearShift.set(true);
        setDefaultCommand(new DriveWithJoystick());
    }
}