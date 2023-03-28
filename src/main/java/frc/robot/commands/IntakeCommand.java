// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;


/** An example command that uses an example subsystem. */
public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  IntakeSubsystem intakeSubsystem;
  CommandPS4Controller controller;
  /**  
   * The default command used by the intake subsystem to get input to decide which way the intake motor turns
   * @param subsystem The single motor intake subsystem to be controlled
   * @param joystick The controller that will
   */
  public IntakeCommand(IntakeSubsystem subsystem, CommandPS4Controller joystick) {
    intakeSubsystem = subsystem;
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
    //take the difference between the two trigger axis and send that to the intake motor
    double moveSpeed = 0;
    /*if(controller.L1().getAsBoolean()){
      moveSpeed += 1;
    }
    if(controller.L2().getAsBoolean()){
      moveSpeed += -1;
    }*/

    moveSpeed = (controller.getL2Axis() + 1)/2 - (controller.getR2Axis() + 1)/2;
    SmartDashboard.putNumber("intake", moveSpeed);
    intakeSubsystem.IntakeDrive(moveSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //intakeSubsystem.IntakeDrive(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //should never end, since the command is default for the intake
    return true;
  }
}
