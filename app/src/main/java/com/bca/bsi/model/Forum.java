package com.bca.bsi.model;

public class Forum {

    public static class Inbox{

        private String username;
        private String date;
        private String content;

        public Inbox() {
        }

        public Inbox(String username, String date, String content) {
            this.username = username;
            this.date = date;
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public String getDate() {
            return date;
        }

        public String getContent() {
            return content;
        }
    }

}
