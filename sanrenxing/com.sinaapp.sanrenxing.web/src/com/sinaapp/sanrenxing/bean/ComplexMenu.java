package com.sinaapp.sanrenxing.bean;

import java.util.ArrayList;
import java.util.List;

public class ComplexMenu extends Menu {

	private List<Menu> sub_button = new ArrayList<Menu>();

	public List<Menu> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Menu> sub_button) {
		this.sub_button = sub_button;
	}
	
}
