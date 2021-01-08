package com.bca.bsi.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JawabanKuis {

    private List<String> pertanyaanList = new ArrayList<>(5);
    private List<String> jawabanOpsiAList = new ArrayList<>(5);
    private List<String> jawabanOpsiBList = new ArrayList<>(5);
    private List<String> jawabanOpsiCList = new ArrayList<>(5);
    private List<String> corretExplList = new ArrayList<>(5);
    private List<String> wrongExplList = new ArrayList<>(5);
    private List<String> kunJawList = new ArrayList<>(5);
    private List<String> jawabanUser = new ArrayList<>(5);
    private List<String> penilaianKuis = Arrays.asList("Wah pengetahuan Anda tentang investasi masih perlu ditingkatkan, Mari kita belajar lagi",
            "Pengetahuan Anda sudah cukup mengenai investasi, Ayo tingkatkan lagi","Wah hebat, kamu sudah menguasai materi ini. Ayo investasi sekarang");

    public String getKunJawList(int index) {
        return kunJawList.get(index);
    }

    public String getJawabanUser(int index) {
        return jawabanUser.get(index);
    }

    public void setKunJawList(List<String> kunJawList) {
        this.kunJawList = kunJawList;
    }

    public void setKunJaw(List<String> kunJaw) {
        for(int i=0;i<5;i++){
            this.kunJawList.add(kunJaw.get(i));
        }
    }

    public List<String> getJawabanUserList() {
        return jawabanUser;
    }

    public void setJawabanUser(List<String> jawabanUser) {
        this.jawabanUser = jawabanUser;
    }

    public void storeKunciJawaban(){

    }

    public void storeJawabanUser(String ans){
        this.jawabanUser.add(ans);
    }

    public String getPenilaianKuis(int index) {
        return penilaianKuis.get(index);
    }

    public List<String> getPertanyaanList() {
        return pertanyaanList;
    }

    public List<String> getJawabanOpsiAList() {
        return jawabanOpsiAList;
    }

    public List<String> getJawabanOpsiBList() {
        return jawabanOpsiBList;
    }

    public List<String> getJawabanOpsiCList() {
        return jawabanOpsiCList;
    }

    public List<String> getCorretExplList() {
        return corretExplList;
    }

    public List<String> getWrongExplList() {
        return wrongExplList;
    }

    public String getPertanyaan(int index) {
        return pertanyaanList.get(index);
    }

    public String getJawabanOpsiA(int index) {
        return jawabanOpsiAList.get(index);
    }

    public String getJawabanOpsiB(int index) {
        return jawabanOpsiBList.get(index);
    }

    public String getJawabanOpsiC(int index) {
        return jawabanOpsiCList.get(index);
    }

    public String getCorretExpl(int index) {
        return corretExplList.get(index);
    }

    public String getWrongExpl(int index) {
        return wrongExplList.get(index);
    }

    public void setPertanyaanList(List<String> pertanyaanList) {
        this.pertanyaanList = pertanyaanList;
    }

    public void setJawabanOpsiAList(List<String> jawabanOpsiAList) {
        this.jawabanOpsiAList = jawabanOpsiAList;
    }

    public void setJawabanOpsiBList(List<String> jawabanOpsiBList) {
        this.jawabanOpsiBList = jawabanOpsiBList;
    }

    public void setJawabanOpsiCList(List<String> jawabanOpsiCList) {
        this.jawabanOpsiCList = jawabanOpsiCList;
    }

    public void setCorretExplList(List<String> corretExplList) {
        this.corretExplList = corretExplList;
    }

    public void setWrongExplList(List<String> wrongExplList) {
        this.wrongExplList = wrongExplList;
    }
}
