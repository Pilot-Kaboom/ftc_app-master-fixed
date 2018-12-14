package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_AutoBot;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Auto3", group="Auto1")
public class Auto3 extends RR2_AutoBot {

    @Override
    public void run() {
        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();



        telemetry.update();

        drive.resetEC();
        //drop
        while (opModeIsActive() && time.seconds() <.5){
            lifter.LiftOps(-1);
            doit=true;
        }
        while (opModeIsActive() && time.seconds() < 2){
            lifter.LiftOps(1);

        }
        lifter.LiftOps(0);
        drive.resetEC();
        //move off of hook
        while(opModeIsActive() && drive.bect() <350 ){
            drive.goForward(-.5);
        }
        time.reset();
        while(opModeIsActive() && time.seconds()< .25){
            drive.StopMotors(0);
        }
        //drive to minerals
        while(opModeIsActive() && sense.backD() < 20){
            drive.goRight(-.25);
            sense.sensortelem();

        }
        drive.StopMotors(0);
        drive.resetEC();
        //hit the correct mineral
        while (opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.CENTER){
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on right, saw C",1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.fect() < 1500){
                drive.goForward(.5);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on right, saw C",1);
                telemetry.update();

            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.lect() < 400){
                drive.goRight(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on right, saw C",1);
                telemetry.update();

            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.rect() < 400){
                drive.goRight(.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on right, saw C",1);
                telemetry.update();
            }

            drive.StopMotors(0);
            drive.resetEC();
            while(opModeIsActive() && drive.bect() < 2250){
                drive.goForward(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on right, saw C",1);
                telemetry.update();

            }
            drive.StopMotors(0);
            drive.resetEC();
        }
        time.reset();
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.RIGHT){
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on left, saw R",1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.bect() < 500){
                drive.goForward(-.5);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on left, saw R",1);
                telemetry.update();

            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.lect() < 400){
                drive.goRight(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on left, saw R",1);
                telemetry.update();

            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.rect() < 400){
                drive.goRight(.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on left, saw R",1);
                telemetry.update();

            }

            drive.StopMotors(0);
            drive.resetEC();
            while(opModeIsActive() && drive.bect() < 500){
                drive.goForward(-.5);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on left, saw R",1);
                telemetry.update();

            }

        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.LEFT){
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold on center, saw L",1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.fect() < 500){
                drive.goForward(.5);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on center, saw L",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.lect() < 400){
                drive.goRight(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on center, saw L",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.rect() < 400){
                drive.goRight(.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on center, saw L",1);
                telemetry.update();
            }

            drive.StopMotors(0);
            drive.resetEC();
            while(opModeIsActive() && drive.bect() < 1500){
                drive.goForward(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold on center, saw L",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();

        }
        while(opModeIsActive() && doit && goldPosition == SampleRandomizedPositions.UNKNOWN){
            /*while(opModeIsActive() && drive.rect() < 50){
                drive.goRight(.5);
                drive.ECtelem();
            }*/
            telemetry.addData("do it", doit);
            telemetry.addData("gold not found, going for center",1);
            telemetry.update();
            doit = false;
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.fect() < 500){
                drive.goForward(.5);
                telemetry.addData("do it", doit);
                telemetry.addData("gold not found, going for center",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.lect() < 400){
                drive.goRight(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold not found, going for center",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();
            sleep(500);
            while(opModeIsActive() && drive.rect() < 400){
                drive.goRight(.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold not found, going for center",1);
                telemetry.update();
            }

            drive.StopMotors(0);
            drive.resetEC();
            while(opModeIsActive() && drive.bect() < 1500){
                drive.goForward(-.75);
                telemetry.addData("do it", doit);
                telemetry.addData("gold not found, going for center",1);
                telemetry.update();
            }
            drive.StopMotors(0);
            drive.resetEC();

        }
        while(opModeIsActive() && sense.sideD()>14){
            drive.teledrive(.75,0,0,.15);
            telemetry.addData("do it", doit);
            telemetry.addData("driving to wall",1);
            drive.ECtelem();

            telemetry.update();
        }
        drive.StopMotors(0);
        drive.resetEC();
        time.reset();
        while(opModeIsActive() && time.seconds()< .4){
            drive.turnClockwise(-.75);
        }
        drive.StopMotors(0);
        drive.resetEC();
        time.reset();
        drive.StopMotors(0);
    }
}
