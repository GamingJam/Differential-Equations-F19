package Solution;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


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
    private LineChart<Number, Number> ta_error_graph;


    @FXML
    private TextField x0_tf;

    @FXML
    private TextField y0_tf;

    @FXML
    private TextField ex_step_tf;

    @FXML
    private TextField lim_tf;

    @FXML
    private TextField taf_tf;

    @FXML
    private TextField tas_tf;

    @FXML
    private ImageView jpg_id;
    private int cnt;

    @FXML
    void btn() {

        if (cnt == 0) {
            jpg_id.opacityProperty().setValue(0.01);
            cnt = 1;
        } else {
            if (cnt == 1) {
                jpg_id.opacityProperty().setValue(0.025);
                cnt = 2;
            } else {
                if (cnt == 2) {
                    jpg_id.opacityProperty().setValue(0.0);
                    cnt = 0;
                }
            }
        }

        main_graph.getData().clear();
        error_graph.getData().clear();
        ta_error_graph.getData().clear();

        int steps = Integer.parseInt(ex_step_tf.getCharacters().toString());
        float y0 = Float.parseFloat(y0_tf.getCharacters().toString());
        float x0 = Float.parseFloat(x0_tf.getCharacters().toString());
        float limit = Float.parseFloat(lim_tf.getCharacters().toString());
        int tas = Integer.parseInt(tas_tf.getCharacters().toString());
        int taf = Integer.parseInt(taf_tf.getCharacters().toString());

        if (eu_ch.isSelected()) {
            Euler eu = new Euler(x0, y0, steps, limit, tas, taf);
            main_graph.getData().add(eu.getSeries());
            ta_error_graph.getData().add(eu.getTAError());
            error_graph.getData().add(eu.getError());
        }

        if (i_eu_ch.isSelected()) {
            ImprovedEuler ieu = new ImprovedEuler(x0, y0, steps, limit, tas, taf);
            main_graph.getData().add(ieu.getSeries());
            ta_error_graph.getData().add(ieu.getTAError());
            error_graph.getData().add(ieu.getError());
        }

        if (rk_ch.isSelected()) {
            RungeKutta rk = new RungeKutta(x0, y0, steps, limit, tas, taf);
            main_graph.getData().add(rk.getSeries());
            ta_error_graph.getData().add(rk.getTAError());
            error_graph.getData().add(rk.getError());
        }

        if (ex_ch.isSelected()) {
            Exact ex = new Exact(x0, y0, steps, limit);
            main_graph.getData().add(ex.getSeries());
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
