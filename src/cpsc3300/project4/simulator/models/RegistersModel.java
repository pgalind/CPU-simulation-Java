package cpsc3300.project4.simulator.models;

public class RegistersModel {
	private int[] registers = new int[32]; // TODO: Identify number of registers to support
	//public static final String[] registerNames = new String[] {"r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15", "r16", "r17", "r18", "r19", "r20", "r21", "r22", "r23", "r24", "r25", "r26", "r27", "r28", "r29", "r30", "r31"}; // TODO: Identify register names in order
	public static final String[] registerNames = new String[] {"$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};

	public int readRegister(byte regAddr) {
		// TODO: return value in register given by index
		if(regAddr >= 0 && regAddr <= 31){
			return registers[regAddr];
		}
		else{
			return -1;
		}
	}

	public void writeRegister(byte regAddr, int word) {
		// TODO: store value word in register given by index
		if (regAddr >= 0 && regAddr <= 31) {
			registers[regAddr] = word;
		}
	}
}
