// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.UnitTestBase;
import frc.robot.swerve.SwerveDrive;
import frc.robot.swerve.commands.TeleopSwerve;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class SwerveTests extends UnitTestBase {
  public final double DELTA = 0.05;
  private static SwerveDrive swerveDrive;

  @BeforeAll
  public static void setup() {
    UnitTestBase.setup();
    swerveDrive = new SwerveDrive();
  }

  @Test
  public void testSetSpeed() {
    Command command = new TeleopSwerve(swerveDrive, () -> 1, () -> 0, () -> 0, true, true);
    runScheduler(5, command, swerveDrive);

    double velocity = swerveDrive.getVelocity();
    System.out.println(velocity);
    assertEquals(1, velocity, DELTA, "Setting velocity to 1");
  }
  @Test
  public void testTurn180() {
    Command command = new TeleopSwerve(swerveDrive, ()-> 0, ()-> 0, ()-> Math.PI, true, true);
    runScheduler(5, command, swerveDrive);

    double angle = swerveDrive.getPitch().getRadians();
    System.out.println(angle);
    assertEquals(Math.PI, angle, DELTA, "Testing rotation of robot to 180deg (PI radians)");
  }

}
