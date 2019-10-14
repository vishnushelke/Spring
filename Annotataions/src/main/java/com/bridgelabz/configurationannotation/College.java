package com.bridgelabz.configurationannotation;

public class College {

	private Principal principal;
	
	private Teacher teacher;

//	public College(Principal principal) {
//		this.principal = principal;
//	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public void show() {
		principal.showPrincipal();
		teacher.teach();
		System.out.println("succesfully worked");
	}

}
