package frc.robot.swerve.helpers;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.Sendable;

public interface SwerveModuleImpl {

    int moduleNumber = -1;

    SwerveModulePosition getPosition();

    void resetToAbsolute();

    void setDesiredState(SwerveModuleState swerveModuleState, boolean isOpenLoop);

    void setDesiredAngleState(SwerveModuleState swerveModuleState);

    Sendable getAngleEncoder();

    void logInit();

    void setDriveMotorNeutralMode(NeutralMode neutralMode);

    boolean test();

    Rotation2d getCanCoder();
    
}
