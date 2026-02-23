package com.tejait.batch15.Dto;



public class AccountResponseDto {
	
	
	private Long accountId;
	private String accountType;
	private String holderName;
	private String ifsc;
	private String kycStatus;
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getKycStatus() {
		return kycStatus;
	}
	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}
	@Override
	public String toString() {
		return "AccountResponseDto [accountId=" + accountId + ", accountType=" + accountType + ", holderName="
				+ holderName + ", ifsc=" + ifsc + ", kycStatus=" + kycStatus + "]";
	}
	
	

}
