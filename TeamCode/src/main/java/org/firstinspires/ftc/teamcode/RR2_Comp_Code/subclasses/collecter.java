package org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class collecter {
    private final DcMotor intake;
    private final Servo dump;
    private final Servo dump2;

    public collecter(HardwareMap col){
        intake = col.dcMotor.get("intake");
        dump = col.servo.get("dump");
        dump2 = col.servo.get("dump2");

    }
    public void collect(double in){
        intake.setPower(-in);
    }
    public void drop(boolean open, boolean closed){
        if(open){
            dump.setPosition(.5);
            dump2.setPosition(.5);
        }
        else{
            dump.setPosition(1);
            dump2.setPosition(0);
        }
    }
}
