// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.intake.commands;

import static frc.robot.Constants.FeatureFlags.*;
import static frc.robot.Constants.VisionConstants.*;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.arm.Arm;
import frc.robot.arm.commands.SetArmAngle;
import frc.robot.elevator.Elevator;
import frc.robot.elevator.commands.ZeroElevator;
import frc.robot.intake.Intake;
import frc.robot.limelight.Limelight;
import java.util.function.BooleanSupplier;

public class GroundIntake extends CommandBase {

  private Elevator elevatorSubsystem;
  private Arm armSubsystem;
  private Intake intakeSubsystem;
  private static int previousPipelineIndex;

  private BooleanSupplier isCurrentPieceCone;

  public GroundIntake(
      Elevator elevatorSubsystem,
      Arm armSubsystem,
      Intake intakeSubsystem,
      BooleanSupplier isCurrentPieceCone) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.armSubsystem = armSubsystem;
    this.intakeSubsystem = intakeSubsystem;
    this.isCurrentPieceCone = isCurrentPieceCone;
  }

  @Override
  public void initialize() {
    if (kGamePieceDetection) {
      this.previousPipelineIndex =
          (int) Limelight.getCurrentPipelineIndex(FrontConstants.kLimelightNetworkTablesName);
      Limelight.setPipelineIndex(
          FrontConstants.kLimelightNetworkTablesName, FrontConstants.kDetectorPipelineIndex);
      isCurrentPieceCone =
          () -> Limelight.isConeDetected(FrontConstants.kLimelightNetworkTablesName);
    }

    Commands.parallel(
        new ZeroElevator(elevatorSubsystem),
        new SetArmAngle(armSubsystem, Arm.ArmPreset.GROUND_INTAKE),
        new ConditionalCommand(
            new IntakeCone(intakeSubsystem), new IntakeCube(intakeSubsystem), isCurrentPieceCone));
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.setPipelineIndex(FrontConstants.kLimelightNetworkTablesName, previousPipelineIndex);
  }
}
