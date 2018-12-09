package org.firstinspires.ftc.teamcode.Oldish_used_code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.LoopingRevGyro;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.UpdatingManager;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;

/**
 * Created by Keith Harder on 1/16/2018.
 */


public class VuRed2B extends LinearOpMode {


    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    private DcMotor FLM;
    private DcMotor FRM;
    private DcMotor BLM;
    private DcMotor BRM;
    private DcMotor flapper;
    private DcMotor thunker;
    private DcMotor lift;

    private Servo gate;
    private Servo arm;
    private Servo finger;
    private Servo shover;

    private ColorSensor color;
    private OpticalDistanceSensor ods;
    private OpticalDistanceSensor odst;
    private OpticalDistanceSensor odsb;



    private Boolean red;
    private Boolean blue;
    private Boolean left;
    private Boolean right;
    private Boolean center;
    private Boolean block;

    private ElapsedTime time = new ElapsedTime();
    private ElapsedTime ttime = new ElapsedTime();
    private ElapsedTime vtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
//init
        FLM = hardwareMap.dcMotor.get("FLM");
        FRM = hardwareMap.dcMotor.get("FRM");
        BLM = hardwareMap.dcMotor.get("BLM");
        BRM = hardwareMap.dcMotor.get("BRM");
        flapper = hardwareMap.dcMotor.get("flapper");
        thunker = hardwareMap.dcMotor.get("thunker");
        lift = hardwareMap.dcMotor.get("lift");

        gate = hardwareMap.servo.get("gate");
        arm = hardwareMap.servo.get("arm");
        finger = hardwareMap.servo.get("finger");
        shover = hardwareMap.servo.get("shover");

        color = hardwareMap.get(ColorSensor.class, "color");
        color.enableLed(true);
        ods = hardwareMap.opticalDistanceSensor.get("ods");
        odst = hardwareMap.opticalDistanceSensor.get("BlockT");
        odsb = hardwareMap.opticalDistanceSensor.get("odsb");
        red = false;
        blue = false;
        block = false;

        arm.setPosition(.6);


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AXk8m93/////AAAAmSo/yH0caUMsqpjNljWxJF8YX6vQ0htlGYE6tx1nTRyclEBgdBc6yxK8FcCcBaKOJ4vuWvJSuv7t5CMUEA9Zo0NrKut9gg+VZovv+S1NoSnG7aQHhCTaDTlhaZ12MRnRxbkEj72yfSdQhZRbT+MGJ7pBGnvWtOSH/YmeK6bffeICGgdjZz9PoHg8AuWBMvWu6vezXnbAX4IzX1KTmQs8KU2ylFsNRzNhErlnZwDw4vTqRPhk6hhKVOV4iaQup/2CgY7fwblJIV/xKhJMMr7GYi8FPDlYctv4dAONrCPqUOLY0MqxhKf++TqI/kNC+kL8Xa/koNY6Cg+2LT9QEn5J819YnhtcwMzIoHzIRPb/j7NM";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        LoopingRevGyro gyro = new LoopingRevGyro(this.hardwareMap, "imu");

        UpdatingManager updatingManager = new UpdatingManager(this);
        updatingManager.addUpdatable(gyro);
        updatingManager.start();
        relicTrackables.activate();

        telemetry.addData("Z", gyro.getGyroZ(DEGREES));
        telemetry.update();

//init
        waitForStart();



        //gyro.getGyroZ(DEGREES)
        //gyro.resetGyroZ();
        shover.setPosition(1);

        gate.setPosition(.7);
        time.reset();
        while (opModeIsActive() && time.seconds() <.5){

        }
        arm.setPosition(.97);
        finger.setPosition(.25);
        //set Booleans
        time.reset();
        while (opModeIsActive() && time.seconds() <1) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            telemetry.addData("VuMark", "%s visible", vuMark);
            telemetry.update();

            if (color.red() > color.blue()){
                red = true;
                blue = false;
            }
            else if (color.red() < color.blue()){
                blue = true;
                red = false;
            }
            if (vuMark.equals(RelicRecoveryVuMark.RIGHT) ) {
                right = true;
                left = false;
                center = false;
            }
            else if (vuMark.equals(RelicRecoveryVuMark.CENTER) ) {
                right = false;
                left = false;
                center = true;
            }
            else if (vuMark.equals(RelicRecoveryVuMark.LEFT) ) {
                right = false;
                left = true;
                center = false;
            }
            else {
                right = false;
                left = false;
                center = true;
            }


        }
        //get jewel
        time.reset();
        while (opModeIsActive() && time.seconds() <1 && red){
            /*FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);*/
            finger.setPosition(.0);

        }

        time.reset();
        while (opModeIsActive() && time.seconds() <1 && blue){
            /*FRM.setPower(1);
            FLM.setPower(1);
            BRM.setPower(-1);
            BLM.setPower(-1);*/
            finger.setPosition(0.6);

        }
        finger.setPosition(.4);
        arm.setPosition(.6);
        //drive off stone
        time.reset();
        while (opModeIsActive() && time.seconds() <.675){
            FRM.setPower(-1);
            FLM.setPower(-1);
            BRM.setPower(1);
            BLM.setPower(1);
        }
        //turn to alingment with cryptobox
        time.reset();
        while (opModeIsActive() && time.seconds() <1.6){
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.update();
            if(gyro.getGyroZ(DEGREES) < 85){
                FRM.setPower(.5);
                FLM.setPower(.5);
                BRM.setPower(.5);
                BLM.setPower(.5);
            }
            else if(gyro.getGyroZ(DEGREES) > 87){
                FRM.setPower(-.2);
                FLM.setPower(-.2);
                BRM.setPower(-.2);
                BLM.setPower(-.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }
        }
        //drive to cryptokey
        time.reset();
        while (opModeIsActive() && time.seconds() <.45 && center){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.2 && right){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.65 && left){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        FRM.setPower(0);
        FLM.setPower(0);
        BRM.setPower(0);
        BLM.setPower(0);
        //dump block {
        time.reset();
        while (opModeIsActive() && time.seconds() <1){
            thunker.setPower(-.33);
            gate.setPosition(1);

        }
        thunker.setPower(0);
        time.reset();
        while (opModeIsActive() && time.seconds() <.5){
            FLM.setPower(1);
            FRM.setPower(-1);
            BLM.setPower(1);
            BRM.setPower(-1);
            thunker.setPower(0);

        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.5){
            FLM.setPower(-.5);
            FRM.setPower(.5);
            BLM.setPower(-.5);
            BRM.setPower(.5);

        }
        FRM.setPower(0);
        FLM.setPower(0);
        BRM.setPower(0);
        BLM.setPower(0);
        //}
        //drive past stone
        time.reset();
        while (opModeIsActive() && time.seconds() <.515 && center){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.8 && right){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.3 && left){
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);
        }
        //turn to alingment with tape
        time.reset();
        while (opModeIsActive() && time.seconds() <2){
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.update();
            if(gyro.getGyroZ(DEGREES) >5){
                FRM.setPower(-.5);
                FLM.setPower(-.5);
                BRM.setPower(-.5);
                BLM.setPower(-.5);
            }
            else if(gyro.getGyroZ(DEGREES) < -3){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }


        }
        //drive to pile
        time.reset();
        while (opModeIsActive() && time.seconds() <1.5){
            FRM.setPower(.5);
            FLM.setPower(.5);
            BRM.setPower(-.5);
            BLM.setPower(-.5);
            thunker.setPower(.33);
            gate.setPosition(0);

        }
        //get blocks
        time.reset();
        while (opModeIsActive() && time.seconds() <1.5){
            shover.setPosition(.7);

            lift.setPower(0);
            if(gyro.getGyroZ(DEGREES) <43){
                FRM.setPower(.5);
                FLM.setPower(.5);
                BRM.setPower(.5);
                BLM.setPower(.5);
            }
            else if(gyro.getGyroZ(DEGREES) > 47){
                FRM.setPower(-.2);
                FLM.setPower(-.2);
                BRM.setPower(-.2);
                BLM.setPower(-.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }
            flapper.setPower(-1);

        }

        thunker.setPower(0);




        lift.setPower(0);
        time.reset();
        while (opModeIsActive() && time.seconds() < .5){
            if (block){
                flapper.setPower(0);

            }

            FLM.setPower(-.4);
            FRM.setPower(.5);
            BLM.setPower(-.4);
            BRM.setPower(.5);
            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                block=true;

            }
        }
        shover.setPosition(1);
        /*
        time.reset();
        while (opModeIsActive() && time.seconds() <3){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();
            if(block){
                flapper.setPower(0);

            }
            if(gyro.getGyroZ(DEGREES) > 2){
                FRM.setPower(-.3);
                FLM.setPower(-.3);
                BRM.setPower(-.3);
                BLM.setPower(-.3);
            }
            else if(gyro.getGyroZ(DEGREES) < -1){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }

            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6 && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }*/
        time.reset();
        while (opModeIsActive() && time.seconds() < .9){
            FLM.setPower(1);
            FRM.setPower(-1);
            BLM.setPower(1);
            BRM.setPower(-1);
            shover.setPosition(1);

        }
        /*
        time.reset();
        while (opModeIsActive() && time.seconds() <1.5){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();
            if(block){
                flapper.setPower(0);

            }
            if(gyro.getGyroZ(DEGREES) > 2){
                FRM.setPower(-.8);
                FLM.setPower(-.8);
                BRM.setPower(-.8);
                BLM.setPower(-.8);
            }
            else if(gyro.getGyroZ(DEGREES) < -1){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }

            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6 && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }*/


        time.reset();

        while (opModeIsActive() && time.seconds() < .75){
            FLM.setPower(-.75);
            FRM.setPower(1);
            BLM.setPower(-.75);
            BRM.setPower(1);
            flapper.setPower(-1);
            shover.setPosition(1);

        }
        time.reset();
        while (opModeIsActive()&& time.seconds() < .5){
            FLM.setPower(.51);
            FRM.setPower(-.51);
            BLM.setPower(.51);
            BRM.setPower(-.51);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .75){
            if (block){
                flapper.setPower(0);

            }


            FLM.setPower(-.75);
            FRM.setPower(1);
            BLM.setPower(-.75);
            BRM.setPower(1);
            shover.setPosition(1);
            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                block = true;

            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .5){
            if (block){
                flapper.setPower(0);

            }

            FLM.setPower(.25);
            FRM.setPower(-.25);
            BLM.setPower(.25);
            BRM.setPower(-.25);
            shover.setPosition(1);
            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                block=true;

            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .5){
            FLM.setPower(-.75);
            FRM.setPower(1);
            BLM.setPower(-.75);
            BRM.setPower(1);
            shover.setPosition(1);

        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .75){
            FLM.setPower(.25);
            FRM.setPower(-.25);
            BLM.setPower(.25);
            BRM.setPower(-.25);
            shover.setPosition(1);

        }
        time.reset();
        while (opModeIsActive() && time.seconds() <3.5){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.update();

            if (block){
                flapper.setPower(0);

            }
            if(gyro.getGyroZ(DEGREES) > -181.5){
                FRM.setPower(-.6);
                FLM.setPower(-.6);
                BRM.setPower(-.6);
                BLM.setPower(-.6);
            }
            else if(gyro.getGyroZ(DEGREES) < -182){
                FRM.setPower(.24);
                FLM.setPower(.24);
                BRM.setPower(.24);
                BLM.setPower(.24);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }

            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                block = true;

            }
        }

        /*
        //This is where the robot is in the pile collecting

        time.reset();
        while (opModeIsActive() && time.seconds() <1){

            lift.setPower(0);
            if(gyro.getGyroZ(DEGREES) <43){
                FRM.setPower(.5);
                FLM.setPower(.5);
                BRM.setPower(.5);
                BLM.setPower(.5);
            }
            else if(gyro.getGyroZ(DEGREES) > 48){
                FRM.setPower(-.2);
                FLM.setPower(-.2);
                BRM.setPower(-.2);
                BLM.setPower(-.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }
            flapper.setPower(-1);

        }

        thunker.setPower(0);
        lift.setPower(0);
        gate.setPosition(0);


        lift.setPower(0);
        time.reset();
        while (opModeIsActive() && time.seconds() <.35){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();

            FRM.setPower(1);
            FLM.setPower(1);
            BRM.setPower(-1);
            BLM.setPower(-1);
            flapper.setPower(-1);
            if(block){
                flapper.setPower(0);

            }
            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6 && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .3){
            FLM.setPower(-.5);
            FRM.setPower(.5);
            BLM.setPower(-.5);
            BRM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <1.8){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();
            FRM.setPower(-.21);
            FLM.setPower(-.21);
            BRM.setPower(.21);
            BLM.setPower(.21);

            if(block){
                flapper.setPower(0);

            }
            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6 && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <1){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();

            if(gyro.getGyroZ(DEGREES) > 2){
                FRM.setPower(-.9);
                FLM.setPower(-.9);
                BRM.setPower(-.9);
                BLM.setPower(-.9);
            }
            else if(gyro.getGyroZ(DEGREES) < -1){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }
            if(block){
                flapper.setPower(0);

            }
            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6 && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .5){
            /*FLM.setPower(-.5);
            FRM.setPower(.5);
            BLM.setPower(-.5);
            BRM.setPower(.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() < .5){
            /*FLM.setPower(.5);
            FRM.setPower(-.5);
            BLM.setPower(.5);
            BRM.setPower(-.5);
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <2.5){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();

            if(gyro.getGyroZ(DEGREES) > -179){
                FRM.setPower(-.5);
                FLM.setPower(-.5);
                BRM.setPower(-.5);
                BLM.setPower(-.5);
            }
            else if(gyro.getGyroZ(DEGREES) < -181){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }
            if(block){
                flapper.setPower(0);

            }
            if(ods.getLightDetected()>.6 || odst.getLightDetected()>.6){
                ttime.reset();
                if(ods.getLightDetected() > .6  && ttime.seconds() > .75){
                    block = true;
                }
                if (odst.getLightDetected() > .6 ){
                    block=true;
                }
            }
        }

        */
        time.reset();
        while (opModeIsActive() && time.seconds() <2.5){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();

            FRM.setPower(.5);
            FLM.setPower(.5);
            BRM.setPower(-.5);
            BLM.setPower(-.5);

            flapper.setPower(0);

        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.5){
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.update();
            if (block){
                flapper.setPower(0);

            }
            FRM.setPower(-.5);
            FLM.setPower(-.5);
            BRM.setPower(.5);
            BLM.setPower(.5);

            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                block = true;

            }

        }
        time.reset();
        while (opModeIsActive() && time.seconds() <2){
            telemetry.addData("odst", odst.getLightDetected() );
            telemetry.addData("Z", gyro.getGyroZ(DEGREES));
            telemetry.update();

            if(gyro.getGyroZ(DEGREES) > -268){
                FRM.setPower(-.5);
                FLM.setPower(-.5);
                BRM.setPower(-.5);
                BLM.setPower(-.5);
            }
            else if(gyro.getGyroZ(DEGREES) < -272){
                FRM.setPower(.2);
                FLM.setPower(.2);
                BRM.setPower(.2);
                BLM.setPower(.2);
            }
            else{
                FRM.setPower(0);
                FLM.setPower(0);
                BRM.setPower(0);
                BLM.setPower(0);
            }

            if(ods.getLightDetected()>.6 && (odst.getLightDetected() > .6 || odsb.getLightDetected() > .6)){
                flapper.setPower(0);

            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.65 && left){
            FRM.setPower(.5);
            FLM.setPower(.5);
            BRM.setPower(-.5);
            BLM.setPower(-.5);
            flapper.setPower(-1);

            if ( gamepad2.right_bumper && vtime.seconds()> .05){
                thunker.setPower(.5);
                vtime.reset();
            }
            else if (gamepad2.right_bumper && vtime.seconds() < .05){
                thunker.setPower(-.5);
            }
        }

        time.reset();
        while (opModeIsActive() && time.seconds() <.33 && !left){
            FRM.setPower(.5);
            FLM.setPower(.5);
            BRM.setPower(-.5);
            BLM.setPower(-.5);
            flapper.setPower(-1);
            if ( gamepad2.right_bumper && vtime.seconds()> .05){
                thunker.setPower(.5);
                vtime.reset();
            }
            else if (gamepad2.right_bumper && vtime.seconds() < .05){
                thunker.setPower(-.5);
            }
        }

        FRM.setPower(0);
        FLM.setPower(0);
        BRM.setPower(0);
        BLM.setPower(0);
        time.reset();
        while (opModeIsActive() && time.seconds() <1){
            flapper.setPower(-1);


            gate.setPosition(.7);


            if ( gamepad2.right_bumper && time.seconds()> .05){
                thunker.setPower(.5);
                vtime.reset();
            }
            else if (gamepad2.right_bumper && time.seconds() < .05){
                thunker.setPower(-.5);
            }
        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.5){
            thunker.setPower(-.33);




        }
        thunker.setPower(0);
        flapper.setPower(0);

        time.reset();
        while (opModeIsActive() && time.seconds() <.5){
            FLM.setPower(1);
            FRM.setPower(-1);
            BLM.setPower(1);
            BRM.setPower(-1);
            thunker.setPower(-.1);
            finger.setPosition(0);


        }
        time.reset();
        while (opModeIsActive() && time.seconds() <.4){
            FLM.setPower(-.5);
            FRM.setPower(.5);
            BLM.setPower(-.5);
            BRM.setPower(.5);


        }
        FRM.setPower(0);
        FLM.setPower(0);
        BRM.setPower(0);
        BLM.setPower(0);
    }

}
