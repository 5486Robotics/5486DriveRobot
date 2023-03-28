// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class armSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax armMotor;
  private DutyCycleEncoder armEncoder;
  private double kDt = 0.02;
  TrapezoidProfile.Constraints m_constraints =
      new TrapezoidProfile.Constraints(10, 100);
  ProfiledPIDController m_controller =
      new ProfiledPIDController(1.3, 0.0, 0.7, m_constraints, kDt);

  boolean moving = false;
  boolean direction = true;// the direction of the elevator movement, true is up
  //current position
  public int currentPosition = 0; //the current position of the arm
  public int targetPosition = 0;
  public armSubsystem() {
    
    
    //the motors are brushless, very important
    armMotor = new CANSparkMax(Constants.armMotorDeviceID, MotorType.kBrushless);
   
    armMotor.restoreFactoryDefaults();

    armEncoder = new DutyCycleEncoder(Constants.throughboreEncoderPort);
    armEncoder.setDistancePerRotation(360); //just make it think in degrees since we are literally after a rotation
    armEncoder.reset();
  }

  public void MoveLift(boolean dir) {
    if(dir){
      //moving up
      if(targetPosition + 1 <= Constants.elevatorPositions.TOP){
        //have room to move up
        targetPosition++;
      }
    }else{
      //moving down
      if(targetPosition - 1 >= Constants.elevatorPositions.LOW){
        targetPosition--;
      }
    }
  }

  @Override
  public void periodic() {
    /*if(targetPosition > currentPosition){
      //moving up
      if(armEncoder.getDistance() < Constants.armEncoderLocations[targetPosition]){
        armMotor.set(Constants.armSpeed);
      }else{
        armMotor.set(0);
        currentPosition = targetPosition;
      }
      armMotor.set(currentPosition);
    }else if(targetPosition < currentPosition){
      //moving down
      if(armEncoder.getDistance() > Constants.armEncoderLocations[targetPosition]){
        armMotor.set(-Constants.armSpeed);
      }else{
        armMotor.set(0);
        currentPosition = targetPosition;
      }
      
    }*/

    m_controller.setGoal(Constants.armEncoderLocations[targetPosition]);
    armMotor.set(m_controller.calculate(armEncoder.getDistance()));

    //put the arm encoder position on the shuffleboard's smart dashboard
    SmartDashboard.putNumber("Arm Encoder", armEncoder.getDistance());
    SmartDashboard.putNumber("Arm Encoder abs pos", armEncoder.getAbsolutePosition());
    SmartDashboard.putBoolean("Arm Encoder is connected", armEncoder.isConnected());
    SmartDashboard.putNumber("Arm Encoder pos offset", armEncoder.getPositionOffset());
  }
  
  public boolean isMoving(){
    return targetPosition == currentPosition;
  }

  public void controlMotors(double speed){
    armMotor.set(speed);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
