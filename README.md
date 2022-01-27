CPU Simulation

Developers: Pablo Galindo
            Yuna Zhao

For this project we implemented the Model View Controller design pattern. This design pattern
allows separation of concerns and data hiding, and helps with debugging and making any future modifications.

To keep track of logic block statistics, we made private variables in ControlModel and their corresponding getter methods.
Each variable was incremented according to the type of instruction that was being processed, using the control signals.
In TextCpuView, we made static variables to track the total count of reads, writes, aluOps and cycles. These static
variables are updated by calling the getter methods from ControlModel object.

Since the Controller calls TextCpuView from inside a while loop, textCpuView should only output the current state of the machine.
One mistake we encountered earlier in the development was that textCpuView was looping through every instruction to get the totals for the
logic block statistics, meaning for each iteration of the Controller loop, we were printing the total values for all instructions,
causing the numbers to grow exponentially. This was fixed by removing the loop, and only printing the statistics for the 
current instruction.
