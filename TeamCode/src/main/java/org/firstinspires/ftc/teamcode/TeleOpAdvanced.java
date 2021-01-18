package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp(name = "Teleop Advanced", group = "Tests")
public class TeleOpAdvanced extends VortechsMethods {
    //This code is based on the "Controls" spreadsheet we made in the Google Drive


    double rotation, speed;

    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        waitForStart();

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y;
            double x = -gamepad1.left_stick_x; //flipped


            //slow rotation using triggers
            if (gamepad1.left_trigger > 0.05) {
                rotation = -gamepad1.left_trigger;
            } else if (gamepad1.right_trigger > 0.05) {
                rotation = gamepad1.right_trigger;
            }
            if (gamepad1.dpad_left) {
                turnRelative(-90);
            } else if (gamepad1.dpad_right) {
                turnRelative(90);
            }

            //the x button slows down speed
            if (gamepad1.x) {
                speed = 1.0;
            } else {
                speed = 0.25;
            }
            if(gamepad2.left_bumper) {
                leftOutTake.setPower(-1 * speed);
            } else {
                leftOutTake.setPower(0);
            }
            if (gamepad2.right_bumper) {
                rightOutTake.setPower(1 * speed);
            } else {
                rightOutTake.setPower(0);
            }
            if(gamepad2.a) {
                launch(0.5,1);
            }

            frontLeft.setPower((y + x + rotation) * speed);
            frontRight.setPower((y + x - rotation) * speed);
            backLeft.setPower((y - x + rotation) * speed);
            backRight.setPower((y - x - rotation) * speed);

            idle();

        }
    }
}