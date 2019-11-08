package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class ImprovedEuler {
    private double lim, step, x0, y0, c;
    private double maxError;
    private int tas, taf;
    private XYChart.Series<Number, Number> seriesE;
    private XYChart.Series<Number, Number> seriesEE;
    private XYChart.Series<Number, Number> seriesEAE;
    private int steps;

    ImprovedEuler(double x0, double y0, int steps, double lim, int tas, int taf) {
        this.x0 = x0;
        this.y0 = y0;
        this.lim = lim;
        this.step = (lim - x0) / steps;
        this.steps = steps;
        this.c = (1 / (-1 * Math.exp(x0) + y0) + x0);
        seriesE = new XYChart.Series<Number, Number>();
        seriesE.setName("Improved Euler");
        seriesEE = new XYChart.Series<>();
        seriesEE.setName("Improved Euler Error");
        seriesEAE = new XYChart.Series<>();
        seriesEAE.setName("Improved Euler Total Approximation Error");
        this.tas = tas;
        this.taf = taf;
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        seriesE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        double x = x0, k1, k2, y1, y = y0;
        while (x + step <= lim) {
            k1 = getDEValue(x,y);
            k2 = getDEValue(x+step,y+step*k1);
            y1 = y + (step / 2) * (k1 + k2);
            y = y1;
            x += step;
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, y));
        }
        return seriesE;
    }

    XYChart.Series<Number, Number> getError() {
        seriesEE.getData().clear();
        seriesEE.getData().add(new XYChart.Data<Number, Number>(x0, 0));
        double x = x0, k1, k2, y1, tae, y = y0;
        while (x + step <= lim) {
            k1 = getDEValue(x,y);
            k2 = getDEValue(x+step,y+step*k1);
            y1 = y + (step / 2) * (k1 + k2);
            y = y1;
            x += step;
            tae = Math.abs(calculation(x) - y);
            seriesEE.getData().add(new XYChart.Data<Number, Number>(x, tae));

            if (tae > maxError) maxError = tae;
        }
        return seriesEE;
    }

    double getMaxError() {
        return maxError;
    }

    XYChart.Series<Number, Number> getTAError() {
        seriesEAE.getData().clear();
        ImprovedEuler ieu;
        for (int i = tas; i <= taf; i++) {
            ieu = new ImprovedEuler(x0, y0, i, lim, 0, 0);
            ieu.getError();
            seriesEAE.getData().add(new XYChart.Data<Number, Number>(i, ieu.getMaxError()));
        }
        return seriesEAE;
    }

    private double calculation(double x) {
        return (Math.exp(x) + 1 / (c - x));
    }

    private double getDEValue(double x, double y){
        return Math.exp(2*x) + Math.exp(x) + y*y - 2 * y * Math.exp(x);
    }

}
