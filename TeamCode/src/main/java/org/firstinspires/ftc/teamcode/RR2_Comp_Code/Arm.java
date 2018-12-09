package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private final DcMotor vert;
    private final DcMotor hori;

    private double pos;
    private double othapos;

    private final LinearOpMode arm;

    public Arm(LinearOpMode arm){
        vert = arm.hardwareMap.dcMotor.get("vert");
        hori = arm.hardwareMap.dcMotor.get("hori");
        this.arm = arm;
    }
    /*
    public void vin(double vin, boolean v){

        if (v){
            vert.setPower(.15+vin);
        }
        else{
            vert.setPower(vin);
        }
    }

    public void hin(double hin, boolean h){
        if(h){
            hori.setPower(.01+hin);
        }
        else{
            hori.setPower(hin);
        }
//a
    }*/
    public void vin(double vin){
            vert.setPower(.25+vin);
    }

    public void hin(double hin, double dis, boolean goit){
        if(dis > 35 && dis < 45 && goit){
            hori.setPower(.15+hin);
        }
        else if(goit && dis > 45){
            hori.setPower(.6+hin);
        }
        else if(dis<35 && goit){
            hori.setPower(-.15+hin);
        }
        else{
            hori.setPower(.15+hin);
        }


    }
    public void VposSet(double posset, boolean doit){
        pos = (pos + posset);
        if(doit){
            vert.setPower(posset);
        }
        else{
            vert.setPower(-Vpower());
        }
    }
    public double Vpower(){
        return(vert.getCurrentPosition()-pos);
    }
    public double HsetPos(){
        return (vert.getCurrentPosition()*1.75);
    }
    public double Hpower(){
        return(hori.getCurrentPosition()-HsetPos());
    }
    public double more(){
        return (hori.getCurrentPosition()-othapos);
    }

    public void HposSet(double posset, boolean doitalot, boolean doitabit){
        othapos = (othapos + posset);
        if(doitalot){
            hori.setPower(posset);
        }
        else if(doitabit){
            hori.setPower(-more());
        }
        else{
            hori.setPower(-Hpower());
        }
    }
    public void resetArmEc(){
        vert.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hori.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.idle();
        vert.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hori.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.idle();
    }
    public void stopusingEC(){
        vert.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hori.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.idle();
    }
    public void Armtelem(){
        arm.telemetry.addData("vec", vert.getCurrentPosition());
        arm.telemetry.addData("HEc", hori.getCurrentPosition());
        arm.telemetry.addData("Vpower", Vpower());
        arm.telemetry.addData("HsetPos", HsetPos());
        arm.telemetry.addData("Hpower", Hpower());
        arm.telemetry.addData("HEc-othapos", more());
        arm.telemetry.addData("othapos", othapos);
        arm.telemetry.addData("pos", pos);
        arm.telemetry.update();
    }
}
