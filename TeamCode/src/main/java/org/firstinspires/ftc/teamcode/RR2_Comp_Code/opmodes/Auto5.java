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
        drive.StopMotors(0);
        time.reset();
        while(opModeIsActive() && time.seconds()< .25+landwait){
            drive.StopMotors(0);
            arm.hin(0,true);
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
            while (opModeIsActive() && !gyro.cutout(135,8)){
                    drive.teledrive(0,0,0,gyro.imuP(135,.025));
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-1000 && arm.Vec()>-3000){
                arm.runToPos(-3000,-1000);
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
            while (opModeIsActive() && !gyro.cutout(90,8)){
                drive.teledrive(0,0,0,gyro.imuP(90,.025));
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-1000 && arm.Vec()>-1200){
                arm.runToPos(-1200,-1000);
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
            while (opModeIsActive() && !gyro.cutout(45,8)){
                drive.teledrive(0,0,0,gyro.imuP(45,.025));
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-2000 && arm.Vec()>-3000){
                arm.runToPos(-3000,-1000);
            }

        }
        time.reset();
        while(opModeIsActive() && time.seconds()<1.5){
            collect.collect(1,false,false);
        }
        time.reset();
        while (opModeIsActive() && !gyro.cutout(90,8) && arm.Vec()>-4000 && arm.Hec()>-2000){
            while(opModeIsActive() &&time.seconds()<.5){
                arm.runToPos(-4000,-2000);
            }
            while (opModeIsActive() &&time.seconds()>= .5){
                drive.teledrive(0,0,0,gyro.imuP(90,.025));
                arm.runToPos(-4000,-2000);
            }
            drive.StopMotors(0);
        }
        drive.StopMotors(0);
        time.reset();
        while (opModeIsActive() && time.seconds()<.5){
            collect.dropnear(true,false,false);
        }
        collect.dropnear(false,false,false);
        time.reset();
        while(opModeIsActive() && time.seconds()<2.5){
            while(opModeIsActive() && arm.Hec()<1500){
                arm.runtolander(false);
                collect.drop(false,true,false);
            }
            while(opModeIsActive() && arm.Hec()>=1500){
                arm.runtolander(false);
                collect.drop(true,false,false);
            }
        }
        while(opModeIsActive() && !gyro.cutout(225,8)){
            arm.runToPos(1000,0);
            drive.teledrive(0,0,0,gyro.imuP(225,.025));
        }
        drive.StopMotors(0);
        while (opModeIsActive() && sense.sideD2()>4){
            drive.teledrive(.75,.75,0,0);
        }
        drive.StopMotors(0);
        drive.resetEC();
        while(opModeIsActive() && drive.fect()<500){

            drive.teledrive(.75,(sense.sideD2()-3)*.5,0,0);
        }
        drive.StopMotors(0);

    }


}
