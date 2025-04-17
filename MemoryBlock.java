public class MemoryBlock {
    int blockNumber;
    int size;
    int startAddress;
    int endAddress;
    boolean isFree;
    String processID;
    int internalFragmentation;

    public MemoryBlock(int blockNumber, int size, int startAddress) {
        this.blockNumber = blockNumber;
        this.size = size;
        this.startAddress = startAddress;
        this.endAddress = startAddress + size - 1;
        this.isFree = true;
        this.processID = "Null";
        this.internalFragmentation = 0;
    }

    public void allocate(String pid, int requestedSize) {
        this.isFree = false;
        this.processID = pid;
        this.internalFragmentation = this.size - requestedSize;
    }

    public void deallocate() {
        this.isFree = true;
        this.processID = "Null";
        this.internalFragmentation = 0;
    }
    public String toString2() {
        return String.format("Block%d   %dKB     %d-%d      %s",
                blockNumber, size, startAddress, endAddress,
                isFree ? "free" : "allocated");
    }

    public String toString() {
        return String.format("Block%d   %dKB   %d-%d   %s   %s   %dKB",
                blockNumber, size, startAddress, endAddress,
                isFree ? "free" : "allocated", processID, internalFragmentation);
    }
}