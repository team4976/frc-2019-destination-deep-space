package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import edu.wpi.first.wpilibj.command.Command;

//Sets the rpm of the right shooter
public class rpmRight extends Command {
    @Override
    protected void initialize(){
        Robot.shooter.rpmRight();
        System.out.println("Rpm executed");
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
