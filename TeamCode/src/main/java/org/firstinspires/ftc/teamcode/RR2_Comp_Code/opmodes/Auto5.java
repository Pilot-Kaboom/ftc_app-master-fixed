package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Oldish_used_code.LoopingRevGyro;
import org.firstinspires.ftc.teamcode.Oldish_used_code.UpdatingManager;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_AutoBot;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Auto5", group="Auto1")
public class Auto5 extends RR2_AutoBot {

    @Override
    public void run() {
        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();



        telemetry.update();

        drive.resetEC();
        //drop

        while (opModeIsActive() && time.seconds() < 2.5){
            lifter.LiftOps(1);

        }
        lifter.LiftOps(0);
        drive.resetEC();
        //move off of hook
        while(opModeIsActive() && drive.bect() <250 ){
            drive.goForward(-.5);
        }
        time.reset();
        while(opModeIsActive() && time.seconds()< .25+landwait){
            drive.StopMotors(0);
        }
        //drive to minerals
        while(opModeIsActive() && sense.backD() < 12){
            drive.goRight(-.65);
            sense.sensortelem();

        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.LEFT) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on left, saw L", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(135,3)){
                    drive.teledrive(0,0,0,gyro.imuP(135,1/20));
            }
            while (opModeIsActive() && arm.Hec()>-1000 && arm.Vec()<3000){
                arm.runToPos(3000,-1000);
            }
        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.CENTER) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on Center, saw C", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(90,3)){
                drive.teledrive(0,0,0,gyro.imuP(90,1/20));
            }
            while (opModeIsActive() && arm.Hec()>-1000 && arm.Vec()<3000){
                arm.runToPos(3000,-1000);
            }
        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.RIGHT) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on right, saw R", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(45,3)){
                drive.teledrive(0,0,0,gyro.imuP(45,1/20));
            }
            while (opModeIsActive() && arm.Hec()>-1000 && arm.Vec()<3000){
                arm.runToPos(3000,-1000);
            }

        }
        time.reset();
        while(opModeIsActive() && time.seconds()<1.5){
            collect.collect(1,false,false);
        }
        while (opModeIsActive() && !gyro.cutout(90,3)){

        }
    }

}
