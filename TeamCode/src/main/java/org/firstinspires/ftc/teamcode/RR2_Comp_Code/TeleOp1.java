package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="tele1", group="Tele1")
public class TeleOp1 extends RR2_TeleBot {

    @Override
    public void run() {
        while(opModeIsActive()){
            //drive
            if(gamepad1.left_bumper){
                drive.teledrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_trigger*.5, gamepad1.left_trigger*.5);
            }
            else{
                drive.teledrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_trigger*.5, gamepad1.left_trigger*.5);

            }
            //arm
            arm.hin(-gamepad2.left_stick_y+(gamepad2.left_trigger*.75)-(gamepad2.right_trigger*.4), sense.bucketDis(), gamepad2.left_bumper);
            arm.vin(-gamepad2.right_stick_y-(gamepad2.left_trigger*.35)+(gamepad2.right_trigger));
            //arm.VposSet(gamepad2.right_stick_y,!gamepad2.a);
            //arm.HposSet(gamepad2.left_stick_y,!gamepad2.b,gamepad2.right_bumper);
            if(gamepad2.a){
                arm.stopusingEC();
            }
            //lift
            lifter.LiftOpss(gamepad2.dpad_up, gamepad2.dpad_down);
            //collecter
            collect.collect(-gamepad1.right_stick_y);
            collect.drop(gamepad2.right_bumper || gamepad1.right_bumper, gamepad2.left_bumper);
            //other stuff
            sense.sensortelem();

        }
    }
}
