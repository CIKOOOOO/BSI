package com.bca.bsi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JawabanKuis {

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
}
