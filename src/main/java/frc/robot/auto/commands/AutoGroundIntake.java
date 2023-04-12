// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.auto.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.intake.commands.GroundIntake;
import frc.robot.limelight.LimelightResults;
import frc.robot.limelight.*;
import frc.robot.swerve.SwerveDrive;

public class AutoGroundIntake extends CommandBase {

  private GroundIntake groundIntake;
  private SwerveDrive swerveDrive;

  public AutoGroundIntake(GroundIntake groundIntakeSubsystem, SwerveDrive swerveDriveSubsystem) {
    this.groundIntake = groundIntakeSubsystem;
    this.swerveDrive = swerveDriveSubsystem;
  }

  @Override
  public void initialize() {
    // Initialize the GroundIntake command
    groundIntake.initialize();

  }
  @Override
  public void execute(){
    if (getAngleToTarget() < 0){
      swerveDrive.drive(new Translation2d(-1, 0), 0, false, true);
    } else {
      swerveDrive.drive(new Translation2d(1, 0), 0, false, true);
    }
    if (getDistanceToTarget() < 0){
      swerveDrive.drive(new Translation2d(0, 1), 0, false, true);
    }
    groundIntake.execute();
  }
  @Override
  public boolean isFinished() {
    // I don't think this logic is entirely correct, and this might
    if (getAngleToTarget() < Constants.VisionConstants.FrontConstants.kMinAngleToTargetDegrees){
      return false;
    }
    if (getDistanceToTarget() < Constants.VisionConstants.FrontConstants.kMinDistanceToTargetInches){
      return false;
    }
    return true;
  }
  public static double getAngleToTarget(){
    // Get the angle to the target from the Limelight
    LimelightResults latestResults = Limelight.getLatestResults(Constants.VisionConstants.FrontConstants.kLimelightNetworkTablesName);
    if (latestResults.targetingResults.targets_Detector.length == 0){
      return -1;
    }

    double angleToTarget = latestResults.targetingResults.targets_Detector[0].tx;
    return angleToTarget;
  }
  public static double getDistanceToTarget(){
    // Get the distance to the target from the Limelight
    LimelightResults latestResults = Limelight.getLatestResults(Constants.VisionConstants.FrontConstants.kLimelightNetworkTablesName);
    if (latestResults.targetingResults.targets_Detector.length == 0){
      return -1;
    }
    double angleToTarget = latestResults.targetingResults.targets_Detector[0].ty;
    double targetHeightFromGround = 0; // TODO: Get the height of the target from the ground: this is Most Likely Always 0 inches - this is a Ground Intake.

    double angleToGoalDegrees = Constants.VisionConstants.FrontConstants.kLimelightMountAngleDegrees + angleToTarget;
    double angleToGoalRadians = Units.degreesToRadians(angleToGoalDegrees);

    double distanceFromLimelightToGoalInches = (targetHeightFromGround - Constants.VisionConstants.FrontConstants.kLimelightLensHeightInches) / Math.tan(angleToGoalRadians);
    return distanceFromLimelightToGoalInches;
  }
}
