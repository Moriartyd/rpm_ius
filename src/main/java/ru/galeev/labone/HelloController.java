package ru.galeev.labone;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.galeev.labone.util.ComplexNumber;
import ru.galeev.labone.util.ComplexVector;

import java.util.Arrays;
import java.util.Random;

public class HelloController {

    private final Random random = new Random();
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField result;

    @FXML
    void initialize() {
        x.setText("");
        y.setText("");
        result.setText("");
    }

    @FXML
    protected void onGenerateX() {
        ComplexNumber[] vector = generateVector();
        x.setText(Arrays.toString(vector)
                .replace("[", "")
                .replace("]", ""));
    }

    @FXML
    protected void onGenerateY() {
        ComplexNumber[] vector = generateVector();
        y.setText(Arrays.toString(vector)
                .replace("[", "")
                .replace("]", ""));
    }

    @FXML
    protected void onSum() {
        ComplexVector vector = ComplexVector.sum(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onDiff() {
        ComplexVector vector = ComplexVector.diff(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onMult() {
        ComplexVector vector = ComplexVector.mult(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onDiv() {
        ComplexVector vector = ComplexVector.div(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    private ComplexNumber[] generateVector() {
        ComplexNumber[] vector = new ComplexNumber[3];
        for (int i = 0; i < 3; i++) {
            vector[i] = new ComplexNumber(random.nextInt() % 10, random.nextInt() % 10);
        }
        return vector;
    }

    private ComplexVector parseVector(String str) {
        String[] strVector = str.split(", ");
        ComplexNumber[] cpxVector = new ComplexNumber[strVector.length];
        int i = 0;
        while (i < cpxVector.length) {
            String[] cpxNumberStr = strVector[i].replace("i", "")
                    .split(" \\+ ");
            ComplexNumber cpxNumber = new ComplexNumber(
                    Integer.parseInt(cpxNumberStr[0]),
                    Integer.parseInt(cpxNumberStr[1]
                            .replace("(", "")
                            .replace(")", "")));
            cpxVector[i] = cpxNumber;
            i++;
        }
        return new ComplexVector(cpxVector);
    }
}