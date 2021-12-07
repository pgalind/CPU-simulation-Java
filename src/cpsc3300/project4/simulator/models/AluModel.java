package cpsc3300.project4.simulator.models;

import cpsc3300.project4.simulator.Instruction;

public class AluModel {

	private boolean zeroFlag = false;
	private int result;

	public int calculate(int data1, int data2, int operation) {
		// TODO: Return the result of ALU operation based on operation
		switch (operation) {
			case 0: // AND
				result = data1 & data2;
				break;
			case 1: // OR
				result = data1 | data2;
				break;
			case 2: // add
				result = data1 + data2;
				break;
			case 6: // sub
				result = data1 - data2;
				break;
			case 7: // slt
				result = (data1 < data2) ? 1 : 0;
				break;
			default:
				break;
		}
		if(result == 0){
			zeroFlag = true;
		}
		return result;
	}

	public static byte getOperation(byte funct, int AluOp) {
		// TODO: Return ALU operation based on function and OpCode
		// LW and SW instructions
		if (AluOp == 0) {
			return 2;
		}
		// beq instruction
		else if (AluOp == 1) {
			return 6;
		}
		// R-format instruction
		else if (AluOp == 2) {
			if(funct == 32) { return 2; } // add
			else if(funct == 34) { return 6; } // sub
			else if(funct == 36) { return 0; } // AND
			else if(funct == 37) { return 1; } // OR
			else if(funct == 42) { return 7; } // slt
		}
		return -1;
	}

	public static byte getOperation(Instruction instr) {
		return getOperation(instr.getFunct(), instr.getAluOp());
	}

	public boolean isZeroFlag() {
		return zeroFlag;
	}

	public int getResult(){
		return result;
	}
}
