package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class RungeKutta {

    private double lim, step, x0, y0, c;
    private double maxError;
    private int tas, taf;
    private XYChart.Series<Number, Number> seriesE;
    private XYChart.Series<Number, Number> seriesEE;
    private XYChart.Series<Number, Number> seriesEAE;

    RungeKutta(double x0, double y0, int steps, double lim, int tas, int taf) {
        this.x0 = x0;
        this.y0 = y0;
        this.lim = lim;
        this.step = (lim - x0) / steps;
        this.c = (1 / (-1 * Math.exp(x0) + y0) + x0);
        seriesE = new XYChart.Series<Number, Number>();
        seriesE.setName("Runge Kutta");
        seriesEE = new XYChart.Series<>();
        seriesEE.setName("Runge Kutta Error");
        seriesEAE = new XYChart.Series<>();
        seriesEAE.setName("Runge Kutta Approximation Error");
        this.tas = tas;
        this.taf = taf;
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        seriesE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        double x = x0, y = y0, k1, k2, k3, k4, y1;
        while (x + step <= lim) {

            k1 = GetDEValue(x,y);
            k2 = GetDEValue(x + step/2, y + (step/2)*k1);
            k3 = GetDEValue(x + step/2, y + (step/2)*k2);
            k4 = GetDEValue(x+step,y+(step*k3));
            y1 = y + (step / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
            y = y1;
            x += step;
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, y));
        }
        return seriesE;
    }

    XYChart.Series<Number, Number> getError() {
        seriesEE.getData().clear();
        seriesEE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        System.out.println(x0 + " " + y0);
        double x = x0, y = y0, k1, k2, k3, k4, y1, tae;

        while (x + step <= lim) {

            k1 = GetDEValue(x,y);
            k2 = GetDEValue(x + step/2, y + (step/2)*k1);
            k3 = GetDEValue(x + step/2, y + (step/2)*k2);
            k4 = GetDEValue(x+step,y+(step*k3));
            y1 = y + (step / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
            y = y1;
            x += step;

            tae = Math.abs(calculation(x) - y);
            if (tae > maxError){
                maxError = tae;
            }

            seriesEE.getData().add(new XYChart.Data<Number, Number>(x, tae));

        }
        return seriesEE;
    }

    private double GetDEValue(double x, double y){
        return Math.exp(2*x) + Math.exp(x) + y*y - 2 * y * Math.exp(x);
    }

    private double getMaxError(){
        return maxError;
    }
    XYChart.Series<Number, Number> getTAError(){
        seriesEAE.getData().clear();
        RungeKutta rk;
        for (int i = tas; i <= taf; i++) {
            rk = new RungeKutta(x0, y0, i, lim, 0, 0);
            rk.getError();
            seriesEAE.getData().add(new XYChart.Data<Number, Number>(i,rk.getMaxError()));
        }
        return seriesEAE;
    }

    private double calculation(double x) {
        return (Math.exp(x) + 1 / (c - x));
    }

}
