package ioc;

public class Vodafone implements SIM{

	@Override
	public void calling() {
		// TODO Auto-generated method stub
		System.out.println("Calling using Vodafone...");
	}

	@Override
	public void data() {
		// TODO Auto-generated method stub
		System.out.println("Browsing internet using Vodafone...");
	}

}
