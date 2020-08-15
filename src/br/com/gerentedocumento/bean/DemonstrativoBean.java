package br.com.gerentedocumento.bean;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.gerentedocumento.dao.ParecerDAO;
import br.com.gerentedocumento.domain.Parecer;

@ManagedBean
@ViewScoped
public class DemonstrativoBean {
	
	private Date dataInicio;
	private Date dataFim;
	private LineChartModel lineGrafico;	
	

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public LineChartModel getLineGrafico() {
		return lineGrafico;
	}

	public void setLineGrafico(LineChartModel lineGrafico) {
		this.lineGrafico = lineGrafico;
	}
	
	/*@PostConstruct
	public void init() throws ParseException{
		demonstrativos();
	}*/
	
	public DemonstrativoBean() throws ParseException{
		demonstrativos();
	}

	public void demonstrativos() throws ParseException {

		lineGrafico = buscarPorPeriodo();
		
		lineGrafico.setTitle("Processos");
		lineGrafico.setLegendPosition("e");
		lineGrafico.setShowPointLabels(true);
		

		lineGrafico.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
		Axis yAxis = lineGrafico.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setTickFormat("%.0f");
		yAxis.setMin(0);
		yAxis.setMax(600);
	}
	
	public LineChartModel buscarPorPeriodo() throws ParseException {
		lineGrafico = new LineChartModel();
		ParecerDAO pdao = new ParecerDAO();
		LineChartModel model = new LineChartModel();
		
		ChartSeries conformidadeLine = new ChartSeries();
		ChartSeries confParcialLine = new ChartSeries();
		ChartSeries desconformidadeLine = new ChartSeries();
		ChartSeries semStatusLine = new ChartSeries();
		
		
        conformidadeLine.setLabel("Conformidade");
        confParcialLine.setLabel("Confor. Parcial");
        desconformidadeLine.setLabel("DesConformidade");
        semStatusLine.setLabel("Sem Status");

		List<Parecer> listaPareceres = pdao.buscarPorPeriodo(this.dataInicio,this.dataFim);
		
		int mes = 12;
		int conformidade = 0;
		int conforParcial = 0;
		int desconformidade = 0;
		int semStatus = 0;

		for (int j = 1; j <= mes; j++) {
			for (int i = 0; i < listaPareceres.size(); i++) {
				Parecer parecerTemp = (Parecer) listaPareceres.get(i);
				GregorianCalendar dataCal = new GregorianCalendar();
				dataCal.setTime(parecerTemp.getDataEntrada());
		    	
		    	int month = dataCal.get(Calendar.MONTH);
				if ( month+1 == j) {
					if (parecerTemp.getStatus().equals("conformidade")) {
						conformidade++;
					}
					if (parecerTemp.getStatus().equals("Conformidade parcial")) {
						conforParcial++;
					}
					if (parecerTemp.getStatus().equals("desconformidade")) {
						desconformidade++;
					}else{
						semStatus++;
					}
				}
			}
			System.out.println("Mês: " + j + "\n conformidade: " + conformidade + "\n Conf. Parcial: " + conforParcial
					+ "\n desconformidade: " + desconformidade + "\n Sem Status: " + semStatus);
			conformidadeLine.set(j, conformidade);
			confParcialLine.set(j, conforParcial);
			desconformidadeLine.set(j, desconformidade);
			semStatusLine.set(j, semStatus);
			
			
			
			conformidade = 0;
			conforParcial = 0;
			desconformidade = 0;
			semStatus = 0;
		}
		
		model.addSeries(conformidadeLine);
		model.addSeries(confParcialLine);
		model.addSeries(desconformidadeLine);
		model.addSeries(semStatusLine);
		
		return model;

	}
	
	
}
