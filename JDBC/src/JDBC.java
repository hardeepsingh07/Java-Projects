import java.sql.*;
import java.util.Scanner;

public class JDBC {

	private static final String username = "system";
	private static final String password = "password";
	private static final String jdbc = "jdbc:oracle:thin:@localhost:1521:CS435";
	public static Statement st = null;
	public static String sql = "Select * From course";
	public static ResultSet rs = null;

	public static void main(String[] args) throws Exception {
		System.out.println(
				"NOTE: Multiple inputs are seperated by commas in this program, with no space before and after comma");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(jdbc, username, password);
		st = con.createStatement();
		Scanner kb = new Scanner(System.in);
		int choice;
		boolean trigger = true;
		while (trigger) {
			System.out.println("***Database Operation Menu***");
			System.out.println("(1): Display schedule");
			System.out.println("(2): Edit Trip offering Table");
			System.out.println("(3): Display stops of given trip");
			System.out.println("(4): Display weekly schedule of a driver");
			System.out.println("(5): Add driver");
			System.out.println("(6): Add bus");
			System.out.println("(7): Delete bus");
			System.out.println("(8): Record data of ActualTripStopInfo");
			System.out.println("(9): Tables");
			System.out.println("(0): Exit");
			System.out.print("Enter your choice: ");
			choice = kb.nextInt();

			switch (choice) {
			case 1:
				displaySchedule();
				break;
			case 2:
				boolean trigger2 = true;
				while (trigger2) {
					System.out.println("(1): Delete a Trip");
					System.out.println("(2): Add a trip");
					System.out.println("(3): Change Driver");
					System.out.println("(4): Change Bus ID");
					System.out.println("(5): Main Menu");
					System.out.print("Enter your choice: ");
					int choice2 = kb.nextInt();
					switch (choice2) {
					case 1:
						deleteTrip();
						break;
					case 2:
						addTrip();
						break;
					case 3:
						changeDriver();
						break;
					case 4:
						changeBusId();
						break;
					case 5:
						trigger2 = false;
						break;
					}
				}
				break;
			case 3:
				displayStops();
				break;
			case 4:
				weeklySchedule();
				break;
			case 5:
				addDriver();
				break;
			case 6:
				addBus();
				break;
			case 7:
				deleteBus();
				break;
			case 8:
				insertRecord();
				break;
			case 9:
				boolean trigger3 = true;
				while (trigger3) {
					System.out.println("(1): Trip");
					System.out.println("(2): TripOffering");
					System.out.println("(3): Driver");
					System.out.println("(4): Bus");
					System.out.println("(5): Stop");
					System.out.println("(6): ActualTripStopInfo");
					System.out.println("(7): TripStopInfo");
					System.out.println("(8): Main Menu");
					System.out.print("Enter your choice: ");
					int choice2 = kb.nextInt();
					switch (choice2) {
					case 1:
						trip();
						break;
					case 2:
						tripoffering();
						break;
					case 3:
						driver();
						break;
					case 4:
						bus();
						break;
					case 5:
						stop();
						break;
					case 6:
						actualtripstopinfo();
						break;
					case 7:
						tripstopinfo();
						break;
					case 8:
						trigger3 = false;
						break;
					}
				}
				break;
			case 0:
				trigger = false;
				System.out.println("Good Bye!");
				con.close();
				System.exit(0);
			}
		}
	}

	public static void displaySchedule() {
		try {
			trip();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter StartLocationName, DestinationName and Date: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "Select trip.tripnumber, trip.startlocationname, trip.destinationname, tripoffering.daate, "
					+ "tripoffering.scheduledstarttime, tripoffering.scheduledarrivaltime, tripoffering.drivername, "
					+ "tripoffering.busid From trip, tripoffering Where trip.tripnumber = tripoffering.tripnumber "
					+ "AND   trip.startlocationname = " + "'" + temp[0] + "'" + "AND   trip.destinationname = " + "'"
					+ temp[1] + "'" + "AND   tripoffering.daate = " + "'" + temp[2] + "'";
			rs = st.executeQuery(query);
			System.out.println("\nResult:");
			System.out.println("  Trip#  |  Start Name  | Dest Name  |  Date  |  "
					+ "Schdeule Start Time |  Schedule Arrival Time  |  Driver Name  |  BUS ID  | ");
			System.out
					.println("-----------------------------------------------------------------------------------------"
							+ "---------------------------------");
			while (rs.next()) {
				System.out.println("    " + rs.getInt(1) + " \t  " + rs.getString(2) + "   " + rs.getString(3) + "   "
						+ rs.getString(4) + " \t\t" + rs.getString(5) + " \t\t\t" + rs.getString(6) + " \t\t     "
						+ rs.getString(7) + "\t     " + rs.getInt(8) + "\t  |");
				System.out.println(
						"-----------------------------------------------------------------------------------------"
								+ "---------------------------------");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void deleteTrip() {
		try {
			tripoffering();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Trip#, Date and ScheduleStartTime: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "DELETE FROM tripoffering WHERE tripnumber = " + temp[0] + "AND daate = '" + temp[1] + "'"
					+ "AND scheduledstarttime = '" + temp[2] + "'";
			st.executeUpdate(query);
			System.out.println("Delete was Successfully");
			System.out.println();
			tripoffering();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void addTrip() {
		try {
			tripoffering();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Trip#, Date, ScheduleStartTime, ScheduledArrivalTime, DriverName, BusID: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "INSERT INTO tripoffering VALUES (" + temp[0] + ",'" + temp[1] + "'" + ",'" + temp[2] + "'"
					+ ",'" + temp[3] + "'" + ",'" + temp[4] + "'," + temp[5] + ")";
			st.executeUpdate(query);
			System.out.println("Insert was Successfully");
			System.out.println();
			tripoffering();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void changeDriver() {
		try {
			driver();
			tripoffering();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Trip#, Date and ScheduleStartTime: ");
			String spec = sc.nextLine();
			System.out.print("Enter the new driver name from driver database: ");
			String name = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "UPDATE tripoffering SET drivername = '" + name + "'" + "WHERE tripnumber = " + temp[0]
					+ "AND daate = '" + temp[1] + "'" + "AND scheduledstarttime = '" + temp[2] + "'";
			st.executeUpdate(query);
			System.out.println("Update was Successfully");
			System.out.println();
			tripoffering();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void changeBusId() {
		try {
			bus();
			tripoffering();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Trip#, Date and ScheduleStartTime: ");
			String spec = sc.nextLine();
			System.out.print("Enter the BusId from Bus database: ");
			int id = sc.nextInt();
			String[] temp = spec.split(",");
			String query = "UPDATE tripoffering SET busid = " + id + "WHERE tripnumber = " + temp[0] + "AND daate = '"
					+ temp[1] + "'" + "AND scheduledstarttime = '" + temp[2] + "'";
			st.executeUpdate(query);
			System.out.println("Update was Successfully");
			System.out.println();
			tripoffering();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void displayStops() {
		try {
			trip();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Trip#: ");
			int spec = sc.nextInt();
			String query = "Select tripstopinfo.tripnumber, tripstopinfo.stopnumber, "
					+ "tripstopinfo.sequencenumber, tripstopinfo.drivingtime "
					+ "From tripstopinfo, trip Where tripstopinfo.tripnumber = trip.tripnumber "
					+ "AND trip.tripnumber = " + spec;
			rs = st.executeQuery(query);
			System.out.println("\nResult:");
			System.out.println("  Trip#  |  Stop #  | Sequence #  |  Drive Time  |");
			System.out.println("---------------------------------------------------");
			while (rs.next()) {
				System.out.println("    " + rs.getInt(1) + "\t\t" + rs.getInt(2) + "\t\t" + rs.getInt(3) + "\t\t"
						+ rs.getString(4) + "\t  |");
				System.out.println("-------------------------------------------------");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void weeklySchedule() {
		try {
			driver();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter DriverName: ");
			String spec = sc.nextLine();
			String query = "Select tripnumber, daate, scheduledstarttime, scheduledarrivaltime, drivername, busid "
					+ "From Tripoffering Where drivername = '" + spec + "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "   "
						+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void addDriver() {
		try {
			driver();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter DriverName and Drive Tele-Number: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "INSERT INTO driver VALUES ('" + temp[0] + "','" + temp[1] + "'" + ")";
			st.executeUpdate(query);
			System.out.println("Insert was Successfully");
			System.out.println();
			driver();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void addBus() {
		try {
			bus();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Bus#, Model, and Year: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "INSERT INTO bus VALUES (" + temp[0] + ",'" + temp[1] + "'," + temp[2] + ")";
			st.executeUpdate(query);
			System.out.println("Insert was Successfully");
			System.out.println();
			bus();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void deleteBus() {
		try {
			bus();
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Bus#, Model, and Year: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "DELETE FROM bus WHERE busid = " + temp[0] + "AND model = '" + temp[1] + "'"
					+ "AND year = " + temp[2];
			st.executeUpdate(query);
			System.out.println("Delete was Successfully");
			System.out.println();
			bus();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void insertRecord() {
		try {
			tripoffering();
			stop();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Trip#, Date, ScheduleStartTime, Stop#, ScheduledArrivalTime, ActualStartTime,"
					+ "ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut: ");
			String spec = sc.nextLine();
			String[] temp = spec.split(",");
			String query = "INSERT INTO actualtripstopinfo VALUES (" + temp[0] + ",'" + temp[1] + "'" + ",'" + temp[2] + "'"
					+ "," + temp[3] + ",'" + temp[4] + "','" + temp[5] + "','" + temp[6] + "'," + temp[7] + "," + temp[8] + ")";
			st.executeUpdate(query);
			System.out.println("Insert was Successfully");
			System.out.println();
			actualtripstopinfo();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured " + e);
		}
	}

	public static void trip() {
		System.out.println("Trip Table:");
		try {
			String query1 = "select * from trip";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void tripoffering() {
		System.out.println("TripOffering Table:");
		try {
			String query1 = "select * from tripoffering";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "   "
						+ rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void driver() {
		System.out.println("Driver Table:");
		try {
			String query1 = "select * from driver";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void bus() {
		System.out.println("Bus Table:");
		try {
			String query1 = "select * from bus";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void stop() {
		System.out.println("Stop Table:");
		try {
			String query1 = "select * from stop";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void actualtripstopinfo() {
		System.out.println("ActualTripStopInfo Table:");
		try {
			String query1 = "select * from actualtripstopinfo";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "   " + rs.getInt(4)
						+ " " + rs.getString(5) + " " + rs.getString(6) + "   " + rs.getString(7) + " " + rs.getInt(8)
						+ " " + rs.getInt(9));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

	public static void tripstopinfo() {
		System.out.println("TripStopInfo Table:");
		try {
			String query1 = "select * from tripstopinfo";
			rs = st.executeQuery(query1);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + "   " + rs.getString(4));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Sorry an error have occured: " + e);
		}
	}

}
