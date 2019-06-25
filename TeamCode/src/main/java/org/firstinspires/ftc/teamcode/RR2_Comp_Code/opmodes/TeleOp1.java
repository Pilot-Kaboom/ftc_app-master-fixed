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
                if(gamepad1.a || gamepad1.b || gamepad1.y){
                    drive.teledrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger*.5, gamepad1.left_trigger*.5);
                }
                else{
                    drive.teledrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger, gamepad1.left_trigger);
                }
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
            if (backwards){
                /*if (sense.mineraldetect()){
                    mintime.reset();
                }*/
                if(gamepad2.right_trigger>.2|| mintime.seconds()<1.5){
                    arm.runtolander(gamepad1.right_bumper);
                }
                else{
                    if(gamepad2.left_stick_y>.1){
                        arm.autohover(0,gamepad2.right_stick_y);
                    }
                    else{
                        arm.vin(-gamepad2.right_stick_y);
                        arm.hin(-gamepad2.left_stick_y, gamepad1.right_bumper);
                    }

                }

                collect.collect(gamepad1.right_stick_y, gamepad1.right_stick_y>.1 || gamepad1.right_stick_y<-.1,gamepad2.right_trigger>.2);

                collect.drop(gamepad2.right_bumper || gamepad1.right_bumper, gamepad2.right_trigger>.2, !gamepad1.right_bumper);
            }
            else{
                if(gamepad2.right_trigger>.2){
                    arm.runtolandernear(false);
                }
                else {
                    arm.vin(-gamepad2.right_stick_y);
                    arm.hin(-gamepad2.left_stick_y, false);
                }
                collect.collectnear(gamepad1.right_stick_y, gamepad1.right_stick_y>.1 || gamepad1.right_stick_y<-.1,gamepad2.right_trigger>.2);
                collect.dropnear(gamepad2.right_bumper || gamepad1.right_bumper, gamepad2.right_trigger>.2, !gamepad1.right_bumper);
            }

            //lift
            lifter.LiftOpss(gamepad2.dpad_up || gamepad1.dpad_up, gamepad2.dpad_down || gamepad1.dpad_down);
            //collecter


            //other stuff
            arm.Armtelem();
            sense.sensortelem();
            //sense.teammarker(0);

        }
    }
}
