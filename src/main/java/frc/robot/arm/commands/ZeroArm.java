// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.arm.commands;

import static frc.robot.arm.ArmConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.arm.Arm;
import frc.robot.arm.Arm.ArmPreset;
import frc.robot.arm.ArmConstants;

public class ZeroArm extends CommandBase {
  private Arm armSubsystem;

  /**
   * Constructor for zeroing the encoder
   *
   * @param armSubsystem
   */
  public ZeroArm(Arm armSubsystem) {
    addRequirements(armSubsystem);
  }

  @Override
  public void initialize() {
    //run CW
    armSubsystem.setInputVoltage(kZeroArmVoltage);
  }

  @Override
  public void end(boolean interrupted) {
    //reset encoder
    if (!interrupted) armSubsystem.zeroThroughboreEncoder();
  }

  @Override
  public boolean isFinished() {
    return armSubsystem.getMotorStatorCurrent() > kZeroArmMaxStatorCurrent;
  }
}
