package ca._4976.destinationdeepspace.commands.autoModules;

import edu.wpi.first.wpilibj.command.Command;

//Used as a delay between steps in autonomous
public class Delay extends Command {
    private long startTime = 0;
    @Override
    protected void initialize(){
        startTime = System.currentTimeMillis();
    }
    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime > 500;
    }
}
