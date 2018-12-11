package org.firstinspires.ftc.teamcode.Oldish_used_code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;


public class TeleOp4 extends LinearOpMode{
    private DcMotor FLM;
    private DcMotor FRM;
    private DcMotor BLM;
    private DcMotor BRM;
    private DcMotor flapper;
    private DcMotor thunker;
    private DcMotor lift;
    //private DcMotor relic;
    private Servo gate;
    private Servo arm;
    private Servo finger;
    private Servo shover;
    // private Servo claw;
    // private Servo Rarm;
    private boolean right;
    private boolean left;
    private boolean open;
    private boolean out;
    private boolean in;
    private boolean back;
    private ElapsedTime time = new ElapsedTime();
    private ElapsedTime ctime = new ElapsedTime();
    private ElapsedTime atime = new ElapsedTime();
    private double pos;

    @Override
    public void runOpMode() throws InterruptedException {
        FLM = hardwareMap.dcMotor.get("FLM");
        FRM = hardwareMap.dcMotor.get("FRM");
        BLM = hardwareMap.dcMotor.get("BLM");
        BRM = hardwareMap.dcMotor.get("BRM");
        flapper = hardwareMap.dcMotor.get("flapper");
        thunker = hardwareMap.dcMotor.get("thunker");
        lift = hardwareMap.dcMotor.get("lift");
        //relic = hardwareMap.dcMotor.get("relic");
        gate = hardwareMap.servo.get("gate");
        arm = hardwareMap.servo.get("arm");
        finger = hardwareMap.servo.get("finger");
        shover = hardwareMap.servo.get("shover");

        LoopingRevGyro gyro= new LoopingRevGyro(this.hardwareMap, "imu");

        UpdatingManager updatingManager = new UpdatingManager(this);
        updatingManager.addUpdatable(gyro);
        updatingManager.start();
        //claw = hardwareMap.servo.get("claw");
        //Rarm = hardwareMap.servo.get("Rarm");
        //FRM.setDirection(DcMotor.Direction.REVERSE);
        //FLM.setDirection(DcMotor.Direction.REVERSE);
        //BRM.setDirection(DcMotor.Direction.REVERSE);
        //BLM.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();



        while(opModeIsActive()){
            arm.setPosition(.65);
            finger.setPosition(.4);
            //claw.setPosition(.5);

            //from here until otherwise stated, the code is for gamepad 2
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.update();

            flapper.setPower(-gamepad2.left_stick_y);
            /*
            if (gamepad2.right_bumper && !open && ctime.seconds() >1){
            open = true;
            ctime.reset();

            }
            else if (gamepad2.right_bumper && open && ctime.seconds() >1){
            open = false;
            ctime.reset();

            }

            if(open){
            claw.setPosition(1);

            }

            else if(!open){
                claw.setPosition(.5);

            }*/
            /*
            if (gamepad2.right_trigger > .5 && atime.seconds() > .5){
                pos = pos+.1;
                atime.reset();

            }
            else if (gamepad2.left_trigger > .5 && atime.seconds() > .5){
                pos = pos-.1;
                atime.reset();

            }

            Rarm.setPosition(pos);
            */

            if ( gamepad2.right_bumper && time.seconds()> .05){
                thunker.setPower(.5);
                time.reset();
            }
            else if (gamepad2.right_bumper && time.seconds() < .05){
                thunker.setPower(-.5);
            }
            else if(gamepad2.dpad_up){
                thunker.setPower(-.33);

            }
            else if(gamepad2.dpad_down){
                thunker.setPower(.33);

            }
            else {
                thunker.setPower(0);

            }


            double liftSpeed = gamepad2.right_stick_y;
            if(gamepad2.left_bumper){
                liftSpeed += -.17;
            }
            lift.setPower(liftSpeed);

            if (gamepad2.dpad_up ){
                gate.setPosition (.6);

            }


            else if (gamepad2.dpad_down){
                gate.setPosition(.145);

            }




            //from here on the code is for gamepad 1
            /*if (gamepad1.y && !right && time.seconds() >1){
                right = true;
                time.reset();
                left = false;
            }
            else if (gamepad1.y && right && time.seconds() >1){
                right = false;
                left = false;
                time.reset();

            }
            if (gamepad1.x && !left && time.seconds() >1){
                left = true;
                time.reset();
                right = false;
            }
            else if (gamepad1.x && left && time.seconds() >1){
                right = false;
                left = false;
                time.reset();

            }*/

            if(gyro.getGyroZ(DEGREES)< 45 && gyro.getGyroZ(DEGREES) > -45){
                right = false;
                left = false;
                back = false;
            }
            if(gyro.getGyroZ(DEGREES)< 135 && gyro.getGyroZ(DEGREES) > 45){
                right = false;
                left = true;
                back = false;
            }
            if(gyro.getGyroZ(DEGREES)< -225 && gyro.getGyroZ(DEGREES) > -270){
                right = false;
                left = true;
                back = false;
            }
            if(gyro.getGyroZ(DEGREES)> -135 && gyro.getGyroZ(DEGREES) < -45){
                right = true;
                left = false;
                back = false;
            }
            if(gyro.getGyroZ(DEGREES)> 225 && gyro.getGyroZ(DEGREES) < 270){
                right = true;
                left = false;
                back = false;
            }
            if(gyro.getGyroZ(DEGREES)< -135 && gyro.getGyroZ(DEGREES) > -180){
                right = false;
                left = false;
                back = true;
            }
            else if(gyro.getGyroZ(DEGREES)> 135 && gyro.getGyroZ(DEGREES) < 180){
                right = false;
                left = false;
                back = true;
            }
            if(gamepad1.right_bumper || gyro.getGyroZ(DEGREES) > 360 || gyro.getGyroZ(DEGREES) < -360){
                gyro.resetGyroZ();
            }
            /*
            if (gamepad1.right_bumper){
                relic. setPower(.5);

            }
            else if (gamepad1.left_bumper){
                relic. setPower(-.5);

            }
            else {
                relic. setPower(0);

            }
            */
            /*
            else if (gamepad1.y) {
                right = !right;
                left = false;
            }
            else if (gamepad1.x) {
                right = false;
                left = !left;
            }
            */

            if (gamepad1.x){
                shover.setPosition(.7);
            }
            else {
                shover.setPosition(.875);

            }

            if (right){
                FLM.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger * .5));
                FRM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BRM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BLM.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));

            }
            else if (left){
                FLM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                FRM.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BRM.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BLM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));

            }
            else if (back){
                FLM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                FRM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BRM.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BLM.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));

            }
            else {
                FLM.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                FRM.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + -gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BRM.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x +(-gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));
                BLM.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x +(gamepad1.right_stick_y*.35 + gamepad1.right_stick_x*.35)+ (gamepad1.left_trigger + -gamepad1.right_trigger* .5));

            }

        }


    }
}
