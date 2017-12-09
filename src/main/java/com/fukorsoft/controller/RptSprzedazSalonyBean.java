package com.fukorsoft.controller;
 
import com.fukorsoft.dao.SprzedazDao;
import com.fukorsoft.dao.SprzedazDaoImpl;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
 
@ManagedBean
@SessionScoped
//@ViewScoped
public class RptSprzedazSalonyBean implements Serializable {
 
    private BarChartModel sprzedazSalonyModel;
    private long maxValue;
    private Date dataOd;
    private Date dataDo;      
    
    public RptSprzedazSalonyBean() {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        this.dataOd = c.getTime();
        
        c.set(Calendar.DAY_OF_MONTH,
              c.getActualMaximum(Calendar.DAY_OF_MONTH));        
        this.dataDo = c.getTime();    
    }

    public Date getDataOd() {
        return dataOd;
    }
    
    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }    
    
    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public BarChartModel getSprzedazSalonyModel() {
        return sprzedazSalonyModel;
    }
 
    private void createAnimatedModels() {                 
        String dataOd = "";
        String dataDo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        if (this.dataOd != null)
            dataOd = sdf.format(this.dataOd);
        
        if (this.dataDo != null)
            dataDo = sdf.format(this.dataDo);

        sprzedazSalonyModel = initBarModel();
        sprzedazSalonyModel.setTitle("Wartość sprzedaży [PLN] wg salonów [od: " + dataOd + " do: " + dataDo + "]");
        sprzedazSalonyModel.setAnimate(true);
        sprzedazSalonyModel.setLegendPosition("ne");
        sprzedazSalonyModel.setLegendPlacement(LegendPlacement.OUTSIDE);
        sprzedazSalonyModel.setShowPointLabels(true);
        sprzedazSalonyModel.setShowDatatip(false);
        Axis yAxis = sprzedazSalonyModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(this.maxValue);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        SprzedazDao sprzedazDao = new SprzedazDaoImpl();
        List<Long> wynikiSprzedazy = new ArrayList<Long>();
        
        wynikiSprzedazy.add(0, sprzedazDao.sumByZakladId(1, this.dataOd, this.dataDo));
        wynikiSprzedazy.add(1, sprzedazDao.sumByZakladId(2, this.dataOd, this.dataDo));
        wynikiSprzedazy.add(2, sprzedazDao.sumByZakladId(3, this.dataOd, this.dataDo));
        wynikiSprzedazy.add(3, sprzedazDao.sumByZakladId(4, this.dataOd, this.dataDo));                
        
        this.maxValue = Collections.max(wynikiSprzedazy) + 100;
        
        ChartSeries bielszowice = new ChartSeries();
        bielszowice.setLabel("Bielszowice");
        bielszowice.set("Wyniki", wynikiSprzedazy.get(0));
        
        ChartSeries godula = new ChartSeries();
        godula.setLabel("Godula");
        godula.set("Wyniki", wynikiSprzedazy.get(1));
        
        ChartSeries halemba = new ChartSeries();
        halemba.setLabel("Halemba");
        halemba.set("Wyniki", wynikiSprzedazy.get(2));
        
        ChartSeries karmanskie = new ChartSeries();
        karmanskie.setLabel("Karmańskie");
        karmanskie.set("Wyniki", wynikiSprzedazy.get(3));
 
        model.addSeries(bielszowice);
        model.addSeries(godula);
        model.addSeries(halemba);
        model.addSeries(karmanskie);
         
        return model;
    }         
}