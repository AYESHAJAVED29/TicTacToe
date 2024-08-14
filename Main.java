import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame frame;
    static JButton[][] buttons = new JButton[3][3];
    static boolean isPlayerOne = true;
    static boolean game_Active = true;

    public static void main(String[] args) {
        frame = new JFrame("Tic Tac Toe");
        frame.setBounds(500, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 220));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBackground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.BOLD, 60);
        Color buttonColor = new Color(240, 240, 240);
        Color textColor = Color.BLACK;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(buttonFont);
                buttons[row][col].setForeground(textColor);
                buttons[row][col].setBackground(buttonColor);
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setOpaque(true);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                panel.add(buttons[row][col]);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        int row, col;

        ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game_Active && buttons[row][col].getText().equals("")) {
                if (isPlayerOne) {
                    buttons[row][col].setText("X");
                } else {
                    buttons[row][col].setText("O");
                }

                if (checkWinner()) {
                    JOptionPane.showMessageDialog(frame, (isPlayerOne ? "Player 1" : "Player 2") + " Wins!");
                    game_Active = false;
                } else if (isTie()) {
                    JOptionPane.showMessageDialog(frame, "It's a Tie!");
                    game_Active = false;
                }

                isPlayerOne = !isPlayerOne;
            }
        }

        private boolean checkWinner() {
            // Check rows
            for (int i = 0; i < 3; i++) {
                if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                        buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                        !buttons[i][0].getText().equals("")) {
                    return true;
                }
            }

            // Check columns
            for (int i = 0; i < 3; i++) {
                if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                        buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                        !buttons[0][i].getText().equals("")) {
                    return true;
                }
            }

            // Check diagonals
            if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                    !buttons[0][0].getText().equals("")) {
                return true;
            }

            if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                    !buttons[0][2].getText().equals("")) {
                return true;
            }

            return false;
        }

        private boolean isTie() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().equals("")) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
