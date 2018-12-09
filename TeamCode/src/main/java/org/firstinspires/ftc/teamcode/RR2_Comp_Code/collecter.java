package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class collecter {
    private final DcMotor intake;
    private final Servo dump;

    public collecter(HardwareMap col){
        intake = col.dcMotor.get("intake");
        dump = col.servo.get("dump");

    }
    public void collect(double in){
        intake.setPower(in);
    }
    public void drop(boolean open, boolean closed){
        if(open){
            dump.setPosition(0);
        }
        else{
            dump.setPosition(.6);
        }
    }
}
