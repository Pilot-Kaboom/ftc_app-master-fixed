package org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class collecter {
    private final DcMotor intake;
    private final Servo dump;
    private final Servo dump2;
    public boolean autoflap;
    public ElapsedTime ontime = new ElapsedTime();

    public collecter(HardwareMap col){
        intake = col.dcMotor.get("intake");
        dump = col.servo.get("dump");
        dump2 = col.servo.get("dump2");

    }
    public void collect(double in, boolean on, boolean off){

        //intake.setPower(-in);
        if(on){
            autoflap=true;

        }
        else if(off){
            autoflap=false;


        }
        if(autoflap){
            intake.setPower(-1);
        }
        else{
            intake.setPower(-in);
        }
    }
    public void drop(boolean open, boolean closed){
        if(open){
            dump.setPosition(.4);
            dump2.setPosition(.6);
        }
        else{
            dump.setPosition(1);
            dump2.setPosition(0);
        }
    }
}
