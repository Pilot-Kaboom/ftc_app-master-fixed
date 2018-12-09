package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="LiftTele", group="Tele1")
public class LiftTeleOp extends RR2_LiftBot {

    @Override
    public void run() {
        while(opModeIsActive()){
            drive.RunInPower();
            //drive
            /*drive.goForward(gamepad1.left_stick_y);
            drive.goRight(gamepad1.left_stick_x);
            drive.turn(gamepad1.right_trigger-gamepad1.left_trigger);*/
            drive.teledrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_trigger, gamepad1.left_trigger);
            //arm
            //vert.setPower(gamepad2.right_stick_y);
            //lift
            lifter.LiftOps(gamepad2.left_stick_y);
            //collecter
            //telemetry
            if(gamepad1.x){
                drive.ECtelem();
            }
            else{
                sense.sensortelem();
            }





        }
    }
}
