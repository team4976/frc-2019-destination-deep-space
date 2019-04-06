package ca._4976.destinationdeepspace.commands;

import ca._4976.destinationdeepspace.commands.autoModules.Delay;
import ca._4976.destinationdeepspace.commands.autoModules.HorizontalCenterShooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpControllerRumbleGroup extends CommandGroup {

    public OpControllerRumbleGroup(){
        addSequential(new RumbleOpController());
        addSequential(new Delay());
        addSequential(new StopRumbleOpController());
    }
}
