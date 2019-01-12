package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.subsystems.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }

    public static OI oi;
    public static Drive drive;

    @Override
    public void robotInit() {
        oi = new OI();
        drive = new Drive();
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() { }

    @Override
    public void testPeriodic() { }
}