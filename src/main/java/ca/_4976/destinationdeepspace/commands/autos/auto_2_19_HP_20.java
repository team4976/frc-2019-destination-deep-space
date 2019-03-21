package ca._4976.destinationdeepspace.commands.autos;

import ca._4976.destinationdeepspace.commands.HP;
import ca._4976.destinationdeepspace.commands.HPRelease;
import ca._4976.destinationdeepspace.commands.autoModules.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class auto_2_19_HP_20 extends CommandGroup {

    public auto_2_19_HP_20(){
        addSequential(new DriveForwardsFromTwoToRocket());
        addSequential(new TurnFromRocketToNineteen());
        addSequential(new DriveForwardsFromRocketToNineteen());
        addSequential(new Delay());
        addSequential(new HorizontalCenter());
        addSequential(new Delay());
        addSequential(new TurnNinteyDegreesRight());
        addSequential(new Delay());
        addSequential(new DriveForHatch());
        addSequential(new Delay());
        addSequential(new HP());
        addSequential(new Delay());
        addSequential(new HPRelease());
        addSequential(new Delay());
        addSequential(new DriveBackwardsABit());
        addSequential(new TurnFromNineteenToTwentyoneToGoForwards());
        addSequential(new DriveForwardsFromNineteenToTwentyone());
    }
}
