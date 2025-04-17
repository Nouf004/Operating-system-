import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        System.out.print("Enter the total number of blocks: ");
        int bnum = input.nextInt();

        int[] blockSizes = new int[bnum];
        System.out.print("Enter the size of each block in KB: ");
        for (int i=0; i < bnum; i++) {
            blockSizes[i]=input.nextInt();
        }

        System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
        int strategy = input.nextInt();

        MemoryManagement manager=new MemoryManagement(blockSizes, strategy);

        while (true) {
            System.out.println("============================================");
            System.out.println("1) Allocates memory blocks");
            System.out.println("2) De-allocates memory blocks");
            System.out.println("3) Print report");
            System.out.println("4) Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            int c=input.nextInt();

            switch (c) {
                case 1:
                    System.out.print("Enter the process ID and size of process: ");
                    String pid=input.next();
                    int size=input.nextInt();
                    manager.allocate(pid, size);
                    break;
                case 2:
                    System.out.print("Enter the process ID to deallocate: ");
                    String pid2=input.next();
                    manager.deallocate(pid2);
                    break;
                case 3:
                    manager.printReport();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
