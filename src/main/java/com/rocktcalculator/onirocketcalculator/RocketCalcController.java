package com.rocktcalculator.onirocketcalculator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class RocketCalcController {
    @FXML private ComboBox<FuelType> cmb_fuel;
    @FXML private ComboBox<String> cmb_oxidizer;
    @FXML private Label lbl_thrust;
    @FXML private Label lbl_thrust_modifier;
    @FXML private Label lbl_takeoff;
    @FXML private TextField txt_rocketweight;
    @FXML private TextField txt_dest;
    @FXML private TextField txt_fuelcap;
    @FXML private ImageView imgv_weight;
    @FXML private ImageView imgv_dest;
    @FXML private ImageView imgv_fuelcap;
    @FXML private ImageView imgv_fuel;
    @FXML private Button btn_calculate;
    @FXML private Label lbl_summary_totalweight;
    @FXML private Label lbl_summary_oxidizer;
    @FXML private Label lbl_summary_fuel;
    @FXML private Label lbl_summary_takeoff;
    @FXML private Label lbl_summary_finalanswer;

    @FXML protected void initialize(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) { // this is the important line
                return change;
            }

            return null;
        };

        txt_rocketweight.setTextFormatter(new TextFormatter<>(filter));
        txt_dest.setTextFormatter(new TextFormatter<>(filter));
        txt_fuelcap.setTextFormatter(new TextFormatter<>(filter));

        cmb_fuel.setItems(FXCollections.observableArrayList(
                new FuelType("Steam", 20),
                        new FuelType("Petroleum",40),
                        new FuelType("Liquid Hydrogen",60)));
        cmb_oxidizer.setItems(FXCollections.observableArrayList("Oxylite", "Liquid Oxygen"));

        //Always select one
        cmb_oxidizer.getSelectionModel().select(0);

    }

    @FXML protected void txt_rocketweight_textChanged(){
        checkParameters();
    }

    @FXML protected void txt_dest_textChanged(){
        checkParameters();
    }

    @FXML protected void txt_fuelcap_textChanged(){
        checkParameters();
    }

    @FXML protected void cmb_fuel_selectionChanged(){
        cmb_oxidizer.setDisable(Objects.equals(cmb_fuel.getSelectionModel().getSelectedItem().getFuelName(), "Steam"));

        recalculateFuelEfficiency();
    }

    @FXML protected void cmb_oxidizer_selectionChanged(){
        recalculateFuelEfficiency();
    }

    @FXML protected void btn_calculate_mouseClicked(){
        int rocketWeight = Integer.parseInt(txt_rocketweight.getText());
        int destination = Integer.parseInt(txt_dest.getText());
        int maxFuelLoad = Integer.parseInt(txt_fuelcap.getText());
        double thrust = Double.parseDouble(lbl_takeoff.getText());

        CalculatorIteration rocketCalc = new CalculatorIteration(rocketWeight, destination, maxFuelLoad, thrust);

        FuelIndicator summary = rocketCalc.BeginIteration();

        lbl_summary_totalweight.setText(String.valueOf(summary.getSumFuelWeight()));
        lbl_summary_fuel.setText(String.valueOf(summary.getFuelWeight()));
        lbl_summary_oxidizer.setText(String.valueOf(summary.getFuelWeight()));
        lbl_summary_takeoff.setText(String.valueOf(summary.getTotalWeight() + rocketCalc.getRocketWeight()));
        lbl_summary_finalanswer.setText((summary.isInLoad())?"YES! :D": "NO :(");

    }

    private void recalculateFuelEfficiency(){
        if(cmb_oxidizer.isDisabled()){
            cmb_oxidizer.getSelectionModel().select(0);
        }

        int fuelEfficiency = cmb_fuel.getSelectionModel().getSelectedItem() != null ? cmb_fuel.getSelectionModel().getSelectedItem().getFuelEfficiency(): 0;
        double fuelEfficiencyModifier = Objects.equals(cmb_oxidizer.getSelectionModel().getSelectedItem(), "Oxylite") ?1:1.33;

        lbl_thrust.setText(String.valueOf(fuelEfficiency));
        lbl_thrust_modifier.setText(String.valueOf(fuelEfficiencyModifier));

        lbl_takeoff.setText(String.format("%.2f",(fuelEfficiency*fuelEfficiencyModifier)));

        checkParameters();
    }

    private void checkParameters() {
        boolean ready = true;
        Image imgCross = null;
        Image imgTick = null;

        try{
            imgCross = new Image(Objects.requireNonNull(getClass().getResource("/icons/x.png")).openStream());
            imgTick = new Image(Objects.requireNonNull(getClass().getResource("/icons/check.png")).openStream());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        if(Objects.equals(txt_rocketweight.getText().trim(), "")){
            ready = false;
            imgv_weight.setImage(imgCross);
        }else {
            imgv_weight.setImage(imgTick);
        }

        if(Objects.equals(txt_dest.getText().trim(), "")){
            ready = false;
            imgv_dest.setImage(imgCross);
        }else {
            imgv_dest.setImage(imgTick);
        }

        if(Objects.equals(txt_fuelcap.getText().trim(), "")){
            ready = false;
            imgv_fuelcap.setImage(imgCross);
        }else {
            imgv_fuelcap.setImage(imgTick);
        }

        if(Double.parseDouble(lbl_takeoff.getText().trim()) == 0){
            ready = false;
            imgv_fuel.setImage(imgCross);
        }else {
            imgv_fuel.setImage(imgTick);
        }

        btn_calculate.setDisable(!ready);
    }

    @FXML private void openInformationWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(InformationViewApplication.class.getResource("information-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Information");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}