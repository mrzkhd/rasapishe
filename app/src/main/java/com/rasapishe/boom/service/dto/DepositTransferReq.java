package com.rasapishe.boom.service.dto;

import android.support.annotation.NonNull;

/**
 * Created by Mohammadreza on 2/16/2017 AD.
 */

public class DepositTransferReq {
	private String customer_number;

	@NonNull
	private String source_deposit;

	@NonNull
	private String destination_deposit;
	private String source_comment;
	private String destination_comment;
	private String reference_number;

	@NonNull
	private String amount;
	private String additional_document_desc;

	public String getCustomer_number() {
		return customer_number;
	}

	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}

	public String getSource_deposit() {
		return source_deposit;
	}

	public void setSource_deposit(String source_deposit) {
		this.source_deposit = source_deposit;
	}

	public String getDestination_deposit() {
		return destination_deposit;
	}

	public void setDestination_deposit(String destination_deposit) {
		this.destination_deposit = destination_deposit;
	}

	public String getSource_comment() {
		return source_comment;
	}

	public void setSource_comment(String source_comment) {
		this.source_comment = source_comment;
	}

	public String getDestination_comment() {
		return destination_comment;
	}

	public void setDestination_comment(String destination_comment) {
		this.destination_comment = destination_comment;
	}

	public String getReference_number() {
		return reference_number;
	}

	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAdditional_document_desc() {
		return additional_document_desc;
	}

	public void setAdditional_document_desc(String additional_document_desc) {
		this.additional_document_desc = additional_document_desc;
	}
}
