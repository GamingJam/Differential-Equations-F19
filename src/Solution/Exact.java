package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;

public class Exact {
    private float c, x0;
    private float step, end;
    private XYChart.Series<Number, Number> seriesE;

    Exact(float x0, float y0, float step, float end) {
        this.x0 = x0;
        this.step = step;
        this.end = end;
        this.c = (float) (1 / (-1 * Math.exp(x0) + y0) + x0);
        seriesE = new XYChart.Series<>();
        seriesE.setName("Exact");
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();

        float x = x0;


        while (x < end) {
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, calculation(x)));

            x += step;

        }

        System.out.println(x + "  " + calculation(x).toString());
        return seriesE;
    }

    private BigDecimal calculation(float x) {
        BigDecimal b = new BigDecimal((Math.exp(x) + 1 / (c - x)), MathContext.DECIMAL64);
        return b;
    }

}
