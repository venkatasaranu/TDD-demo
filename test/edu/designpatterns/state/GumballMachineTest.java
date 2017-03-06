package edu.designpatterns.state;

import static org.junit.Assert.*;

import org.junit.*;

public class GumballMachineTest {
	private TestDevice device = new TestDevice();
	private GumballMachine gumballMachine;
	

	@Before
	public void setup() {
		gumballMachine = new GumballMachine(device);
	}

	@Test
	public void initialConditionsEmptyMachineShouldShowSoldOut() {
		assertEquals(Messages.SO_START, device.getLine());
	}

	
	@Test
	public void emptyMachineInsertQuarterShouldDisplayNoGumballs(){
		gumballMachine.insertQuarter();
		assertEquals(Messages.SO_QUART, device.getLine());
	}
	@Test
	public void initialConditionsEmptyMachineShouldNotDispenseQuarter() {
		assertFalse(device.hasQuarterEjected());
	}
	@Test
	public void initialConditionsEmptyMachineShouldDisplayNotASlotMachine() {
		gumballMachine.ejectQuarter();
		assertEquals(Messages.SO_EJECT, device.getLine());
	}
	
	@Test
	public void refillShouldDisplayNQStart(){
		gumballMachine.refill();
		assertEquals(Messages.NQ_START, device.getLine());
	}
	
	@Test
	public void turnCrankEmptyMachineShouldDisplayNoGumballs(){
		gumballMachine.turnCrank();
		assertEquals(Messages.SO_CRANK, device.getLine());
	}
	
	@Test
	public void insertQuarterAfterRefillShouldDisplayCrank(){
		refillHasQuarter();
		assertEquals(Messages.NQ_QUART, device.getLine());
	}

	
	@Test
	public void ejectQuarterAfterRefillShouldDisplayYouHaveNoQuarter(){
		refill();
		gumballMachine.ejectQuarter();
		assertEquals(Messages.NQ_EJECT, device.getLine());
	}
	
	@Test
	public void noQuarterTurnCrankShouldDisplayPayFirst(){
		refill();
		gumballMachine.turnCrank();
		assertEquals(Messages.NQ_CRANK, device.getLine());
	}
	
	@Test
	public void hasQuarterInsertShouldDisplayNoMoreQuarters(){
		refillHasQuarter();
		gumballMachine.insertQuarter();
		assertEquals(Messages.HQ_QUART, device.getLine());
	}
	@Test
	public void hasQuarterInsertShouldEjectQuarter(){
		refillHasQuarter();
		gumballMachine.insertQuarter();
		assertTrue(device.hasQuarterEjected());
	}
	
	@Test
	public void hasQuarterEjectShouldDisplayPickUpQuarter(){
		refillHasQuarter();
		gumballMachine.ejectQuarter();
		assertEquals(Messages.HQ_EJECT, device.getLine());
	}
	
	@Test
	public void hasQuarterEjectShouldEject(){
		refillHasQuarter();
		gumballMachine.ejectQuarter();
		assertTrue(device.hasQuarterEjected());
	}
	
	@Test
	public void hasQuarterTurnCrankHasGumballsShouldDisplayQuarter(){
		refillHasQuarter();
		gumballMachine.turnCrank();
		assertEquals(Messages.HQ_CRANK, device.getLine());
	}
	
	@Test
	public void hasQuartTurnCrankSoldOutShouldDisplaySoldOut(){
		refillHasQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		assertEquals(Messages.SO_START,device.getLine());
	}
	
	@Test
	public void hasQuartTurnCrankSouldOutShouldDispenseQuarter(){
		refillHasQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		assertTrue(device.hasQuarterEjected());
	}
	
	private void refill() {
		device.addGumballs(3);
		gumballMachine.refill();
	}
	
	private void refillHasQuarter() {
		refill();
		gumballMachine.insertQuarter();
	}
	
}
