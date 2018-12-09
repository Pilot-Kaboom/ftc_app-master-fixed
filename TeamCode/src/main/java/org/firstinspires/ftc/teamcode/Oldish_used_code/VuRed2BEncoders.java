package org.firstinspires.ftc.teamcode.Oldish_used_code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.LoopingRevGyro;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.UpdatingManager;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;

/**
 * Created by Keith Harder on 1/16/2018.
 */


public class VuRed2BEncoders extends LinearOpMode {


    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    private DcMotor FLM;
    private DcMotor FRM;
    private DcMotor BLM;
    private DcMotor BRM;
    private DcMotor flapper;
    private DcMotor thunker;
    private DcMotor lift;

    private Servo gate;
    private Servo arm;
    private Servo finger;
    private Servo shover;

    private ColorSensor color;
    private OpticalDistanceSensor ods;
    private OpticalDistanceSensor odst;
    private OpticalDistanceSensor odsb;



    private Boolean red;
    private Boolean blue;
    private Boolean left;
    private Boolean right;
    private Boolean center;
    private Boolean block;

    private ElapsedTime time = new ElapsedTime();
    private ElapsedTime ttime = new ElapsedTime();
    private ElapsedTime vtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
//init
        FLM = hardwareMap.dcMotor.get("FLM");
        FRM = hardwareMap.dcMotor.get("FRM");
        BLM = hardwareMap.dcMotor.get("BLM");
        BRM = hardwareMap.dcMotor.get("BRM");
        flapper = hardwareMap.dcMotor.get("flapper");
        thunker = hardwareMap.dcMotor.get("thunker");
        lift = hardwareMap.dcMotor.get("lift");

        gate = hardwareMap.servo.get("gate");
        arm = hardwareMap.servo.get("arm");
        finger = hardwareMap.servo.get("finger");
        shover = hardwareMap.servo.get("shover");

        color = hardwareMap.get(ColorSensor.class, "color");
        color.enableLed(true);
        ods = hardwareMap.opticalDistanceSensor.get("ods");
        odst = hardwareMap.opticalDistanceSensor.get("BlockT");
        odsb = hardwareMap.opticalDistanceSensor.get("odsb");
        red = false;
        blue = false;
        block = false;

        arm.setPosition(.6);


        ///*encoder stuff
        while(BRM.getCurrentPosition() != 0){
            BRM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        BRM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position
        //*/
        while(FLM.getCurrentPosition() != 0){
            FLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        FLM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position

        while(BLM.getCurrentPosition() != 0){
            BLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Make sure encoders are set to 0, if they aren't, reset them.
        }
        BLM.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Set mode to run to position
        //encoder stuff ends

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AXk8m93/////AAAAmSo/yH0caUMsqpjNljWxJF8YX6vQ0htlGYE6tx1nTRyclEBgdBc6yxK8FcCcBaKOJ4vuWvJSuv7t5CMUEA9Zo0NrKut9gg+VZovv+S1NoSnG7aQHhCTaDTlhaZ12MRnRxbkEj72yfSdQhZRbT+MGJ7pBGnvWtOSH/YmeK6bffeICGgdjZz9PoHg8AuWBMvWu6vezXnbAX4IzX1KTmQs8KU2ylFsNRzNhErlnZwDw4vTqRPhk6hhKVOV4iaQup/2CgY7fwblJIV/xKhJMMr7GYi8FPDlYctv4dAONrCPqUOLY0MqxhKf++TqI/kNC+kL8Xa/koNY6Cg+2LT9QEn5J819YnhtcwMzIoHzIRPb/j7NM";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        LoopingRevGyro gyro = new LoopingRevGyro(this.hardwareMap, "imu");

        UpdatingManager updatingManager = new UpdatingManager(this);
        updatingManager.addUpdatable(gyro);
        updatingManager.start();
        relicTrackables.activate();

        telemetry.addData("Z", gyro.getGyroZ(DEGREES));
        telemetry.update();

//init
        waitForStart();



        //gyro.getGyroZ(DEGREES)
        //gyro.resetGyroZ();



        FLM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();
//fix this sometime.  needs to be set to RUN_USING_ENCODERS

        while(FLM.getCurrentPosition() < 1000  &&  BLM.getCurrentPosition() < 1000 && FRM.getCurrentPosition() < -1000){
            FLM.setPower(.1);
            FRM.setPower(-.1);
            BLM.setPower(.1);
            BRM.setPower(-.1);
            //update gyro corrections here
        }
        FRM.setPower(0);
        FLM.setPower(0);
        BRM.setPower(0);
        BLM.setPower(0);

    }

}
