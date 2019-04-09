package org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses;

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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.PushbotAutoDriveByEncoder_Linear;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class sensors {

    private final DistanceSensor Rd;
    private final DistanceSensor Sd;
    private final DistanceSensor Sd2;
    //private final DistanceSensor Fd;
    //private final DistanceSensor Bd;
    /*private final ColorSensor fc;
    private final ColorSensor bc;
    private final OpticalDistanceSensor fd;
    private final OpticalDistanceSensor bd;*/
    //private final RevBlinkinLedDriver light;
    //private final ColorSensor white;
    //private final OpticalDistanceSensor whiteD;
    //private final Servo dumper;
    private boolean stillmin;
   // private final BNO055IMU imu;
   // private Orientation angle;
    private final LinearOpMode sense;
    public sensors(LinearOpMode sense){
        Rd = sense.hardwareMap.get(DistanceSensor.class, "bd");
        Sd = sense.hardwareMap.get(DistanceSensor.class, "sd");
        Sd2 = sense.hardwareMap.get(DistanceSensor.class, "sd2");
        //light = sense.hardwareMap.get(RevBlinkinLedDriver.class, "light");
        //Fd = sense.hardwareMap.get(DistanceSensor.class, "frontd");
        //Bd = sense.hardwareMap.get(DistanceSensor.class, "backd");
        /*fc = sense.hardwareMap.colorSensor.get("fcd");
        fd = sense.hardwareMap.opticalDistanceSensor.get("fcd");
        bc = sense.hardwareMap.colorSensor.get("bcd");
        bd = sense.hardwareMap.opticalDistanceSensor.get("bcd");*/
        //whiteD = sense.hardwareMap.opticalDistanceSensor.get("white");
        //white = sense.hardwareMap.colorSensor.get("white");
        //dumper = sense.hardwareMap.servo.get("dumper");

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
        return (Rd.getDistance(DistanceUnit.INCH));
    }
    public double sideD(){
        return (Sd.getDistance(DistanceUnit.INCH));
    }
    public double sideD2(){
        return (Sd2.getDistance(DistanceUnit.INCH));
    }
    /*
    public double frontD(){
        return (Fd.getDistance(DistanceUnit.INCH));
    }
    public double rearD(){
        return (Bd.getDistance(DistanceUnit.INCH));
    }*/
/*
    public double colorf(){
        return(fc.red());
    }
    public double colorb(){
        return(bc.red());
    }
    public double disb(){
        return(bd.getLightDetected());
    }
    public double disf(){
        return(fd.getLightDetected());
    }
    public boolean mineraldetect(){
        return (colorf()>900 && colorb()>900 );
    }*/

    //Keith is cool and great and stuff. //
    /*
    public void IAlight(double color, double timer){

        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
    }
    public void Alights(double color, double timer){

        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES);
    }
    public void Tlight(double color, double timer){

        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_WAVES);
    }*/
    //public double white(){
    //    return (white.blue()-4);
    //}
    //public double bucketDis(){
      //  return (whiteD.getLightDetected());
    //}
    //public void teammarker(double pos){
        //dumper.setPosition(pos);
    //}
    public void sensortelem(){
        //sense.telemetry.addData("white", white());
        //sense.telemetry.addData("white dis", bucketDis());
        /*
        sense.telemetry.addData("colorF", colorf());
        sense.telemetry.addData("colorB", colorb());
        sense.telemetry.addData("disF", disf());
        sense.telemetry.addData("disB", disb());*/
        sense.telemetry.addData("sideD2", sideD2());
        sense.telemetry.addData("sideD", sideD());
        sense.telemetry.addData("backD", backD());
        //sense.telemetry.addData("frontD", frontD());
        //sense.telemetry.addData("rearD", rearD());

        sense.telemetry.update();
    }


}
