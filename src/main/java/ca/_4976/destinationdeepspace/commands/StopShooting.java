package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.Robot;
import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;
import edu.wpi.first.wpilibj.command.Command;

public class StopShooting extends Command {
    @Override
    protected void initialize(){
        Robot.shooterCock.stopDaddy();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
