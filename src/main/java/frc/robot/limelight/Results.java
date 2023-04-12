package frc.robot.limelight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Results {

    @JsonProperty("pID")
    double pipelineID;

    @JsonProperty("tl")
    double latency_pipeline;

    @JsonProperty("tl_cap")
    double latency_capture;

    double latency_jsonParse;

    @JsonProperty("ts")
    double timestamp_LIMELIGHT_publish;

    @JsonProperty("ts_rio")
    double timestamp_RIOFPGA_capture;

    @JsonProperty("v")
    double valid;

    @JsonProperty("botpose")
    double[] botpose;

    @JsonProperty("botpose_wpired")
    double[] botpose_wpired;

    @JsonProperty("botpose_wpiblue")
    double[] botpose_wpiblue;

    @JsonProperty("Retro")
    LimelightTarget_Retro[] targets_Retro;

    @JsonProperty("Fiducial")
    LimelightTarget_Fiducial[] targets_Fiducials;

    @JsonProperty("Classifier")
    LimelightTarget_Classifier[] targets_Classifier;

    @JsonProperty("Detector")
    public
    LimelightTarget_Detector[] targets_Detector;

    @JsonProperty("Barcode")
    LimelightTarget_Barcode[] targets_Barcode;

    Results() {
        botpose = new double[6];
        botpose_wpired = new double[6];
        botpose_wpiblue = new double[6];
        targets_Retro = new LimelightTarget_Retro[0];
        targets_Fiducials = new LimelightTarget_Fiducial[0];
        targets_Classifier = new LimelightTarget_Classifier[0];
        targets_Detector = new LimelightTarget_Detector[0];
        targets_Barcode = new LimelightTarget_Barcode[0];
    }
}
