/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.PerzistenceObject;
import model.Perzistence;

/**
 *
 * @author zsolti
 */
public class Expert extends PerzistenceObject implements Comparable<Expert>, Cloneable {

    public Expert() {
    }

    Expert(Perzistence per) {
        super(per);
    }

    private Double step;
    private Double maximum;
    private Double lots;
    private Integer takeProfit;
    private Integer stopLoss;

    private Boolean isOpenMa;
    private Integer maOpenPeriod;
    private Double maOpenDiff;

    private Boolean isOpenWpr;
    private Integer wprOpenPeriod;
    private Integer wprOpenBottom;
    private Integer wprOpenTop;

    private Boolean isCloseWprTakeProfit;
    private Integer wprClosePeriod;
    private Integer wprCloseTakeProfitBottom;
    private Integer wprCloseTakeProfitTop;

    private Boolean isCloseMaTakeProfit;
    private Integer maClosePeriod;
    private Double maCloseTakeProfitDiff;

    private Boolean isCloseRSIStopLoss;
    private Integer rsiClosePeriod;
    private Integer rsiCloseStopLossTop;
    private Integer rsiCloseStopLossBottom;

    public void setStep(Double step) {
        if (maOpenDiff > 0) {
            this.step = step;
            set("step", step);
        }
    }

    public void refreshStep(Double step) {
        this.step = step;
    }

    public Double getStep() {
        if (step == null) {
            getDouble("step");
        }
        return step;
    }

    public void setMaximum(Double maximum) {
        if (maximum > 0) {
            this.maximum = maximum;
            set("maximum", maximum);
        }
    }

    public void refreshMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMaximum() {
        if (maximum == null) {
            getDouble("maximum");
        }
        return maximum;
    }

    public void setLots(Double lots) {
        if (lots > 0) {
            this.lots = lots;
            set("lots", lots);
        }
    }

    public void refreshLots(Double lots) {
        this.lots = lots;
    }

    public Double getLots() {
        if (lots == null) {
            getDouble("lots");
        }
        return lots;
    }

    public void setTakeProfit(Integer takeProfit) {
        if (takeProfit > 0) {
            this.takeProfit = takeProfit;
            set("takeProfit", takeProfit);
        }
    }

    public void refreshTakeProfit(Integer takeProfit) {
        this.takeProfit = takeProfit;
    }

    public Integer getTakeProfit() {
        if (takeProfit == null) {
            getInt("takeProfit");
        }
        return takeProfit;
    }

    public void setStopLoss(Integer stopLoss) {
        if (stopLoss > 0) {
            this.stopLoss = stopLoss;
            set("stopLoss", stopLoss);
        }
    }

    public void refreshStopLoss(Integer stopLoss) {
        this.stopLoss = stopLoss;
    }

    public Integer getStopLoss() {
        if (stopLoss == null) {
            getInt("stopLoss");
        }
        return stopLoss;
    }

    public void setIsOpenMa(Boolean isOpenMa) {
        this.isOpenMa = isOpenMa;
        set("isOpenMa", isOpenMa);
    }

    public void refreshIsOpenMa(Boolean isOpenMa) {
        this.isOpenMa = isOpenMa;
    }

    public Boolean getIsOpenMa() {
        if (isOpenMa == null) {
            getBoolean("isOpenMa");
        }
        return isOpenMa;
    }

    public void setMaOpenPeriod(Integer maOpenPeriod) {
        if (maOpenPeriod > 0) {
            this.maOpenPeriod = maOpenPeriod;
            set("maOpenPeriod", maOpenPeriod);
        }
    }

    public void refreshMaOpenPeriod(Integer maOpenPeriod) {
        this.maOpenPeriod = maOpenPeriod;
    }

    public Integer getMaOpenPeriod() {
        if (maOpenPeriod == null) {
            getInt("maOpenPeriod");
        }
        return maOpenPeriod;
    }

    public void setMaOpenDiff(Double maOpenDiff) {
        if (maOpenDiff > 0) {
            this.maOpenDiff = maOpenDiff;
            set("maOpenDiff", maOpenDiff);
        }
    }

    public void refreshMaOpenDiff(Double maOpenDiff) {
        this.maOpenDiff = maOpenDiff;
    }

    public Double getMaOpenDiff() {
        if (maOpenDiff == null) {
            getDouble("maOpenDiff");
        }
        return maOpenDiff;
    }

    public void setIsOpenWpr(Boolean isOpenWpr) {
        this.isOpenWpr = isOpenWpr;
        set("isOpenWpr", isOpenWpr);
    }

    public void refreshIsOpenWpr(Boolean isOpenWpr) {
        this.isOpenWpr = isOpenWpr;
    }

    public Boolean getIsOpenWpr() {
        if (isOpenWpr == null) {
            getBoolean("isOpenWpr");
        }
        return isOpenWpr;
    }

    public void setWprOpenPeriod(Integer wprOpenPeriod) {
        if (wprOpenPeriod > 0) {
            this.wprOpenPeriod = wprOpenPeriod;
            set("wprOpenPeriod", wprOpenPeriod);
        }
    }

    public void refreshWprOpenPeriod(Integer wprOpenPeriod) {
        this.wprOpenPeriod = wprOpenPeriod;
    }

    public Integer getWprOpenPeriod() {
        if (wprOpenPeriod == null) {
            getInt("wprOpenPeriod");
        }
        return wprOpenPeriod;
    }

    public void setWprOpenBottom(Integer wprOpenBottom) {
        if (wprOpenBottom < 0) {
            this.wprOpenBottom = wprOpenBottom;
            set("wprOpenBottom", wprOpenBottom);
        }
    }

    public void refreshWprOpenBottom(Integer wprOpenBottom) {
        this.wprOpenBottom = wprOpenBottom;
    }

    public Integer getWprOpenBottom() {
        if (wprOpenBottom == null) {
            getInt("wprOpenBottom");
        }
        return wprOpenBottom;
    }

    public void setWprOpenTop(Integer wprOpenTop) {
        if (wprOpenTop < 0) {
            this.wprOpenTop = wprOpenTop;
            set("wprOpenTop", wprOpenTop);
        }
    }

    public void refreshWprOpenTop(Integer wprOpenTop) {
        this.wprOpenTop = wprOpenTop;
    }

    public Integer getWprOpenTop() {
        if (wprOpenTop == null) {
            getInt("wprOpenTop");
        }
        return wprOpenTop;
    }

    public void setIsCloseWprTakeProfit(Boolean isCloseWprTakeProfit) {
        this.isCloseWprTakeProfit = isCloseWprTakeProfit;
        set("isCloseWprTakeProfit", isCloseWprTakeProfit);
    }

    public void refreshIsCloseWprTakeProfit(Boolean isCloseWprTakeProfit) {
        this.isCloseWprTakeProfit = isCloseWprTakeProfit;
    }

    public Boolean getIsCloseWprTakeProfit() {
        if (isCloseWprTakeProfit == null) {
            getBoolean("isCloseWprTakeProfit");
        }
        return isCloseWprTakeProfit;
    }

    public void setWprClosePeriod(Integer wprClosePeriod) {
        if (wprClosePeriod > 0) {
            this.wprClosePeriod = wprClosePeriod;
            set("wprClosePeriod", wprClosePeriod);
        }
    }

    public void refreshWprClosePeriod(Integer wprClosePeriod) {
        this.wprClosePeriod = wprClosePeriod;
    }

    public Integer getWprClosePeriod() {
        if (wprClosePeriod == null) {
            getInt("wprClosePeriod");
        }
        return wprClosePeriod;
    }

    public void setWprCloseTakeProfitBottom(Integer wprCloseTakeProfitBottom) {
        if (wprCloseTakeProfitBottom < 0) {
            this.wprCloseTakeProfitBottom = wprCloseTakeProfitBottom;
            set("wprCloseTakeProfitBottom", wprCloseTakeProfitBottom);
        }
    }

    public void refreshWprCloseTakeProfitBottom(Integer wprCloseTakeProfitBottom) {
        this.wprCloseTakeProfitBottom = wprCloseTakeProfitBottom;
    }

    public Integer getWprCloseTakeProfitBottom() {
        if (wprCloseTakeProfitBottom == null) {
            getInt("wprCloseTakeProfitBottom");
        }
        return wprCloseTakeProfitBottom;
    }

    public void setWprCloseTakeProfitTop(Integer wprCloseTakeProfitTop) {
        if (wprCloseTakeProfitTop < 0) {
            this.wprCloseTakeProfitTop = wprCloseTakeProfitTop;
            set("wprCloseTakeProfitTop", wprCloseTakeProfitTop);
        }
    }

    public void refreshWprCloseTakeProfitTop(Integer wprCloseTakeProfitTop) {
        this.wprCloseTakeProfitTop = wprCloseTakeProfitTop;
    }

    public Integer getWprCloseTakeProfitTop() {
        if (wprCloseTakeProfitTop == null) {
            getInt("wprCloseTakeProfitTop");
        }
        return wprCloseTakeProfitTop;
    }

    public void setIsCloseMaTakeProfit(Boolean isCloseMaTakeProfit) {
        this.isCloseMaTakeProfit = isCloseMaTakeProfit;
        set("isCloseMaTakeProfit", isCloseMaTakeProfit);
    }

    public void refreshIsCloseMaTakeProfit(Boolean isCloseMaTakeProfit) {
        this.isCloseMaTakeProfit = isCloseMaTakeProfit;
    }

    public Boolean getIsCloseMaTakeProfit() {
        if (isCloseMaTakeProfit == null) {
            getBoolean("isCloseMaTakeProfit");
        }
        return isCloseMaTakeProfit;
    }

    public void setMaClosePeriod(Integer maClosePeriod) {
        if (maClosePeriod > 0) {
            this.maClosePeriod = maClosePeriod;
            set("maClosePeriod", maClosePeriod);
        }
    }

    public void refreshMaClosePeriod(Integer maClosePeriod) {
        this.maClosePeriod = maClosePeriod;
    }

    public Integer getMaClosePeriod() {
        if (maClosePeriod == null) {
            getInt("maClosePeriod");
        }
        return maClosePeriod;
    }

    public void setMaCloseTakeProfitDiff(Double maCloseTakeProfitDiff) {
        if (maCloseTakeProfitDiff > 0) {
            this.maCloseTakeProfitDiff = maCloseTakeProfitDiff;
            set("maCloseTakeProfitDiff", maCloseTakeProfitDiff);
        }
    }

    public void refreshMaCloseTakeProfitDiff(Double maCloseTakeProfitDiff) {
        this.maCloseTakeProfitDiff = maCloseTakeProfitDiff;
    }

    public Double getMaCloseTakeProfitDiff() {
        if (maCloseTakeProfitDiff == null) {
            getDouble("maCloseTakeProfitDiff");
        }
        return maCloseTakeProfitDiff;
    }

    public void setIsCloseRSIStopLoss(Boolean isCloseRSIStopLoss) {
        this.isCloseRSIStopLoss = isCloseRSIStopLoss;
        set("isCloseRSIStopLoss", isCloseRSIStopLoss);
    }

    public void refreshIsCloseRSIStopLoss(Boolean isCloseRSIStopLoss) {
        this.isCloseRSIStopLoss = isCloseRSIStopLoss;
    }

    public Boolean getIsCloseRSIStopLoss() {
        if (isCloseRSIStopLoss == null) {
            getBoolean("isCloseRSIStopLoss");
        }
        return isCloseRSIStopLoss;
    }

    public void setRsiClosePeriod(Integer rsiClosePeriod) {
        if (rsiClosePeriod > 0) {
            this.rsiClosePeriod = rsiClosePeriod;
            set("rsiClosePeriod", rsiClosePeriod);
        }
    }

    public void refreshRsiClosePeriod(Integer rsiClosePeriod) {
        this.rsiClosePeriod = rsiClosePeriod;
    }

    public Integer getRsiClosePeriod() {
        if (rsiClosePeriod == null) {
            getInt("rsiClosePeriod");
        }
        return rsiClosePeriod;
    }

    public void setRsiCloseStopLossTop(Integer rsiCloseStopLossTop) {
        if (rsiCloseStopLossTop > 0) {
            this.rsiCloseStopLossTop = rsiCloseStopLossTop;
            set("rsiCloseStopLossTop", rsiCloseStopLossTop);
        }
    }

    public void refreshRsiCloseStopLossTop(Integer rsiCloseStopLossTop) {
        this.rsiCloseStopLossTop = rsiCloseStopLossTop;
    }

    public Integer getRsiCloseStopLossTop() {
        if (rsiCloseStopLossTop == null) {
            getInt("rsiCloseStopLossTop");
        }
        return rsiCloseStopLossTop;
    }

    public void setRsiCloseStopLossBottom(Integer rsiCloseStopLossBottom) {
        if (rsiCloseStopLossBottom > 0) {
            this.rsiCloseStopLossBottom = rsiCloseStopLossBottom;
            set("rsiCloseStopLossBottom", rsiCloseStopLossBottom);
        }
    }

    public void refreshRsiCloseStopLossBottom(Integer rsiCloseStopLossBottom) {
        this.rsiCloseStopLossBottom = rsiCloseStopLossBottom;
    }

    public Integer getRsiCloseStopLossBottom() {
        if (rsiCloseStopLossBottom == null) {
            getInt("rsiCloseStopLossBottom");
        }
        return rsiCloseStopLossBottom;
    }

    @Override
    public int compareTo(Expert o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public Object clone() {

        Expert expert = new Expert();

        expert.setIsCloseMaTakeProfit(new Boolean(this.getIsCloseMaTakeProfit()));
        expert.setIsCloseRSIStopLoss(new Boolean(this.getIsCloseRSIStopLoss()));
        expert.setIsCloseWprTakeProfit(new Boolean(this.getIsCloseWprTakeProfit()));
        expert.setIsOpenMa(new Boolean(this.getIsOpenMa()));
        expert.setIsOpenWpr(new Boolean(this.getIsOpenWpr()));

        expert.setLots(new Double(this.getLots()));
        expert.setMaClosePeriod(new Integer(this.getMaClosePeriod()));
        expert.setMaCloseTakeProfitDiff(new Double(this.getMaCloseTakeProfitDiff()));
        expert.setMaOpenDiff(new Double(this.getMaOpenDiff()));
        expert.setMaOpenPeriod(new Integer(this.getMaOpenPeriod()));
        expert.setMaximum(new Double(this.getMaximum()));

        expert.setName(new String(this.getName()));
        expert.setRsiClosePeriod(new Integer(this.getRsiClosePeriod()));
        expert.setRsiCloseStopLossBottom(new Integer(this.getRsiCloseStopLossBottom()));
        expert.setRsiCloseStopLossTop(new Integer(this.getRsiCloseStopLossTop()));
        expert.setStep(new Double(this.getStep()));
        expert.setStopLoss(new Integer(this.getStopLoss()));
        expert.setTakeProfit(new Integer(this.getTakeProfit()));
        expert.setWprClosePeriod(new Integer(this.getWprClosePeriod()));
        expert.setWprCloseTakeProfitBottom(new Integer(this.getWprCloseTakeProfitBottom()));
        expert.setWprCloseTakeProfitTop(new Integer(this.getWprCloseTakeProfitTop()));
        expert.setWprOpenBottom(new Integer(this.getWprOpenBottom()));
        expert.setWprOpenPeriod(new Integer(this.getWprOpenPeriod()));
        expert.setWprOpenTop(new Integer(this.getWprOpenTop()));

        return expert;
    }

}
