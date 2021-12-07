package cpsc3300.project4.simulator.views;

import cpsc3300.project4.simulator.models.ControlModel;
import cpsc3300.project4.simulator.models.DataMemoryModel;
import cpsc3300.project4.simulator.models.InstructionMemoryModel;
import cpsc3300.project4.simulator.models.RegistersModel;

public class TextCpuView implements ICpuView {

	// private variables for calculations
	static int total_reads = 0;
	static int total_writes = 0;
	static int total_cycles = 0;
	static int total_aluOps = 0;

	@Override
	public void updateView(InstructionMemoryModel IM, DataMemoryModel DM, RegistersModel regs, int pc) {

		// TODO: Display PC information

		System.out.println("------------------Registers------------------");
		// TODO: Display register information
		for (int i =0; i < 32; i++){
			System.out.printf("%9s = %d\t", regs.registerNames[i], regs.readRegister((byte) i));
			if ((i + 1) % 4 == 0){
				System.out.printf("\n");
			}
		}
		System.out.printf("\n\n");

		// TODO: Display memory information
		System.out.println("---Address---|---Data---|-------Instruction-------");
		for(int pcc = 0; pcc <= pc; pcc+=4){
			ControlModel c = new ControlModel(IM.readInstruction(pcc));
			String padded = String.format("%8s%6s%5s%6s%24s", pcc, "|", DM.readData(pcc), "|", IM.readInstruction(pcc).decode());
			System.out.println(padded);
		}
		System.out.printf("\n\n");

		// TODO: Display logic block statistics
		/* You should track # cycles for a given program.
		   Also, you should track ALU arithmetic operations (how many add, sub, etc ops).
		   Note: Some instructions besides "add" use add. example is beq; this counts as an ALU arithmetic op, incrementing the PC does not.
		   You should track # of memory reads/writes too.
		   The control should track the # of each individual instruction. */
		ControlModel c = new ControlModel(IM.readInstruction(pc));
		total_reads += c.getNum_reads();
		total_writes += c.getNum_writes();
		total_cycles += c.getNum_cycles();
		total_aluOps += c.getNum_aluOps();

		System.out.println("--------Logic Block Statistics--------");
		System.out.println("Total Cycles: " + total_cycles);
		System.out.println("Total ALU Operations: " + total_aluOps);
		System.out.println("Total Reads: " + total_reads);
		System.out.println("Total Writes: " + total_writes);

		System.out.printf("\n\n");
	}

	/*TESTING

	public static void main(String[] args) {
		// OPTIONAL TODO: Update constructors and initialization as needed -- only for testing
		InstructionMemoryModel im   = new InstructionMemoryModel();
		DataMemoryModel        dm   = new DataMemoryModel();
		RegistersModel         regs = new RegistersModel();

		TextCpuView v = new TextCpuView();
	}

	 */
}
