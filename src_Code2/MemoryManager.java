import java.util.*;

public class MemoryManagement {
    List<MemoryBlock> blocks;
    int strategy; // 1 = first-fit, 2 = best-fit, 3 = worst-fit

    public MemoryManagement(int[] sizeArr, int strategy){
        this.strategy = strategy;
        blocks = new ArrayList<>();
        int begin = 0;
        for (int i = 0; i < sizeArr.length; i++) {
            blocks.add(new MemoryBlock(i, sizeArr[i], begin));
            begin += sizeArr[i];
        }

        if (begin != 0) {
            System.out.println("Memory blocks are created...");
            System.out.println("Memory blocks:");
            System.out.println("==========================================================");
            System.out.println("Block#  Size(KB)  Start-End  Status");
            System.out.println("==========================================================");
            for (MemoryBlock block : blocks) {
                // use toString2 from MemoryBlock
                System.out.println(block.toString2());
            }
        }
    }

    public void allocate(String proid, int size) {
        MemoryBlock target = null;

            for (MemoryBlock block:blocks) {
                if (block.status.equals("free") && block.size>=size) {
                    if (target == null) {
                        target = block;
                    } else {
                        switch (strategy) {
                            case 1: // First-Fit
                                break;
                            case 2: // Best-Fit
                                if (block.size<target.size)
                                    target=block;
                                break;
                            case 3: // Worst-Fit
                                if (block.size>target.size)
                                    target=block;
                                break;
                        }
                    }
                }
            }

            if (target != null) {
            allocateBlock(target, proid, size);
        } else {
            System.out.println("Request rejected! No sufficient memory available.");
        }
    }

    private void allocateBlock(MemoryBlock block, String proid, int size) {
        block.allocate(proid, size);
        System.out.printf("%s Allocated at address %d, and the internal fragmentation is %dKB\n",
                proid, block.startAddress, block.internalFragmentation);
    }

    public void deallocate(String proid) {
        for (MemoryBlock block : blocks) {
            if (block.status.equals("allocated") && block.processID.equals(proid)) {
                block.deallocate();
                System.out.println(proid + " deallocated successfully.");
                return;
            }
        }
        System.out.println("Process ID not found!");
    }

    public void printReport() {
        System.out.println("==========================================================");
        System.out.println("Block#  Size(KB)  Start-End  Status     PID     InternalFragmentation");
        System.out.println("==========================================================");
        for (MemoryBlock block : blocks) {
            // use toString from MemoryBlock
            System.out.println(block.toString());
        }
        System.out.println("==========================================================");
    }
}
