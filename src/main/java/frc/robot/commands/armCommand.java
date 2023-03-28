// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.armSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

/** An example command that uses an example subsystem. */
public class armCommand extends CommandBase {
 
  armSubsystem armSubsystem;
  CommandPS4Controller controller;

  //direction, true is up, false is down
  boolean direction = true;

  double distanceToTravel = 0;
  public armCommand(armSubsystem subsystem, CommandPS4Controller joystick) {
    armSubsystem = subsystem;
    controller = joystick;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //elevatorSubsystem.MoveArm(direction);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(controller.triangle().getAsBoolean()){
      armSubsystem.MoveLift(true);
    }else if(controller.circle().getAsBoolean()){
      armSubsystem.MoveLift(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return armSubsystem.isMoving();
  }
}
