package ca._4976.destinationdeepspace;

import ca._4976.destinationdeepspace.subsystems.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

    private Scheduler scheduler;

    public static  OI oi;
    public static Drive drive;
    public static Compresor compressor;
    public static IntakeArm intakeArm;
    public static Controller controller;
    public static IntakeRamp intakeRamp;
    public static Climber climber;
    public static HatchPanelMech hatchPanelMech;
    public static Shooter shooter;
    public static Vision vision;

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }

    @Override
    public void robotInit() {
        scheduler = Scheduler.getInstance();

        oi = new OI();
        drive = new Drive();
        compressor = new Compresor();
        intakeArm = new IntakeArm();
        controller = new Controller();
        intakeRamp = new IntakeRamp();
        climber = new Climber();
        hatchPanelMech = new HatchPanelMech();
        shooter = new Shooter();
        vision = new Vision();

        CameraServer.getInstance().startAutomaticCapture();

        Robot.vision.ledModeR.setDouble(1);
//        Robot.vision.ledModeL.setDouble(1);
    }

    @Override
    public void robotPeriodic(){
        scheduler.run();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() {
        Robot.vision.ledModeR.setDouble(1);
//        Robot.vision.ledModeL.setDouble(1);
    }

    @Override
    public void teleopInit() {
        Robot.vision.ledModeR.setDouble(1);
//        Robot.vision.ledModeL.setDouble(1);
    }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testPeriodic() { }
}