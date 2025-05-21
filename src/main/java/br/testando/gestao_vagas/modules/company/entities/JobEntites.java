package br.testando.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
public class JobEntites {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
private String description;
private String benefits;
@NotBlank(message = "Esse campo é obrigatório")
private String level;
@ManyToOne()
@JoinColumn(name="company_id")
private CompanyEntity companyEntity;
@CreationTimestamp
    private LocalDateTime createdAt;

public JobEntites(){
}

    public JobEntites(UUID id, String description, String benefits, String level, CompanyEntity companyEntity) {
        this.id = id;
        this.description = description;
        this.benefits = benefits;
        this.level = level;
        this.companyEntity = companyEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
