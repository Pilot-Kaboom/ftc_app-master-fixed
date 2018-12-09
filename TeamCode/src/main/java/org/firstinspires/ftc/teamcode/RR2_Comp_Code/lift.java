package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class lift {
    private final DcMotor winch;
    //private final TouchSensor uplimit;
    //private final TouchSensor downlimit;

    public lift(HardwareMap lif){
        winch = lif.dcMotor.get("winch");
        //uplimit = lif.touchSensor.get("uplimit");
        //downlimit = lif.touchSensor.get("downlimit");
    }

    public void LiftOps(double up){
        /*if (uplimit.isPressed()){
            winch.setPower(0);
        }
        else if (downlimit.isPressed()){
            winch.setPower(0);
        }
        else{*/
        winch.setPower(up);

        //b}
    }
    public void LiftOpss(boolean uup, boolean down){
        /*if (uplimit.isPressed()){
            winch.setPower(0);
        }
        else if (downlimit.isPressed()){
            winch.setPower(0);
        }
        else{*/

        if(uup){
            winch.setPower(-.75);
        }
        else if(down){
            winch.setPower(.75);
        }
        else{
            winch.setPower(0);
        }
        //}
    }

}
