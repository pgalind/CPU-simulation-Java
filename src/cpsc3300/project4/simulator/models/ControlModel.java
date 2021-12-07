package cpsc3300.project4.simulator.models;

import cpsc3300.project4.simulator.Instruction;

public class ControlModel {

	private boolean regDst;
	private boolean branch;
	private boolean memRead;
	private boolean memToReg;
	private byte    aluOp; //2 bits
	private boolean memWrite;
	private boolean aluSrc;
	private boolean regWrite;

	// private variables for calculations
	private int num_reads = 0;
	private int num_writes = 0;
	private int num_cycles = 0;
	private int num_aluOps = 0;


	public ControlModel(byte opcode) {
		// TODO: Switch on OpCode to set controls for the private variables for each kind of instruction
		switch (opcode) {
			// R-format instruction (add, sub, and, or, slt)
			case 0:
				regDst = true;
				aluSrc = false;
				memToReg = false;
				regWrite = true;
				memRead = false;
				memWrite = false;
				branch = false;
				aluOp = 2;
				break;
			// LW instruction (opcode 35)
			case 35:
				regDst = false;
				aluSrc = true;
				memToReg = true;
				regWrite = true;
				memRead = true;
				memWrite = false;
				branch = false;
				aluOp = 0;
				break;
			// SW instruction (opcode 45)
			case 45:
				regDst = false; // X
				aluSrc = true;
				memToReg = false; // X
				regWrite = false;
				memRead = false;
				memWrite = true;
				branch = false;
				aluOp = 0;
				break;
			// beq instruction (opcode 4)
			case 4:
				regDst = false; // X
				aluSrc = false;
				memToReg = false; // X
				regWrite = false;
				memRead = false;
				memWrite = false;
				branch = true;
				aluOp = 1;
				break;
			default:
				regDst = false;
				aluSrc = false;
				memToReg = false;
				regWrite = false;
				memRead = false;
				memWrite = false;
				branch = false;
				aluOp = 0;
				break;
		}
		// update statistic variables based on control variables
		if(isMemRead()) num_reads++;
		if(isMemWrite()) num_writes++;
		if(opcode == 0 || opcode == 45){
			num_cycles += 4;
			num_aluOps++;
		}
		else if(opcode == 35){
			num_cycles += 5;
			num_aluOps++;
		}
		else if(opcode == 4){
			num_cycles += 3;
			num_aluOps++;
		}
		else if(opcode == 2){
			num_cycles += 3;
		}

	}

	public ControlModel(Instruction instr) {
		this(instr.getOpcode());
	}

	public boolean isRegDst() {
		return regDst;
	}

	public boolean isBranch() {
		return branch;
	}

	public boolean isMemRead() {
		return memRead;
	}

	public boolean isMemToReg() {
		return memToReg;
	}

	public byte getAluOp() {
		return aluOp;
	}

	public boolean isMemWrite() {
		return memWrite;
	}

	public boolean isAluSrc() {
		return aluSrc;
	}

	public boolean isRegWrite() {
		return regWrite;
	}

	public int getNum_reads(){ return num_reads; }

	public int getNum_writes(){ return num_writes; }

	public int getNum_cycles(){ return num_cycles; }

	public int getNum_aluOps(){ return num_aluOps; }
}
