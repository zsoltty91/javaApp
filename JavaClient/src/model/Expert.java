/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
        if (step == null) {
            return;
        }
        if (step > 0) {
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
        if (maximum == null) {
            return;
        }
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
        if (lots == null) {
            return;
        }
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
        if (takeProfit == null) {
            return;
        }
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
        if (stopLoss == null) {
            return;
        }
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
        if (maOpenPeriod == null) {
            return;
        }
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
        if (maOpenDiff == null) {
            return;
        }
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
        if (wprOpenPeriod == null) {
            return;
        }
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
        if (wprOpenBottom == null) {
            return;
        }
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
        if (wprOpenTop == null) {
            return;
        }
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
        if (wprClosePeriod == null) {
            return;
        }
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
        if (wprCloseTakeProfitBottom == null) {
            return;
        }
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
        if (wprCloseTakeProfitTop == null) {
            return;
        }
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
        if (maClosePeriod == null) {
            return;
        }
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
        if (maCloseTakeProfitDiff == null) {
            return;
        }
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
        if (rsiClosePeriod == null) {
            return;
        }
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
        if (rsiCloseStopLossTop == null) {
            return;
        }
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
        if (rsiCloseStopLossBottom == null) {
            return;
        }
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

        expert.refreshIsCloseMaTakeProfit(this.getIsCloseMaTakeProfit());
        expert.refreshIsCloseRSIStopLoss(this.getIsCloseRSIStopLoss());
        expert.refreshIsCloseWprTakeProfit(this.getIsCloseWprTakeProfit());
        expert.refreshIsOpenMa(this.getIsOpenMa());
        expert.refreshIsOpenWpr(this.getIsOpenWpr());

        expert.refreshLots(this.getLots());

        expert.refreshMaClosePeriod(this.getMaClosePeriod());
        expert.refreshMaCloseTakeProfitDiff(this.getMaCloseTakeProfitDiff());
        expert.refreshMaOpenDiff(this.getMaOpenDiff());
        expert.refreshMaOpenPeriod(this.getMaOpenPeriod());
        expert.refreshMaximum(this.getMaximum());

        expert.setName(this.getName());
        expert.refreshRsiClosePeriod(this.getRsiClosePeriod());
        expert.refreshRsiCloseStopLossBottom(this.getRsiCloseStopLossBottom());
        expert.refreshRsiCloseStopLossTop(this.getRsiCloseStopLossTop());
        expert.refreshStep(this.getStep());
        expert.refreshStopLoss(this.getStopLoss());
        expert.refreshTakeProfit(this.getTakeProfit());
        expert.refreshWprClosePeriod(this.getWprClosePeriod());
        expert.refreshWprCloseTakeProfitBottom(this.getWprCloseTakeProfitBottom());
        expert.refreshWprCloseTakeProfitTop(this.getWprCloseTakeProfitTop());
        expert.refreshWprOpenBottom(this.getWprOpenBottom());
        expert.refreshWprOpenPeriod(this.getWprOpenPeriod());
        expert.refreshWprOpenTop(this.getWprOpenTop());

        return expert;
    }

    @Override
    public String toString() {
        return "Expert{ name= "+this.getName() + "step=" + step + ", maximum=" + maximum + ", lots=" + lots + ", takeProfit=" + takeProfit + ", stopLoss=" + stopLoss + ", isOpenMa=" + isOpenMa + ", maOpenPeriod=" + maOpenPeriod + ", maOpenDiff=" + maOpenDiff + ", isOpenWpr=" + isOpenWpr + ", wprOpenPeriod=" + wprOpenPeriod + ", wprOpenBottom=" + wprOpenBottom + ", wprOpenTop=" + wprOpenTop + ", isCloseWprTakeProfit=" + isCloseWprTakeProfit + ", wprClosePeriod=" + wprClosePeriod + ", wprCloseTakeProfitBottom=" + wprCloseTakeProfitBottom + ", wprCloseTakeProfitTop=" + wprCloseTakeProfitTop + ", isCloseMaTakeProfit=" + isCloseMaTakeProfit + ", maClosePeriod=" + maClosePeriod + ", maCloseTakeProfitDiff=" + maCloseTakeProfitDiff + ", isCloseRSIStopLoss=" + isCloseRSIStopLoss + ", rsiClosePeriod=" + rsiClosePeriod + ", rsiCloseStopLossTop=" + rsiCloseStopLossTop + ", rsiCloseStopLossBottom=" + rsiCloseStopLossBottom + '}';
    }

    
}
