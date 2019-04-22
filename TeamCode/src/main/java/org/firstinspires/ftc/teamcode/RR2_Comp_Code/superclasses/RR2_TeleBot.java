package org.firstinspires.ftc.teamcode.RR2_Comp_Code.superclasses;


import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Arm;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.Drive;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.collecter;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.lift;
import org.firstinspires.ftc.teamcode.RR2_Comp_Code.subclasses.sensors;

public abstract class RR2_TeleBot extends SuperSuperClass {

    public Arm arm;
    public Drive drive;
    public collecter collect;
    public lift lifter;
    public sensors sense;
    public boolean doit;
    public boolean backwards;
    public double othapos;

    @Override
    public void initiate(){
        arm = new Arm(this);
        drive = new Drive(this);
        collect = new collecter(hardwareMap);
        lifter = new lift(hardwareMap);
        sense = new sensors(this);
        collect.autoflap = false;
        backwards=false;
        if (gamepad2.x){
            arm.resetArmEc();
        }
        if (gamepad1.x && atime.seconds() > .5) {
            backwards = !backwards;
            atime.reset();

        }
        arm.Armtelem();
        telemetry.addData("back", backwards);
        telemetry.update();
    }
}
