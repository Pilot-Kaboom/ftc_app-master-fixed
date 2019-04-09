package org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses;

import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Drive;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Gyro;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.lift;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.sensors;

public abstract class RR2_LiftBot extends SuperSuperClass {

    public Drive drive;
    public lift lifter;
    public sensors sense;
    public Gyro gyro;
    //public DcMotor vert;
    //public LoopingRevGyro gyro;
    public boolean Gright;
    public boolean Gleft;
    public boolean Gcenter;
    public boolean doit;
    @Override
    public void initiate(){
        gyro = new Gyro(this);
        drive = new Drive(this);
        lifter = new lift(hardwareMap);
        sense = new sensors(this);
        //vert = hardwareMap.dcMotor.get("vert");
        //LoopingRevGyro gyro= new LoopingRevGyro(this.hardwareMap, "imu");

        //UpdatingManager updatingManager = new UpdatingManager(this);
        //updatingManager.addUpdatable(gyro);
        //updatingManager.start();

    }
}
