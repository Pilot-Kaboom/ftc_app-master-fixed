package org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

public class Gyro {


    private final LinearOpMode gyro;

    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;

    public Gyro(LinearOpMode gyro){

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = gyro.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity  = imu.getGravity();


        this.gyro = gyro;
    }
    public double imuz(){
        return imu.getAngularOrientation().firstAngle;
    }
    public double imuP(double target, double multiply){
        if ((imuz()-target)*-multiply>1){
            return 1;
        }
        else if ((imuz()-target)*-multiply<-1){
            return -1;
        }
        else {
            return (imuz()-target)*-multiply;
        }
    }
    public boolean cutout(double target, double tolerance){
        return imuz()-target<tolerance;
    }

}
