// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoBalanceCommand;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.armTest;
import frc.robot.commands.elevatorTest;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.armSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem eleSubsystem = new ElevatorSubsystem();
  private final armSubsystem m_armSubsystem = new armSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  //private final LEDSubsystem ledSubsystem;

  private final AutoDriveCommand m_autoCommand; 
  //private final AutoBalanceCommand m_autoBalCommand; 

  CommandPS4Controller driverControl;
  CommandPS4Controller opControl;

  /** The container for the robot. Contains subsystems, IO devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    driverControl = new CommandPS4Controller(0);
    opControl = new CommandPS4Controller(1);
    m_DriveSubsystem.setDefaultCommand(new DriveCommand(m_DriveSubsystem, driverControl));
    m_armSubsystem.setDefaultCommand(new armTest(m_armSubsystem, opControl));
    eleSubsystem.setDefaultCommand(new elevatorTest(eleSubsystem, opControl));
    intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem, opControl));

    m_autoCommand = new AutoDriveCommand(m_DriveSubsystem);
    //m_autoBalCommand = new AutoBalanceCommand(m_DriveSubsystem);
    //ledSubsystem = new LEDSubsystem();
    configureButtonBindings();
    //CameraServer.startAutomaticCapture();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*opControl.square().onTrue(new ElevatorCommand(eleSubsystem, opControl));
    opControl.cross().onTrue(new ElevatorCommand(eleSubsystem, opControl));
    opControl.b().onTrue(new ElevatorCommand(eleSubsystem, false));
    opControl.x().onTrue(new armCommand(armSubsystem, true));
    opControl.y().onTrue(new armCommand(armSubsystem, false));*/
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
