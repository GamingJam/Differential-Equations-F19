package Solution;

import javafx.scene.chart.XYChart;


public class Exact {
    private double c, x0;
    private double step, lim;
    private XYChart.Series<Number, Number> seriesE;


    Exact(double x0, double y0, int steps, double lim) {
        this.x0 = x0;
        this.step = (lim - x0) / steps;
        this.lim = lim;
        this.c = ((1 / (-1 * Math.exp(x0) + y0)) + x0);
        seriesE = new XYChart.Series<>();
        seriesE.setName("Exact");
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        double x = x0;

        while (x < lim) {
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, calculation(x)));
            x += step;
        }

        return seriesE;
    }

    private double calculation(double x) {
        return (Math.exp(x) + 1 / (c - x));
    }


    /*
    private BigDecimal calculation(float x) {
        BigDecimal b = new BigDecimal((Math.exp(x) + 1 / (c - x)), MathContext.DECIMAL64);
        return b;
    }
    */
}
