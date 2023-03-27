package led.commands;

import com.ctre.phoenix.led.CANdle;
import led.LED;

public class setCone extends LED {
    private CANdle candle;
    public setCone(CANdle candle) {
        super(candle);
        this.candle = candle;
        candle.setLEDStrip()
    }
}
