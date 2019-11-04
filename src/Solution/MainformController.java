package Solution;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class MainformController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox eu_ch;

    @FXML
    private CheckBox ex_ch;

    @FXML
    private CheckBox i_eu_ch;

    @FXML
    private CheckBox rk_ch;

    @FXML
    private Button btn_id;

    @FXML
    private LineChart<Number, Number> main_graph;

    @FXML
    private LineChart<Number, Number> error_graph;


    @FXML
    private TextField x0_tf;

    @FXML
    private TextField y0_tf;

    @FXML
    private TextField ex_step_tf;

    @FXML
    private TextField lim_tf;

    @FXML
    void btn() {
        main_graph.getData().clear();
        error_graph.getData().clear();

        float ex_step = Float.parseFloat(ex_step_tf.getCharacters().toString());
        float y0 = Float.parseFloat(y0_tf.getCharacters().toString());
        float x0 = Float.parseFloat(x0_tf.getCharacters().toString());
        float limit = Float.parseFloat(lim_tf.getCharacters().toString());

        if (ex_ch.isSelected()) {
            Exact ex = new Exact(x0, y0, ex_step, limit);
            main_graph.getData().add(ex.getSeries());
        }

        if (eu_ch.isSelected()) {
            Euler eu = new Euler(x0, y0, ex_step, limit);
            main_graph.getData().add(eu.getSeries());
            error_graph.getData().add(eu.getError());
        }

        if (i_eu_ch.isSelected()) {
            ImprovedEuler ieu = new ImprovedEuler(x0, y0, ex_step, limit);
            main_graph.getData().add(ieu.getSeries());
            error_graph.getData().add(ieu.getError());
        }

        if (rk_ch.isSelected()) {
            RungeKutta rk = new RungeKutta(x0, y0, ex_step, limit);
            main_graph.getData().add(rk.getSeries());
            error_graph.getData().add(rk.getError());
        }

        if (!eu_ch.isSelected() && !rk_ch.isSelected() && !ex_ch.isSelected() && !i_eu_ch.isSelected()) {
            main_graph.getData().clear();
        }


    }

    @FXML
    void initialize() {
        assert btn_id != null : "fx:id=\"btn_id\" was not injected: check your FXML file 'mainform.fxml'.";
        assert ex_step_tf != null : "fx:id=\"ex_step_tf\" was not injected: check your FXML file 'mainform.fxml'.";
        assert lim_tf != null : "fx:id=\"lim_tf\" was not injected: check your FXML file 'mainform.fxml'.";
        assert main_graph != null : "fx:id=\"main_graph\" was not injected: check your FXML file 'mainform.fxml'.";
        assert x0_tf != null : "fx:id=\"x0_tf\" was not injected: check your FXML file 'mainform.fxml'.";
        assert y0_tf != null : "fx:id=\"y0_tf\" was not injected: check your FXML file 'mainform.fxml'.";
    }

}
