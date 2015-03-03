/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import model.Expert;
import model.ExpertManager;
import model.ExpertVO;
import model.MetaPersistence;
import model.ModelChangeListener;
import network.CommunicationHandler;
import network.ConnectionWrapper;
import network.Request;
import network.RequestFactory;
import network.RequestHandler;
import network.RequestType;
import network.Value;

/**
 *
 * @author zsolti
 */
public class MainWindow extends javax.swing.JFrame implements ModelChangeListener {

    NetworkSettingsWindow networkPanel;
    CommunicationHandler commHandler;
    ExpertManager expertManager;

    DefaultListModel<ExpertVO> experts = new DefaultListModel<>();

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        networkPanel = new NetworkSettingsWindow();
        commHandler = CommunicationHandler.getInstance();
        expertManager = ExpertManager.getInstance();

        RequestFactory.getAllExpert();
        MetaPersistence.getInstance().addModelChangeListener(this);

        ExpertVO expert = new ExpertVO();
        Expert e = new Expert();
        e.setName("alma");
        expert.setMetaExpert(e);
        experts.addElement(expert);
        setAllCheckBox(false);
        setComponentsState();
    }

    private void setAllCheckBox(boolean state) {
        stepCheckBox.setSelected(state);
        maximumCheckBox.setSelected(state);
        takeProfitCheckBox.setSelected(state);
        stopLossCheckBox.setSelected(state);
        lotsCheckBox.setSelected(state);
        isOpenMaCheckBox.setSelected(state);
        maOpenPeriodCheckBox.setSelected(state);
        maOpenDiffCheckBox.setSelected(state);
        isOpenWprCheckBox.setSelected(state);
        wprOpenPeriodCheckBox.setSelected(state);
        wprOpenBottomCheckBox.setSelected(state);
        wprOpenTopCheckBox.setSelected(state);
        isCloseWprTakeProfitCheckBox.setSelected(state);
        wprClosePeriodCheckBox.setSelected(state);
        wprCloseTakeProfitBottomCheckBox.setSelected(state);
        wprCloseTakeProfitTopCheckBox.setSelected(state);
        isCloseMaTakeProfitCheckBox.setSelected(state);
        maClosePeriodCheckBox.setSelected(state);
        maCloseTakeProfitDiffCheckBox.setSelected(state);
        isCloseRsiStopLossCheckBox.setSelected(state);
        rsiClosePeriodCheckBox.setSelected(state);
        rsiCloseStopLossTopCheckBox.setSelected(state);
        rsiCloseStopLossBottomCheckBox.setSelected(state);
    }

    public void setMetaFieldsState(boolean state) {
        metaStepTextField.setEnabled(state);
        metaMaximumTextField.setEnabled(state);
        metaTakeProfitTextField.setEnabled(state);
        metaStopLossTextField.setEnabled(state);
        metaLotsTextField.setEnabled(state);
        metaIsOpenMaCheckBox.setEnabled(state);
        metaMaOpenPeriodTextField.setEnabled(state);
        metaMaOpenDiffTextField.setEnabled(state);
        metaIsOpenWprCheckBox.setEnabled(state);
        metaWprOpenPeriodTextField.setEnabled(state);
        metaWprOpenBottomTextField.setEnabled(state);
        metaWprOpenTopTextField.setEnabled(state);
        metaIsCloseWprTakeProfitCheckBox.setEnabled(state);
        metaWprClosePeriodTextField.setEnabled(state);
        metaWprCloseTakeProfitBottomTextField.setEnabled(state);
        metaWprCloseTakeProfitTopTextField.setEnabled(state);
        metaIsCloseMaTakeProfitCheckBox.setEnabled(state);
        metaMaClosePeriodTextField.setEnabled(state);
        metaMaCloseTakeProfitDiffTextField.setEnabled(state);
        metaIsCloseRsiStopLossCheckBox.setEnabled(state);
        metaRsiClosePeriodTextField.setEnabled(state);
        metaRsiCloseStopLossTopTextField.setEnabled(state);
        metaRsiCloseStopLossBottomTextField.setEnabled(state);
    }

    public void clearMetaFields() {
        metaStepTextField.setText("");
        metaMaximumTextField.setText("");
        metaTakeProfitTextField.setText("");
        metaStopLossTextField.setText("");
        metaLotsTextField.setText("");
        metaIsOpenMaCheckBox.setSelected(false);
        metaMaOpenPeriodTextField.setText("");
        metaMaOpenDiffTextField.setText("");
        metaIsOpenWprCheckBox.setSelected(false);
        metaWprOpenPeriodTextField.setText("");
        metaWprOpenBottomTextField.setText("");
        metaWprOpenTopTextField.setText("");
        metaIsCloseWprTakeProfitCheckBox.setSelected(false);
        metaWprClosePeriodTextField.setText("");
        metaWprCloseTakeProfitBottomTextField.setText("");
        metaWprCloseTakeProfitTopTextField.setText("");
        metaIsCloseMaTakeProfitCheckBox.setSelected(false);
        metaMaClosePeriodTextField.setText("");
        metaMaCloseTakeProfitDiffTextField.setText("");
        metaIsCloseRsiStopLossCheckBox.setSelected(false);
        metaRsiClosePeriodTextField.setText("");
        metaRsiCloseStopLossTopTextField.setText("");
        metaRsiCloseStopLossBottomTextField.setText("");
    }

    public void setMetaFieldsValue(Expert expert) {
        if (expert == null) {
            clearMetaFields();
            setMetaFieldsState(false);
            return;
        }
        metaStepTextField.setText(expert.getStep() == null ? "" : expert.getStep().toString());
        metaMaximumTextField.setText(expert.getMaximum() == null ? "" : expert.getMaximum().toString());
        metaTakeProfitTextField.setText(expert.getTakeProfit() == null ? "" : expert.getTakeProfit().toString());
        metaStopLossTextField.setText(expert.getStopLoss() == null ? "" : expert.getStopLoss().toString());
        metaLotsTextField.setText(expert.getLots() == null ? "" : expert.getLots().toString());
        metaIsOpenMaCheckBox.setSelected(expert.getIsOpenMa() == null ? false : expert.getIsOpenMa());
        metaMaOpenPeriodTextField.setText(expert.getMaOpenPeriod() == null ? "" : expert.getMaOpenPeriod().toString());
        metaMaOpenDiffTextField.setText(expert.getMaOpenDiff() == null ? "" : expert.getMaOpenDiff().toString());
        metaIsOpenWprCheckBox.setSelected(expert.getIsOpenWpr() == null ? false : expert.getIsOpenWpr());
        metaWprOpenPeriodTextField.setText(expert.getWprOpenPeriod() == null ? "" : expert.getWprOpenPeriod().toString());
        metaWprOpenBottomTextField.setText(expert.getWprOpenBottom() == null ? "" : expert.getWprOpenBottom().toString());
        metaWprOpenTopTextField.setText(expert.getWprOpenTop() == null ? "" : expert.getWprOpenTop().toString());
        metaIsCloseWprTakeProfitCheckBox.setSelected(expert.getIsCloseWprTakeProfit() == null ? false : expert.getIsCloseWprTakeProfit());
        metaWprClosePeriodTextField.setText(expert.getWprClosePeriod() == null ? "" : expert.getWprClosePeriod().toString());
        metaWprCloseTakeProfitBottomTextField.setText(expert.getWprCloseTakeProfitBottom() == null ? "" : expert.getWprCloseTakeProfitBottom().toString());
        metaWprCloseTakeProfitTopTextField.setText(expert.getWprCloseTakeProfitTop() == null ? "" : expert.getWprCloseTakeProfitTop().toString());
        metaIsCloseMaTakeProfitCheckBox.setSelected(expert.getIsCloseMaTakeProfit() == null ? false : expert.getIsCloseMaTakeProfit());
        metaMaClosePeriodTextField.setText(expert.getMaClosePeriod() == null ? "" : expert.getMaClosePeriod().toString());
        metaMaCloseTakeProfitDiffTextField.setText(expert.getMaCloseTakeProfitDiff() == null ? "" : expert.getMaCloseTakeProfitDiff().toString());
        metaIsCloseRsiStopLossCheckBox.setSelected(expert.getIsCloseRSIStopLoss() == null ? false : expert.getIsCloseRSIStopLoss());
        metaRsiClosePeriodTextField.setText(expert.getRsiClosePeriod() == null ? "" : expert.getRsiClosePeriod().toString());
        metaRsiCloseStopLossTopTextField.setText(expert.getRsiCloseStopLossTop() == null ? "" : expert.getRsiCloseStopLossTop().toString());
        metaRsiCloseStopLossBottomTextField.setText(expert.getRsiCloseStopLossBottom() == null ? "" : expert.getRsiCloseStopLossBottom().toString());
    }

    public void setExpertFromMetaFields(Expert expert) {
        if (expert == null) {
            return;
        }
        if (stepCheckBox.isSelected()) {
            try {
                Double number = Double.parseDouble(metaStepTextField.getText());
                expert.setStep(number);
                metaStepTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaStepTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (maximumCheckBox.isSelected()) {
            try {
                Double number = Double.parseDouble(metaMaximumTextField.getText());
                expert.setMaximum(number);
                metaMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (takeProfitCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaTakeProfitTextField.getText());
                expert.setTakeProfit(number);
                metaTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (stopLossCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaStopLossTextField.getText());
                expert.setStopLoss(number);
                metaStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (lotsCheckBox.isSelected()) {
            try {
                Double number = Double.parseDouble(metaLotsTextField.getText());
                expert.setLots(number);
                metaLotsTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaLotsTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (isOpenMaCheckBox.isSelected()) {
            expert.setIsOpenMa(metaIsOpenMaCheckBox.isSelected());
            metaIsOpenMaCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        if (maOpenPeriodCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaMaOpenPeriodTextField.getText());
                expert.setMaOpenPeriod(number);
                metaMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (maOpenDiffCheckBox.isSelected()) {
            try {
                Double number = Double.parseDouble(metaMaOpenDiffTextField.getText());
                expert.setMaOpenDiff(number);
                metaMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (isOpenWprCheckBox.isSelected()) {
            expert.setIsOpenWpr(metaIsOpenWprCheckBox.isSelected());
            metaIsOpenWprCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        if (wprOpenPeriodCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprOpenPeriodTextField.getText());
                expert.setWprOpenPeriod(number);
                metaWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (wprOpenBottomCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprOpenBottomTextField.getText());
                expert.setWprOpenBottom(number);
                metaWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (wprOpenTopCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprOpenTopTextField.getText());
                expert.setWprOpenTop(number);
                metaWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (isCloseWprTakeProfitCheckBox.isSelected()) {
            expert.setIsCloseWprTakeProfit(metaIsCloseWprTakeProfitCheckBox.isSelected());
            metaIsCloseWprTakeProfitCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        if (wprClosePeriodCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprClosePeriodTextField.getText());
                expert.setWprClosePeriod(number);
                metaWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (wprCloseTakeProfitBottomCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprCloseTakeProfitBottomTextField.getText());
                expert.setWprCloseTakeProfitBottom(number);
                metaWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (wprCloseTakeProfitTopCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaWprCloseTakeProfitTopTextField.getText());
                expert.setWprCloseTakeProfitTop(number);
                metaWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (isCloseMaTakeProfitCheckBox.isSelected()) {
            expert.setIsCloseMaTakeProfit(metaIsCloseMaTakeProfitCheckBox.isSelected());
            metaIsCloseMaTakeProfitCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        if (maClosePeriodCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaMaClosePeriodTextField.getText());
                expert.setMaClosePeriod(number);
                metaMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (maCloseTakeProfitDiffCheckBox.isSelected()) {
            try {
                Double number = Double.parseDouble(metaMaCloseTakeProfitDiffTextField.getText());
                expert.setMaCloseTakeProfitDiff(number);
                metaMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (isCloseRsiStopLossCheckBox.isSelected()) {
            expert.setIsCloseRSIStopLoss(metaIsCloseRsiStopLossCheckBox.isSelected());
            metaIsCloseRsiStopLossCheckBox.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        if (rsiClosePeriodCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaRsiClosePeriodTextField.getText());
                expert.setRsiClosePeriod(number);
                metaRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (rsiCloseStopLossTopCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaRsiCloseStopLossTopTextField.getText());
                expert.setRsiCloseStopLossTop(number);
                metaRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        if (rsiCloseStopLossBottomCheckBox.isSelected()) {
            try {
                Integer number = Integer.parseInt(metaRsiCloseStopLossBottomTextField.getText());
                expert.setRsiCloseStopLossBottom(number);
                metaRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.black));
            } catch (NumberFormatException nfe) {
                metaRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
    }

    public void setLocalFieldsState(boolean state) {
        localStepTextField.setEnabled(state);
        localMaximumTextField.setEnabled(state);
        localTakeProfitTextField.setEnabled(state);
        localStopLossTextField.setEnabled(state);
        localLotsTextField.setEnabled(state);
        localIsOpenMaCheckBox.setEnabled(state);
        localMaOpenPeriodTextField.setEnabled(state);
        localMaOpenDiffTextField.setEnabled(state);
        localIsOpenWprCheckBox.setEnabled(state);
        localWprOpenPeriodTextField.setEnabled(state);
        localWprOpenBottomTextField.setEnabled(state);
        localWprOpenTopTextField.setEnabled(state);
        localIsCloseWprTakeProfitCheckBox.setEnabled(state);
        localWprClosePeriodTextField.setEnabled(state);
        localWprCloseTakeProfitBottomTextField.setEnabled(state);
        localWprCloseTakeProfitTopTextField.setEnabled(state);
        localIsCloseMaTakeProfitCheckBox.setEnabled(state);
        localMaClosePeriodTextField.setEnabled(state);
        localMaCloseTakeProfitDiffTextField.setEnabled(state);
        localIsCloseRsiStopLossCheckBox.setEnabled(state);
        localRsiClosePeriodTextField.setEnabled(state);
        localRsiCloseStopLossTopTextField.setEnabled(state);
        localRsiCloseStopLossBottomTextField.setEnabled(state);
    }

    public void clearLocalFields() {
        localStepTextField.setText("");
        localMaximumTextField.setText("");
        localTakeProfitTextField.setText("");
        localStopLossTextField.setText("");
        localLotsTextField.setText("");
        localIsOpenMaCheckBox.setSelected(false);
        localMaOpenPeriodTextField.setText("");
        localMaOpenDiffTextField.setText("");
        localIsOpenWprCheckBox.setSelected(false);
        localWprOpenPeriodTextField.setText("");
        localWprOpenBottomTextField.setText("");
        localWprOpenTopTextField.setText("");
        localIsCloseWprTakeProfitCheckBox.setSelected(false);
        localWprClosePeriodTextField.setText("");
        localWprCloseTakeProfitBottomTextField.setText("");
        localWprCloseTakeProfitTopTextField.setText("");
        localIsCloseMaTakeProfitCheckBox.setSelected(false);
        localMaClosePeriodTextField.setText("");
        localMaCloseTakeProfitDiffTextField.setText("");
        localIsCloseRsiStopLossCheckBox.setSelected(false);
        localRsiClosePeriodTextField.setText("");
        localRsiCloseStopLossTopTextField.setText("");
        localRsiCloseStopLossBottomTextField.setText("");
    }

    public void setLocalFieldsValue(Expert expert) {
        if (expert == null) {
            clearLocalFields();
            setLocalFieldsState(false);
            return;
        }
        localStepTextField.setText(expert.getStep() == null ? "" : expert.getStep().toString());
        localMaximumTextField.setText(expert.getMaximum() == null ? "" : expert.getMaximum().toString());
        localTakeProfitTextField.setText(expert.getTakeProfit() == null ? "" : expert.getTakeProfit().toString());
        localStopLossTextField.setText(expert.getStopLoss() == null ? "" : expert.getStopLoss().toString());
        localLotsTextField.setText(expert.getLots() == null ? "" : expert.getLots().toString());
        localIsOpenMaCheckBox.setSelected(expert.getIsOpenMa() == null ? false : expert.getIsOpenMa());
        localMaOpenPeriodTextField.setText(expert.getMaOpenPeriod() == null ? "" : expert.getMaOpenPeriod().toString());
        localMaOpenDiffTextField.setText(expert.getMaOpenDiff() == null ? "" : expert.getMaOpenDiff().toString());
        localIsOpenWprCheckBox.setSelected(expert.getIsOpenWpr() == null ? false : expert.getIsOpenWpr());
        localWprOpenPeriodTextField.setText(expert.getWprOpenPeriod() == null ? "" : expert.getWprOpenPeriod().toString());
        localWprOpenBottomTextField.setText(expert.getWprOpenBottom() == null ? "" : expert.getWprOpenBottom().toString());
        localWprOpenTopTextField.setText(expert.getWprOpenTop() == null ? "" : expert.getWprOpenTop().toString());
        localIsCloseWprTakeProfitCheckBox.setSelected(expert.getIsCloseWprTakeProfit() == null ? false : expert.getIsCloseWprTakeProfit());
        localWprClosePeriodTextField.setText(expert.getWprClosePeriod() == null ? "" : expert.getWprClosePeriod().toString());
        localWprCloseTakeProfitBottomTextField.setText(expert.getWprCloseTakeProfitBottom() == null ? "" : expert.getWprCloseTakeProfitBottom().toString());
        localWprCloseTakeProfitTopTextField.setText(expert.getWprCloseTakeProfitTop() == null ? "" : expert.getWprCloseTakeProfitTop().toString());
        localIsCloseMaTakeProfitCheckBox.setSelected(expert.getIsCloseMaTakeProfit() == null ? false : expert.getIsCloseMaTakeProfit());
        localMaClosePeriodTextField.setText(expert.getMaClosePeriod() == null ? "" : expert.getMaClosePeriod().toString());
        localMaCloseTakeProfitDiffTextField.setText(expert.getMaCloseTakeProfitDiff() == null ? "" : expert.getMaCloseTakeProfitDiff().toString());
        localIsCloseRsiStopLossCheckBox.setSelected(expert.getIsCloseRSIStopLoss() == null ? false : expert.getIsCloseRSIStopLoss());
        localRsiClosePeriodTextField.setText(expert.getRsiClosePeriod() == null ? "" : expert.getRsiClosePeriod().toString());
        localRsiCloseStopLossTopTextField.setText(expert.getRsiCloseStopLossTop() == null ? "" : expert.getRsiCloseStopLossTop().toString());
        localRsiCloseStopLossBottomTextField.setText(expert.getRsiCloseStopLossBottom() == null ? "" : expert.getRsiCloseStopLossBottom().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        moreSettingsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lotsCheckBox = new javax.swing.JCheckBox();
        stepCheckBox = new javax.swing.JCheckBox();
        takeProfitCheckBox = new javax.swing.JCheckBox();
        stopLossCheckBox = new javax.swing.JCheckBox();
        maximumCheckBox = new javax.swing.JCheckBox();
        isOpenMaCheckBox = new javax.swing.JCheckBox();
        maOpenPeriodCheckBox = new javax.swing.JCheckBox();
        maOpenDiffCheckBox = new javax.swing.JCheckBox();
        isOpenWprCheckBox = new javax.swing.JCheckBox();
        wprOpenPeriodCheckBox = new javax.swing.JCheckBox();
        wprOpenBottomCheckBox = new javax.swing.JCheckBox();
        wprClosePeriodCheckBox = new javax.swing.JCheckBox();
        wprOpenTopCheckBox = new javax.swing.JCheckBox();
        isCloseWprTakeProfitCheckBox = new javax.swing.JCheckBox();
        wprCloseTakeProfitBottomCheckBox = new javax.swing.JCheckBox();
        isCloseMaTakeProfitCheckBox = new javax.swing.JCheckBox();
        wprCloseTakeProfitTopCheckBox = new javax.swing.JCheckBox();
        maClosePeriodCheckBox = new javax.swing.JCheckBox();
        maCloseTakeProfitDiffCheckBox = new javax.swing.JCheckBox();
        isCloseRsiStopLossCheckBox = new javax.swing.JCheckBox();
        rsiClosePeriodCheckBox = new javax.swing.JCheckBox();
        rsiCloseStopLossTopCheckBox = new javax.swing.JCheckBox();
        rsiCloseStopLossBottomCheckBox = new javax.swing.JCheckBox();
        allCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        metaMaOpenPeriodTextField = new javax.swing.JTextField();
        metaWprOpenBottomTextField = new javax.swing.JTextField();
        metaWprOpenPeriodTextField = new javax.swing.JTextField();
        metaMaOpenDiffTextField = new javax.swing.JTextField();
        metaWprClosePeriodTextField = new javax.swing.JTextField();
        metaWprOpenTopTextField = new javax.swing.JTextField();
        metaStepTextField = new javax.swing.JTextField();
        metaMaximumTextField = new javax.swing.JTextField();
        metaLotsTextField = new javax.swing.JTextField();
        metaTakeProfitTextField = new javax.swing.JTextField();
        metaStopLossTextField = new javax.swing.JTextField();
        metaWprCloseTakeProfitBottomTextField = new javax.swing.JTextField();
        metaWprCloseTakeProfitTopTextField = new javax.swing.JTextField();
        metaMaClosePeriodTextField = new javax.swing.JTextField();
        metaMaCloseTakeProfitDiffTextField = new javax.swing.JTextField();
        metaRsiClosePeriodTextField = new javax.swing.JTextField();
        metaRsiCloseStopLossTopTextField = new javax.swing.JTextField();
        metaRsiCloseStopLossBottomTextField = new javax.swing.JTextField();
        metaIsOpenMaCheckBox = new javax.swing.JCheckBox();
        metaIsOpenWprCheckBox = new javax.swing.JCheckBox();
        metaIsCloseWprTakeProfitCheckBox = new javax.swing.JCheckBox();
        metaIsCloseMaTakeProfitCheckBox = new javax.swing.JCheckBox();
        metaIsCloseRsiStopLossCheckBox = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        localWprCloseTakeProfitBottomTextField = new javax.swing.JTextField();
        localMaCloseTakeProfitDiffTextField = new javax.swing.JTextField();
        localWprOpenPeriodTextField = new javax.swing.JTextField();
        localMaClosePeriodTextField = new javax.swing.JTextField();
        localRsiCloseStopLossBottomTextField = new javax.swing.JTextField();
        localWprOpenTopTextField = new javax.swing.JTextField();
        localWprCloseTakeProfitTopTextField = new javax.swing.JTextField();
        localRsiClosePeriodTextField = new javax.swing.JTextField();
        localMaOpenDiffTextField = new javax.swing.JTextField();
        localWprOpenBottomTextField = new javax.swing.JTextField();
        localMaximumTextField = new javax.swing.JTextField();
        localWprClosePeriodTextField = new javax.swing.JTextField();
        localRsiCloseStopLossTopTextField = new javax.swing.JTextField();
        localTakeProfitTextField = new javax.swing.JTextField();
        localLotsTextField = new javax.swing.JTextField();
        localMaOpenPeriodTextField = new javax.swing.JTextField();
        localStepTextField = new javax.swing.JTextField();
        localStopLossTextField = new javax.swing.JTextField();
        localIsOpenMaCheckBox = new javax.swing.JCheckBox();
        localIsOpenWprCheckBox = new javax.swing.JCheckBox();
        localIsCloseWprTakeProfitCheckBox = new javax.swing.JCheckBox();
        localIsCloseMaTakeProfitCheckBox = new javax.swing.JCheckBox();
        localIsCloseRsiStopLossCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        expertList = new javax.swing.JList();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        loadMetaExpertButton = new javax.swing.JButton();
        loadLocalExpertButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton4.setText("GetAvailable");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("read");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("send");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("get");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("StopListen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("StopSender");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("StopProcess");
        jButton3.setMaximumSize(new java.awt.Dimension(89, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(89, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(89, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout moreSettingsPanelLayout = new javax.swing.GroupLayout(moreSettingsPanel);
        moreSettingsPanel.setLayout(moreSettingsPanelLayout);
        moreSettingsPanelLayout.setHorizontalGroup(
            moreSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moreSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(moreSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        moreSettingsPanelLayout.setVerticalGroup(
            moreSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moreSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lotsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lotsCheckBoxActionPerformed(evt);
            }
        });

        allCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stepCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maOpenDiffCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isOpenWprCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprOpenPeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprOpenBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprOpenTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isCloseWprTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprCloseTakeProfitBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isCloseMaTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wprCloseTakeProfitTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maCloseTakeProfitDiffCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isCloseRsiStopLossCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsiClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsiCloseStopLossTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rsiCloseStopLossBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maximumCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lotsCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maOpenPeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isOpenMaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopLossCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(allCheckBox)
                .addGap(11, 11, 11)
                .addComponent(stopLossCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(takeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lotsCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maximumCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isOpenMaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maOpenPeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maOpenDiffCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isOpenWprCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprOpenPeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprOpenBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprOpenTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isCloseWprTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprCloseTakeProfitBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wprCloseTakeProfitTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isCloseMaTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maCloseTakeProfitDiffCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isCloseRsiStopLossCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsiClosePeriodCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsiCloseStopLossTopCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rsiCloseStopLossBottomCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        metaMaOpenPeriodTextField.setText("jTextField7");

        metaWprOpenBottomTextField.setText("jTextField11");
        metaWprOpenBottomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaWprOpenBottomTextFieldActionPerformed(evt);
            }
        });

        metaWprOpenPeriodTextField.setText("jTextField10");

        metaMaOpenDiffTextField.setText("jTextField8");

        metaWprClosePeriodTextField.setText("jTextField12");

        metaWprOpenTopTextField.setText("jTextField13");

        metaStepTextField.setText("jTextField5");
        metaStepTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                metaStepTextFieldFocusLost(evt);
            }
        });

        metaMaximumTextField.setText("jTextField4");
        metaMaximumTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaMaximumTextFieldActionPerformed(evt);
            }
        });

        metaLotsTextField.setText("jTextField3");

        metaTakeProfitTextField.setText("jTextField2");
        metaTakeProfitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaTakeProfitTextFieldActionPerformed(evt);
            }
        });

        metaStopLossTextField.setText("jTextField1");
        metaStopLossTextField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                metaStopLossTextFieldInputMethodTextChanged(evt);
            }
        });

        metaWprCloseTakeProfitBottomTextField.setText("jTextField15");

        metaWprCloseTakeProfitTopTextField.setText("jTextField16");

        metaMaClosePeriodTextField.setText("jTextField18");

        metaMaCloseTakeProfitDiffTextField.setText("jTextField19");

        metaRsiClosePeriodTextField.setText("jTextField21");

        metaRsiCloseStopLossTopTextField.setText("jTextField22");

        metaRsiCloseStopLossBottomTextField.setText("jTextField23");

        metaIsOpenMaCheckBox.setText("Enable");
        metaIsOpenMaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metaIsOpenMaCheckBoxActionPerformed(evt);
            }
        });

        metaIsOpenWprCheckBox.setText("Enable");

        metaIsCloseWprTakeProfitCheckBox.setText("Enable");

        metaIsCloseMaTakeProfitCheckBox.setText("Enable");

        metaIsCloseRsiStopLossCheckBox.setText("Enable");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(metaMaOpenDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaMaOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprOpenBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprOpenTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprCloseTakeProfitBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaWprCloseTakeProfitTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaMaClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaMaCloseTakeProfitDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaRsiClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaRsiCloseStopLossTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaRsiCloseStopLossBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaMaximumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(metaStopLossTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaTakeProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(metaLotsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(metaIsOpenMaCheckBox))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(metaIsOpenWprCheckBox))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(metaIsCloseWprTakeProfitCheckBox))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(metaIsCloseMaTakeProfitCheckBox))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(metaIsCloseRsiStopLossCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(metaStopLossTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaTakeProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaLotsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaMaximumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(metaIsOpenMaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(metaMaOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaMaOpenDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(metaIsOpenWprCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(metaWprOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaWprOpenBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaWprClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaWprOpenTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(metaIsCloseWprTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(metaWprCloseTakeProfitBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaWprCloseTakeProfitTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(metaIsCloseMaTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(metaMaClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaMaCloseTakeProfitDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(metaIsCloseRsiStopLossCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(metaRsiClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaRsiCloseStopLossTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(metaRsiCloseStopLossBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setText("Metatrader values");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("rsiClosePeriod");
        jLabel21.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel21.setVerifyInputWhenFocusTarget(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("takeProfit");
        jLabel1.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel1.setVerifyInputWhenFocusTarget(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("isOpenWpr");
        jLabel9.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel9.setVerifyInputWhenFocusTarget(false);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("wprCloseTakeProfitTop");
        jLabel16.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel16.setVerifyInputWhenFocusTarget(false);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("maCloseTakeProfitDiff");
        jLabel19.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel19.setVerifyInputWhenFocusTarget(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("maOpenDiff");
        jLabel8.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel8.setVerifyInputWhenFocusTarget(false);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("wprClosePeriod");
        jLabel14.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel14.setVerifyInputWhenFocusTarget(false);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("isCloseMaTakeProfit");
        jLabel17.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel17.setVerifyInputWhenFocusTarget(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("stopLoss");
        jLabel4.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel4.setVerifyInputWhenFocusTarget(false);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("rsiCloseStopLossBottom");
        jLabel23.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel23.setVerifyInputWhenFocusTarget(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("wprOpenTop");
        jLabel12.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel12.setVerifyInputWhenFocusTarget(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("maximum");
        jLabel2.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("step");
        jLabel3.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel3.setVerifyInputWhenFocusTarget(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("wprOpenPeriod");
        jLabel10.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel10.setVerifyInputWhenFocusTarget(false);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("rsiCloseStopLossTop");
        jLabel22.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel22.setVerifyInputWhenFocusTarget(false);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("isCloseWprTakeProfit");
        jLabel13.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel13.setVerifyInputWhenFocusTarget(false);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("wprOpenBottom");
        jLabel11.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel11.setVerifyInputWhenFocusTarget(false);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("maClosePeriod");
        jLabel18.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel18.setVerifyInputWhenFocusTarget(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("isOpenMa");
        jLabel6.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel6.setVerifyInputWhenFocusTarget(false);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("wprCloseTakeProfitBottom");
        jLabel15.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel15.setVerifyInputWhenFocusTarget(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("maOpenPeriod");
        jLabel7.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel7.setVerifyInputWhenFocusTarget(false);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("isCloseRSIStopLoss");
        jLabel20.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel20.setVerifyInputWhenFocusTarget(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("lots");
        jLabel5.setMinimumSize(new java.awt.Dimension(25, 120));
        jLabel5.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel12, jLabel13, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19, jLabel2, jLabel20, jLabel21, jLabel22, jLabel23, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jLabel25.setText("Saved values");

        localWprCloseTakeProfitBottomTextField.setText("jTextField38");

        localMaCloseTakeProfitDiffTextField.setText("jTextField42");

        localWprOpenPeriodTextField.setText("jTextField33");

        localMaClosePeriodTextField.setText("jTextField41");

        localRsiCloseStopLossBottomTextField.setText("jTextField46");

        localWprOpenTopTextField.setText("jTextField36");

        localWprCloseTakeProfitTopTextField.setText("jTextField39");

        localRsiClosePeriodTextField.setText("jTextField44");

        localMaOpenDiffTextField.setText("jTextField31");

        localWprOpenBottomTextField.setText("jTextField34");

        localMaximumTextField.setText("jTextField27");

        localWprClosePeriodTextField.setText("jTextField35");

        localRsiCloseStopLossTopTextField.setText("jTextField45");

        localTakeProfitTextField.setText("jTextField25");

        localLotsTextField.setText("jTextField26");

        localMaOpenPeriodTextField.setText("jTextField30");

        localStepTextField.setText("jTextField28");

        localStopLossTextField.setText("jTextField24");

        localIsOpenMaCheckBox.setText("Enable");

        localIsOpenWprCheckBox.setText("Enable");

        localIsCloseWprTakeProfitCheckBox.setText("Enable");

        localIsCloseMaTakeProfitCheckBox.setText("Enable");

        localIsCloseRsiStopLossCheckBox.setText("Enable");
        localIsCloseRsiStopLossCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localIsCloseRsiStopLossCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(localMaOpenDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localMaOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localMaximumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localStopLossTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localTakeProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localLotsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprOpenBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprOpenTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprCloseTakeProfitBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localWprCloseTakeProfitTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localMaClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localMaCloseTakeProfitDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localRsiClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localRsiCloseStopLossTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localRsiCloseStopLossBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localIsOpenWprCheckBox)
                    .addComponent(localIsOpenMaCheckBox)
                    .addComponent(localIsCloseMaTakeProfitCheckBox)
                    .addComponent(localIsCloseRsiStopLossCheckBox)
                    .addComponent(localIsCloseWprTakeProfitCheckBox))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(localStopLossTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localTakeProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localLotsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localMaximumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localStepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(localIsOpenMaCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(localMaOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localMaOpenDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(localIsOpenWprCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(localWprOpenPeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localWprOpenBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localWprClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localWprOpenTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(localIsCloseWprTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(localWprCloseTakeProfitBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localWprCloseTakeProfitTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(localIsCloseMaTakeProfitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(localMaClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localMaCloseTakeProfitDiffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(localIsCloseRsiStopLossCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(localRsiClosePeriodTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localRsiCloseStopLossTopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(localRsiCloseStopLossBottomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel25)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jScrollPane2.setViewportView(jPanel2);

        expertList.setModel(experts);
        expertList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        expertList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expertListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(expertList);

        jButton8.setText("resetMeta");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("resetLocal");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("enableMeta");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("enableLocal");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("jCheckBox3");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        loadMetaExpertButton.setText("Load Meta Expert");
        loadMetaExpertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMetaExpertButtonActionPerformed(evt);
            }
        });

        loadLocalExpertButton.setText("Load local Expert");
        loadLocalExpertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadLocalExpertButtonActionPerformed(evt);
            }
        });

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jMenu1.setText("Settings");

        jMenuItem1.setText("Network");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("More Settings");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moreSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7)
                                    .addComponent(jButton4)
                                    .addComponent(jButton8)
                                    .addComponent(jButton9)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loadLocalExpertButton)
                                    .addComponent(loadMetaExpertButton))))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(loadMetaExpertButton))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loadLocalExpertButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))))
                    .addComponent(moreSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        networkPanel.setVisible(true);
        System.out.println("fut");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (commHandler.isProcess()) {
            commHandler.stopProcess();
            jButton3.setText("StartProcess");
        } else {
            commHandler.startProcess();
            jButton3.setText("StopProcess");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (commHandler.isListen()) {
            commHandler.stopListen();
            jButton1.setText("StartListen");
        } else {
            commHandler.startListen();
            jButton1.setText("StopListen");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (commHandler.isSend()) {
            commHandler.stopSend();
            jButton2.setText("StartSend");
        } else {
            commHandler.startSend();
            jButton2.setText("StopSend");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ExpertManager.getInstance().getAllMetaExperts();
        System.out.println("getall");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.out.println(new ConnectionWrapper().getString());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new ConnectionWrapper().send("alma");
        System.out.println("Küldve");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Request req = new Request();

        req.setType(RequestType.GET);
        req.setObjectName("expert");
        HashMap<String, Value> values = new HashMap<String, Value>();
        Value value = new Value(network.Type.STRING);
        values.put("takeProfit", value);
        req.setValues(values);
        RequestHandler.getInstance().addRequest(req);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        if (jCheckBoxMenuItem1.isSelected()) {
            moreSettingsPanel.setVisible(true);
        } else {
            moreSettingsPanel.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void metaMaximumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaMaximumTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_metaMaximumTextFieldActionPerformed

    private void metaWprOpenBottomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaWprOpenBottomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_metaWprOpenBottomTextFieldActionPerformed

    private void allCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCheckBoxActionPerformed
        setAllCheckBox(allCheckBox.isSelected());
    }//GEN-LAST:event_allCheckBoxActionPerformed

    private void metaIsOpenMaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaIsOpenMaCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_metaIsOpenMaCheckBoxActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        clearLocalFields();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        setMetaFieldsState(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearMetaFields();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        setLocalFieldsState(jCheckBox2.isSelected());
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void expertListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expertListMouseClicked
        setComponentsState();
    }//GEN-LAST:event_expertListMouseClicked

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        metaStepTextField.setFocusable(true);
        if (jCheckBox3.isSelected()) {
            metaStepTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        } else {
            metaStepTextField.setFocusable(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void metaStopLossTextFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_metaStopLossTextFieldInputMethodTextChanged
        System.out.println("changed"
                + "");
    }//GEN-LAST:event_metaStopLossTextFieldInputMethodTextChanged

    private void metaTakeProfitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metaTakeProfitTextFieldActionPerformed
        System.out.println("hello");
    }//GEN-LAST:event_metaTakeProfitTextFieldActionPerformed

    private void metaStepTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_metaStepTextFieldFocusLost
        System.out.println("elhagytad");
    }//GEN-LAST:event_metaStepTextFieldFocusLost

    private void lotsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lotsCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lotsCheckBoxActionPerformed

    private void localIsCloseRsiStopLossCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localIsCloseRsiStopLossCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localIsCloseRsiStopLossCheckBoxActionPerformed

    private void loadMetaExpertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMetaExpertButtonActionPerformed
        setExpertFromMetaFields(getSelectedMetaExpert());
    }//GEN-LAST:event_loadMetaExpertButtonActionPerformed

    private void loadLocalExpertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadLocalExpertButtonActionPerformed
        setExpertFromMetaFields(getSelectedLocalExpert());
    }//GEN-LAST:event_loadLocalExpertButtonActionPerformed

    private void setMetaFieldsChangedState(Expert expert) {
        boolean isNull = false;
        if (expert == null) {
            clearMetaFields();
            setMetaFieldsState(false);
            isNull = true;
        }
        if (isNull || (expert.getStep() != null && expert.getStep().toString().equals(metaStepTextField.getText()))) {
            metaStepTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaStepTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getMaximum() != null && expert.getMaximum().toString().equals(metaMaximumTextField.getText()))) {
            metaMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getTakeProfit() != null && expert.getTakeProfit().toString().equals(metaTakeProfitTextField.getText()))) {
            metaTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getStopLoss() != null && expert.getStopLoss().toString().equals(metaStopLossTextField.getText()))) {
            metaStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getLots() != null && expert.getLots().toString().equals(metaLotsTextField.getText()))) {
            metaLotsTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaLotsTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getIsOpenMa() != null && expert.getIsOpenMa().equals(metaIsOpenMaCheckBox.isSelected()))) {
            metaIsOpenMaCheckBox.setForeground(Color.BLACK);
        } else {
            metaIsOpenMaCheckBox.setForeground(Color.BLUE);
        }
        if (isNull || (expert.getMaOpenPeriod() != null && expert.getMaOpenPeriod().toString().equals(metaMaOpenPeriodTextField.getText()))) {
            metaMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getMaOpenDiff() != null && expert.getMaOpenDiff().toString().equals(metaMaOpenDiffTextField.getText()))) {
            metaMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getIsOpenWpr() != null && expert.getIsOpenWpr().equals(metaIsOpenWprCheckBox.isSelected()))) {
            metaIsOpenWprCheckBox.setForeground(Color.BLACK);
        } else {
            metaIsOpenWprCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getWprOpenPeriod() != null && expert.getWprOpenPeriod().toString().equals(metaWprOpenPeriodTextField.getText()))) {
            metaWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprOpenBottom() != null && expert.getWprOpenBottom().toString().equals(metaWprOpenBottomTextField.getText()))) {
            metaWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprOpenTop() != null && expert.getWprOpenTop().toString().equals(metaWprOpenTopTextField.getText()))) {
            metaWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseWprTakeProfit() != null && expert.getIsCloseWprTakeProfit().equals(metaIsCloseWprTakeProfitCheckBox.isSelected()))) {
            metaIsCloseWprTakeProfitCheckBox.setForeground(Color.BLACK);
        } else {
            metaIsCloseWprTakeProfitCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getWprClosePeriod() != null && expert.getWprClosePeriod().toString().equals(metaWprClosePeriodTextField.getText()))) {
            metaWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprCloseTakeProfitBottom() != null && expert.getWprCloseTakeProfitBottom().toString().equals(metaWprCloseTakeProfitBottomTextField.getText()))) {
            metaWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprCloseTakeProfitTop() != null && expert.getWprCloseTakeProfitTop().toString().equals(metaWprCloseTakeProfitTopTextField.getText()))) {
            metaWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseMaTakeProfit() != null && expert.getIsCloseMaTakeProfit().equals(metaIsCloseMaTakeProfitCheckBox.isSelected()))) {
            metaIsCloseMaTakeProfitCheckBox.setForeground(Color.BLACK);
        } else {
            metaIsCloseMaTakeProfitCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getMaClosePeriod() != null && expert.getMaClosePeriod().toString().equals(metaMaClosePeriodTextField.getText()))) {
            metaMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getMaCloseTakeProfitDiff() != null && expert.getMaCloseTakeProfitDiff().toString().equals(metaMaCloseTakeProfitDiffTextField.getText()))) {
            metaMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseRSIStopLoss() != null && expert.getIsCloseRSIStopLoss().equals(metaIsCloseRsiStopLossCheckBox.isSelected()))) {
            metaIsCloseRsiStopLossCheckBox.setForeground(Color.BLACK);
        } else {
            metaIsCloseRsiStopLossCheckBox.setForeground(Color.BLUE);
        }
        if (isNull || (expert.getRsiClosePeriod() != null && expert.getRsiClosePeriod().toString().equals(metaRsiClosePeriodTextField.getText()))) {
            metaRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getRsiCloseStopLossTop() != null && expert.getRsiCloseStopLossTop().toString().equals(metaRsiCloseStopLossTopTextField.getText()))) {
            metaRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getRsiCloseStopLossBottom() != null && expert.getRsiCloseStopLossBottom().toString().equals(metaRsiCloseStopLossBottomTextField.getText()))) {
            metaRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            metaRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

    }

    private void setLocalFieldsChangedState(Expert expert) {
        boolean isNull = false;
        if (expert == null) {
            clearMetaFields();
            setMetaFieldsState(false);
            isNull = true;
        }
        if (isNull || (expert.getStep() != null && expert.getStep().toString().equals(localStepTextField.getText()))) {
            localStepTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localStepTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getMaximum() != null && expert.getMaximum().toString().equals(localMaximumTextField.getText()))) {
            localMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localMaximumTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getTakeProfit() != null && expert.getTakeProfit().toString().equals(localTakeProfitTextField.getText()))) {
            localTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localTakeProfitTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getStopLoss() != null && expert.getStopLoss().toString().equals(localStopLossTextField.getText()))) {
            localStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localStopLossTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getLots() != null && expert.getLots().toString().equals(localLotsTextField.getText()))) {
            localLotsTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localLotsTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getIsOpenMa() != null && expert.getIsOpenMa().equals(localIsOpenMaCheckBox.isSelected()))) {
            localIsOpenMaCheckBox.setForeground(Color.BLACK);
        } else {
            localIsOpenMaCheckBox.setForeground(Color.BLUE);
        }
        if (isNull || (expert.getMaOpenPeriod() != null && expert.getMaOpenPeriod().toString().equals(localMaOpenPeriodTextField.getText()))) {
            localMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localMaOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getMaOpenDiff() != null && expert.getMaOpenDiff().toString().equals(localMaOpenDiffTextField.getText()))) {
            localMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localMaOpenDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getIsOpenWpr() != null && expert.getIsOpenWpr().equals(localIsOpenWprCheckBox.isSelected()))) {
            localIsOpenWprCheckBox.setForeground(Color.BLACK);
        } else {
            localIsOpenWprCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getWprOpenPeriod() != null && expert.getWprOpenPeriod().toString().equals(localWprOpenPeriodTextField.getText()))) {
            localWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprOpenPeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprOpenBottom() != null && expert.getWprOpenBottom().toString().equals(localWprOpenBottomTextField.getText()))) {
            localWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprOpenBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprOpenTop() != null && expert.getWprOpenTop().toString().equals(localWprOpenTopTextField.getText()))) {
            localWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprOpenTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseWprTakeProfit() != null && expert.getIsCloseWprTakeProfit().equals(localIsCloseWprTakeProfitCheckBox.isSelected()))) {
            localIsCloseWprTakeProfitCheckBox.setForeground(Color.BLACK);
        } else {
            localIsCloseWprTakeProfitCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getWprClosePeriod() != null && expert.getWprClosePeriod().toString().equals(localWprClosePeriodTextField.getText()))) {
            localWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprCloseTakeProfitBottom() != null && expert.getWprCloseTakeProfitBottom().toString().equals(localWprCloseTakeProfitBottomTextField.getText()))) {
            localWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprCloseTakeProfitBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getWprCloseTakeProfitTop() != null && expert.getWprCloseTakeProfitTop().toString().equals(localWprCloseTakeProfitTopTextField.getText()))) {
            localWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localWprCloseTakeProfitTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseMaTakeProfit() != null && expert.getIsCloseMaTakeProfit().equals(localIsCloseMaTakeProfitCheckBox.isSelected()))) {
            localIsCloseMaTakeProfitCheckBox.setForeground(Color.BLACK);
        } else {
            localIsCloseMaTakeProfitCheckBox.setForeground(Color.BLUE);
        }

        if (isNull || (expert.getMaClosePeriod() != null && expert.getMaClosePeriod().toString().equals(localMaClosePeriodTextField.getText()))) {
            localMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localMaClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getMaCloseTakeProfitDiff() != null && expert.getMaCloseTakeProfitDiff().toString().equals(localMaCloseTakeProfitDiffTextField.getText()))) {
            localMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localMaCloseTakeProfitDiffTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

        if (isNull || (expert.getIsCloseRSIStopLoss() != null && expert.getIsCloseRSIStopLoss().equals(localIsCloseRsiStopLossCheckBox.isSelected()))) {
            localIsCloseRsiStopLossCheckBox.setForeground(Color.BLACK);
        } else {
            localIsCloseRsiStopLossCheckBox.setForeground(Color.BLUE);
        }
        if (isNull || (expert.getRsiClosePeriod() != null && expert.getRsiClosePeriod().toString().equals(localRsiClosePeriodTextField.getText()))) {
            localRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localRsiClosePeriodTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getRsiCloseStopLossTop() != null && expert.getRsiCloseStopLossTop().toString().equals(localRsiCloseStopLossTopTextField.getText()))) {
            localRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localRsiCloseStopLossTopTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        if (isNull || (expert.getRsiCloseStopLossBottom() != null && expert.getRsiCloseStopLossBottom().toString().equals(localRsiCloseStopLossBottomTextField.getText()))) {
            localRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            localRsiCloseStopLossBottomTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }

    }

    private void setComponentsState() {
        ExpertVO expertVO = null;
        if (expertList.isSelectionEmpty()) {
            clearMetaFields();
            clearLocalFields();
            setMetaFieldsState(false);
            setLocalFieldsState(false);
        } else {
            expertVO = (ExpertVO) expertList.getSelectedValue();
            setMetaFieldsValue(expertVO.getMetaExpert());
            setLocalFieldsValue(expertVO.getLocalExpert());
        }
        setMetaFieldsChangedState(expertVO == null ? null : expertVO.getMetaExpert());
        setLocalFieldsChangedState(expertVO == null ? null : expertVO.getLocalExpert());
    }

    private Expert getSelectedMetaExpert() {
        return expertList.getSelectedValue() == null ? null : ((ExpertVO) expertList.getSelectedValue()).getMetaExpert();
    }

    private Expert getSelectedLocalExpert() {
        return expertList.getSelectedValue() == null ? null : ((ExpertVO) expertList.getSelectedValue()).getLocalExpert();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow window = new MainWindow();

                window.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JCheckBox allCheckBox;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JList expertList;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBox isCloseMaTakeProfitCheckBox;
    private javax.swing.JCheckBox isCloseRsiStopLossCheckBox;
    private javax.swing.JCheckBox isCloseWprTakeProfitCheckBox;
    private javax.swing.JCheckBox isOpenMaCheckBox;
    private javax.swing.JCheckBox isOpenWprCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadLocalExpertButton;
    private javax.swing.JButton loadMetaExpertButton;
    private javax.swing.JCheckBox localIsCloseMaTakeProfitCheckBox;
    private javax.swing.JCheckBox localIsCloseRsiStopLossCheckBox;
    private javax.swing.JCheckBox localIsCloseWprTakeProfitCheckBox;
    private javax.swing.JCheckBox localIsOpenMaCheckBox;
    private javax.swing.JCheckBox localIsOpenWprCheckBox;
    private javax.swing.JTextField localLotsTextField;
    private javax.swing.JTextField localMaClosePeriodTextField;
    private javax.swing.JTextField localMaCloseTakeProfitDiffTextField;
    private javax.swing.JTextField localMaOpenDiffTextField;
    private javax.swing.JTextField localMaOpenPeriodTextField;
    private javax.swing.JTextField localMaximumTextField;
    private javax.swing.JTextField localRsiClosePeriodTextField;
    private javax.swing.JTextField localRsiCloseStopLossBottomTextField;
    private javax.swing.JTextField localRsiCloseStopLossTopTextField;
    private javax.swing.JTextField localStepTextField;
    private javax.swing.JTextField localStopLossTextField;
    private javax.swing.JTextField localTakeProfitTextField;
    private javax.swing.JTextField localWprClosePeriodTextField;
    private javax.swing.JTextField localWprCloseTakeProfitBottomTextField;
    private javax.swing.JTextField localWprCloseTakeProfitTopTextField;
    private javax.swing.JTextField localWprOpenBottomTextField;
    private javax.swing.JTextField localWprOpenPeriodTextField;
    private javax.swing.JTextField localWprOpenTopTextField;
    private javax.swing.JCheckBox lotsCheckBox;
    private javax.swing.JCheckBox maClosePeriodCheckBox;
    private javax.swing.JCheckBox maCloseTakeProfitDiffCheckBox;
    private javax.swing.JCheckBox maOpenDiffCheckBox;
    private javax.swing.JCheckBox maOpenPeriodCheckBox;
    private javax.swing.JCheckBox maximumCheckBox;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBox metaIsCloseMaTakeProfitCheckBox;
    private javax.swing.JCheckBox metaIsCloseRsiStopLossCheckBox;
    private javax.swing.JCheckBox metaIsCloseWprTakeProfitCheckBox;
    private javax.swing.JCheckBox metaIsOpenMaCheckBox;
    private javax.swing.JCheckBox metaIsOpenWprCheckBox;
    private javax.swing.JTextField metaLotsTextField;
    private javax.swing.JTextField metaMaClosePeriodTextField;
    private javax.swing.JTextField metaMaCloseTakeProfitDiffTextField;
    private javax.swing.JTextField metaMaOpenDiffTextField;
    private javax.swing.JTextField metaMaOpenPeriodTextField;
    private javax.swing.JTextField metaMaximumTextField;
    private javax.swing.JTextField metaRsiClosePeriodTextField;
    private javax.swing.JTextField metaRsiCloseStopLossBottomTextField;
    private javax.swing.JTextField metaRsiCloseStopLossTopTextField;
    private javax.swing.JTextField metaStepTextField;
    private javax.swing.JTextField metaStopLossTextField;
    private javax.swing.JTextField metaTakeProfitTextField;
    private javax.swing.JTextField metaWprClosePeriodTextField;
    private javax.swing.JTextField metaWprCloseTakeProfitBottomTextField;
    private javax.swing.JTextField metaWprCloseTakeProfitTopTextField;
    private javax.swing.JTextField metaWprOpenBottomTextField;
    private javax.swing.JTextField metaWprOpenPeriodTextField;
    private javax.swing.JTextField metaWprOpenTopTextField;
    private javax.swing.JPanel moreSettingsPanel;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JCheckBox rsiClosePeriodCheckBox;
    private javax.swing.JCheckBox rsiCloseStopLossBottomCheckBox;
    private javax.swing.JCheckBox rsiCloseStopLossTopCheckBox;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JCheckBox stepCheckBox;
    private javax.swing.JCheckBox stopLossCheckBox;
    private javax.swing.JCheckBox takeProfitCheckBox;
    private javax.swing.JCheckBox wprClosePeriodCheckBox;
    private javax.swing.JCheckBox wprCloseTakeProfitBottomCheckBox;
    private javax.swing.JCheckBox wprCloseTakeProfitTopCheckBox;
    private javax.swing.JCheckBox wprOpenBottomCheckBox;
    private javax.swing.JCheckBox wprOpenPeriodCheckBox;
    private javax.swing.JCheckBox wprOpenTopCheckBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshModel() {
        System.out.println("mainwindow refreshmodel");
        for (ExpertVO expert : expertManager.getExpertVOs()) {
            System.out.println("add expert to list " + expert);
            experts.addElement(expert);
        }
        System.out.println("listben lévő expertek");
        for (int i = 0; i < experts.getSize(); i++) {
            System.out.println(experts.get(i).getMetaExpert());
        }
    }

}
