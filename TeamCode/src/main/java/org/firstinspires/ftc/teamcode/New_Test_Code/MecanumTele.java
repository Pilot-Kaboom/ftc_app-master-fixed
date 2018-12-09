package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Keith Harder on 8/13/2018.
 */
//@TeleOp(name="MecanumTele", group="Tele1")
public class MecanumTele extends mecanumBotHard {
    @Override
    public void run() {
        while (opModeIsActive()){
            telem();
            if(time.seconds()<2){
                RunInpower();
                idle();
            }

            if(gamepad1.dpad_up){
                goForward(1);
            }
            else if(gamepad1.dpad_down){
                goForward(-1);
            }
            else if (gamepad1.dpad_left){
                goRight(-1);
            }
            else if (gamepad1.dpad_right){
                goRight(1);
            }
            else if(gamepad1.left_trigger>.1){
                turn(gamepad1.left_trigger*.5);
            }
            else if(gamepad1.right_trigger>.1){
                turn(-gamepad1.right_trigger*.5);
            }
            else{
                StopMotors(0);
            }
            intake.setPower(gamepad2.left_stick_y);

            if(gamepad2.right_bumper){
                outtake.setPosition(.5);
            }
            else {
                outtake.setPosition(0);
            }


            Arm.setPower(gamepad2.right_stick_y);


            lights.setPosition(light);
            light = pos;

            if (gamepad1.right_bumper && atime.seconds() > .5){
                pos = pos+.01;
                atime.reset();

            }
            else if (gamepad1.left_bumper && atime.seconds() > .5){
                pos = pos-.01;
                atime.reset();

            }
        }


    }
}
