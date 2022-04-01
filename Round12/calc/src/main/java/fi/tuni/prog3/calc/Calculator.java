package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Calculator extends Application {

    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Calculator");
        
        var fieldOp1 = new TextField();
        var fieldOp2 = new TextField();
        fieldOp1.setId("fieldOp1");
        fieldOp2.setId("fieldOp2");
        
        var labelOp1 = new Label("First operand:");
        labelOp1.setId("labelOp1");
        var labelOp2 = new Label("Second operand:");
        labelOp2.setId("labelOp2");
        
        var btnAdd = new Button();
        btnAdd.setId("btnAdd");
        btnAdd.setText("Add");
        
        var btnSub = new Button();
        btnSub.setId("btnSub");
        btnSub.setText("Subtract");
        
        var btnMul = new Button();
        btnMul.setId("btnMul");
        btnMul.setText("Multiply");
        
        var btnDiv = new Button();
        btnDiv.setId("btnDiv");
        btnDiv.setText("Divide");
        
        var fieldRes = new Label();
        fieldRes.setId("labelRes");
        fieldRes.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),new Insets(5))));
        fieldRes.setText("");
        
        var labelRes = new Text("Result:");
        labelRes.setId("labelRes");
        
        var grid = new GridPane();
        
        var scene = new Scene(grid,250,100);
        
        stage.setScene(scene);
        grid.add(labelOp1, 0, 0,2,1);
        grid.add(fieldOp1, 2, 0,2,1);
        grid.add(labelOp2,0,1,2,1);
        grid.add(fieldOp2,2,1,2,1);
        
        grid.add(btnAdd, 0, 2);
        grid.add(btnSub, 1, 2);
        grid.add(btnMul, 2, 2);
        grid.add(btnDiv, 3, 2);
        
        grid.add(labelRes,0,3); 
        grid.add(fieldRes,2,3);
        stage.show();
        
        btnAdd.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                double a = Double.parseDouble(fieldOp1.getText());
                double b = Double.parseDouble(fieldOp2.getText());
                double result = a+b;
                fieldRes.setText(String.format("%f",result));
            }
        });
        btnSub.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                double a = Double.parseDouble(fieldOp1.getText());
                double b = Double.parseDouble(fieldOp2.getText());
                double result = a-b;
                fieldRes.setText(String.format("%f",result));
            }
        });
        btnMul.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                double a = Double.parseDouble(fieldOp1.getText());
                double b = Double.parseDouble(fieldOp2.getText());
                double result = a*b;
                fieldRes.setText(String.format("%f",result));
            }
        });
        btnDiv.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                double a = Double.parseDouble(fieldOp1.getText());
                double b = Double.parseDouble(fieldOp2.getText());
                double result = a/b;
                fieldRes.setText(String.format("%f",result));
            }
        });        
    }

    public static void main(String[] args) {
        launch();
    }

}