import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIAppOne extends JFrame implements ActionListener {

    private JLabel inputLabel;
    private boolean hasDecimalPoint;
    private boolean isArithmetic;

    public GUIAppOne() {
        setSize(242, 366);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        hasDecimalPoint = false;
        isArithmetic = false;

        JPanel root = new JPanel();
        root.setLayout(null);
        setTitle("Digitron©");
        this.add(root);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Font copyrightfont = new Font(Font.SANS_SERIF, Font.BOLD, 8);

        String[] elements = new String[]{
                "C", "/", "*", "<",
                "7", "8", "9", "-",
                "4", "5", "6", "+",
                "1", "2", "3", "=",
                "%", "0", ".", "+/-"
        };


        int colCount = 4;
        int margin = 60;

        inputLabel = new JLabel("");
        inputLabel.setBounds(10, 10, 220, 30);
        inputLabel.setFont(font);
        inputLabel.setBackground(Color.lightGray);
        inputLabel.setOpaque(true);
        root.add(inputLabel);

        for (int i = 0; i < elements.length; i++) {
            JButton btn = new JButton(elements[i]);
            btn.setBounds(
                    5 + margin * (i % 4), (50 + margin * (i / 4)), 50, 50);
            btn.setFont(font);
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.addActionListener(this);
            root.add(btn);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnText = ((JButton) e.getSource()).getText();
        String lblText = inputLabel.getText();

        StringBuilder sb = new StringBuilder(lblText);

        if (btnText.equals("C")) {
            sb = new StringBuilder();
        } else if (btnText.equals("<")) {
            if (lblText.length() > 0) {
                sb.deleteCharAt(lblText.length() - 1);
            }
        } else if (btnText.equals(".")) {
            if (lblText.isEmpty()) return;
            if (!containsPoint(lblText)) {
                sb.append(btnText);
            }
        }else if (btnText.equals("=")) {
            if (lblText.length() > 0) {
                int i = containsOperator(lblText);
                if (i != -1) {
                    if (!isLastCharOperator(lblText)) {
                        sb = new StringBuilder();
                        double a = Double.valueOf(lblText.substring(0, i));
                        double b = Double.valueOf(lblText.substring(i + 1));
                        char c = lblText.charAt(i);

                        switch (c) {
                            case '+': sb.append(a + b); break;
                            case '-': sb.append(a - b); break;
                            case '*': sb.append(a * b); break;
                            case '/': sb.append(a / b); break;
                        }
                    }
                }
            }
        } else {
            if (isOperator(btnText)) {
                if (lblText.isEmpty()) return;
                if (isLastCharOperator(lblText)) {
                    sb.deleteCharAt(lblText.length() - 1);
                }
            }
            sb.append(btnText);
        }



        inputLabel.setText(sb.toString());
    }

    private boolean isLastCharOperator(String s) {
        int length = s.length();
        return length > 0 && isOperator(s.substring(length - 1));
    }

    private int containsOperator (String input) {
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(String.valueOf(input.charAt(i)))) {
                return i;
            }
        }
        return -1;
    }

    private boolean isOperator(String s) {
        return s.length() == 1 && "+-/*".contains(s);
   }

   private boolean containsPoint(String input) {
        for (int i = input.length()-1; i >= 0; i--) {
            if (input.charAt(i) == '.') {
                return true;
            } else if (isOperator(String.valueOf(input.charAt(i)))) {
                return false;
            }
        }
        return false;
   }
}
