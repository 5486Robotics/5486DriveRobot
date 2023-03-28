// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.Queue;

import edu.wpi.first.wpilibj.DigitalOutput;

public class LEDSubsystem extends SubsystemBase {

  private final DigitalOutput m_LEDoutput;

  private  DigitalOutput arduinoLEDoutput0;
  private  DigitalOutput arduinoLEDoutput1;

  public enum colors{
    YELLOW, PURPLE, RED;
  }

  Queue<colors> commands;

  //concurrent variables
  boolean canExecute = true; //its possible to run a command to the LEDS
  
  colors previousColor; //the color to blink in between the red flashes
  public LEDSubsystem() {
    if(Constants.useArduino){
      arduinoLEDoutput0 = new DigitalOutput(Constants.arduinoControlPin0);
      arduinoLEDoutput1 = new DigitalOutput(Constants.arduinoControlPin1);
      arduinoLEDControl(colors.YELLOW); //start up with yellow
    }else{
      m_LEDoutput = new DigitalOutput(Constants.ledControlPin);
      writeCommand(0xFF02FD); // turn on the LEDS
      commands.add(colors.YELLOW);
      commands.add(colors.PURPLE);
      commands.add(colors.YELLOW);
    } 
  }

  public void setLEDYellow() {
    commands.add(colors.YELLOW);
  }

  public void setLEDPurple() {
    commands.add(colors.PURPLE);
  }

  public void blinkLEDRed() {
    commands.add(colors.RED);
    commands.add(previousColor);
    commands.add(colors.RED);
    commands.add(previousColor);
    commands.add(colors.RED);
    commands.add(previousColor);
  }
  
  @Override
  public void periodic() {
    if(canExecute){//no thread is running, we are good to execute next LED command
      canExecute = false;
      if(!commands.isEmpty()){
        colors c = commands.remove();
        if(c == colors.YELLOW){
          writeCommand(0xFF2AD5);
          previousColor = colors.YELLOW;
        }else if(c == colors.PURPLE){
          writeCommand(0xFFB24D);
          previousColor = colors.PURPLE;
        }else {
          writeCommand(0xFF1AE5);
        }
      }
    }
  }

  @Override
  public void simulationPeriodic() {
   //simulating LEDS lol
  }

  void writeCommand(long command)
  //*****************************************************************************
  // This function will send a 32bits command the same under the format
  // that the IR receiver expects it.
  //
  //
  //*****************************************************************************
  //	OFF 	  0xFF827D
  //	ON 	    0xFF02FD
  //  yellow	  0xFF2AD5
  //  purple    0xFFB24D
  //  red       0xFF1AE5
  {

    new Thread() {
      public void run() {
          m_LEDoutput.set(false);
          try {
            Thread.sleep(9, 200000);//sleep for 9200 microseconds, or 9 milliseconds and 200000 nanoseconds
            m_LEDoutput.set(true);
            Thread.sleep(4, 500000); //sleep for 4500 microseconds, or 4 milliseconds and 500000 nanoseconds
            writeByte((byte)(command >> 24));
            writeByte((byte)(command >> 16));
            writeByte((byte)(command >> 8));
            writeByte((byte)(command));
            m_LEDoutput.set(false);
            Thread.sleep(0, 550000); //sleep for 550 microseconds, or 0 milliseconds and 550000 nanoseconds
            m_LEDoutput.set(true);
            if(command == 0xFF1AE5){
              //if red, wait extra long before executing next command because we are blinking
              Thread.sleep(500); //sleep for half a second before allowing us to switch colors again
            }
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          canExecute = true; //good to execute next color command
      }
    }.start();
  }

  void writeLogical1(){
    m_LEDoutput.set(false);
    try {
      m_LEDoutput.set(false);
      Thread.sleep(0, 550000);  //sleep for 550 microseconds, or 0 milliseconds and 550000 nanoseconds
      m_LEDoutput.set(true);
      Thread.sleep(1, 690000); //sleep for 1690 microseconds, or 1 milliseconds and 690000 nanoseconds
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void writeLogical0(){
    m_LEDoutput.set(false);
    try {
      m_LEDoutput.set(false);
      Thread.sleep(0, 550000);  //sleep for 550 microseconds, or 0 milliseconds and 550000 nanoseconds
      m_LEDoutput.set(true);
      Thread.sleep(0, 550000);  //sleep for 550 microseconds, or 0 milliseconds and 550000 nanoseconds
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void writeByte(byte byte_to_send)
  {
    for (int i=0; i<8; i++) 
    { 
      if (((byte_to_send << i) & 0x80) == 0x80)
      {
        writeLogical1(); 
      }
      else
      {
        writeLogical0(); 
      }
    }
  }

  void arduinoLEDControl(colors c){
    if(Constants.useArduino){
      if(c == colors.YELLOW){
        arduinoLEDoutput0.set(false);
        arduinoLEDoutput1.set(false);
      }else if(c == colors.PURPLE){
        arduinoLEDoutput0.set(false);
        arduinoLEDoutput1.set(true);
      }else {
        arduinoLEDoutput0.set(true);
      }
    }
  }
}
