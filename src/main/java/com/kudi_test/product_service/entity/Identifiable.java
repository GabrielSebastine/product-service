package com.kudi_test.product_service.entity;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {

	public void setId(ID id);
	
	public ID getId();
}
