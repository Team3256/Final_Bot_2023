package frc.robot.limelight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LimelightTarget_Detector {

    @JsonProperty("class")
    String className;

    @JsonProperty("classID")
    double classID;

    @JsonProperty("conf")
    double confidence;

    @JsonProperty("ta")
    double ta;

    @JsonProperty("tx")
    double tx;

    @JsonProperty("txp")
    double tx_pixels;

    @JsonProperty("ty")
    public
    double ty;

    @JsonProperty("typ")
    double ty_pixels;

    LimelightTarget_Detector() {
    }
}
