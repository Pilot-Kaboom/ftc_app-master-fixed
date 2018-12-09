package org.firstinspires.ftc.teamcode.New_Test_Code;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Keith Harder on 7/27/2018.
 */

public abstract class mecanumBotHard extends superSuperClass {

    public DcMotor BRM;
    public DcMotor FLM;
    public DcMotor BLM;
    public DcMotor FRM;
    public DcMotor Arm;
    public DcMotor intake;

    public Servo lights;
    //public CRServo intake;
    public Servo outtake;

    //public OpticalDistanceSensor ODS;


    public double FEC;
    public double BEC;
    public double REC;
    public double LEC;
    public double percm;
    public double perc;
    public double tic;
    public double light;
    public double pos;


    @Override
    public void initiate() {
        FLM = hardwareMap.dcMotor.get("flm");
        BLM = hardwareMap.dcMotor.get("blm");
        FRM = hardwareMap.dcMotor.get("frm");
        BRM = hardwareMap.dcMotor.get("brm");
        Arm = hardwareMap.dcMotor.get("arm");
        //ODS = hardwareMap.opticalDistanceSensor.get("ods");

        lights = hardwareMap.servo.get("lights");
        //intake = hardwareMap.crservo.get("intake");
        intake = hardwareMap.dcMotor.get("intake");
        outtake = hardwareMap.servo.get("outtake");

        while(BRM.getCurrentPosition() != 0){
            BRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        BRM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position
        //*/
        while(FLM.getCurrentPosition() != 0){
            FLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        FLM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position

        while(BLM.getCurrentPosition() != 0){
            BLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        BLM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position

        while(FRM.getCurrentPosition() != 0){
            FRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        FLM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position

    }

    //for traditional mecanum setup, use noted public voids.  needs work.
    //  you should really just redo the configuration file.
    /*
    public void goForward(double power){
        FLM.setPower(-power);
        FRM.setPower(power);
        BLM.setPower(-power);
        BRM.setPower(power);
    }
    public void goBackward(double power){
        FLM.setPower(power);
        FRM.setPower(-power);
        BLM.setPower(power);
        BRM.setPower(-power);
    }
    public void goRight(double power){
        FLM.setPower(-power);
        FRM.setPower(-power);
        BLM.setPower(power);
        BRM.setPower(power);
    }
    public void goLeft(double power){
        FLM.setPower(power);
        FRM.setPower(power);
        BLM.setPower(-power);
        BRM.setPower(-power);
    }
     */
    public void goForward(double power){
        FLM.setPower(-power);
        FRM.setPower(power);
        BLM.setPower(-power);
        BRM.setPower(power);
    }/*
    public void goBackward(double power){
        FLM.setPower(power);
        FRM.setPower(-power);
        BLM.setPower(power);
        BRM.setPower(-power);
    }*/
    public void goRight(double power){
        FLM.setPower(-power);
        FRM.setPower(-power);
        BLM.setPower(power);
        BRM.setPower(power);
    }
    public void turn(double power){
        FLM.setPower(power);
        FRM.setPower(power);
        BLM.setPower(power);
        BRM.setPower(power);
    }
    public void StopMotors(double stop){
        FLM.setPower(stop);
        FRM.setPower(stop);
        BLM.setPower(stop);
        BRM.setPower(stop);
    }
    public void ticks(){
        FEC = FRM.getCurrentPosition()/4 + BRM.getCurrentPosition()/4 + -FLM.getCurrentPosition()/4 +  -BLM.getCurrentPosition() / 4;
        BEC = FLM.getCurrentPosition()/4 +  BLM.getCurrentPosition()/4 + -FRM.getCurrentPosition()/4 + -BRM.getCurrentPosition() / 4;
        REC = -FLM.getCurrentPosition()/4 +  BLM.getCurrentPosition()/4 + -FRM.getCurrentPosition()/4 + BRM.getCurrentPosition() / 4;
        LEC = FLM.getCurrentPosition()/4 +  -BLM.getCurrentPosition()/4 + FRM.getCurrentPosition()/4 + -BRM.getCurrentPosition() / 4;
    }
    public void ResetEC(){
        FLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void RunInEC(){
        FLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void RunInpower(){
        FLM.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        BLM.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        BRM.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        FRM.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }
    public void telem(){
        telemetry.addData("FEC",FEC);
        telemetry.addData("BEC",BEC);
        telemetry.addData("REC",REC);
        telemetry.addData("LEC",LEC);
        telemetry.addData("FLM", FLM.getCurrentPosition());
        telemetry.addData("BLM", BLM.getCurrentPosition());
        telemetry.addData("BRM", BRM.getCurrentPosition());
        telemetry.addData("FRM", FRM.getCurrentPosition());
        //telemetry.addData("ods", ODS.getLightDetected());
        telemetry.addData("color", lights.getPosition());
        telemetry.update();
    }
    public void spdUpF(double dis){

        percm = dis /100;
        perc = dis/percm;
        tic = FEC/percm;

        if(tic <15){
            goForward(.15);
        }
        else if(tic <35){
            goForward(.25);
        }
        else if(tic < 65){
            goForward(.5);
        }
        else if (tic <100){
            goForward(.85);
        }
    }
}
