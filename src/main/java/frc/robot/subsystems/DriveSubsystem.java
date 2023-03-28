// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax m_leftMotor1;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor1;
  private CANSparkMax m_rightMotor2;
 
  //ADIS16470_IMU gyro;
  
  private DifferentialDrive m_drivetrain;

  double max = 0;
  
  public DriveSubsystem() {
    m_leftMotor1 = new CANSparkMax(Constants.left1DeviceID, MotorType.kBrushed);
    m_leftMotor2 = new CANSparkMax(Constants.left2DeviceID, MotorType.kBrushed);
    m_rightMotor1 = new CANSparkMax(Constants.right1DeviceID, MotorType.kBrushed);
    m_rightMotor2 = new CANSparkMax(Constants.right2DeviceID, MotorType.kBrushed);
    m_leftMotor1.restoreFactoryDefaults();
    m_leftMotor2.restoreFactoryDefaults();
    m_rightMotor1.restoreFactoryDefaults();
    m_rightMotor2.restoreFactoryDefaults();
    m_leftMotor2.follow(m_leftMotor1);
    m_rightMotor2.follow(m_rightMotor1);
    m_leftMotor1.setInverted(true);
    m_drivetrain = new DifferentialDrive(m_leftMotor1, m_rightMotor1);
   // gyro = new ADIS16470_IMU();
    //gyro.calibrate();
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
   /*  SmartDashboard.putNumber("Gyro angle yaw Z", gyro.getAngle());
    SmartDashboard.putNumber("Gyro angle X", gyro.getXComplementaryAngle());
    SmartDashboard.putNumber("Gyro angle Y", gyro.getYComplementaryAngle());
    SmartDashboard.putNumber("Gyro angle Y rate", gyro.getRate());
    SmartDashboard.putNumber("Gyro angle X rate", gyro.getXFilteredAccelAngle());
    SmartDashboard.putNumber("Gyro angle Y rate", gyro.getYFilteredAccelAngle());*/
    SmartDashboard.updateValues();
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
