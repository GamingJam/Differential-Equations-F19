package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;

public class Euler {
    private double lim, step, x0, y0, c;
    private double maxError;
    private XYChart.Series<Number, Number> seriesE;
    private XYChart.Series<Number, Number> seriesEE;
    private XYChart.Series<Number, Number> seriesEAE;
    private int tas, taf;

    Euler(double x0, double y0, int steps, double lim, int tas, int taf) {
        this.x0 = x0;
        this.y0 = y0;
        this.lim = lim;
        this.step = (lim - x0) / steps;
        this.c = ((1 / (-1 * Math.exp(x0) + y0)) + x0);

        seriesE = new XYChart.Series<>();
        seriesE.setName("Euler");
        seriesEE = new XYChart.Series<>();
        seriesEE.setName("Euler Error");
        seriesEAE = new XYChart.Series<>();
        seriesEAE.setName("Euler Total Approximation Error");

        this.tas = tas;
        this.taf = taf;
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        seriesE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        double x = x0, y = y0, y1;

        while (x + step <= lim) {
            y1 = y + step * getDEValue(x, y);
            y = y1;
            x += step;
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, y));
        }
        return seriesE;
    }

    XYChart.Series<Number, Number> getError() {

        seriesEE.getData().clear();
        double x = x0;
        double y = y0;
        double y1;
        double tae;
        seriesEE.getData().add(new XYChart.Data<Number, Number>(x, 0));

        while (x + step <= lim) {
            y1 = y + step * getDEValue(x, y);
            y = y1;
            x += step;

            tae = Math.abs(calculation(x) - y);

            seriesEE.getData().add(new XYChart.Data<Number, Number>(x, tae));

            if (tae > maxError) {
                maxError = tae;
            }


        }
        return seriesEE;
    }

    private double getMaxError() {
        return maxError;
    }

    XYChart.Series<Number, Number> getTAError() {
        seriesEAE.getData().clear();
        Euler eu;
        for (int i = tas; i <= taf; i++) {
            eu = new Euler(x0, y0, i, lim, 0, 0);
            eu.getError();
            seriesEAE.getData().add(new XYChart.Data<Number, Number>(i, eu.getMaxError()));
        }
        return seriesEAE;
    }

    private double calculation(double x) {
        return (Math.exp(x) + 1 / (c - x));
    }

    private double getDEValue(double x, double y) {
        return Math.exp(2 * x) + Math.exp(x) + y * y - 2 * y * Math.exp(x);
    }
}
