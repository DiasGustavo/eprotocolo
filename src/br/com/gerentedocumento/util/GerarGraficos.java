package br.com.gerentedocumento.util;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

public class GerarGraficos {
	
	private LineChartModel lineGrafico;
	
	public GerarGraficos(){
		gerarGrafico();
	}

	public LineChartModel getLineGrafico() {
		return lineGrafico;
	}

	public void setLineGrafico(LineChartModel lineGrafico) {
		this.lineGrafico = lineGrafico;
	}
	
	public void gerarGrafico(){
		ChartSeries processos = new ChartSeries();
		processos.setLabel("Processos");
		processos.set("abril", 200);
		processos.set("maio", 50);
		processos.set("junho", 290);
		
		
		
		lineGrafico.addSeries(processos);
		lineGrafico.setTitle("Processos");
		
		lineGrafico.setLegendPosition("e");
		lineGrafico.setShowPointLabels(true);
		lineGrafico.getAxes().put(AxisType.X, new CategoryAxis("Years"));
		Axis yAxis = lineGrafico.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}
}
