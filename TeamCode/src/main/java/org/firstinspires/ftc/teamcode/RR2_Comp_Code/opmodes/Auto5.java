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
        atime.reset();
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
        while(opModeIsActive() && sense.backD() < 11 && (goldPosition == SampleRandomizedPositions.CENTER)){
            drive.goRight(-.65);
            sense.sensortelem();
            telemetry.addData("4",4);
            telemetry.update();
            collect.drop(false,false,true);
        }
        while(opModeIsActive() && sense.backD() < 12 && (goldPosition == SampleRandomizedPositions.RIGHT ||goldPosition == SampleRandomizedPositions.LEFT ) ){
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
            while (opModeIsActive() && arm.Hec()>-1700 && arm.Vec()>-1600){
                arm.runToPos(-1400,-1900);
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
            while (opModeIsActive() && !gyro.cutout(82,8)){
                drive.teledrive(0,0,0,gyro.imuP(82,.025));
                telemetry.addData("6",6);
                telemetry.update();
            }
            drive.StopMotors(0);
            while (opModeIsActive() && arm.Hec()>-800){
                arm.hin(-.75,false);
                arm.Armtelem();
                telemetry.addData("7",7);
                telemetry.update();
            }
            arm.hin(0,false);
            drive.StopMotors(0);
            drive.resetEC();
            while(opModeIsActive() && drive.fect() <50){
                drive.goForward(.5);
                telemetry.addData("2",2);
                telemetry.update();
            }
            drive.StopMotors(0);

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
        while(opModeIsActive() && time.seconds()<1){
            collect.collect(1,false,false);
            telemetry.addData("8",8);
            telemetry.update();
        }

        time.reset();
        while (opModeIsActive() && (!gyro.cutout(90,8) || arm.Vec()>-5600 || arm.Hec()>-3800)){
            while(opModeIsActive() &&time.seconds()<.5){
                arm.runToPos(-5800,-4000);
                telemetry.addData("9",9);
                telemetry.update();
            }
            while (opModeIsActive()&& (!gyro.cutout(90,8) || arm.Vec()>-5600 || arm.Hec()>-3800)){
                drive.teledrive(0,0,0,gyro.imuP(90,.025));
                arm.runToPos(-5800,-4000);
                arm.Armtelem();
                gyro.gyrotelem();
                telemetry.addData("10",10);
                telemetry.update();
            }
            arm.vin(0);
            arm.hin(0,false);
            drive.StopMotors(0);
        }
        collect.collect(0,true,false);
        drive.StopMotors(0);
        time.reset();
        while (opModeIsActive() && time.seconds()<.25){
            collect.drop(false,true,false);
            telemetry.addData("11",11);
            telemetry.update();
            collect.collect(.5,true,false);
        }
        collect.collect(0,true,false);
        collect.drop(false,true,false);
        time.reset();
        drive.resetEC();
        while(opModeIsActive() && drive.bect() <150 && goldPosition == SampleRandomizedPositions.CENTER){
            drive.goForward(-.5);
            telemetry.addData("2",2);
            telemetry.update();
        }
        drive.StopMotors(0);
        while(opModeIsActive() && time.seconds()<3){
            while(opModeIsActive() && arm.Hec()<200&& time.seconds()<3){
                arm.runToPos(-5450,500);
                collect.drop(false,true,false);
                telemetry.addData("12",12);
                arm.Armtelem();
                telemetry.update();
            }
            arm.vin(0);
            arm.hin(0,false);
            while(opModeIsActive() && arm.Hec()>=200&& time.seconds()<3){
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
        while (opModeIsActive() && sense.sideD2()>5){
            drive.teledrive(-.5,.64,0,0);
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
            collect.collect(1,true,false);
            telemetry.addData("17",17);
            telemetry.update();
        }
        time.reset();
        while (opModeIsActive() && time.seconds()<.5){
            collect.collect(-.2,false,true);
            arm.runtolander(false);
            telemetry.addData("18",18);
            telemetry.update();
        }
        time.reset();
        while (opModeIsActive() && time.seconds()<1){
            drive.teledrive(.25,-.25,.75,0);
            telemetry.addData("19",19);
            telemetry.update();
        }
        while (atime.seconds()<28 && opModeIsActive() && (!gyro.cutout(147,8) || arm.Vec()>-5150 || arm.Hec()<1200) ){
            drive.teledrive(0,0,0, gyro.imuP(147,.025));
            arm.runtolander(false);
            telemetry.addData("20",20);
            telemetry.update();
        }
        drive.StopMotors(0);
        arm.vin(0);
        arm.hin(0,false);
        time.reset();
        while (atime.seconds()<28 &&opModeIsActive()&& time.seconds()<1){
            drive.teledrive(.6,0,0,0);
            collect.drop(false,true,false);
            arm.runtolander(true);
            telemetry.addData("21",21);
            telemetry.update();
        }
        drive.StopMotors(0);
        time.reset();
        while (atime.seconds()<28 &&opModeIsActive() && time.seconds()<1){
            collect.drop(true,false,false);
            telemetry.addData("22",22);
            telemetry.update();
        }
        collect.drop(false,false,false);
        time.reset();
        while(atime.seconds()<28 &&opModeIsActive() && gyro.cutout(135,8)){
            drive.teledrive(0,0,0,gyro.imuP(135,.025));
            arm.vin(-.15);
            arm.hin(-.09,false);
            telemetry.addData("23",23);
            telemetry.update();
        }
        while(opModeIsActive() && time.seconds()<1.75){
            drive.teledrive(-1,0,0,.75);
            arm.vin(-.15);
            arm.hin(-.09,false);
            telemetry.addData("24",24);
            telemetry.update();
        }
        drive.StopMotors(0);
        while(opModeIsActive() &&time.seconds()<2.5 ){
            drive.StopMotors(0);
            arm.vin(-.14);
            arm.hin(-.09,false);
            telemetry.addData("25",25);
            telemetry.update();
        }
        drive.StopMotors(0);


    }


}
