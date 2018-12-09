package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Elayne Harder on 7/27/2018.
 */
//@Autonomous(name="ElayneTestAuto", group="Auto1")
public class ElayneTestAuto extends ElayneBotHard {


    @Override
    public void run() {

        while (opModeIsActive()&& time.seconds() <2){

            left.setPower(-1);
            right.setPower(1);

        }
    }
}
