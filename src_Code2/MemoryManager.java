import java.util.*;

public class MemoryManager {
    List<MemoryBlock> blocks;
    int strategy; // 1 = first-fit, 2 = best-fit, 3 = worst-fit

    public MemoryManager(int[] sizes, int strategy) {
        this.strategy = strategy;
        blocks = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sizes.length; i++) {
            blocks.add(new MemoryBlock(i, sizes[i], start));
            start += sizes[i];
        }
    }

    public boolean allocate(String pid, int size) {
        MemoryBlock target = null;

        for (MemoryBlock block : blocks) {
            if (block.isFree && block.size >= size) {
                if (target == null) {
                    target = block;
                } else {
                    switch (strategy) {
                        case 1: // First-Fit
                            return allocateBlock(block, pid, size);
                        case 2: // Best-Fit
                            if (block.size < target.size) target = block;
                            break;
                        case 3: // Worst-Fit
                            if (block.size > target.size) target = block;
                            break;
                    }
                }
            }
        }

        if (target != null) {
            return allocateBlock(target, pid, size);
        } else {
            System.out.println("Error: No suitable block found.");
            return false;
        }
    }

    private boolean allocateBlock(MemoryBlock block, String pid, int size) {
        block.allocate(pid, size);
        System.out.printf("%s Allocated at address %d, and the internal fragmentation is %dKB\n",
                pid, block.startAddress, block.internalFragmentation);
        return true;
    }

    public void deallocate(String pid) {
        for (MemoryBlock block : blocks) {
            if (!block.isFree && block.processID.equals(pid)) {
                block.deallocate();
                System.out.println(pid + " deallocated successfully.");
                return;
            }
        }
        System.out.println("Error: Process ID not found.");
    }

    public void printReport() {
        System.out.println("==========================================================");
        System.out.println("Block#   Size(KB)   Start-End    Status     PID    InternalFragmentation");
        System.out.println("==========================================================");
        for (MemoryBlock block : blocks) {
            System.out.println(block.toString());
        }
        System.out.println("==========================================================");
    }
}
