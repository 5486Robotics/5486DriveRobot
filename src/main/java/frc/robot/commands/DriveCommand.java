// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  DriveSubsystem driveSubsystem;
  CommandPS4Controller controller;
  
  /**  
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveSubsystem subsystem, CommandPS4Controller joystick) {
    //pass the reference to the drive subsystem into this command
    driveSubsystem = subsystem;
    //we want to pass a reference to the Joystick down because we want to delay reading joystick values until as close to execution as possible
    controller = joystick;
    // Use addRequirements() here to declare subsystem dependencies so that the command structure knows that we need 
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = controller.getLeftY();
    if(Math.abs(moveSpeed) <= 0.1){
      moveSpeed = 0;
    }
    double rotateSpeed = -controller.getRightX();
    if(Math.abs(rotateSpeed) <= 0.1 ){
      rotateSpeed = 0;
    }
    driveSubsystem.arcadeDrive(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //driveSubsystem.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
