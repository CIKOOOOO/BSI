package com.bca.bsi.model;

import java.util.Arrays;
import java.util.List;

public class KuisData {

    private String categoryId;
    private List<Quiz> quiz;
    private List<String> penilaianKuis = Arrays.asList("Wah pengetahuan Anda tentang investasi masih perlu ditingkatkan, Mari kita belajar lagi",
            "Pengetahuan Anda sudah cukup mengenai investasi, Ayo tingkatkan lagi","Wah hebat, kamu sudah menguasai materi ini. Ayo investasi sekarang");

    public KuisData(String categoryId, List<Quiz> quiz) {
        this.categoryId = categoryId;
        this.quiz = quiz;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public Quiz getQuizIndex(int index){
        return quiz.get(index);
    }

    public String getPenilaianKuisIndex (int index){
        return penilaianKuis.get(index);
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public static class Quiz {
        private String questionId;
        private String questionText;
        private List<DataJawaban> dataJawaban;
        private DataJawaban kunciJawaban;
        private String correctExplanation;
        private String falseExplanation;

        public Quiz(String questionId, String questionText, List<DataJawaban> dataJawaban, DataJawaban kunciJawaban, String correctExplanation, String falseExplanation) {
            this.questionId = questionId;
            this.questionText = questionText;
            this.dataJawaban = dataJawaban;
            this.kunciJawaban = kunciJawaban;
            this.correctExplanation = correctExplanation;
            this.falseExplanation = falseExplanation;
        }

        public String getQuestionId() {
            return questionId;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<DataJawaban> getDataJawaban() {
            return dataJawaban;
        }

        public DataJawaban getDataJawabanIndex(int index){
            return dataJawaban.get(index);
        }

        public DataJawaban getKunciJawaban() {
            return kunciJawaban;
        }

        public String getCorrectExplanation() {
            return correctExplanation;
        }

        public String getFalseExplanation() {
            return falseExplanation;
        }
    }

    public static class DataJawaban {
        private String answerId;
        private String answerOption;
        private String answerText;

        public DataJawaban(String answerId, String answerOption, String answerText) {
            this.answerId = answerId;
            this.answerOption = answerOption;
            this.answerText = answerText;
        }

        public String getAnswerId() {
            return answerId;
        }

        public String getAnswerOption() {
            return answerOption;
        }

        public String getAnswerText() {
            return answerText;
        }
    }

}


