package cpsc3300.project4.simulator;

public class Instruction {

	private int instr;
	private byte opcode;
	private byte rs;
	private byte rt;
	private short tail;

	// variables for R-format instructions
	private byte rd;
	private byte shamt;
	private byte funct;
	private int jumpAdd;


	public Instruction(int instr) {
		this.instr = instr;
		// TODO: Set opcode, rs, rt, and tail based on binary data in instr
		this.opcode = (byte) (instr >>> 26); // get the last
		this.rs = (byte) (instr >>> 21 & 0x1F); // mask: 0x1F = 0001 1111
		this.rt = (byte) (instr >>> 16 & 0x1F);
		this.tail = (short) (instr & 0xFFFF); // mask: 1111 1111 1111 1111
	}

	public int getInstr() {
		return instr;
	}

	public byte getOpcode() {
		return opcode;
	}

	public byte getRs() {
		return rs;
	}

	public byte getRt() {
		return rt;
	}

	public int getExtendedAddress() {
		return tail;
	}

	public byte getRd() {
		// TODO: Isolate Rd value from instruction
		rd = (byte) (instr >>> 11 & 0x1F);
		return rd;
	}

	public byte getShamt() {
		// TODO: Isolate Shamt value from instruction
		shamt = (byte) (instr >>> 6 & 0x1F);
		return shamt;
	}

	public byte getFunct() {
		// TODO: Isolate Funct value from instruction
		funct = (byte) (instr & 0x3F);
		return funct;
	}

	public int getJumpAddress() {
		// TODO: Isolate address value from jump instruction
		jumpAdd = instr << 6;
		jumpAdd = jumpAdd >>> 6;
		return jumpAdd;
	}

	public int getAluOp() {
		switch (opcode) {
			// TODO: Make cases for various types based on opcode, returning the appropriate ALU Op
			case 0: // R-format instruction
				return 2;
			case 4: // beq
				return 1;
			case 35: // lw
				return 0;
			case 43: // sw
				return 0;
			default:
				return 0;
		}
	}

	public String decode() {
		//TODO convert instruction back to assembly code
		String rs_name = getAssemblyName(rs);
		String rt_name = getAssemblyName(rt);
		String rd_name = getAssemblyName(this.getRd());

		String decoded = "";
		if(opcode == 0){
			if(this.getFunct() == 32){
				decoded += "add";
			}
			else if(this.getFunct() == 34){
				decoded += "sub";
			}
			else if(this.getFunct() == 36){
				decoded += "and";
			}
			else if(this.getFunct() == 37){
				decoded += "or";
			}
			else if(this.getFunct() == 42){
				decoded += "slt";
			}
			decoded += " " + rd_name + ", " + rs_name + ", " + rt_name;
		}
		else if(opcode == 35){
			decoded += "lw " + rd_name + ", " + tail + "(" + rs_name + ")";
		}
		else if(opcode == 43){
			decoded += "sw " + rs_name + ", " + tail + "(" + rd_name + ")";
		}
		else if(opcode == 4){
			decoded += "beq " + rs_name + ", " + rt_name + ", " + tail;
		}
		else if(opcode == 2){
			decoded += "j " + this.getJumpAddress();
		}
		return decoded;
	}

	private String getAssemblyName(byte register){
		switch(register){
			case 0:
				return "$zero";
			case 1:
				return "$at";
			case 2:
				return "$v0";
			case 3:
				return "$v1";
			case 4:
				return "$a0";
			case 5:
				return "$a1";
			case 6:
				return "$a2";
			case 7:
				return "$a3";
			case 8:
				return "$t0";
			case 9:
				return "$t1";
			case 10:
				return "$t2";
			case 11:
				return "$t3";
			case 12:
				return "$t4";
			case 13:
				return "$t5";
			case 14:
				return "$t6";
			case 15:
				return "$t7";
			case 16:
				return "$s0";
			case 17:
				return "$s1";
			case 18:
				return "$s2";
			case 19:
				return "$s3";
			case 20:
				return "$s4";
			case 21:
				return "$s5";
			case 22:
				return "$s6";
			case 23:
				return "$s7";
			case 24:
				return "$t8";
			case 25:
				return "$t9";
			case 26:
				return "$k0";
			case 27:
				return "$k1";
			case 28:
				return "$gp";
			case 29:
				return "$sp";
			case 30:
				return "$fp";
			case 31:
				return "$ra";
			default:
				return "";
		}
	}

	@Override
	public String toString() {
		String bin = Integer.toBinaryString(instr);
		//left pad with zeros to be full 32 bit width
		return "00000000000000000000000000000000".substring(bin.length()) + bin;
	}
}
