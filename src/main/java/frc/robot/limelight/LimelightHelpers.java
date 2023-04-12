// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.limelight;

// Copyright (c) 2023 FRC 3256
// https://github.com/Team3256
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

import com.fasterxml.jackson.annotation.JsonProperty;

class LimelightTarget_Retro {

  @JsonProperty("t6c_ts")
  double[] cameraPose_TargetSpace;

  @JsonProperty("t6r_fs")
  double[] robotPose_FieldSpace;

  @JsonProperty("t6r_ts")
  double[] robotPose_TargetSpace;

  @JsonProperty("t6t_cs")
  double[] targetPose_CameraSpace;

  @JsonProperty("t6t_rs")
  double[] targetPose_RobotSpace;

  @JsonProperty("ta")
  double ta;

  @JsonProperty("tx")
  double tx;

  @JsonProperty("txp")
  double tx_pixels;

  @JsonProperty("ty")
  double ty;

  @JsonProperty("typ")
  double ty_pixels;

  @JsonProperty("ts")
  double ts;

  LimelightTarget_Retro() {
    cameraPose_TargetSpace = new double[6];
    robotPose_FieldSpace = new double[6];
    robotPose_TargetSpace = new double[6];
    targetPose_CameraSpace = new double[6];
    targetPose_RobotSpace = new double[6];
  }
}

class LimelightTarget_Fiducial {

  @JsonProperty("fID")
  double fiducialID;

  @JsonProperty("fam")
  String fiducialFamily;

  @JsonProperty("t6c_ts")
  double[] cameraPose_TargetSpace;

  @JsonProperty("t6r_fs")
  double[] robotPose_FieldSpace;

  @JsonProperty("t6r_ts")
  double[] robotPose_TargetSpace;

  @JsonProperty("t6t_cs")
  double[] targetPose_CameraSpace;

  @JsonProperty("t6t_rs")
  double[] targetPose_RobotSpace;

  @JsonProperty("ta")
  double ta;

  @JsonProperty("tx")
  double tx;

  @JsonProperty("txp")
  double tx_pixels;

  @JsonProperty("ty")
  double ty;

  @JsonProperty("typ")
  double ty_pixels;

  @JsonProperty("ts")
  double ts;

  LimelightTarget_Fiducial() {
    cameraPose_TargetSpace = new double[6];
    robotPose_FieldSpace = new double[6];
    robotPose_TargetSpace = new double[6];
    targetPose_CameraSpace = new double[6];
    targetPose_RobotSpace = new double[6];
  }
}

class LimelightTarget_Barcode {}

class LimelightTarget_Classifier {

  @JsonProperty("class")
  String className;

  @JsonProperty("classID")
  double classID;

  @JsonProperty("conf")
  double confidence;

  @JsonProperty("zone")
  double zone;

  @JsonProperty("tx")
  double tx;

  @JsonProperty("txp")
  double tx_pixels;

  @JsonProperty("ty")
  double ty;

  @JsonProperty("typ")
  double ty_pixels;

  LimelightTarget_Classifier() {}
}

