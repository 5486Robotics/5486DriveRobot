// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  private WPI_VictorSPX intakeMotor;
  
  public IntakeSubsystem() {
    intakeMotor = new WPI_VictorSPX(Constants.IntakeMotorDeviceID);
  }

  public void IntakeDrive(double speed) { //direction is true for intake, false for outtake
    intakeMotor.set(speed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
