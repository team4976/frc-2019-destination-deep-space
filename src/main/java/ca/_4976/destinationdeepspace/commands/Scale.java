package ca._4976.destinationdeepspace.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Scale extends CommandGroup {

    public Scale(){
        addSequential(new intakeClimb());
        addSequential(new ClimberLeg());
        addSequential(new ClimberLegLock());
        //addParallel(new DriveToEncoderPosition);
        addSequential(new intakeHome()); //Parallel
        addSequential(new ClimberLegLock());
        addSequential(new ClimberLeg());
    }
}
