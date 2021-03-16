package tk.stockquotesservice.entity;

import javax.persistence.*;

/**
 * @author Andrey Fyodorov
 * Created on 13.03.2021
 */

@Entity
@Table(name = "company")
@IdClass(CompanyPK.class)
public class Company {

    @Id
    private String symbol;

    @Column(name = "company_name")
    private String companyName;

    @Id
    private String exchange;

    private String industry;

    private String website;

    @Column(name = "security_name")
    private String securityName;

    private String issueType;

    private String sector;

    private String country;

    @Transient
    private CompanyPK pk;

    public Company() {
    }

    public Company(CompanyPK companyPK) {
        this.pk = companyPK;
        symbol = companyPK.getSymbol();
        exchange = companyPK.getExchange();
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CompanyPK getPk() {
        return pk;
    }

    public void setPk(CompanyPK pk) {
        this.pk = pk;
    }
}
