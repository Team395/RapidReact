package frc.robot.commands.enabling;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class EnableDrivetrain extends CommandBase {
    RobotContainer robotContainer;

    public EnableDrivetrain(RobotContainer rc) {
        this.robotContainer = rc;
    }

    @Override
    public void execute() {
        robotContainer.enableDrivetrain();
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
