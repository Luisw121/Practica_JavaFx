package com.example.tres_en_raya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controlador implements Initializable {
    @FXML
    Button b0;
    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button b5;
    @FXML
    Button b6;
    @FXML
    Button b7;
    @FXML
    Button b8;
    @FXML
    Button bc;
    @FXML
    Button bd;
    @FXML
    BorderPane borderPane;
    @FXML
    private Text ganadorText;
    @FXML
    RadioButton computervscomputer;
    @FXML
    RadioButton humanovshumano;
    @FXML
    RadioButton humanvscomputer;
    String negro = this.getClass().getResource("TemaNegro.css").toExternalForm();
    String rojo = this.getClass().getResource("ColorNegro.css").toExternalForm();
    ArrayList<Button> buttons = new ArrayList<>();
    int idModo = 0;
    int contadorRondas=0;
    boolean turnoJugador = false;
    boolean turnoMaquina = false;
    boolean comenzarJuego=false;
    boolean finDelJuego=false;
    boolean comenzarhumanovshumano=false;
    boolean comenzarhumanovscomputer=false;
    boolean empate=false;
    boolean win=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.add(b0);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);

    }

    public void comprobarModo(){
        if(humanovshumano.isSelected()){
            idModo=0;
            resetearTablero();
            comenzarhumanovshumano=false;
            comenzarhumanovscomputer=false;

        }
        if(computervscomputer.isSelected()){
            idModo=1;
            resetearTablero();
            comenzarhumanovshumano=false;
            comenzarhumanovscomputer=false;
        }
        if(humanvscomputer.isSelected()){
            idModo=2;
            resetearTablero();
            comenzarhumanovshumano=false;
            comenzarhumanovscomputer=false;

        }
    }

    public void Marcar(ActionEvent event){
        bc = (Button) event.getSource();
        for(int i=0;i<1;i++){
            if(comenzarhumanovshumano){
                HumanVSHuman();
                break;
            }


            if(comenzarhumanovscomputer){
                HumanVSComputer();
                break;
            }
        }
    }


    public void HumanVSHuman(){

        for(int i=0;i<1;i++) {

            if(comenzarJuego== true){
                turnoJugador=false;
                comenzarJuego=false;
            }

            if(bc.getText() == "X" || bc.getText() == "O"){
                break;
            }

            if (turnoJugador == false) {
                ganadorText.setText("Turno de X");
                bc.setText("X");
                turnoJugador = true;
                contadorRondas++;
                comprobarGanador();
                break;

            }

            if (turnoJugador == true) {
                ganadorText.setText("Turno de O");
                turnoJugador = false;
                bc.setText("O");
                contadorRondas++;
                comprobarGanador();
                break;
            }
        }
    }

    public void ComputerVSComputer() {
        turnoMaquina = false;
        while (!finDelJuego) {
            bd = buttons.get(botonlibre());

            if (turnoMaquina == false) {
                bd.setText("X");
                turnoMaquina = true;
                contadorRondas++;
                comprobarGanador();
                continue;

            }

            if (turnoMaquina == true) {
                bd.setText("O");
                turnoMaquina = false;
                contadorRondas++;
                comprobarGanador();
                continue;
            }


        }
        comprobarGanador();
    }

    public void HumanVSComputer(){
        turnoMaquina = false;

        for(int i=0;i<2;i++) {
            if(win==true){
                break;
            }

            else {
                if (turnoMaquina == false) {
                    if(bc.getText()=="X" || bc.getText()=="O"){
                        break;
                    }
                    else{
                        bc.setText("X");
                        turnoMaquina = true;
                        contadorRondas++;
                        comprobarGanador();
                        continue;
                    }

                }

                if (turnoMaquina == true) {
                    bd = buttons.get(botonlibre());
                    bd.setText("O");
                    turnoMaquina = false;
                    contadorRondas++;
                    comprobarGanador();
                }
            }

        }
        comprobarGanador();
    }


    public void resetearTablero(){
        contadorRondas=0;

        turnoJugador = false;

        empate =false;

        turnoMaquina = false;

        comenzarJuego=false;

        finDelJuego=false;

        win=false;

        for(int i=0; i<buttons.size();i++){
            buttons.get(i).setDisable(false);
            buttons.get(i).setText("");
        }
    }

    //Comenzar juegos
    public void comenzarJuego() {
        ganadorText.setText("tres en raya");
        resetearTablero();

        if (idModo == 0) {
            comenzarhumanovshumano = true;
        }
        if(idModo==1){
            ComputerVSComputer();
        }

        if (idModo == 2) {
            comenzarhumanovscomputer = true;
        }

        comenzarJuego = true;
    }


    //Comprobar el ganador
    public void comprobarGanador() {
        for (int i = 0; i < 8; i++) {
            String linea = switch (i) {
                case 0 -> b0.getText() + b1.getText() + b2.getText();
                case 1 -> b3.getText() + b4.getText() + b5.getText();
                case 2 -> b6.getText() + b7.getText() + b8.getText();
                case 3 -> b0.getText() + b4.getText() + b8.getText();
                case 4 -> b2.getText() + b4.getText() + b6.getText();
                case 5 -> b0.getText() + b3.getText() + b6.getText();
                case 6 -> b1.getText() + b4.getText() + b7.getText();
                case 7 -> b2.getText() + b5.getText() + b8.getText();
                default -> null;
            };


            if (linea.equals("XXX")) {
                ganadorText.setText("¡Gano X!");
                finDelJuego=true;
                win=true;
                contadorRondas=0;
                comenzarhumanovshumano=false;
                comenzarhumanovscomputer=false;

                for(int j=0; j<buttons.size();j++){
                    buttons.get(j).setDisable(true);
                }
            }

            else if (linea.equals("OOO")) {
                ganadorText.setText("¡Gano O!");
                finDelJuego=true;
                win=true;
                contadorRondas=0;
                comenzarhumanovshumano=false;
                comenzarhumanovscomputer=false;
                for(int j=0; j<buttons.size();j++){
                    buttons.get(j).setDisable(true);
                }

            }
        }
        if (contadorRondas==9){
            ganadorText.setText("Empate");
            empate=true;
            comenzarhumanovshumano=false;
            comenzarhumanovscomputer=false;
            finDelJuego=true;
            for(int j=0; j<buttons.size();j++) {
                buttons.get(j).setDisable(true);
            }

        }
    }
    //Boton libre disponible
    public int botonlibre(){
        int botonIA = (int) (Math.random()*9+0);
        while(!buttons.get(botonIA).getText().isEmpty()){
            botonIA= (int) (Math.random()*9+0);
        }
        return botonIA;
    }


}
