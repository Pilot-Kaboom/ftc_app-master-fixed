package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

public abstract class RR2_AutoBot extends SuperSuperClass {

    public  Arm arm;
    public  Drive drive;
    public collecter collect;
    public lift lifter;
    public sensors sense;
    public LoopingRevGyro gyro;
    public TensorFlow tfoddd;
    public boolean doit;

    @Override
    public void initiate(){
        tfoddd = new TensorFlow(this);
        arm = new Arm(this);
        drive = new Drive(this);
        collect = new collecter(hardwareMap);
        lifter = new lift(hardwareMap);
        sense = new sensors(this);
        LoopingRevGyro gyro= new LoopingRevGyro(this.hardwareMap, "imu");

        UpdatingManager updatingManager = new UpdatingManager(this);
        updatingManager.addUpdatable(gyro);
        updatingManager.start();
        tfoddd.initVison();


    }
}

