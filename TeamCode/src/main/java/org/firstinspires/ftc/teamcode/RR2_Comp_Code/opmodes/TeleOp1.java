package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_TeleBot;

@TeleOp(name="tele1", group="Tele1")
public class TeleOp1 extends RR2_TeleBot {

    @Override
    public void run() {
        while(opModeIsActive()){
            //drive
            if (gamepad1.x && atime.seconds() > .5) {
                backwards = !backwards;
                atime.reset();

            }
            if(gamepad1.left_bumper){
                drive.teledrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_trigger*.75, gamepad1.left_trigger*.75);
            }
            else if (backwards){
                drive.teledrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_trigger, gamepad1.left_trigger);

            }
            else{
                drive.teledrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_trigger*.5, gamepad1.left_trigger*.5);

            }
            //arm

            if(gamepad2.a){
                arm.stopusingEC();
            }
            else if(gamepad2.x){
                arm.resetArmEc();
            }
            if(gamepad2.right_trigger>.2){
                arm.runtolander();
            }
            else{
                arm.vin(-gamepad2.right_stick_y);
                arm.hin(-gamepad2.left_stick_y);
            }
            //lift
            lifter.LiftOpss(gamepad2.dpad_up || gamepad1.dpad_up, gamepad2.dpad_down || gamepad1.dpad_down);
            //collecter
            collect.collect(gamepad1.right_stick_y, gamepad1.y,gamepad1.right_bumper);
            collect.drop(gamepad2.right_bumper || gamepad1.right_bumper, gamepad2.right_trigger>.2, !gamepad1.right_bumper);
            //other stuff
            arm.Armtelem();
            sense.sensortelem();
            sense.Tlight(1,1);
            //sense.teammarker(0);

        }
    }
}
