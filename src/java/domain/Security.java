package domain;

public class Security {
    private String securityQuestion;
    private String securityAnswer;

    public Security(String securityQuestion, String securityAnswer) {
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }
    
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}
