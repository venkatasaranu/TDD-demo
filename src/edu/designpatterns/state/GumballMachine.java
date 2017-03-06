
package edu.designpatterns.state;

public class GumballMachine {
	private GumballHardwareDevice device;
	private static final int SOLD_OUT=0;
	private static final int HAS_QUARTER=1;
	private static final int NO_QUARTER=2;
	
	private int state;
	public GumballMachine(GumballHardwareDevice device) {
		this.device = device;
		device.displayLine(Messages.SO_START);
		state=SOLD_OUT;
	}

	public void insertQuarter() {
	    if(state==SOLD_OUT){
			device.displayLine(Messages.SO_QUART);
		} else if(state==NO_QUARTER){
			state=HAS_QUARTER;
			device.displayLine(Messages.NQ_QUART);
		} else if(state==HAS_QUARTER){
	    	device.displayLine(Messages.HQ_QUART);
	    	device.dispenseQuarter();
	    	state=NO_QUARTER;
	    }
		
		device.dispenseQuarter();
	}

	public void ejectQuarter() {
		if(state == SOLD_OUT){
			device.displayLine(Messages.SO_EJECT);
		}else if(state == NO_QUARTER){
			device.displayLine(Messages.NQ_EJECT);
		} else if(state == HAS_QUARTER){
			device.dispenseQuarter();
			device.displayLine(Messages.HQ_EJECT);
			state=NO_QUARTER;
		}
	}

	public void turnCrank() {
		if(state==SOLD_OUT){
			device.displayLine(Messages.SO_CRANK);
		} else if(state==NO_QUARTER){
			device.displayLine(Messages.NQ_CRANK);
		}else if(state==HAS_QUARTER){
			if(device.dispenseGumball()){
				device.displayLine(Messages.HQ_CRANK);
				state=NO_QUARTER;
			} else{
				device.displayLine(Messages.SO_START);
				device.dispenseQuarter();
				state=SOLD_OUT;
			}
		}
	}

	public void refill() {
		if(state==HAS_QUARTER){
			device.displayLine(Messages.HQ_START);
		} else {
			device.displayLine(Messages.NQ_START);
			state=NO_QUARTER;
		}
	}
}
