package frc.robot.intake;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorTimeBase;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.drivers.CANDeviceTester;
import frc.robot.drivers.CANTestable;
import frc.robot.drivers.TalonFXFactory;
import frc.robot.intake.commands.GroundIntakeCone;
import frc.robot.intake.commands.GroundIntakeCube;
import frc.robot.logging.Loggable;

import static frc.robot.Constants.ShuffleboardConstants.kDriverTabName;
import static frc.robot.Constants.ShuffleboardConstants.kIntakeLayoutName;
import static frc.robot.Constants.kDebugEnabled;
import static frc.robot.intake.IntakeConstants.*;
import static frc.robot.intake.IntakeConstants.kIntakeMaxCurrent;

public class GroundIntake extends SubsystemBase implements Loggable, CANTestable {

    DutyCycleEncoder CANCoderIntake = new DutyCycleEncoder(5);
    CANCoderConfiguration config = new CANCoderConfiguration();

    private WPI_TalonFX armMotor;
    private WPI_TalonFX intakeMotor;

    public GroundIntake() {
        if (RobotBase.isReal()) {
            configureGroundIntake();
        } else {
            configureGroundSim();
        }
        off();
        System.out.println("Intake initialized");
    }

    private void configureGroundIntake() {
        armMotor = TalonFXFactory.createDefaultTalon(kIntakeCANDevice);
        armMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor = TalonFXFactory.createDefaultTalon(kIntakeCANDevice);
        intakeMotor.setNeutralMode(NeutralMode.Brake);

    }

    private void configureGroundSim() {
        intakeMotor = new WPI_TalonFX(kIntakeMotorID);
        intakeMotor.setNeutralMode(NeutralMode.Brake);
        armMotor = new WPI_TalonFX(kIntakeMotorID);
        armMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void off() {

        intakeMotor.neutralOutput();
        armMotor.neutralOutput();
        System.out.println("Intake off");
    }

    public double getIntakeSpeed() {
        return intakeMotor.getMotorOutputPercent();
    }

    /*
    public void latchCone() {
        if (kDebugEnabled) System.out.println("Latch cone");
        intakeMotor.set(ControlMode.PercentOutput, kLatchConeSpeed);
    }

    public void latchCube() {
        if (kDebugEnabled) System.out.println("Latch Cube");
        intakeMotor.set(ControlMode.PercentOutput, kLatchCubeSpeed);
    }

     */

    public void configureCurrentLimit(boolean enabled) {
        if (kDebugEnabled) System.out.println("Setting Current Limit Configuration: " + enabled);
        intakeMotor.configStatorCurrentLimit(
                new StatorCurrentLimitConfiguration(
                        enabled, kGamePieceMaxCurrent, kIntakeMaxCurrent, kTriggerThresholdTime));
    }

    public void intakeCone() {
        System.out.println("Intake cone");
        intakeMotor.set(ControlMode.PercentOutput, kIntakeConeSpeed);
        armMotor.set(ControlMode.PercentOutput, kIntakeConeSpeed);
    }

    public void intakeCube() {
        System.out.println("Intake cube");
        intakeMotor.set(ControlMode.PercentOutput, kIntakeCubeSpeed);
        armMotor.set(ControlMode.PercentOutput, kIntakeCubeSpeed);
    }

    //:TODO: Do this later for the two motors
    public boolean isCurrentSpiking() {
        return intakeMotor.getStatorCurrent() > kIntakeMaxCurrent ||armMotor.getStatorCurrent() > kIntakeMaxCurrent;

    }



    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake supply current", intakeMotor.getSupplyCurrent());
        SmartDashboard.putNumber("Intake stator current", intakeMotor.getStatorCurrent());
        SmartDashboard.putNumber("Intake supply current", armMotor.getSupplyCurrent());
        SmartDashboard.putNumber("Intake stator current", armMotor.getStatorCurrent());
    }

    public void logInit() {
        getLayout(kDriverTabName).add(this);
        getLayout(kDriverTabName).add(new GroundIntakeCube(this));
        getLayout(kDriverTabName).add(new GroundIntakeCone(this));
        getLayout(kDriverTabName).add(intakeMotor);
        getLayout(kDriverTabName).add(armMotor);
    }

    public ShuffleboardLayout getLayout(String tab) {
        return Shuffleboard.getTab(tab)
                .getLayout(kIntakeLayoutName, BuiltInLayouts.kList)
                .withSize(2, 4);
    }

    public boolean CANTest() {
        String status;
        System.out.println("Testing intake CAN:");
        boolean result1 = CANDeviceTester.testTalonFX(intakeMotor);
        boolean result2 = CANDeviceTester.testTalonFX(armMotor);

        if (result1 && result2) {
            status = "good";
        }
        else if ((!result1 && result2) || (result1 && !result2)) {
            status = "one failed";
        }
        else {
            status = "both test failed";
        }
        System.out.println("Intake CAN connected status: " + status);
        SmartDashboard.putString("Intake CAN connected", status);
        return result1 || result2;
    }

}
