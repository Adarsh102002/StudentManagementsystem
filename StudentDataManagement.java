package ProjectStudentDataManagement;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class StudentDataManagement extends JFrame {

    private JPanel contentPane;
    private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12;
    private JTable table;
    Connection conn;
    JRadioButton r4, r5;
    JComboBox<String> comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentDataManagement frame = new StudentDataManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentDataManagement() {
        conn = DataBaseConnection.dbConn();
        setFont(new Font("Dialog", Font.BOLD, 18));
        setBackground(SystemColor.activeCaption);
        setTitle("Student Data Management");
        setIconImage(Toolkit.getDefaultToolkit().getImage("path_to_your_image"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1273, 680);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 433, 1239, 200);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(240, 255, 255));
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "First Name", "Surname", "Age", "Date of Birth", "Gender", "Contact No", "Year of Join", "ID", "Department", "Class", "Father's Name", "Mother's Name" }));
        scrollPane.setViewportView(table);

        // Add GUI components initialization here
        // Labels, TextFields, Buttons, and their respective event listeners

        // Display Student Data in JTable
        DisplayTable();
    }

    private void DisplayTable() {
        try {
            String query = "SELECT * FROM studentdatamgmt";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add action listeners for CRUD operations (Add, Update, Delete, etc.)
}
