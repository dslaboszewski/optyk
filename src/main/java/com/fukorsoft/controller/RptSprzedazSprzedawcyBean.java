package com.fukorsoft.controller;

import com.fukorsoft.dao.SprzedazDao;
import com.fukorsoft.dao.SprzedazDaoImpl;
import com.fukorsoft.dao.UzytkownikDao;
import com.fukorsoft.dao.UzytkownikDaoImpl;
import com.fukorsoft.entity.Uzytkownik;
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
public class RptSprzedazSprzedawcyBean implements Serializable {

    private BarChartModel sprzedazSprzedawcyModel;
    private long maxValue = 0;
    private Date dataOd;
    private Date dataDo;        
    
    public RptSprzedazSprzedawcyBean() {
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

    public BarChartModel getSprzedazSprzedawcyModel() {
        return sprzedazSprzedawcyModel;
    }

    private void createAnimatedModels() {
        String dataOd = "";
        String dataDo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        if (this.dataOd != null) {
            dataOd = sdf.format(this.dataOd);
        }

        if (this.dataDo != null) {
            dataDo = sdf.format(this.dataDo);
        }

        sprzedazSprzedawcyModel = initBarModel();
        sprzedazSprzedawcyModel.setTitle("Wartość sprzedaży [PLN] wg sprzedawców [od: " + dataOd + " do: " + dataDo + "]");
        sprzedazSprzedawcyModel.setAnimate(true);
        sprzedazSprzedawcyModel.setLegendPosition("ne");
        sprzedazSprzedawcyModel.setLegendPlacement(LegendPlacement.OUTSIDE);
        sprzedazSprzedawcyModel.setShowPointLabels(true);
        sprzedazSprzedawcyModel.setShowDatatip(false);
        Axis yAxis = sprzedazSprzedawcyModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(this.maxValue);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        SprzedazDao sprzedazDao = new SprzedazDaoImpl();
        long wynikSprzedazy;
        UzytkownikDao uzytkownikDao = new UzytkownikDaoImpl();

        for (Uzytkownik uz : uzytkownikDao.findSprzedawcy()) {
            wynikSprzedazy = sprzedazDao.sumBySprzedawcaId(uz.getId(), this.dataOd, this.dataDo);

            ChartSeries chSer = new ChartSeries();
            chSer.setLabel(uz.getImie() + " " + uz.getNazwisko());
            chSer.set("Wyniki", wynikSprzedazy);
            model.addSeries(chSer);

            if (wynikSprzedazy > this.maxValue) {
                this.maxValue = wynikSprzedazy;
            }
        }
        this.maxValue = this.maxValue + 100;

        return model;
    }
}
