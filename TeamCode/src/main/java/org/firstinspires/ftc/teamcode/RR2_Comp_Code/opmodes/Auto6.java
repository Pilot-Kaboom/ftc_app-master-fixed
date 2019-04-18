package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Oldish_used_code.LoopingRevGyro;
import org.firstinspires.ftc.teamcode.Oldish_used_code.UpdatingManager;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_AutoBot;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Auto6", group="Auto1")
public class Auto6 extends RR2_AutoBot {

    @Override
    public void run() {
        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();



        telemetry.update();

        drive.resetEC();
        //drop

        while (opModeIsActive() && time.seconds() < 2.5){
            lifter.LiftOps(1);
            arm.runToPos(-1200,200);
            telemetry.addData("1",1);
            telemetry.update();
        }
        arm.vin(0);
        arm.hin(0,false);
        lifter.LiftOps(0);
        drive.resetEC();
        //move off of hook
        while(opModeIsActive() && drive.bect() <250 ){
            drive.goForward(-.5);
            telemetry.addData("2",2);
            telemetry.update();
        }
        drive.StopMotors(0);
        time.reset();
        while(opModeIsActive() && time.seconds()< .25+landwait){
            drive.StopMotors(0);
            telemetry.addData("3",3);
            telemetry.update();
        }

        //drive to minerals
        while(opModeIsActive() && sense.backD() < 12){
            drive.goRight(-.65);
            sense.sensortelem();
            telemetry.addData("4",4);
            telemetry.update();
            collect.drop(false,false,true);
        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.LEFT) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("5",5);
            telemetry.addData("do it", doit);
            telemetry.addData("gold on left, saw L", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(125,8)){
                drive.teledrive(0,0,0,gyro.imuP(125,.025));
                telemetry.addData("6",6);
                telemetry.update();
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-1800 && arm.Vec()>-2800){
                arm.runToPos(-3000,-2000);
                telemetry.addData("7",7);
                arm.Armtelem();
                telemetry.update();
            }
        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.CENTER) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("5",5);
            telemetry.addData("do it", doit);
            telemetry.addData("gold on Center, saw C", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(90,8)){
                drive.teledrive(0,0,0,gyro.imuP(90,.025));
                telemetry.addData("6",6);
                telemetry.update();
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-800 && arm.Vec()>-1000){
                arm.runToPos(-1200,-1000);
                arm.Armtelem();
                telemetry.addData("7",7);
                telemetry.update();
            }
        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.RIGHT) {
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("5",5);
            telemetry.addData("do it", doit);
            telemetry.addData("gold on right, saw R", 1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            while (opModeIsActive() && !gyro.cutout(45,8)){
                drive.teledrive(0,0,0,gyro.imuP(45,.025));
                telemetry.addData("6",6);
                telemetry.update();
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-1800 && arm.Vec()>-2800){
                arm.runToPos(-3000,-2000);
                telemetry.addData("7",7);
                arm.Armtelem();
                telemetry.update();
            }

        }
        arm.vin(0);
        arm.hin(0,false);
        time.reset();
        while(opModeIsActive() && time.seconds()<1.5){
            collect.collect(1,false,false);
            telemetry.addData("8",8);
            telemetry.update();
        }

        time.reset();
        while (opModeIsActive() && (!gyro.cutout(90,8) || arm.Vec()>-5400 || arm.Hec()>-3700)){
            while(opModeIsActive() &&time.seconds()<.5){
                arm.runToPos(-5600,-3900);
                telemetry.addData("9",9);
                telemetry.update();
            }
            while (opModeIsActive()&& (!gyro.cutout(90,8) || arm.Vec()>-5400 || arm.Hec()>-3700)){
                drive.teledrive(0,0,0,gyro.imuP(90,.025));
                arm.runToPos(-5600,-3900);
                arm.Armtelem();
                gyro.gyrotelem();
                telemetry.addData("10",10);
                telemetry.update();
            }
            arm.vin(0);
            arm.hin(0,false);
            drive.StopMotors(0);
        }
        collect.collect(0,false,false);
        drive.StopMotors(0);
        time.reset();
        while (opModeIsActive() && time.seconds()<.5){
            collect.dropnear(false,true,false);
            telemetry.addData("11",11);
            telemetry.update();
            collect.collect(-.5,false,false);
        }
        collect.collect(0,false,false);
        collect.dropnear(false,true,false);
        time.reset();
        while(opModeIsActive() && time.seconds()<3){
            while(opModeIsActive() && arm.Hec()<400&& time.seconds()<3){
                arm.runToPos(-5450,500);
                collect.drop(false,true,false);
                telemetry.addData("12",12);
                arm.Armtelem();
                telemetry.update();
            }
            arm.vin(0);
            arm.hin(0,false);
            while(opModeIsActive() && arm.Hec()>=400&& time.seconds()<3){
                arm.runToPos(-5450,500);
                collect.drop(true,false,false);
                telemetry.addData("13",13);
                arm.Armtelem();
                telemetry.update();
            }
            arm.vin(0);
            arm.hin(0,false);
        }

        collect.drop(false,false,false);
        time.reset();
        while (opModeIsActive() && time.seconds()<1.35){
            drive.teledrive(0,0,0,.75);
            collect.drop(false,false,false);
            arm.runToPos(-2300,0);
        }

        while(opModeIsActive() && !gyro.cutout(-140,8)){
            arm.runToPos(-2300,0);
            drive.teledrive(0,0, 0, gyro.imuP(-140,.025));
            telemetry.addData("14",14);
            gyro.gyrotelem();
            telemetry.update();
        }
        arm.vin(0);
        arm.hin(0,false);
        drive.StopMotors(0);
        while (opModeIsActive() && sense.sideD2()>7){
            drive.teledrive(-.5,.75,0,0);
            telemetry.addData("15",15);
            telemetry.update();
        }
        drive.StopMotors(0);
        drive.resetEC();
        while(opModeIsActive() && arm.Hec()>-3600){
            arm.runToPos(-5600,-3800);
            telemetry.addData("16",16);
            telemetry.update();
        }
        arm.vin(0);
        arm.hin(0,false);
        time.reset();
        while(opModeIsActive() && time.seconds()<1.5){
            collect.collect(1,false,false);
        }
        time.reset();
        while (time.seconds()<.5){
            collect.collect(-.2,false,false);
            arm.runtolander(false);

        }
        time.reset();
        while (opModeIsActive() && time.seconds()<1){
            drive.teledrive(.25,-.25,.75,0);
        }
        while (opModeIsActive() && (!gyro.cutout(145,8) || arm.Vec()>-5250 || arm.Hec()<1400) ){
            drive.teledrive(0,0,0, gyro.imuP(145,.025));
            arm.runtolander(false);
        }
        drive.StopMotors(0);
        arm.vin(0);
        arm.hin(0,false);
        time.reset();
        while (opModeIsActive()&& time.seconds()<1){
            drive.teledrive(.5,0,0,0);
            collect.drop(false,true,false);
        }
        time.reset();
        while (opModeIsActive() && time.seconds()<1){
            collect.drop(true,false,false);
        }
        collect.drop(false,false,false);
        time.reset();
        while(opModeIsActive() && time.seconds()<1){

            arm.vin(-.06);
            drive.teledrive(-.75,0,0,.25);
        }
        drive.StopMotors(0);
        while(opModeIsActive() && time.seconds()<2){
            arm.vin(-.06);
            arm.hin(-.03,false);
        }

    }


}
