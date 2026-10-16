package org.firstinspires.ftc.teamcode.pedro.procedures;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="program principal")
public class controllere extends OpMode {
    private mecanumdrive sasiu;
    private servoExemplu echercompas;

    public void init(){
        sasiu=new mecanumdrive(hardwareMap);
        echercompas= new servoExemplu(hardwareMap);
    }
    @Override
    public void loop(){
        sasiu.mutautilajul(gamepad1);
        echercompas.misca(gamepad1);
    }
}