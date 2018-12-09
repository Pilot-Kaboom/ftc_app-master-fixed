package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Keith Harder on 8/11/2018.
 */
//@Autonomous(name="mecanumEncodedAuto", group="Auto1")
public class mecanumEncodedAuto extends mecanumBotHard {
    @Override
    public void run() {
        ResetEC();
        idle();
        RunInEC();
        idle();
        while(opModeIsActive() && perc < tic){
            spdUpF(500);
        }
        while(opModeIsActive() && FEC < 3000){


            if(FEC>500 && FEC<2000){
                goForward(.85);
            }
            else if(FEC<2500){
                goForward(.5);
            }
            else if(FEC<2750){
                goForward(.25);
            }
            else{
                goForward(.1);
            }
            ticks();
            telem();
        }
        StopMotors(0);

        ResetEC();
        idle();
        RunInEC();
        idle();


        while(opModeIsActive() && REC < 1000){
            goRight(.25);
            ticks();
            telem();
        }
        StopMotors(0);
        ResetEC();
        idle();
        RunInEC();
        idle();
        while(opModeIsActive() && BEC < 1000){
            goForward(-.25);
            ticks();
            telem();
        }
        StopMotors(0);
        ResetEC();
        idle();
        RunInEC();
        idle();
        while(opModeIsActive() && LEC < 1000){
            goRight(-.25);
            ticks();
            telem();
        }
        StopMotors(0);
        ResetEC();
        idle();
        RunInEC();
        idle();
    }
}
