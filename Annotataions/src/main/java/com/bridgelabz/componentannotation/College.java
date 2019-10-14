package com.bridgelabz.componentannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class College {

	@Value("${college.name}")
	private String collegeName;
	@Autowired
	private Principal principal;
	@Autowired
	@Qualifier("mathTeacher")
	private Teacher teacher;

//	public College(Principal principal) {
//		this.principal = principal;
//	}

	/**
	 * public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	 */
	

	public void show() {
		principal.showPrincipal();
		teacher.teach();
		System.out.println("My College name is "+collegeName);
		System.out.println("succesfully worked");
	}

}
