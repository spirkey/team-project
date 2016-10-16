import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.*;

public class MainMenu extends JFrame implements ActionListener {

	private JPanel contentPane;						// Main Window
	private String path;							// file path
	private File file;								// file object
	private JPanel center;							// center panel (CENTER)
	private JPanel bottom;							// bottom panel (BOTTOM)
	private int enteredQuestions;					// -User supplied- number of questions to use
	private int numQuestions;						// The length of the list or number of actual questions
	private int currentQuestion = 1;				// Question the user is on 
	
	// button panel items 
	private JPanel buttonPanel;						// Button panel
	private JButton btnStartQuiz;					// Start Quiz button
	private JButton btnCreateQuiz;					// Create Quiz button
	private JButton btnModifyQuiz;					// Modify Quiz button
	private JTextField txtQuestions;				// Number of questions
	
	// word panel items
	private JPanel wordPanel;						// Word panel
	private JTextField word;						// Field to hold word
	private JTextField def;							// Field to hold definition
	private JButton	btnAddWord;						// Add Word button
	private JButton btnDeleteWord;					// Remove word button
	private JButton btnReturn;						// Return to button panel
	private BufferedWriter bw;						// 
	private StringBuffer sb = new StringBuffer();   // 
	private StringBuffer prevBuffer = new StringBuffer();// Read contents of a file for Modify
	private boolean create = false;					//
	
	// file panel items
	private JPanel filePanel;						// File panel
	private JButton btnFilePath;					// File path button
	private JFileChooser fc = new JFileChooser();	// File chooser object
	private JTextField txtFilePath;					// file path of quiz
	
	// quiz panel items --
	private JPanel quizPanel;						// quiz interface panel
	private JLabel lblQuestion;
	private JRadioButton radio1;					
	private JRadioButton radio2;
	private JRadioButton radio4;
	private JRadioButton radio3;
	private ButtonGroup rg;							// radio button group
	private JButton btnNext;						// next button
	
	// results panel items --
	private JPanel resultsPanel;						// results panel
	private JLabel lblResults = new JLabel("Results");
	private JScrollPane sp;
	private JTextArea ta;
	
	// info panel items --
	private JPanel infoPanel;						// info panel
	private JLabel infoLabel;						// information space
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		
		// Main Menu Window
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 450, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		BorderLayout borders = new BorderLayout(5, 5);
		contentPane.setLayout(borders);
		
		// Title Panel (TOP)
		
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Vocabulary Quiz");
		titleLabel.setFont(new Font("Ravie", Font.PLAIN, 22));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(titleLabel);
		contentPane.add(titlePanel,BorderLayout.NORTH);
		
		// Center panel (CENTER)
		
		center = new JPanel();
		center.setLayout(new CardLayout());
		contentPane.add(center, BorderLayout.CENTER);
		
		// Button Panel (CENTER)
		
		buttonPanel = new JPanel();
		center.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.setEnabled(false);
		btnStartQuiz.setBounds(159, 27, 117, 29);
		buttonPanel.add(btnStartQuiz);
		btnStartQuiz.addActionListener(this);
		
		btnCreateQuiz = new JButton("Create Quiz");
		btnCreateQuiz.setEnabled(false);
		btnCreateQuiz.setBounds(159, 67, 117, 29);
		buttonPanel.add(btnCreateQuiz);
		btnCreateQuiz.addActionListener(this);
		
		btnModifyQuiz = new JButton("Modify Quiz");
		btnModifyQuiz.setEnabled(false);
		btnModifyQuiz.setBounds(159, 107, 117, 29);
		buttonPanel.add(btnModifyQuiz);
		btnModifyQuiz.addActionListener(this);
		
		JLabel lblQuestions = new JLabel("Number of Questions:");
		lblQuestions.setToolTipText("Must be greater than 4 and less than the number of quiz terms");
        lblQuestions.setBounds(125, 158, 133, 22);
        buttonPanel.add(lblQuestions);
		
		txtQuestions = new JTextField("");
		txtQuestions.setBounds(260, 154, 30, 30);
		buttonPanel.add(txtQuestions);
		txtQuestions.addActionListener(this);
		
		// Quiz words create / modify (CENTER)
	
		wordPanel = new JPanel();
		wordPanel.setVisible(false);
		wordPanel.setBounds(0, 0, 446, 195);
		center.add(wordPanel);
		wordPanel.setLayout(null);
		
		JLabel lblword = new JLabel("Word: ");
		lblword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblword.setBounds(58, 33, 52, 14);
		wordPanel.add(lblword);

		word = new JTextField();
		word.setBounds(120, 30, 150, 20);
		wordPanel.add(word);
		word.setColumns(18);
		
		JLabel lblDefinition = new JLabel("Definition: ");
		lblDefinition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDefinition.setBounds(46, 58, 70, 14);
		wordPanel.add(lblDefinition);
		
		def = new JTextField();
		def.setBounds(120, 61, 257, 64);
		wordPanel.add(def);
		def.setColumns(18);
		
		btnAddWord = new JButton("Add word");
		btnAddWord.setBounds(70, 136, 101, 31);
		wordPanel.add(btnAddWord);
		btnAddWord.addActionListener(this);
		

		btnDeleteWord = new JButton("Strike word");
		btnDeleteWord.setBounds(181, 136, 101, 31);
		wordPanel.add(btnDeleteWord);
		btnDeleteWord.addActionListener(this);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(292, 136, 97, 31);
		wordPanel.add(btnReturn);
		btnReturn.addActionListener(this);
		
		// Bottom panel
		
		bottom = new JPanel();
		bottom.setLayout(new CardLayout());
		contentPane.add(bottom, BorderLayout.SOUTH);
		
		// File selection menu (BOTTOM)
		
		filePanel = new JPanel();
		bottom.add(filePanel);
		
			// File selection button
		
		btnFilePath = new JButton("File path");
		btnFilePath.setToolTipText("Select a .txt file");
		filePanel.add(btnFilePath);
		btnFilePath.addActionListener(this);
		
			// File path display
		
		txtFilePath = new JTextField();
		txtFilePath.setColumns(25);
		filePanel.add(txtFilePath);
		
		// Quiz panel (CENTER)
		
		quizPanel = new JPanel();
		quizPanel.setVisible(false);
		center.add(quizPanel);
		quizPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		lblQuestion = new JLabel("Question");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		quizPanel.add(lblQuestion);
		
		radio1 = new JRadioButton("radio1");
		quizPanel.add(radio1);
		radio1.addActionListener(this);
		
		radio2 = new JRadioButton("radio2");
		quizPanel.add(radio2);
		radio2.addActionListener(this);
		
		radio3 = new JRadioButton("radio3");
		quizPanel.add(radio3);
		radio3.addActionListener(this);
		
		radio4 = new JRadioButton("radio4");
		quizPanel.add(radio4);
		radio4.addActionListener(this);
		
		rg = new ButtonGroup();					// radio button group
		rg.add(radio1);
		rg.add(radio2);
		rg.add(radio3);
		rg.add(radio4);
		
		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		quizPanel.add(btnNext);
		btnNext.addActionListener(this);
		
		// Info panel (SOUTH)
		
		infoPanel = new JPanel();
		infoPanel.setVisible(false);
		bottom.add(infoPanel);
		
		infoLabel = new JLabel(":: Question: X/Y");
		infoPanel.add(infoLabel);
		
		// Results panel (CENTER)
		
		resultsPanel = new JPanel();
		center.add(resultsPanel);					
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
        lblResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblResults.setBounds(184, 0, 74, 25);
        resultsPanel.add(lblResults);				// adds label that says "results"
		ta = new JTextArea();						// make text area
		ta.setLineWrap(true);
		sp = new JScrollPane(ta);					// add text area to scroll pane
		sp.setBounds(10, 25, 422, 153);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultsPanel.setLayout(null);
		resultsPanel.add(sp);						// add scroll pane to results panel
		
		//************		This section will set the quiz results output
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			s.append("filler text");
			s.append(System.getProperty("line.separator"));
		}
		s.append("there is no horizontal scroll bar because this text area is set to wrap long lines of text.");
		ta.setText(s.toString());
        //*************
		
		ta.setCaretPosition(0);						// sets cursor back to beginning of the text area so that user will scroll down
	}
	
	// Action handlers for everything
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartQuiz) {					// Start Quiz
			buttonPanel.setVisible(false);
			wordPanel.setVisible(false);
			quizPanel.setVisible(true);
			filePanel.setVisible(false);
			infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
			infoPanel.setVisible(true);

			//**********
			// pull first quiz question here
			//*********
			
			infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
		}
		if(e.getSource() == btnCreateQuiz || e.getSource() == btnModifyQuiz) {
			buttonPanel.setVisible(false);
			wordPanel.setVisible(true);					// word panel 'opened'
			btnFilePath.setEnabled(false);
			if(e.getSource() == btnCreateQuiz) {		// Create button boolean
				create = true;
			}
			sb = new StringBuffer();					// instantiate StringBuffer to hold input(s)
		}
		if(e.getSource() == btnFilePath) {				// Select File
			String[] ft = {"txt"};						// String array of acceptable file types
			FileNameExtensionFilter filter = new FileNameExtensionFilter("text document", ft);
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				path = file.getAbsolutePath();
				txtFilePath.setText(path);
				txtQuestions.setText("");
				btnModifyQuiz.setEnabled(true);
				btnCreateQuiz.setEnabled(true);
			}
		}
		if(e.getSource() == btnAddWord) {					// Add word
			System.out.println("Add word " + word.getText() + ", definition " + def.getText());
			if(def.getText().equals("")) {
				System.out.println("definition scrape request");
				//**********
				// scrape definition here
				//*********
				def.setText("def_scrape");
			}
			sb.append(word.getText() + ":" + def.getText() + System.getProperty("line.separator"));
			
			//**********
			// add word here
			//*********
		}
		if(e.getSource() == btnReturn) {					// Return to main menu and add write buffer to file
			try {
				if(create == false) {
					InputStream is = new FileInputStream(file);
					String previous = convertStreamToString(is);
					System.out.println("Append previous contents:\n" + previous.toString() + " to bufferwriter");
					prevBuffer = new StringBuffer(previous.toString());
				}
				else {
					if(file.exists()) {
						System.out.println("Delete");
						file.delete();
					}
					try {
						System.out.println("Create");
						file.createNewFile();
					} catch (IOException ioe) {
						System.out.println("File creation error");
					}
				}
				bw = new BufferedWriter(new FileWriter(file));
				System.out.println("Append new contents:\n" + sb.toString() + " to bufferwriter");
				if(create == false) {
					bw.append(prevBuffer); 	// append prev contents if modifying file
				}
				bw.append(sb.toString());
				System.out.println("bufferWriter written to file");
				bw.flush();
				bw.close();
			} catch (IOException bwioe) {
				System.out.println("bufferedwriter io error");
			}
			wordPanel.setVisible(false);
			btnFilePath.setEnabled(true);
			buttonPanel.setVisible(true);
		}
		if(e.getSource() == radio1 || e.getSource() == radio2 || e.getSource() == radio3 || e.getSource() == radio4) {
			btnNext.setEnabled(true);
		}
		if(e.getSource() == btnNext) {						// Quiz Next button
			String s = "";
			if(radio1.isSelected()) {
				s = radio1.getText();
			}
			else if(radio2.isSelected()) {
				s = radio2.getText();
			}
			else if(radio3.isSelected()) {
				s = radio3.getText();
			}
			else if(radio4.isSelected()) {
				s = radio4.getText();
			}
			rg.clearSelection();
			btnNext.setEnabled(false);
			System.out.println("You chose " + s);
			if(currentQuestion == enteredQuestions) {		// handles end of quiz
				quizPanel.setVisible(false);
				resultsPanel.setVisible(true);
			}
			currentQuestion++;
			
			//**********
			// pull next quiz question here
			//*********
			
			infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
		}
		if(e.getSource() == txtQuestions) {				// Quiz questions selection 
			String s = txtQuestions.getText();
			try {
				enteredQuestions = Integer.valueOf(s);
			}
			catch(NumberFormatException nfe) {			// disables btnStartQuiz if it can't parse the text to integer
				btnStartQuiz.setEnabled(false);
				System.out.println("Enter a number");
				return;
			}
			try {
				if(file.exists() && enteredQuestions > 4 )
					btnStartQuiz.setEnabled(true);
				/*
				if(enteredQuestions > numQuestions) {		// disable btnStartQuiz if enteredQuestions > numQuestions
					btnStartQuiz.setEnabled(false);
					return;
				}
				*/
			}
			catch(NullPointerException npe) {
				return;
			}
			return;
		}
		if(e.getSource() == btnDeleteWord) {		// delete word 
			//**********
			// delete a word here
			//*********
			System.out.println("delete " + word.getText());
		}
	}
	
	static String convertStreamToString(InputStream is) {		// snippet of code for file reading
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}