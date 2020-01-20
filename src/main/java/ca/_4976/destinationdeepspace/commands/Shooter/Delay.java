package ca._4976.destinationdeepspace.commands.Shooter;


import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {
    long startTime = 0;

    @Override
    protected void initialize(){
        startTime = System.currentTimeMillis();
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime > 1000;
    }
}