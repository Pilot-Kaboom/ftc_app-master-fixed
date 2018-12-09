/*package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="testTFOD", group="Tele1")
public class TestTFOD extends RR2_TeleBot {

    @Override
    public void run() {
        while(opModeIsActive()){
            while(opModeIsActive() && time.seconds()< 10){
                tfoddd.initVison();
            }
            tfoddd.runTFscan();
            telemetry.addData("gold on left", tfoddd.Left());
            telemetry.addData("gold on right", tfoddd.Right());
            telemetry.addData("gold on center", tfoddd.Center());
            drive.ECtelem();

            telemetry.update();
        }

    }
}*/
