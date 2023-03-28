// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class ElevatorCommand extends CommandBase {
 
  ElevatorSubsystem elevatorSubsystem;
  CommandPS4Controller controller;
 
  /**  
   * The command activated by a button pressed to move the elevator
   * @param subsystem The single motor intake subsystem to be controlled
   * @param joystick The controller that will give the button press to raise or lower the elevator
   */
  public ElevatorCommand(ElevatorSubsystem subsystem, CommandPS4Controller joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    elevatorSubsystem = subsystem;
    controller = joystick;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(controller.square().getAsBoolean()){
      elevatorSubsystem.MoveLift(true);
    }else if(controller.cross().getAsBoolean()){
      elevatorSubsystem.MoveLift(false);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevatorSubsystem.isMoving();
  }
}
