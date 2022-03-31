// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Climb.ClimbRotate;
import frc.robot.commands.Intake.IntakeJoystick;
import frc.robot.commands.zeroing.ZeroIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final IO io = new IO();
  public Drivetrain drivetrain;
  public Climber climber;
  public Intake intake;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    if (Constants.Drivetrain.Enabled) {
      drivetrain = new Drivetrain(io);
      drivetrain.setDefaultCommand(new TankDrive(drivetrain, io));
    }

    if (Constants.Climber.Enabled) {
      climber = new Climber();
      configureClimberBindings();
    }

    if (Constants.Intake.Enabled) {
      intake = new Intake();
      configureIntakeBindings();

      SmartDashboard.putData("Zero Intake", new ZeroIntake(intake));
    }
  }

  private void configureClimberBindings() {
    io.solenoidXboxYButton.whenPressed(new InstantCommand(climber::rotateToMid, climber));
    io.solenoidXboxBButton.whenPressed(new InstantCommand(climber::rotateToHigh, climber));
    io.solenoidXboxAButton.whenPressed(new InstantCommand(climber::rotateToTraversal, climber));
    io.solenoidXboxXButton.whenPressed(new InstantCommand(climber::rotateToRest, climber));
    // io.solenoidLeftShoulderButton.whenPressed(new
    // InstantCommand(climber::toggleLock1, climber));
    // io.solenoidXboxXButton.whenPressed(new InstantCommand(climber::toggleHook1,
    // climber));
    // io.solenoidXboxYButton.whenPressed(new InstantCommand(climber::toggleHook2,
    // climber));

    // io.solenoidRightShoulderButton.whenPressed(new
    // InstantCommand(climber::toggleLock2, climber));
    // io.solenoidXboxAButton.whenPressed(new InstantCommand(climber::toggleHook3,
    // climber));
    // io.solenoidXboxBButton.whenPressed(new InstantCommand(climber::toggleHook4,
    // climber));

    // io.solenoidLeftStick.whenPressed(new InstantCommand(climber::toggleRaise,
    // climber));
  }

  private void configureIntakeBindings() {
    io.solenoidRightShoulderButton.whenHeld(new InstantCommand(intake::moveDown, intake));
    io.solenoidRightShoulderButton.whenReleased(new InstantCommand(intake::moveUp, intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
