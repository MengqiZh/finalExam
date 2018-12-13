package pkgCoreTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

public class Retirement_Test {

	
	@Test
	public void RetirementCoreMethods_Test1() {
		double dExpectedPV = 1454485.55;
		double dExpectedtoSave = 554.13;
		
		Retirement ret = new Retirement();
		ret.setiYearsToWork(40);
		ret.setdAnnualReturnWorking(7);
		ret.setiYearsRetired(20);
		ret.setdAnnualReturnRetired(2);
		ret.setdRequiredIncome(-10000);
		ret.setdMonthlySSI(-2642);
		
		assertEquals(dExpectedPV, ret.TotalAmountSaved(), 0.01); 
		assertEquals(dExpectedtoSave, ret.AmountToSave(), 0.01);
	}

}
