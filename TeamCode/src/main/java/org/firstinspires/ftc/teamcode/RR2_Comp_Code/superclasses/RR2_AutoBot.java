package org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Arm;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Drive;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.collecter;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.lift;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.sensors;
import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

public abstract class RR2_AutoBot extends SuperSuperClass {

    public Arm arm;
    public Drive drive;
    public collecter collect;
    public lift lifter;
    public sensors sense;
    public boolean doit;
    public MasterVision vision;
    public SampleRandomizedPositions goldPosition;

    @Override
    public void initiate(){
        arm = new Arm(this);
        drive = new Drive(this);
        collect = new collecter(hardwareMap);
        lifter = new lift(hardwareMap);
        sense = new sensors(this);
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "AXk8m93/////AAAAmSo/yH0caUMsqpjNljWxJF8YX6vQ0htlGYE6tx1nTRyclEBgdBc6yxK8FcCcBaKOJ4vuWvJSuv7t5CMUEA9Zo0NrKut9gg+VZovv+S1NoSnG7aQHhCTaDTlhaZ12MRnRxbkEj72yfSdQhZRbT+MGJ7pBGnvWtOSH/YmeK6bffeICGgdjZz9PoHg8AuWBMvWu6vezXnbAX4IzX1KTmQs8KU2ylFsNRzNhErlnZwDw4vTqRPhk6hhKVOV4iaQup/2CgY7fwblJIV/xKhJMMr7GYi8FPDlYctv4dAONrCPqUOLY0MqxhKf++TqI/kNC+kL8Xa/koNY6Cg+2LT9QEn5J819YnhtcwMzIoHzIRPb/j7NM";


        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_RIGHT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time

        telemetry.addData("goldPosition is c", goldPosition == SampleRandomizedPositions.CENTER);
        telemetry.addData("goldPosition is l", goldPosition == SampleRandomizedPositions.LEFT);
        telemetry.addData("goldPosition is R", goldPosition == SampleRandomizedPositions.RIGHT);
        telemetry.update();



    }
}

