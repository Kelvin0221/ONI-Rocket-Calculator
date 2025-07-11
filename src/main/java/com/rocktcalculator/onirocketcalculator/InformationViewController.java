package com.rocktcalculator.onirocketcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class InformationViewController {
    @FXML Label lbl_assumptions;
    @FXML Label lbl_disclaimer;
    @FXML Hyperlink link_git;

    @FXML
    protected void initialize(){
        String strAssumptions = """
                Input Terminologies:\s
                 \
                - Rocket Dry mass: The total weight of rocket itself without the weight of fuel.
                - Target Destination: Destination set to flight to.
                - Max Fuel Capacity: Fuel Capacity to the maximum amount rocket can take in KG.
                - Oxidizer Weight & Fuel Weight: Refer to the amount as parameter to reach target destination.
                \s
                
                Assumptions:
                - This calculator only applies to Oxygen Not Included base game.
                - Solid Thruster is being unconsidered in this calculator.
                - This calculator tries to calculate the most optimal fuel requirements in least iterations.
                - Parameters are already known to apply to the calculator.
                """
                ;

        String strDisclaimer = """
                Disclaimers:\s
                \
                This application is for non-commercial, personal use only.
                It is not affiliated with Klei Entertainment or any related entities.
                
                The application is provided "as is," without any express or implied warranties.
                This application DOES NOT guarantee it will be error-free, secure, or meet your specific needs.
                
                Developers is not liable for any direct, indirect, or consequential damages resulting from
                use or inability to use this application.
                
                You are solely responsible for your use of this application and its outcomes.
                
                This application is for entertainment and personal use only.
                And does not provide any professional advice.
                
                If you find any misinformation, issues or requests within this application,
                please contact me for correction at link below.
                """;

        lbl_assumptions.setText(strAssumptions);
        lbl_disclaimer.setText(strDisclaimer);


    }

    @FXML public void openLink(){
        try{
            Desktop.getDesktop().browse(new URL("https://github.com/Kelvin0221/ONI-Rocket-Calculator").toURI());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
