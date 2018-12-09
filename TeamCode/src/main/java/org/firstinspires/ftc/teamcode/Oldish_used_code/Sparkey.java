package org.firstinspires.ftc.teamcode.Oldish_used_code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Keith Harder on 10/9/2018.
 */

public class Sparkey extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    private Servo tail;

    @Override
    public void runOpMode() {
        right=hardwareMap.dcMotor.get("right");
        left=hardwareMap.dcMotor.get("left");
        tail=hardwareMap.servo.get("tail");

        waitForStart();

        while (opModeIsActive()){
            left.setPower(-gamepad1.left_stick_y);
            right.setPower(gamepad1.right_stick_y);
            if (gamepad1.right_bumper){
                tail.setPosition(1);
            }
            else if (gamepad1.left_bumper){
                tail.setPosition(0);
            }
            else{
                tail.setPosition(.5);
            }
        }
    }
}
