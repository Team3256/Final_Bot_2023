package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.intake.commands.GroundIntake;

public class AutoGroundIntake extends CommandBase {

    private GroundIntake groundIntake;

    public AutoGroundIntake(GroundIntake groundIntakeSubsystem){
        this.groundIntake = groundIntakeSubsystem;
    }

    @Override
    public void initialize(){

    }

}
