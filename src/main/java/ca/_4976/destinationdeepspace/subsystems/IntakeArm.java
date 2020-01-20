package ca._4976.destinationdeepspace.subsystems;

import ca._4976.destinationdeepspace.commands.Intake.MoveIntakeWithJoystick;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static com.ctre.phoenix.motorcontrol.ControlMode.Position;

public class IntakeArm extends Subsystem {
    private TalonSRX intakeArm = new TalonSRX(43);
    private VictorSPX intakeArmSlave = new VictorSPX(42);
    public boolean rentalMode = false;

    public IntakeArm() {
        intakeArm.setSensorPhase(true);
    }

    public void moveToSetPoint(int setPoint) {
        intakeArm.setIntegralAccumulator(0);
        intakeArm.set(Position, setPoint);
    }

    public double applyDeadband(double x) {
        double deadband = 0.25;
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

    public void moveIntakeArmWithJoystick(Joystick joy) {
        if (applyDeadband(joy.getRawAxis(5)) == 0) {
            if (intakeArm.getControlMode() == PercentOutput){
                intakeArm.set(PercentOutput, -0.07);
            }
        } else if (!rentalMode) {
            intakeArm.set(PercentOutput, 0.4 * (applyDeadband(joy.getRawAxis(5))));
        } else {
            intakeArm.set(PercentOutput, (applyDeadband(joy.getRawAxis(5))));
        }
    }

    public void toggleRentalMode() {
        rentalMode = !rentalMode;
    }

    @Override
    protected void initDefaultCommand() {
        intakeArmSlave.setInverted(true);
        intakeArmSlave.follow(intakeArm);
        setDefaultCommand(new MoveIntakeWithJoystick());
    }
}
