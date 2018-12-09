package org.firstinspires.ftc.teamcode.New_Test_Code;

/**
 * Created by Keith Harder on 8/24/2018.
 */

public class ElayneReview extends mecanumBotHard {
    @Override
    public void run() {
        while (opModeIsActive() && time.seconds() < 2) {
            turn(1);
        }
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive() && FEC < 500) {
            goForward(1);
        }
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive() && REC < 500) {
            goRight(1);
        }
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive()&&FEC<500)
            goForward(1);
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive()&&REC<500){
            goRight(1);
        }
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive()&&BEC<1000){
            goForward(-1);
        }
        ResetEC();
        idle();
        RunInEC();
        idle();
        while (opModeIsActive()&&LEC<1000){
            goRight(-1);
        }
    }
}
