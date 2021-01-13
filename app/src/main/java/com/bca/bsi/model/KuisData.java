package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class KuisData {

    public KuisData() {
    }

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("quizList")
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
        @SerializedName("question_id")
        private String questionId;

        @SerializedName("question_text")
        private String questionText;

        @SerializedName("data_jawabanList")
        private List<DataJawaban> dataJawaban;

        @SerializedName("kunci_jawaban")
        private DataJawaban kunciJawaban;

        @SerializedName("correct_explanation")
        private String correctExplanation;

        @SerializedName("false_explanation")
        private String falseExplanation;

        public Quiz(String questionId, String questionText, List<DataJawaban> dataJawaban, DataJawaban kunciJawaban, String correctExplanation, String falseExplanation) {
            this.questionId = questionId;
            this.questionText = questionText;
            this.dataJawaban = dataJawaban;
            this.kunciJawaban = kunciJawaban;
            this.correctExplanation = correctExplanation;
            this.falseExplanation = falseExplanation;
        }

        public Quiz() {}

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
        @SerializedName("answer_id")
        private String answerId;

        @SerializedName("answer_option")
        private String answerOption;

        @SerializedName("answer_text")
        private String answerText;

        public DataJawaban(String answerId, String answerOption, String answerText) {
            this.answerId = answerId;
            this.answerOption = answerOption;
            this.answerText = answerText;
        }

        public DataJawaban(){}

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

    public static class UserScore {
        public String categoryId;
        public String bcaId;
        public String dateAttempt;
        public String score;

        public UserScore(String categoryId, String bcaId, String dateAttempt, String score) {
            this.categoryId = categoryId;
            this.bcaId = bcaId;
            this.dateAttempt = dateAttempt;
            this.score = score;
        }

        public UserScore(){}

        public String getCategoryId() {
            return categoryId;
        }

        public String getBcaId() {
            return bcaId;
        }

        public String getDateAttempt() {
            return dateAttempt;
        }

        public String getScore() {
            return score;
        }
    }

}


