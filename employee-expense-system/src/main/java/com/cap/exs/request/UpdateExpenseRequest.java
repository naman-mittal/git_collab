package com.cap.exs.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UpdateExpenseRequest {

	@NotNull
	@Positive
	private int expenseCode;
	
	@NotNull
	@Size(min = 5,max = 30)
	private String expenseType;
	
	@NotNull
	@Size(min = 5,max = 50)
	private String expenseDescription;

	public UpdateExpenseRequest() {
		super();
		
	}

	public int getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(int expenseCode) {
		this.expenseCode = expenseCode;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public UpdateExpenseRequest(@NotNull @Positive int expenseCode,
			@NotNull @Size(min = 5, max = 30) String expenseType,
			@NotNull @Size(min = 5, max = 50) String expenseDescription) {
		super();
		this.expenseCode = expenseCode;
		this.expenseType = expenseType;
		this.expenseDescription = expenseDescription;
	}
	
	
}
