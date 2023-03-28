// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.armSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;



public class armTest extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  armSubsystem armSubsystem;
  CommandPS4Controller controller;
  /**  
   * The default command used by the intake subsystem to get input to decide which way the intake motor turns
   * @param subsystem The single motor intake subsystem to be controlled
   * @param joystick The controller that will
   */
  public armTest(armSubsystem subsystem, CommandPS4Controller joystick) {
    armSubsystem = subsystem;
    controller = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = controller.getRightY();
    if(Math.abs(moveSpeed) <= 0.1){
      moveSpeed = 0;
    }
    armSubsystem.controlMotors(moveSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //should never end, since the command is default for the arm when set to be used
    return true;
  }
}
