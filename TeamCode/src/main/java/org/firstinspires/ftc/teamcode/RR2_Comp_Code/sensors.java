package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.PushbotAutoDriveByEncoder_Linear;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class sensors {

    private final DistanceSensor Bd;
    private final DistanceSensor Sd;
    /*private final ColorSensor Rc;
    private final ColorSensor Lc;
    private final TouchSensor backtouch;
    private final OpticalDistanceSensor Ld;
    private final OpticalDistanceSensor Rd;*/
    private final ColorSensor white;
    private final OpticalDistanceSensor whiteD;
    private final Servo dumper;

   // private final BNO055IMU imu;
   // private Orientation angle;
    private final LinearOpMode sense;
    public sensors(LinearOpMode sense){
        Bd = sense.hardwareMap.get(DistanceSensor.class, "bd");
        Sd = sense.hardwareMap.get(DistanceSensor.class, "sd");
        /*Rc = sense.hardwareMap.colorSensor.get("rcd");
        Rd = sense.hardwareMap.opticalDistanceSensor.get("rcd");
        backtouch = sense.hardwareMap.touchSensor.get("backtouch");
        Lc = sense.hardwareMap.colorSensor.get("lcd");
        Ld = sense.hardwareMap.opticalDistanceSensor.get("lcd");*/
        whiteD = sense.hardwareMap.opticalDistanceSensor.get("white");
        white = sense.hardwareMap.colorSensor.get("white");
        dumper = sense.hardwareMap.servo.get("dumper");

        /*
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = sense.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);*/
        this.sense = sense;
    }

    //public double angular(){return formatAngle(sense.angle.angleUnit, sense.angle.firstAngle)}
    public double backD(){
        return (Bd.getDistance(DistanceUnit.INCH));
    }
    public double sideD(){
        return (Sd.getDistance(DistanceUnit.INCH));
    }
    /*
    public double colorR(){
        return(Rc.blue());
    }
    public double colorL(){
        return(Lc.blue());
    }
    public double disL(){
        return(Ld.getLightDetected());
    }
    public double disR(){
        return(Rd.getLightDetected());
    }
    public boolean touch(){
        return (backtouch.isPressed());
    }*/
    public double white(){
        return (white.blue()-4);
    }
    public double bucketDis(){
        return (whiteD.getLightDetected());
    }
    public void teammarker(double pos){
        dumper.setPosition(pos);
    }
    public void sensortelem(){
        sense.telemetry.addData("white", white());
        sense.telemetry.addData("white", bucketDis());
        /*
        sense.telemetry.addData("colorL", colorL());
        sense.telemetry.addData("colorR", colorR());
        sense.telemetry.addData("disL", disL());
        sense.telemetry.addData("disR", disR());
        sense.telemetry.addData("touch", touch());*/
        sense.telemetry.addData("sideD", sideD());
        sense.telemetry.addData("backD", backD());

        sense.telemetry.update();
    }


}
