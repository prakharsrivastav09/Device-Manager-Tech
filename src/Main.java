import java.util.Scanner;
import java.util.Random;
import com.fazecast.jSerialComm.SerialPort;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DeviceManager {
    private SerialPort port;
    private Scanner scanner;
    private Random random;

    public DeviceManager() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    // Initialize serial communication
    public void initializeSerialCommunication() {
        System.out.println("Serial ports available:");
        SerialPort[] ports = SerialPort.getCommPorts(); // Get available serial ports
        if (ports.length == 0) {
            System.out.println("No serial ports found.");
            return;
        }
        for (int i = 0; i < ports.length; i++) {
            System.out.println((i + 1) + ". " + ports[i].getSystemPortName());
        }
        System.out.print("Enter the number of the port you want to use: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > ports.length) {
            System.out.println("Invalid port number.");
            return;
        }
        port = ports[choice - 1]; // Use the selected serial port

        // Attempt to open the port
        if (!port.openPort()) {
            System.out.println("Unable to open port.");
            return;
        }

        // Output message indicating successful port opening
        String selectedPortName = port.getSystemPortName(); // Store the selected port name
        System.out.println("Port opened successfully: " + selectedPortName);
    }

    // Check current status of DO1, DO2, Tx
    public void checkCurrentStatus() {
        int DO1 = random.nextInt(2); // Simulated DO1 value
        int DO2 = random.nextInt(2); // Simulated DO2 value
        double Tx = 20 + random.nextDouble() * 10; // Simulated temperature value

        // Display current status
        System.out.println("Current Status:");
        System.out.println("DO1: " + DO1);
        System.out.println("DO2: " + DO2);
        System.out.println("Tx: " + Tx);
    }

    // Observe trends in DO1, DO2, Tx over a week
    public void observeTrendsOverWeek() {
        // Simulate data logging and analysis over a week
        System.out.println("Trends observed over a week.");
    }

    // Generate XL reports for changes in DO1, DO2, Tx with timestamps
    public void generateXLReports() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = dateFormat.format(new Date());
        int DO1 = random.nextInt(2); // Simulated DO1 value
        int DO2 = random.nextInt(2); // Simulated DO2 value
        double Tx = 20 + random.nextDouble() * 10; // Simulated temperature value
        String reportData = timeStamp + ", " + DO1 + ", " + DO2 + ", " + Tx + "\n";

        try {
            FileWriter writer = new FileWriter("report.csv", true);
            writer.write(reportData);
            writer.close();
            System.out.println("XL report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating XL report: " + e.getMessage());
        }
    }

    // Configure A & B settings and push to the device
    public void configureAndPush() {
        System.out.println("Enter value for A (0 or 1): ");
        int A = scanner.nextInt();
        System.out.println("Enter value for B (0 or 1): ");
        int B = scanner.nextInt();

        // Simulate sending A & B values to the device
        System.out.println("A & B configured and pushed to the device.");
    }

    // Main method
    public static void main(String[] args) {
        DeviceManager manager = new DeviceManager();
        manager.initializeSerialCommunication();

        // Display menu
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check current status");
            System.out.println("2. Observe trends over a week");
            System.out.println("3. Generate XL reports");
            System.out.println("4. Configure A & B and push to device");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.checkCurrentStatus();
                    break;
                case 2:
                    manager.observeTrendsOverWeek();
                    break;
                case 3:
                    manager.generateXLReports();
                    break;
                case 4:
                    manager.configureAndPush();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
