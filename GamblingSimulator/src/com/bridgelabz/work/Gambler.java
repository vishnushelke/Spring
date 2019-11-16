package com.bridgelabz.work;

public class Gambler {

	public static final int stakeInitial=100;
	public static final int bet=100;
	
	
	//all results of each day of month
		public static int[] monthBets()
		{
			//array for storign result of the day
			int[] dayResults = new int[30];
			//used for calculating result of day
			int count = 0;
			
			//for loop is used because he will play for 30days in month
			for (int i = 0; i < 30; i++) {
				//each day stakes will be set as 100 or initial stakes
				int stake = stakeInitial;
				//he will continue playing on that day while his he dosent resign 
				while(!isResign(stake))
				{
					//putting bet and updating the stakes
					count  = isWon();
				}
				//finally storing stakes of the particular day
				dayResults[i]= count;
				
			}
			//returning array of a month which contains final remaining stakes of each day
			return dayResults;
		}
		
	
	//4.
	//calculating result of first 20 days
		public static int resultOfTwentyDays()
		{
			int result=0;
			//one array taken to store results of a month
			int[] resultOfMonth = monthBets();
			//for first 20days,we will fetch first 20elemets of array and calculate the result
			//the result of the day whether he won or lost is calculated by comparing that value 
			//with 100
			//for example,if the result of day is 105 he it is 5 more than 100
			//so,player won 5 ruppes on that day and if result of the day is 95 the itis 5 less than 100
			//so, player lost 5 rupees on that day
			//so like this we will add all days result of a 20days and give output 
			for (int i = 0; i < 20; i++) {
				result=result+(resultOfMonth[i]-100);//added the result of each day with previous day result
			}
			return result;
		}
		//5. deciding whetyher to play or not after end of the month
		public static boolean continueGamble()
		{
			int result=0;
			//one array taken to store results of a month
			int[] resultOfMonth = monthBets();
			//just like above,calculated the result of the month
			for (int i = 0; i < resultOfMonth.length; i++) {
				result=result+(resultOfMonth[i]-100);//added the result of each day with previous day result
			}
			//now,checking whether result is positive or negative
			//if result is positive then it means he has won in that particular month and he will coninue to gamble
			//else he will stop gambling
			
			if(result<0)//if result is negative,stop playing.i.e.return false
				return false;
			else 
				return true;
		}
	
	
}
