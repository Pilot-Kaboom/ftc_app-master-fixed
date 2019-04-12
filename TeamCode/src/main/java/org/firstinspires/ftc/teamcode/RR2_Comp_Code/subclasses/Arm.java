package org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses;

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

        vert.setPower(-.15-vin);
    }
    public void hin(double hin, boolean gofull){
        if (gofull){
            hori.setPower(hin+1);
        }
        else{
            hori.setPower(hin+.1);
        }

    }
    /*public void hin(double hin, double dis, boolean goit){

        if(!goit && dis > .55){
            hori.setPower(.5+hin);
        }
        else if(dis<.45 && !goit){
            hori.setPower(-.15+hin);
        }
        else{
            hori.setPower(.05+hin);
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
    }*/
    public double Vec(){
        return vert.getCurrentPosition();
    }

    public double Hec() {
        return hori.getCurrentPosition();
    }
    public void runToPos(double vpos, double hpos){
        if ((vert.getCurrentPosition()-vpos)*-.005<-1){
            vert.setPower(-1);
        }
        else if ((vert.getCurrentPosition()-vpos)*-.005>1){
            vert.setPower(1);
        }
        else{
            vert.setPower((vert.getCurrentPosition()-vpos)*-.005);
        }
        if (((hori.getCurrentPosition()-hpos)*-.005)<-1){
            hori.setPower(-1);
        }
        else if (((hori.getCurrentPosition()-hpos)*-.005)>1){
            hori.setPower(1);
        }
        else{
            hori.setPower((hori.getCurrentPosition()-hpos)*-.005);
        }
    }
    public void runtolandernear(boolean gofull){
        if(vert.getCurrentPosition()>-4000){
            vert.setPower(-1);
        }
        else if(vert.getCurrentPosition()>-4100&& vert.getCurrentPosition()<-4000){
            vert.setPower(-.35);
        }
        else if(vert.getCurrentPosition()<-4100){
            vert.setPower(0);
        }
        else{
            vert.setPower(-.15);
        }
        if(hori.getCurrentPosition()<1100){
            hori.setPower(1);
        }
        else if (hori.getCurrentPosition()>1200&&hori.getCurrentPosition()<1100){
            hori.setPower(.25);
        }
        else{
            hori.setPower(.05);
        }
    }
    public void runtolander(boolean gofull){
        if(vert.getCurrentPosition()>-5250){
            vert.setPower(-1);
        }
        else if(vert.getCurrentPosition()>-5350&& vert.getCurrentPosition()<-5250){
            vert.setPower(-.35);
        }
        else if(vert.getCurrentPosition()<-5350){
            vert.setPower(0);
        }
        else{
            vert.setPower(-.15);
        }
        if(hori.getCurrentPosition()<1400){
            hori.setPower(1);
        }
        else if (hori.getCurrentPosition()>1500&&hori.getCurrentPosition()<1400){
            if(gofull){
                hori.setPower(1);
            }
            else{
                hori.setPower(.25);
            }

        }
        else{
            if(gofull){
                hori.setPower(1);
            }
            else{
                hori.setPower(.05);
            }

        }
    }
    public void resetArmEc(){
        vert.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hori.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.idle();
        vert.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hori.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

    }
}
