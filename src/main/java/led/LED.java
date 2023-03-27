package led;

import com.ctre.phoenix.led.CANdle;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.logging.Loggable;

public class LED extends SubsystemBase implements Loggable{
    private final CANdle LEDstrip;
    private int currentR;
    private int currentG;
    private int currentB;
    public LED(CANdle LEDstrip) {
        this.LEDstrip = LEDstrip;
    }
    public void setLEDstrip(int r, int g, int b) {
        LEDstrip.setLEDs(r, g, b);
        currentR = r;
        currentG = g;
        currentB = b;
    }
    public int[] getLEDstripColor() {
        int[] RGBList = {currentR, currentG, currentB};
        return RGBList;
    }
    public void toggleGamePiece() {

    }
    public void periodic() {
        LEDstrip.setLEDs(currentR, currentG, currentB);
    }
    @Override
    public void logInit() {

    }
    @Override
    public ShuffleboardLayout getLayout(String tab) {
        return null;
    }
}
