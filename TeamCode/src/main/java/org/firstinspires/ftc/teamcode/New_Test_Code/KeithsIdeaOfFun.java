package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Elayne Harder on 7/27/2018.
 */
//@Autonomous(name="KeithsIdeaOfFun", group="Auto1")
public class KeithsIdeaOfFun extends ElayneBotHard{

    @Override
    public void run() {


        while (opModeIsActive() && time.seconds()<3){
            left.setPower(-1);
        }
        time.reset();
        while (opModeIsActive() &&time.seconds()<3){
            right.setPower(1);
            left.setPower(0);
        }
        time.reset();
        while (opModeIsActive() &&time.seconds()<3){
            left.setPower(-1);
            right.setPower(1);
        }

    }
}
