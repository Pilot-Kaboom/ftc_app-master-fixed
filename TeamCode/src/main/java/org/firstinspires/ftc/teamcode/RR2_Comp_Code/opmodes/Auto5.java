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
        while (opModeIsActive() && Math.abs(gyro.getGyroY(AngleUnit.DEGREES))>4);
    }

}
