package com.bridgelabz.objectinjection;

public class Student {
	private MathCheat mathCheat;

	public void setMathCheat(MathCheat mathCheat) {
		this.mathCheat = mathCheat;
	}

	public void cheating()
	{
		mathCheat.mathCheat();
	}
}
