package com.bridgelabz.objectinjection;

public class AnotherStudent {
	private MathCheat cheat;

	public void setCheat(MathCheat cheat) {
		this.cheat = cheat;
	}
	
	public void cheating()
	{
		cheat.mathCheat();
	}
}
