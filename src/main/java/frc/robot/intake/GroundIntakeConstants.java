package frc.robot.intake;

import frc.robot.drivers.CanDeviceId;

import static frc.robot.intake.IntakeConstants.kIntakeMotorID;

public class GroundIntakeConstants {

    public static final int kIntakeMotorID = 4;

    public static final String kIntakeCANBus = "mani";
    public static final CanDeviceId kIntakeCANDevice = new CanDeviceId(kIntakeMotorID, kIntakeCANBus);

    public static final double kIntakeConeSpeed = 0;
    public static final double kIntakeCubeSpeed = 0;
    public static final double kLatchConeSpeed = 0;
    public static final double kLatchCubeSpeed = 0;
    public static final double kGamePieceMaxCurrent = 0;
    public static final double kGroundIntakeMaxCurrent = 0;
    public static final double kTriggerThresholdTime = 0;
}
