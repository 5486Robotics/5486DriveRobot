// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

 //wiring pins
 

public final class Constants {
    public static final int ledControlPin = 1;
    public static final int arduinoControlPin0 = 1;
    public static final int arduinoControlPin1 = 2;
    public static final boolean useArduino = false;

    public static final int throughboreEncoderPort = 0;

    public static final int left1DeviceID = 3; 
    public static final int left2DeviceID = 4; 
    public static final int right1DeviceID = 1;
    public static final int right2DeviceID = 2;

    public static final int eleLeftDeviceID = 6; 
    public static final int eleRightDeviceID = 5;

    public static final int armMotorDeviceID = 7;
    public static final int IntakeMotorDeviceID = 10;

    public static final class elevatorPositions{
        public static int TOP = 2;
        public static int MID = 1;
        public static int LOW = 0;
    }

    public static final int[]  elevatorEncoderLocations = {0, 1260, 2520};

    public static final double[]  armEncoderLocations = {0, 0.17};

    public static final double elevatorSpeed = 0.12;
    public static final double armSpeed = 0.12;
    public static final double intakeSpeed = 0.75;
    public static final double outtakeSpeed = -0.12;


    public static int pieceMode = 0; //0 is cone mode, 1 is cube mode
}
