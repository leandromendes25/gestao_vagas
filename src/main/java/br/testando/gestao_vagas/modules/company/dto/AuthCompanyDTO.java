package br.testando.gestao_vagas.modules.company.dto;

public class AuthCompanyDTO {
    private String password;
    private String userName;

    public AuthCompanyDTO(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
