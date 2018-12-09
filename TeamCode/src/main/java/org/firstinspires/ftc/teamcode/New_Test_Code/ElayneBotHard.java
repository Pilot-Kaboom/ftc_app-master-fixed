package org.firstinspires.ftc.teamcode.New_Test_Code;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Keith Harder on 7/23/2018.
 */

public abstract class ElayneBotHard extends superSuperClass {

    public DcMotor left;
    public DcMotor right;

    public CRServo arm;


    @Override
    public void initiate() {

        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");

        arm = hardwareMap.crservo.get("arm");

        left.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    public void powerLeft(double power){

        left.setPower(power);
    }
    public void powerRight(double power){

        right.setPower(power);
    }


}
