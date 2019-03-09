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

        //intake.setPower(-in);
        if(dump.getPosition()<.75 && !ishalf){
            autoflap=true;
        }
        else{
            autoflap=false;
        }
        if(autoflap){
            intake.setPower(.75);
        }
        else{
            intake.setPower(-in);
        }
    }
    public void drop(boolean open, boolean half, boolean closed){
        if(open){
            dump.setPosition(0);
            dump2.setPosition(1);
            ontime.reset();
        }
        else if (ishalf){
            dump.setPosition(.2);
            dump2.setPosition(.8);
        }
        else if(ontime.seconds()<1){
            dump.setPosition(.6);
            dump2.setPosition(.4);
        }

        else{
            dump.setPosition(.8);
            dump2.setPosition(.2);
        }
        if (half){
            ishalf = true;
        }
        else if(open){
            ishalf = false;
        }
    }
}
