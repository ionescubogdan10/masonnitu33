package org.firstinspires.ftc.teamcode.pedro.procedures;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class servoExemplu {

    public Servo servo33;
    public  servoExemplu(HardwareMap hardwareMap){
        servo33=hardwareMap.get(Servo.class,"servo33");
    }
    public void misca(Gamepad gamepad1){
        if(gamepad1.a){
            servo33.setPosition(0.0);
        }
        if(gamepad1.b){
            servo33.setPosition(0.5);
        }

    }
}
