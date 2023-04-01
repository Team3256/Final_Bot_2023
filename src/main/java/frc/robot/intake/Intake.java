// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.intake;

import static frc.robot.Constants.ShuffleboardConstants.kDriverTabName;
import static frc.robot.Constants.ShuffleboardConstants.kIntakeLayoutName;
import static frc.robot.Constants.kDebugEnabled;
import static frc.robot.intake.IntakeConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.drivers.CANDeviceTester;
import frc.robot.drivers.CANTestable;
import frc.robot.intake.commands.GroundIntakeCone;
import frc.robot.intake.commands.GroundIntakeCube;
import frc.robot.logging.Loggable;

public class Intake extends SubsystemBase implements Loggable, CANTestable {
  private WPI_TalonFX intakeMotor;





  public double getIntakeSpeed() {
    return intakeMotor.getMotorOutputPercent();
  }

  public void latchCone() {
    if (kDebugEnabled) System.out.println("Latch cone");
    intakeMotor.set(ControlMode.PercentOutput, kLatchConeSpeed);
  }

  public void latchCube() {
    if (kDebugEnabled) System.out.println("Latch Cube");
    intakeMotor.set(ControlMode.PercentOutput, kLatchCubeSpeed);
  }

  public void configureCurrentLimit(boolean enabled) {
    if (kDebugEnabled) System.out.println("Setting Current Limit Configuration: " + enabled);
    intakeMotor.configStatorCurrentLimit(
        new StatorCurrentLimitConfiguration(
            enabled, kGamePieceMaxCurrent, kIntakeMaxCurrent, kTriggerThresholdTime));
  }

  public void intakeCone() {
    System.out.println("Intake cone");
    intakeMotor.set(ControlMode.PercentOutput, kIntakeConeSpeed);
  }

  public void intakeCube() {
    System.out.println("Intake cube");
    intakeMotor.set(ControlMode.PercentOutput, kIntakeCubeSpeed);
  }

  public boolean isCurrentSpiking() {
    return intakeMotor.getStatorCurrent() > kIntakeMaxCurrent;
  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake supply current", intakeMotor.getSupplyCurrent());
    SmartDashboard.putNumber("Intake stator current", intakeMotor.getStatorCurrent());
  }

  public void logInit() {
    getLayout(kDriverTabName).add(this);
    //getLayout(kDriverTabName).add(new GroundIntakeCube(this));
    //getLayout(kDriverTabName).add(new GroundIntakeCone(this));
    getLayout(kDriverTabName).add(intakeMotor);
  }

  public ShuffleboardLayout getLayout(String tab) {
    return Shuffleboard.getTab(tab)
        .getLayout(kIntakeLayoutName, BuiltInLayouts.kList)
        .withSize(2, 4);
  }

  public boolean CANTest() {
    System.out.println("Testing intake CAN:");
    boolean result = CANDeviceTester.testTalonFX(intakeMotor);
    System.out.println("Intake CAN connected: " + result);
    SmartDashboard.putBoolean("Intake CAN connected", result);
    return result;
  }
}
