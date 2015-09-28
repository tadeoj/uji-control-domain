package es.uji.control.domain.people.impl;

import es.uji.control.domain.people.ILinkage;

final public class Linkage implements ILinkage {
	
	private String name;
	
	public Linkage(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}
