package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public static class ForumUser{
        @SerializedName("profile_id")
        private String profileID;

        @SerializedName("bca_id")
        private String bcaID;

        @SerializedName("username")
        private String username;

        @SerializedName("follower_count")
        private String followerCount;

        @SerializedName("img_profile")
        private String imageProfile;

        @SerializedName("is_active")
        private String active;

        public ForumUser() {
        }

        public ForumUser(String profileID, String username, String imageProfile) {
            this.profileID = profileID;
            this.username = username;
            this.imageProfile = imageProfile;
        }

        public String getProfileID() {
            return profileID;
        }

        public String getBcaID() {
            return bcaID;
        }

        public String getUsername() {
            return username;
        }

        public String getFollowerCount() {
            return followerCount;
        }

        public String getImageProfile() {
            return imageProfile;
        }

        public String getActive() {
            return active;
        }
    }

    public static class WelmaUser{

        @SerializedName("name")
        private String name;

        @SerializedName("email")
        private String email;

        @SerializedName("bca_id")
        private String bcaID;

        @SerializedName("nomor_rekening")
        private String accountNumber;

        @SerializedName("profile_risiko")
        private String profileRisiko;

        @SerializedName("token_user")
        private String tokenUser;

        public WelmaUser() {
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getBcaID() {
            return bcaID;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getProfileRisiko() {
            return profileRisiko;
        }

        public String getTokenUser() {
            return tokenUser;
        }
    }

    public static class BCAUser{

        public static class Rekening{

            @SerializedName("saldo")
            private String saldo;

            @SerializedName("status_pembelian_pertama")
            private String statusPembelianPertama;

            public Rekening() {
            }

            public String getSaldo() {
                return saldo;
            }

            public String getStatusPembelianPertama() {
                return statusPembelianPertama;
            }
        }
    }
}
