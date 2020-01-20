package ca._4976.destinationdeepspace.commands.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FireSequence extends CommandGroup {
    public FireSequence(){
        addSequential(new FireBannanas());
        addSequential(new Delay());
        addSequential(new StopShooter());
    }
}
