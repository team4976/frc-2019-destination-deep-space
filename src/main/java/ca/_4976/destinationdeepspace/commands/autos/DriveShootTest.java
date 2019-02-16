package ca._4976.destinationdeepspace.commands.autos;

import ca._4976.destinationdeepspace.commands.IsShootingHigh;
import ca._4976.destinationdeepspace.commands.autoModules.Delay;
import ca._4976.destinationdeepspace.commands.autoModules.DriveForwardsFromGroundToLeftSide;
import ca._4976.destinationdeepspace.commands.autoModules.HorizontalCenter;
import ca._4976.destinationdeepspace.commands.shootRight;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveShootTest extends CommandGroup {

    public DriveShootTest(){
        addSequential(new DriveForwardsFromGroundToLeftSide());
        addSequential(new Delay());
        addSequential(new HorizontalCenter());
        addSequential(new IsShootingHigh());
        addSequential(new shootRight());
    }
}
