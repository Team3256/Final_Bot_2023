// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.elevator.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.elevator.Elevator;

public class SetEndEffectorState extends ParallelCommandGroup {
  public enum EndEffectorPreset {
    SCORE_CONE_HIGH(Elevator.ElevatorPreset.CONE_HIGH),
    SCORE_CONE_MID(Elevator.ElevatorPreset.ANY_PIECE_MID),
    SCORE_ANY_LOW(Elevator.ElevatorPreset.ANY_PIECE_LOW),
    DOUBLE_SUBSTATION_CONE(Elevator.ElevatorPreset.DOUBLE_SUBSTATION_CONE),
    STOW_CONE(Elevator.ElevatorPreset.STOW_CONE),
    SCORE_CUBE_HIGH(Elevator.ElevatorPreset.CUBE_HIGH),
    SCORE_CUBE_MID(Elevator.ElevatorPreset.ANY_PIECE_MID),
    DOUBLE_SUBSTATION_CUBE(Elevator.ElevatorPreset.DOUBLE_SUBSTATION_CUBE),
    STOW_CUBE(Elevator.ElevatorPreset.STOW_CUBE);

    public final Elevator.ElevatorPreset elevatorPreset;

    EndEffectorPreset(Elevator.ElevatorPreset elevatorPreset) {
      this.elevatorPreset = elevatorPreset;
    }
  }

  public SetEndEffectorState(Elevator elevatorSubsystem, EndEffectorPreset endEffectorPreset) {
    addCommands(new SetElevatorExtension(elevatorSubsystem, endEffectorPreset.elevatorPreset));
  }

  public SetEndEffectorState(
      Elevator elevatorSubsystem, double elevatorExtension, Rotation2d armAngle) {
    addCommands(new SetElevatorExtension(elevatorSubsystem, elevatorExtension));
  }
}
