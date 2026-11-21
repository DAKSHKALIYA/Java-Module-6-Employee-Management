
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeManagement extends JFrame {

    JTextField idField, nameField, deptField, salaryField;
    JTable table;

    String url = "jdbc:mysql://localhost:3306/company_db";
    String user = "root";
    String password = "Daksh@3121";

    EmployeeManagement() {

        setTitle("Employee Management System");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Form Panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Department:"));
        deptField = new JTextField();
        panel.add(deptField);

        panel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        JButton submitButton = new JButton("Submit");
        JButton viewButton = new JButton("View All Employees");

        panel.add(submitButton);
        panel.add(viewButton);

        add(panel, BorderLayout.NORTH);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        submitButton.addActionListener(e -> saveEmployee());
        viewButton.addActionListener(e -> viewEmployees());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Database Connection
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Save Employee
    void saveEmployee() {

        String sql = "INSERT INTO employees VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int id = Integer.parseInt(idField.getText());
            double salary = Double.parseDouble(salaryField.getText());

            ps.setInt(1, id);
            ps.setString(2, nameField.getText());
            ps.setString(3, deptField.getText());
            ps.setDouble(4, salary);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Employee Saved Successfully!");

            idField.setText("");
            nameField.setText("");
            deptField.setText("");
            salaryField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Enter valid Employee ID and Salary!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage());
        }
    }

    // View All Employees
    void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Name", "Department", "Salary"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                });
            }

            table.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new EmployeeManagement());
    }
}

