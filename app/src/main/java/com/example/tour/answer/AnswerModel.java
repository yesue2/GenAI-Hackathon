package com.example.tour.answer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnswerModel extends ViewModel {
    private MutableLiveData<int[]> opennessData = new MutableLiveData<>();
    private MutableLiveData<int[]> conscientiousnessData = new MutableLiveData<>();
    private MutableLiveData<int[]> extraversionData = new MutableLiveData<>();
    private MutableLiveData<int[]> agreeablenessData = new MutableLiveData<>();
    private MutableLiveData<int[]> neuroticismData = new MutableLiveData<>();



    public LiveData<int[]> getOpennessData() {
        return opennessData;
    }
    public void setOpennessData(int[] openness) {
        opennessData.setValue(openness);
    }

    public LiveData<int[]> getConscientiousnessData() {
        return conscientiousnessData;
    }
    public void setConscientiousnessData(int[] conscientiousness) {conscientiousnessData.setValue(conscientiousness);}

    public LiveData<int[]> getExtraversionData() {
        return extraversionData;
    }

    public void setExtraversionData(int[] extraversion) {
        extraversionData.setValue(extraversion);
    }

    public LiveData<int[]> getAgreeablenessData() {
        return agreeablenessData;
    }

    public void setAgreeablenessData(int[] extraversion) {agreeablenessData.setValue(extraversion);}

    public LiveData<int[]> getNeuroticismData() {
        return neuroticismData;
    }

    public void setNeuroticismData(int[] extraversion) {
        neuroticismData.setValue(extraversion);
    }

}