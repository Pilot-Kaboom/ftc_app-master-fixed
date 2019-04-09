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
    public boolean ishalf;
    public ElapsedTime ontime = new ElapsedTime();

    public collecter(HardwareMap col){
        intake = col.dcMotor.get("intake");
        dump = col.servo.get("dump");
        dump2 = col.servo.get("dump2");

    }
    public void collect(double in, boolean on, boolean off){

        intake.setPower(-in);
        if(dump.getPosition()<.75 && !ishalf){
            autoflap=true;
        }
        else{
            autoflap=false;
        }
        if(autoflap){
            intake.setPower(-.5);
        }
        else{
            intake.setPower(-in);
        }
    }
    public void dropnear(boolean open, boolean half, boolean closed){
        if(open){
            dump.setPosition(.35);
            dump2.setPosition(.35);
            ontime.reset();
        }
        else{
            dump.setPosition(1);
            dump2.setPosition(0);
        }
        if (half){
            ishalf = true;
        }
        else if(open){
            ishalf = false;
        }
    }
    public void drop(boolean open, boolean half, boolean closed){
        if(open){
            dump.setPosition(.05);
            dump2.setPosition(.95);
            ontime.reset();
        }
        else if (ishalf){
            dump.setPosition(.3);
            dump2.setPosition(.7);
        }
        else if(ontime.seconds()<0){
            dump.setPosition(.5);
            dump2.setPosition(.5);
        }

        else{
            dump.setPosition(1);
            dump2.setPosition(0);
        }
        if (half){
            ishalf = true;
        }
        else if(open){
            ishalf = false;
        }
    }
}
