// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class AutoBalanceCommand extends CommandBase {
  DriveSubsystem driveSubsystem;

  ADIS16470_IMU gyro;

  boolean level = false;
  Timer timer;

  /**  
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoBalanceCommand(DriveSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = subsystem;
    gyro = new ADIS16470_IMU();
    gyro.calibrate();
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    gyro.reset();
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(!(gyro.getRate() > 10 || timer.hasElapsed(2.5))){
      driveSubsystem.arcadeDrive(0.5, 0);
    }
    driveSubsystem.arcadeDrive(0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
