// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax eleMotorLeft;
  private CANSparkMax eleMotorRight;

  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  boolean  direction = true;// the direction of the elevator movement, true is up
  //current position
  public int currentPosition = 0; //the robot starts at the bottom: 0 has Mid:1 and Top:2
  public int targetPosition = 0;

  private static double kDt = 0.02;
  TrapezoidProfile.Constraints m_constraints =
      new TrapezoidProfile.Constraints(10, 100);
  ProfiledPIDController m_controller =
      new ProfiledPIDController(1.3, 0.0, 0.7, m_constraints, kDt);

  public ElevatorSubsystem() {
    
    //the motors are brushless, very important
    eleMotorLeft = new CANSparkMax(Constants.eleLeftDeviceID, MotorType.kBrushless);
    eleMotorRight = new CANSparkMax(Constants.eleRightDeviceID, MotorType.kBrushless);

    rightEncoder = eleMotorRight.getEncoder();
    leftEncoder = eleMotorLeft.getEncoder();
    eleMotorLeft.restoreFactoryDefaults();
    eleMotorRight.restoreFactoryDefaults();

    eleMotorRight.follow(eleMotorLeft, true);
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

  public void controlMotors(double speed){
    double pos = (leftEncoder.getPosition() + rightEncoder.getPosition())/2;
    if(Math.abs(pos) < 200 && speed > 0){
      eleMotorLeft.set(speed);
    }else if(pos >= 0 && speed < 0){
      eleMotorLeft.set(speed);
    }else{
      eleMotorLeft.set(0);
    }
   
  }

  public boolean isMoving(){
    return targetPosition == currentPosition;
  }
  
  @Override
  public void periodic() {
    double pos = ((leftEncoder.getPosition() + rightEncoder.getPosition())/2);
    /*if(targetPosition > currentPosition){
      //moving up
      if(pos < Constants.elevatorEncoderLocations[targetPosition]){
        eleMotorLeft.set(Constants.elevatorSpeed);
      }else{
        eleMotorLeft.set(0);
        currentPosition = targetPosition;
      }
      eleMotorLeft.set(currentPosition);
    }else if(targetPosition < currentPosition){
      //moving down
      if(pos > Constants.elevatorEncoderLocations[targetPosition]){
        eleMotorLeft.set(-Constants.elevatorSpeed);
      }else{
        eleMotorLeft.set(0);
        currentPosition = targetPosition;
      }
    }

    m_controller.setGoal(Constants.elevatorEncoderLocations[targetPosition]);
    eleMotorLeft.set(m_controller.calculate(pos));
*/
    //put the arm encoder position on the shuffleboard's smart dashboard
    SmartDashboard.putNumber("Left Elevator Encoder", leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Elevator Encoder", rightEncoder.getPosition());
    SmartDashboard.putNumber("Averaged Elevator Encoder", pos);
    SmartDashboard.updateValues();
  }
  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
