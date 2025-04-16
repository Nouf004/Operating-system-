import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the total number of blocks: ");
        int m = sc.nextInt();
        int[] sizes = new int[m];
        System.out.print("Enter the size of each block in KB: ");
        for (int i = 0; i < m; i++) {
            sizes[i] = sc.nextInt();
        }

        System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
        int strategy = sc.nextInt();

        MemoryManager manager = new MemoryManager(sizes, strategy);

        while (true) {
            System.out.println("============================================");
            System.out.println("1) Allocates memory blocks");
            System.out.println("2) De-allocates memory blocks");
            System.out.println("3) Print report");
            System.out.println("4) Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the process ID and size of process: ");
                    String pid = sc.next();
                    int size = sc.nextInt();
                    manager.allocate(pid, size);
                    break;
                case 2:
                    System.out.print("Enter the process ID to deallocate: ");
                    String pidDel = sc.next();
                    manager.deallocate(pidDel);
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
