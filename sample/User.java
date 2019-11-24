package sample;

public class User {
    private String userID;
    private String numeUser;
    private String CNP;
    private String parola;
    private String esteMentor;
    private String puncte="0";

    public String getPuncte() {
        return puncte;
    }

    public void setPuncte(String puncte) {
        this.puncte = puncte;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNumeUser() {
        return numeUser;
    }

    public void setNumeUser(String numeUser) {
        this.numeUser = numeUser;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEsteMentor() {
        return esteMentor;
    }

    public void setEsteMentor(String esteMentor) {
        this.esteMentor = esteMentor;
    }
    public boolean checkCnp(){
        int LL = Integer.parseInt(this.CNP.substring(3,5));
        int ZZ = Integer.parseInt(this.CNP.substring(5,7));
        int JJ = Integer.parseInt(this.CNP.substring(7,9));
        if(this.CNP.length() != 13){
            return false;
        }else if(Character.getNumericValue(this.CNP.charAt(0)) == 0){
            return false;
        }
        else if(LL > 12 || LL <= 0){
            return false;
        }else if(ZZ > 31 || ZZ <= 0){
            return false;
        }else if(JJ > 52){
            return false;
        }
        else {
            String control = "279146358279";
            int sum = 0;
            int rest;
            for(int i = 0 ;i < 12;i++){
                sum += Character.getNumericValue(this.CNP.charAt(i))* Character.getNumericValue(control.charAt(i));
            }
            rest = sum - 11*(sum/11);
            if(rest == 10){
                rest = 1;
            }
            if(rest != Character.getNumericValue(this.CNP.charAt(12))){
                return false;
            }
        }
        return true;
    }
}
