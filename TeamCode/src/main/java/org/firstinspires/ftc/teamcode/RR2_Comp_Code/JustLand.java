package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="just land and park", group="Auto1")
public class JustLand extends RR2_LiftBot {

    @Override
    public void run() {
        drive.resetEC();
        //drop
        while (opModeIsActive() && time.seconds() <.5){
            lifter.LiftOps(-1);
            doit=true;
        }
        while (opModeIsActive() && time.seconds() < 2){
            lifter.LiftOps(1);
            //vert.setPower(1);a
        }
        lifter.LiftOps(0);
        //vert.setPower(0);
        drive.resetEC();
        //hi ho robot, away!
        while(opModeIsActive() && drive.bect() <400 ){
            drive.goForward(-.5);
        }
        time.reset();
        while(opModeIsActive() && time.seconds()< .25){
            drive.StopMotors(0);
        }
        /*
        while(opModeIsActive() && sense.backD()< 14){
            drive.goRight(-.75);
            sense.sensortelem();

        }*/
        time.reset();
        while(opModeIsActive() && time.seconds() <6){
            drive.goRight(-.25);
            sense.sensortelem();

        }

    }
}

