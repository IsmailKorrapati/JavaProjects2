import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton, submitButton;

    int currentQuestion = 0, score = 0;

    String[][] questions = {
            {"Is Java is Purely Object-Oriented Programming language", "YES", "Not purely But Object-Oriented", "Some times", "Can't defined", "2"},
            {"Java is which type of programming Language", "Compiled programming Language", "Interpreted programming Language", "both", "None", "3"},
            {"Select the True Statement..", "Java is a Purely  Object-Oriented Programming Language", "Java is not Purely Object-oriented because of primitive data types..", "Java is not  Purely Object-oriented because of inheritance", "Java is Procedural Programming Language", "2"},
            {"Which of the following is a resizable array implementation in Java?", "Array", "ArrrayList", "Vector", "LinkedList", "2"},
            {"Which interface does HashMap implements?", "Graph", "Map", "Set", "Collections", "2"},
            {"Which of these allows duplicate elements?", "HashSet", "TreeSet", "ArrayList", "LinkedHashSet", "3"},
            {"What is the Time Complexity of searching in HashMap?", "O(n)", "O(log n)", "O(1)", "O(n log n)", "3"},
            {"Which class in Java is thread-safe and synchronized?", "ArrayList", "LinkedList", "Vector", "HashSet", "3"},
            {"What is the default capacity of ArrayList in Java?", "5", "10", "0", "20", "2"},
            {"What will peek() method return in Queue if it is empty?", "Throws Exception", "null", "-1", "0", "2"}
    };

    public QuizApp() {
        setTitle("Quiz Application");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        UIManager.put("RadioButton.icon", new Icon() {
            private final int size = 22;

            public int getIconWidth() {
                return size;
            }

            public int getIconHeight() {
                return size;
            }

            public void paintIcon(Component c, Graphics g, int x, int y) {
                AbstractButton button = (AbstractButton) c;
                ButtonModel model = button.getModel();
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(180, 180, 180));
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(x, y, size - 1, size - 1);

                if (model.isSelected()) {
                    g2.setColor(new Color(33, 150, 243)); // Modern blue
                    g2.fillOval(x + 6, y + 6, size - 12, size - 12);
                }
            }
        });

        questionLabel = new JLabel();
        questionLabel.setBounds(100, 100, 1600, 50);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(questionLabel);

        group = new ButtonGroup();
        int y = 200;

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(120, y, 1000, 50);
            options[i].setFont(new Font("Arial", Font.PLAIN, 22));
            options[i].setBackground(Color.WHITE);
            group.add(options[i]);
            add(options[i]);
            y += 70;
        }


        nextButton = new JButton("Next");
        nextButton.setBounds(120, y + 40, 150, 40);
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextButton.setBackground(new Color(220, 220, 220));
        nextButton.addActionListener(this);
        add(nextButton);


        submitButton = new JButton("Submit");
        submitButton.setBounds(300, y + 40, 150, 40);
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setBackground(new Color(220, 220, 220));
        submitButton.addActionListener(this);
        submitButton.setVisible(false);
        add(submitButton);

        loadQuestion(currentQuestion);
        setVisible(true);
    }

    void loadQuestion(int index) {
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index][i + 1]);
        }
        group.clearSelection();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (isAnswerCorrect()) score++;
            currentQuestion++;
            if (currentQuestion == questions.length - 1) {
                nextButton.setVisible(false);
                submitButton.setVisible(true);
            }
            loadQuestion(currentQuestion);
        } else if (e.getSource() == submitButton) {
            if (isAnswerCorrect()) score++;
            JOptionPane.showMessageDialog(this,
                    "Quiz Completed!\nYour Score: " + score + "/" + questions.length,
                    "Result", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    boolean isAnswerCorrect() {
        int correctOption = Integer.parseInt(questions[currentQuestion][5]);
        return options[correctOption - 1].isSelected();
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
