package me.yahyaismail.model;

public class Loan {

	public double computePayment(String principal, String period, String interest, String fixedInterest) throws Exception {
		double principalParsed = Double.parseDouble(principal);
		double periodParsed = Double.parseDouble(period);
		double interestParsed = Double.parseDouble(interest);
		double fixedInterestParsed = Double.parseDouble(fixedInterest);

		if (principalParsed < 0 || periodParsed < 0 || interestParsed < 0 || interestParsed > 100)
			throw new Exception("Entries may not be negative!");
		
		interestParsed = (interestParsed + fixedInterestParsed) / 100;
		double monthlyPayment = Math.pow((1+(interestParsed/12)), -periodParsed);
		monthlyPayment = principalParsed / (1 - monthlyPayment);
		monthlyPayment *= (interestParsed/12);
		return monthlyPayment;
	}
	
	public void validateStudentNum(String studentNum) throws Exception {
		int studentNumParsed = Integer.parseInt(studentNum);
		if (studentNumParsed <= 0 || studentNumParsed > 999999999) 
			throw new Exception("Student Number Must Be Valid!");
	}
	
	public double computeGrace(String principal, String gracePeriod, String interest, String fixedInterest) throws Exception {
		double principalParsed = Double.parseDouble(principal);
		double interestParsed = Double.parseDouble(interest);
		double fixedInterestParsed = Double.parseDouble(fixedInterest);
		double gracePeriodParsed = Double.parseDouble(gracePeriod);
		
		if (principalParsed < 0 || interestParsed < 0 || interestParsed > 100)
			throw new Exception("Entries may not be negative!");
		
		double result = principalParsed * (((interestParsed + fixedInterestParsed) / 100) / 12) * gracePeriodParsed;
		return result;
	}

	public double getTotalInterest(String interest, String fixedInterest) throws Exception {
		double interestParsed = Double.parseDouble(interest);
		double fixedInterestParsed = Double.parseDouble(fixedInterest);
		
		return interestParsed + fixedInterestParsed;
	}
	
	public String maxPrincipal(String currentMax, String principal) {
		if (currentMax == null)
			return principal;
		double curMaxParsed = Double.parseDouble(currentMax);
		double principalParsed = Double.parseDouble(principal);
		
		return Double.toString(Math.max(curMaxParsed, principalParsed));
	}
}

