package ca._4976.destinationdeepspace.commands.autos;

import ca._4976.destinationdeepspace.commands.HP;
import ca._4976.destinationdeepspace.commands.HPRelease;
import ca._4976.destinationdeepspace.commands.autoModules.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class auto_2_9_HP_20 extends CommandGroup {

    public auto_2_9_HP_20(){
        addSequential(new DriveForwardsFromPlatformToRightSide());
        addSequential(new Delay());
        addSequential(new HorizontalCenterShooter());
        addSequential(new Delay());
        addSequential(new TurnNinteyDegreesLeft());
        addSequential(new Delay());
        addSequential(new DriveForHatch());
        addSequential(new Delay());
        addSequential(new HP());
        addSequential(new Delay());
        addSequential(new HPRelease());
        addSequential(new Delay());
        addSequential(new DriveBackwardsABit());
        addSequential(new TurnFromNinetToTwentyoneToGoBackwards());
        addSequential(new DriveBackwardsFromNineToTwentyone());
    }
}
