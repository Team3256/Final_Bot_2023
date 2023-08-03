// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.elevator.commands;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import frc.robot.elevator.Elevator;
import java.util.function.BooleanSupplier;

public class StowEndEffector extends ConditionalCommand {
  public StowEndEffector(Elevator elevatorSubsystem, BooleanSupplier isCurrentPieceCone) {
    super(
        new SetEndEffectorState(elevatorSubsystem, SetEndEffectorState.EndEffectorPreset.STOW_CONE),
        new SetEndEffectorState(elevatorSubsystem, SetEndEffectorState.EndEffectorPreset.STOW_CUBE),
        isCurrentPieceCone);
  }
}
