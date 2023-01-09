package ru.galeev.labone;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.galeev.labone.util.ComplexNumber;
import ru.galeev.labone.util.ComplexVector;

import java.util.Arrays;
import java.util.Random;

import static ru.galeev.labone.util.StringUtils.isEmpty;

public class HelloController {

    private final Random random = new Random();
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField result;

    @FXML
    private TextField addedX;
    @FXML
    private TextField addedY;
    @FXML
    private TextField addedXIndex;
    @FXML
    private TextField addedYIndex;

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

    @FXML
    protected void addToX() {
        String vectorValue = x.getText();
        String indexValue = addedXIndex.getText();
        String addedValue = addedX.getText();
        ComplexVector result;

        if (isEmpty(vectorValue)) {
            result = new ComplexVector(new ComplexNumber[]{parseComplexNumber(addedValue)});
        } else {
            result = parseVector(vectorValue);
            if (isEmpty(indexValue)) {
                result.addToEnd(parseComplexNumber(addedValue));
            } else {
                result.addElement(Integer.parseInt(indexValue),
                        parseComplexNumber(addedValue));
            }
        }
        x.setText(result.toString());
    }

    @FXML
    protected void addToY() {
        String vectorValue = y.getText();
        String indexValue = addedYIndex.getText();
        String addedValue = addedY.getText();
        ComplexVector result;

        if (isEmpty(vectorValue)) {
            result = new ComplexVector(new ComplexNumber[]{parseComplexNumber(addedValue)});
        } else {
            result = parseVector(vectorValue);
            if (isEmpty(indexValue)) {
                result.addToEnd(parseComplexNumber(addedValue));
            } else {
                result.addElement(Integer.parseInt(indexValue),
                        parseComplexNumber(addedValue));
            }
        }
        x.setText(result.toString());
    }

    @FXML
    protected void deleteX() {
        String addedValue = addedX.getText();
        String indexValue = addedXIndex.getText();
        String vectorValue = x.getText();
        ComplexVector result;

        if (isEmpty(vectorValue)) {
            throw new IllegalArgumentException("Vector value is null");
        } else {
            result = parseVector(vectorValue);
            if (!isEmpty(indexValue)) {
                result.removeElement(Integer.parseInt(indexValue), null);
            } else if (!isEmpty(addedValue)) {
                result.removeElement(parseComplexNumber(addedValue));
            }
        }

        x.setText(result.toString());
    }

    @FXML
    protected void deleteY() {
        String addedValue = addedY.getText();
        String indexValue = addedYIndex.getText();
        String vectorValue = y.getText();
        ComplexVector result;

        if (isEmpty(vectorValue)) {
            throw new IllegalArgumentException("Vector value is null");
        } else {
            result = parseVector(vectorValue);
            if (!isEmpty(indexValue)) {
                result.removeElement(Integer.parseInt(indexValue), null);
            } else if (!isEmpty(addedValue)) {
                result.removeElement(parseComplexNumber(addedValue));
            }
        }

        y.setText(result.toString());
    }

    private ComplexNumber[] generateVector() {
        ComplexNumber[] vector = new ComplexNumber[3];
        for (int i = 0; i < 3; i++) {
            vector[i] = new ComplexNumber(random.nextInt() % 10, random.nextInt() % 10);
        }
        return vector;
    }

    private ComplexVector parseVector(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException("Text is blank!");
        }
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

    private ComplexNumber parseComplexNumber(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException("Text is blank!");
        }

        String[] cpxNumberStr = str.replace("i", "")
                .split(" \\+ ");

        return new ComplexNumber(
                Integer.parseInt(cpxNumberStr[0]),
                Integer.parseInt(cpxNumberStr[1]
                        .replace("(", "")
                        .replace(")", "")));
    }
}