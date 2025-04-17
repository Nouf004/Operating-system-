public class MemoryBlock {
    int blockNum;
    int size;
    int startAddress;
    int endAddress;
    String status; // "free" or "allocated"
    String processID;
    int internalFragmentation;

    public MemoryBlock(int blockNum, int size, int startAddress) {
        this.blockNum = blockNum;
        this.size = size;
        this.startAddress = startAddress;
        this.endAddress = startAddress + size - 1;
        this.status = "free";
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    public void allocate(String processID, int Size) {
        status = "allocated";
        this.processID = processID;
        internalFragmentation = this.size - Size;
    }

    public void deallocate() {
        status = "free";
        processID = "Null";
        internalFragmentation = 0;
    }

    // For printReport()
    public String toString() {
        return String.format("Block%d   %dKB   %d-%d   %s   %s   %dKB",
                blockNum, size, startAddress, endAddress, status, processID, internalFragmentation);
    }

    // For the initial memory
    public String toString2() {
        return String.format("Block%d   %dKB     %d-%d      %s",
                blockNum, size, startAddress, endAddress, status);
    }
}
