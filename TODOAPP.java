import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton, completeButton;

    public ToDoApp() {
        // Frame setup
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Task list model
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Input field + add button
        JPanel inputPanel = new JPanel(new BorderLayout());
        taskField = new JTextField();
        addButton = new JButton("Add");
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        completeButton = new JButton("Complete");
        deleteButton = new JButton("Delete");
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        completeButton.addActionListener(e -> completeTask());

        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Enter a task!");
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete!");
        }
    }

    private void completeTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            String task = taskListModel.get(index);
            taskListModel.set(index, "✔ " + task);
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to complete!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
