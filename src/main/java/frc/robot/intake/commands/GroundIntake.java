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
import frc.robot.elevator.Elevator;
import frc.robot.elevator.commands.ZeroElevator;
import frc.robot.helpers.ParentCommand;
import frc.robot.intake.Intake;
import frc.robot.limelight.Limelight;
import java.util.function.BooleanSupplier;

public class GroundIntake extends ParentCommand {

  private Elevator elevatorSubsystem;
  private Intake intakeSubsystem;

  private BooleanSupplier isCurrentPieceCone;

  public GroundIntake(
      Elevator elevatorSubsystem,
      Intake intakeSubsystem,
      BooleanSupplier isCurrentPieceCone) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.intakeSubsystem = intakeSubsystem;
    this.isCurrentPieceCone = isCurrentPieceCone;
  }

  @Override
  public void initialize() {
    if (kGamePieceDetection) {
      Limelight.setPipelineIndex(
          FrontConstants.kLimelightNetworkTablesName, kDetectorPipelineIndex);
      isCurrentPieceCone =
          () -> Limelight.isConeDetected(FrontConstants.kLimelightNetworkTablesName);
    }


    super.initialize();
  }

  @Override
  public void end(boolean interrupted) {
    Limelight.setPipelineIndex(FrontConstants.kLimelightNetworkTablesName, kDefaultPipeline);
    super.end(interrupted);
  }
}
