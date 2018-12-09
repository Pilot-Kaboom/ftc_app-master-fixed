package org.firstinspires.ftc.teamcode.RR2_Comp_Code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
//@TeleOp(name = "Concept: TensorFlow Object Detection", group = "Concept")
//@Disabled
public class TensorFlow {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "AXk8m93/////AAAAmSo/yH0caUMsqpjNljWxJF8YX6vQ0htlGYE6tx1nTRyclEBgdBc6yxK8FcCcBaKOJ4vuWvJSuv7t5CMUEA9Zo0NrKut9gg+VZovv+S1NoSnG7aQHhCTaDTlhaZ12MRnRxbkEj72yfSdQhZRbT+MGJ7pBGnvWtOSH/YmeK6bffeICGgdjZz9PoHg8AuWBMvWu6vezXnbAX4IzX1KTmQs8KU2ylFsNRzNhErlnZwDw4vTqRPhk6hhKVOV4iaQup/2CgY7fwblJIV/xKhJMMr7GYi8FPDlYctv4dAONrCPqUOLY0MqxhKf++TqI/kNC+kL8Xa/koNY6Cg+2LT9QEn5J819YnhtcwMzIoHzIRPb/j7NM";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private boolean right;
    private boolean left;
    private boolean center;
    private TFObjectDetector tfod;
    private final LinearOpMode tfoddd;
    public TensorFlow(LinearOpMode tfoddd){

        this.tfoddd = tfoddd;
    }
    public void initVison(){
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            tfoddd.telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        tfoddd.telemetry.addData("gold on left", Left());
        tfoddd.telemetry.addData("gold on right", Right());
        tfoddd.telemetry.addData("gold on center",Center());
        tfoddd.telemetry.addData(">", "Press Play to start tracking");
        tfoddd.telemetry.update();
    }

    public void runTFscan() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        if (tfod != null) {
            tfod.activate();
        }

        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                tfoddd.telemetry.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            tfoddd.telemetry.addData("Gold Mineral Position", "Left");

                            right=false;
                            center=false;
                            left=true;
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            tfoddd.telemetry.addData("Gold Mineral Position", "Right");
                            right=true;
                            center=false;
                            left=false;
                        } else {
                            tfoddd.telemetry.addData("Gold Mineral Position", "Center");
                            right=false;
                            center=true;
                            left=false;
                        }
                    }
                }
                tfoddd.telemetry.update();
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }

    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = tfoddd.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", tfoddd.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
    public boolean Right(){
        return(right);
    }
    public boolean Left(){
        return(left);
    }
    public boolean Center(){
        return(center);
    }
}
