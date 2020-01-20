package ca._4976.destinationdeepspace.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;

import static ca._4976.destinationdeepspace.Robot.*;

public class ToggleRentalMode extends Command {
    protected void initialize(){
        intakeArm.toggleRentalMode();
        if (intakeArm.rentalMode) {
            controller.rumble(oi.operator, 1);
            controller.rumble(oi.driver, 1);
        } else {
            controller.rumble(oi.operator, 0);
            controller.rumble(oi.driver, 0);
        }
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
