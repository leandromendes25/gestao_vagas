package br.testando.gestao_vagas.modules.company.entity;

import jakarta.persistence.*;
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
private String level;
@ManyToOne()
@JoinColumn(name="company_id", insertable = false, updatable = false)
private CompanyEntity companyEntity;
@JoinColumn(name = "company_id")
private UUID companyId;
@CreationTimestamp
    private LocalDateTime createdAt;

public JobEntites(){
}

    public JobEntites(UUID id, String description, String benefits, String level, CompanyEntity companyEntity, UUID companyId) {
        this.id = id;
        this.description = description;
        this.benefits = benefits;
        this.level = level;
        this.companyEntity = companyEntity;
        this.companyId = companyId;
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

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
