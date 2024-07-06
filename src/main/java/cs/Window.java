package cs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Window extends JFrame implements ActionListener {

    Poem userPoem = new Poem();
    private String inputText;

    public String getInputText() {
        return inputText;
    }

    JFrame frame = new JFrame("Erasure Poetry Generator");
    JLabel inputLabel = new JLabel("Please insert your text below:");
    JTextArea userInputArea = new JTextArea();
    JButton button = new JButton("Generate");
    JLabel outputLabel = new JLabel("Generated Poem:");
    JTextArea generatedPoemOutput = new JTextArea();
    Color frameColor = frame.getBackground ();
    Border lowBevel = BorderFactory.createLoweredBevelBorder();
    JScrollPane scrollPane = new JScrollPane(userInputArea);


    protected Window(){




            frame.setVisible(true);
            frame.setSize(1000, 800);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(null);


            inputLabel.setBounds(150, 50, 350, 40);
            inputLabel.setForeground(Color.BLACK);
            inputLabel.setFont(new Font("Dialog", Font.PLAIN, 24));


            userInputArea.setBounds(50,100,500,500);
            userInputArea.setFont(new Font("Dialog", Font.PLAIN, 18));
            userInputArea.setLineWrap(true);
            userInputArea.setBorder(lowBevel);
            userInputArea.setWrapStyleWord(true);
            scrollPane.setBounds(50,100,500,500);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            button.setBounds(225, 650, 150, 30);
            button.addActionListener(this);
            button.setFont(new Font("Dialog", Font.PLAIN, 18));

            outputLabel.setBounds(575, 100, 200, 40);
            outputLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
            outputLabel.setForeground(Color.BLACK);

            generatedPoemOutput.setBounds(575, 150, 300, 450);
            generatedPoemOutput.setFont(new Font("Dialog", Font.PLAIN, 18));
            generatedPoemOutput.setForeground(Color.BLACK);
            generatedPoemOutput.setBackground(frameColor);
            generatedPoemOutput.setBorder(lowBevel);
            generatedPoemOutput.setLineWrap(true);
            generatedPoemOutput.setWrapStyleWord(true);

            //frame.add(userInputArea);
            frame.add(inputLabel);
            frame.add(button);
            frame.add(outputLabel);
            frame.add(generatedPoemOutput);
            frame.add(scrollPane);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
           inputText = userInputArea.getText();

            userPoem.tagText(getInputText());

            List<String> startingPoem = userPoem.getStartingPoem();
            System.out.println(startingPoem);
            String poem = userPoem.poemOutput(startingPoem);
            System.out.println(poem);

            generatedPoemOutput.setText(poem);

            userPoem.clearLists();
        }

    }
}

