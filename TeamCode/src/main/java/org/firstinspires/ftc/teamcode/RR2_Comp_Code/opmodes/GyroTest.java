package org.firstinspires.ftc.teamcode.RR2_Comp_Code.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Oldish_used_code.LoopingRevGyro;
import org.firstinspires.ftc.teamcode.Oldish_used_code.UpdatingManager;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses.RR2_AutoBot;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous(name="Gyrotest", group="Auto1")
public class GyroTest extends RR2_AutoBot {

    @Override
    public void run() {
        telemetry.addData("1",1);
        gyro.gyrotelem();
        sense.sensortelem();
        while (opModeIsActive() && !gyro.cutout(90,8)){
            drive.teledrive(0,0,0,gyro.imuP(90,.025));
            gyro.gyrotelem();
            telemetry.addData("2",2);
            sense.sensortelem();
        }
        while (opModeIsActive() && !gyro.cutout(-90,8)){
            drive.teledrive(0,0,0,gyro.imuP(-90,.025));
            gyro.gyrotelem();
            telemetry.addData("3",3);

            sense.sensortelem();
        }
        drive.StopMotors(0);
        gyro.gyrotelem();
        telemetry.addData("4",4);

        sense.sensortelem();
    }
}