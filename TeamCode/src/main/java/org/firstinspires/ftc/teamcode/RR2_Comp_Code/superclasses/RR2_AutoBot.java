package org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
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
        doit = true;

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");// recommended camera direction
        parameters.vuforiaLicenseKey = "AYh5Avz/////AAABmcaUgF0Qc0IZgZolSSILtLp09gYOjB/jTLSa7BOfFrRXXFYruND/4Um7u78qpFbnLBgyDRxIiwVzYWgWuSxqTqDLAYAVQ/uhVXOB8/U7JYy1bR/ocZOWdS2qLv1AxgzPhk88PHW/QpzwTp6G9go5tvRrd/PIXhVTZUgm1bz62QeFLVV1bYKK5xrrBric2iBf/E3I7UlUbPoLxVviRkN9SHwAxIwQB6ovF/bJsRFwFHuMm+pYwzEsE0ns6nzDaUndC6TqHV4URbMKRKYSuqG2fd5nuDZynzPjLUg4i+JFDz8AdN1qXxvdgKKUruLxV+ULbCXHY4NEXEcMZ7SyAOECj732mMCYKBOIofIRI6BwV3Zj";


        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_NONE);
        vision.init();// enables the camera overlay. this will take a couple of seconds

        vision.enable();// enables the tracking algorithms. this might also take a little time
        while(!isStarted() && !isStopRequested()){
            telemetry.addData("goldPosition", vision.getTfLite().getLastKnownSampleOrder());
            telemetry.addData("goldPosition is c", vision.getTfLite().getLastKnownSampleOrder() == SampleRandomizedPositions.CENTER);
            telemetry.addData("goldPosition is l", vision.getTfLite().getLastKnownSampleOrder() == SampleRandomizedPositions.LEFT);
            telemetry.addData("goldPosition is r", vision.getTfLite().getLastKnownSampleOrder() == SampleRandomizedPositions.RIGHT);
            telemetry.update();

        }




    }
}

