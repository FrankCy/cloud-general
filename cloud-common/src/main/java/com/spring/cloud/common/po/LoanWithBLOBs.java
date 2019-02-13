package com.spring.cloud.common.po;

public class LoanWithBLOBs extends Loan {
    private String companyDescription;

    private String companyName;

    private String contractType;

    private String customPicture;

    private String description;

    private String fundDescription;

    private String guaranteeCompanyDescription;

    private String guaranteeCompanyName;

    private String guaranteeInfoDescription;

    private String loanInstruction;

    private String location;

    private String policyDescription;

    private String riskDescription;

    private String riskInstruction;

    private String guarantycontractType;

    private String transferType;

    private String investContractType;

    private String nodeBody;

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription == null ? null : companyDescription.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public String getCustomPicture() {
        return customPicture;
    }

    public void setCustomPicture(String customPicture) {
        this.customPicture = customPicture == null ? null : customPicture.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFundDescription() {
        return fundDescription;
    }

    public void setFundDescription(String fundDescription) {
        this.fundDescription = fundDescription == null ? null : fundDescription.trim();
    }

    public String getGuaranteeCompanyDescription() {
        return guaranteeCompanyDescription;
    }

    public void setGuaranteeCompanyDescription(String guaranteeCompanyDescription) {
        this.guaranteeCompanyDescription = guaranteeCompanyDescription == null ? null : guaranteeCompanyDescription.trim();
    }

    public String getGuaranteeCompanyName() {
        return guaranteeCompanyName;
    }

    public void setGuaranteeCompanyName(String guaranteeCompanyName) {
        this.guaranteeCompanyName = guaranteeCompanyName == null ? null : guaranteeCompanyName.trim();
    }

    public String getGuaranteeInfoDescription() {
        return guaranteeInfoDescription;
    }

    public void setGuaranteeInfoDescription(String guaranteeInfoDescription) {
        this.guaranteeInfoDescription = guaranteeInfoDescription == null ? null : guaranteeInfoDescription.trim();
    }

    public String getLoanInstruction() {
        return loanInstruction;
    }

    public void setLoanInstruction(String loanInstruction) {
        this.loanInstruction = loanInstruction == null ? null : loanInstruction.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription == null ? null : policyDescription.trim();
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription == null ? null : riskDescription.trim();
    }

    public String getRiskInstruction() {
        return riskInstruction;
    }

    public void setRiskInstruction(String riskInstruction) {
        this.riskInstruction = riskInstruction == null ? null : riskInstruction.trim();
    }

    public String getGuarantycontractType() {
        return guarantycontractType;
    }

    public void setGuarantycontractType(String guarantycontractType) {
        this.guarantycontractType = guarantycontractType == null ? null : guarantycontractType.trim();
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    public String getInvestContractType() {
        return investContractType;
    }

    public void setInvestContractType(String investContractType) {
        this.investContractType = investContractType == null ? null : investContractType.trim();
    }

    public String getNodeBody() {
        return nodeBody;
    }

    public void setNodeBody(String nodeBody) {
        this.nodeBody = nodeBody == null ? null : nodeBody.trim();
    }
}