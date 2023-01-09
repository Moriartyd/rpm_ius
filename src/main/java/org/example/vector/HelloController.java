package org.example.vector;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.vector.util.Vector;

import java.util.Arrays;
import java.util.Random;

import static org.example.vector.util.StringUtils.isEmpty;

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
        Double[] vector = generateVector();
        x.setText(Arrays.toString(vector)
                .replace("[", "")
                .replace("]", ""));
    }

    @FXML
    protected void onGenerateY() {
        Double[] vector = generateVector();
        y.setText(Arrays.toString(vector)
                .replace("[", "")
                .replace("]", ""));
    }

    @FXML
    protected void onSum() {
        Vector vector = Vector.sum(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onDiff() {
        Vector vector = Vector.diff(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onMult() {
        Vector vector = Vector.mult(
                parseVector(x.getText()),
                parseVector(y.getText())
        );

        result.setText(vector.toString());
    }

    @FXML
    protected void onDiv() {
        Vector vector = Vector.div(
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
        Vector result;

        if (isEmpty(vectorValue)) {
            result = new Vector(new Double[]{parseDouble(addedValue)});
        } else {
            result = parseVector(vectorValue);
            if (isEmpty(indexValue)) {
                result.addToEnd(parseDouble(addedValue));
            } else {
                result.addElement(Integer.parseInt(indexValue),
                        parseDouble(addedValue));
            }
        }
        x.setText(result.toString());
    }

    @FXML
    protected void addToY() {
        String vectorValue = y.getText();
        String indexValue = addedYIndex.getText();
        String addedValue = addedY.getText();
        Vector result;

        if (isEmpty(vectorValue)) {
            result = new Vector(new Double[]{parseDouble(addedValue)});
        } else {
            result = parseVector(vectorValue);
            if (isEmpty(indexValue)) {
                result.addToEnd(parseDouble(addedValue));
            } else {
                result.addElement(Integer.parseInt(indexValue),
                        parseDouble(addedValue));
            }
        }
        x.setText(result.toString());
    }

    @FXML
    protected void deleteX() {
        String addedValue = addedX.getText();
        String indexValue = addedXIndex.getText();
        String vectorValue = x.getText();
        Vector result;

        if (isEmpty(vectorValue)) {
            throw new IllegalArgumentException("Vector value is null");
        } else {
            result = parseVector(vectorValue);
            if (!isEmpty(indexValue)) {
                result.removeElement(Integer.parseInt(indexValue), null);
            } else if (!isEmpty(addedValue)) {
                result.removeElement(parseDouble(addedValue));
            }
        }

        x.setText(result.toString());
    }

    @FXML
    protected void deleteY() {
        String addedValue = addedY.getText();
        String indexValue = addedYIndex.getText();
        String vectorValue = y.getText();
        Vector result;

        if (isEmpty(vectorValue)) {
            throw new IllegalArgumentException("Vector value is null");
        } else {
            result = parseVector(vectorValue);
            if (!isEmpty(indexValue)) {
                result.removeElement(Integer.parseInt(indexValue), null);
            } else if (!isEmpty(addedValue)) {
                result.removeElement(parseDouble(addedValue));
            }
        }

        y.setText(result.toString());
    }

    private Double[] generateVector() {
        Double[] vector = new Double[3];
        for (int i = 0; i < 3; i++) {
            vector[i] = random.nextDouble();
        }
        return vector;
    }

    private Vector parseVector(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException("Text is blank!");
        }
        String[] strVector = str.split(", ");
        Double[] dblVector = new Double[strVector.length];
        int i = 0;
        while (i < dblVector.length) {
            dblVector[i] = Double.valueOf(strVector[i]);
            i++;
        }
        return new Vector(dblVector);
    }

    private Double parseDouble(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException("Text is blank!");
        }
        return Double.parseDouble(str);
    }
}