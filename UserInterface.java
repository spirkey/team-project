import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.awt.*;

public class UserInterface extends JFrame implements ActionListener {
	
	private Question question;						// Question object
	private StringBuilder results;					// hold results information
	
	private quiz_Logic2 quizLogic = new quiz_Logic2();			// quiz logic
	private Random r = new Random(System.currentTimeMillis());	// random
	private String contents;						// content of text file
	private String wrongAnswer1;					// incorrect answers
	private String wrongAnswer2;
	private String wrongAnswer3;
	private Stack<Integer> used;					// used words
	private Set words;								// data structures and 'readers'
	private Iterator wordsItr;
	private Collection defs;
	private Iterator defsItr;
	private int correctAns;							// number of correct answers for results view
	
	private Scrape scrape = new Scrape();			// Definition scrape object

	private JPanel contentPane;						// Main Window Panel
	private String path;							// file path
	private File file;								// file object
	private JPanel titlePanel;						// top panel (TOP)
	private JPanel center;							// center panel (CENTER)
	private JPanel bottom;							// bottom panel (BOTTOM)
	private int enteredQuestions;					// User supplied number of questions to use
	private int currentQuestion;					// Question the user is on 
	
	// button panel items 
	private JPanel buttonPanel;						// Button panel
	private JButton btnStartQuiz;					// Start Quiz button
	private JButton btnCreateQuiz;					// Create Quiz button
	private JButton btnModifyQuiz;					// Modify Quiz button
	private JTextField txtQuestions;				// Number of questions
	
	// word panel items
	private JPanel wordPanel;						// Word panel
	private JTextField word;						// Field to hold word
	private JTextArea def;							// Field to hold definition
	private JButton	btnAddWord;						// Add Word button
	private JButton btnSearchWord;					// Search Word button
	private JButton btnDeleteWord;					// Remove Word button
	private JButton btnReturn;						// Return to button panel
	private boolean create = false;					//
	
	// file panel items
	private JPanel filePanel;						// File panel
	private JButton btnFilePath;					// File path button
	private JFileChooser fc = new JFileChooser();	// File chooser object
	private JTextField txtFilePath;					// file path of quiz
	
	// end panel items
	private JPanel endPanel;						// End of quiz panel
	private JButton btnReturnToMenu;				// Return to menu
	private JButton btnExit;						// Exit Application
	
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
					UserInterface frame = new UserInterface();
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
	public UserInterface() {
		
		// Main Menu Window
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 450, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		BorderLayout borders = new BorderLayout(5, 5);
		contentPane.setLayout(borders);
		
		// Title Panel (TOP)
		
		titlePanel = new JPanel();
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
		//lblQuestions.setToolTipText("Must be greater than 4 and less than the number of quiz terms");
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
		
		def = new JTextArea();
		def.setLineWrap(true);
		def.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		def.setBounds(120, 61, 257, 64);
		wordPanel.add(def);
		def.setColumns(18);
		
		btnAddWord = new JButton("Add word");
		btnAddWord.setBounds(12, 136, 101, 31);
		wordPanel.add(btnAddWord);
		btnAddWord.addActionListener(this);
		
		btnSearchWord = new JButton("Search");
		btnSearchWord.setBounds(120, 136, 101, 31);
		wordPanel.add(btnSearchWord);
		btnSearchWord.addActionListener(this);
		
		btnDeleteWord = new JButton("Strike word");
		btnDeleteWord.setBounds(231, 136, 100, 31);
		wordPanel.add(btnDeleteWord);
		btnDeleteWord.addActionListener(this);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(335, 136, 97, 31);
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
		
		// End of quiz panel (BOTTOM)
		
		endPanel = new JPanel();
		bottom.add(endPanel);
		btnReturnToMenu = new JButton("Return to Menu");
		endPanel.add(btnReturnToMenu);
		btnReturnToMenu.addActionListener(this);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		endPanel.add(btnExit);
		
		// Results panel (CENTER)
		
		resultsPanel = new JPanel();
		center.add(resultsPanel);					
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
        lblResults.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblResults.setBounds(184, 0, 74, 25);
        resultsPanel.add(lblResults);				// title label that says "Results"
		ta = new JTextArea();						// make text area
		ta.setLineWrap(true);
		sp = new JScrollPane(ta);					// add text area to scroll pane
		sp.setBounds(10, 25, 422, 153);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultsPanel.setLayout(null);
		resultsPanel.add(sp);						// add scroll pane to results panel
	}
	
	// Action handlers for everything
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartQuiz) {// Start Quiz
			if(enteredQuestions < 4) {
				JOptionPane.showMessageDialog(contentPane, "Not enough questions" ,"Quiz must consist of 4+ questions.", JOptionPane.WARNING_MESSAGE);
			}
			else if(!file.exists()) {
				JOptionPane.showMessageDialog(contentPane, "Quiz not found", "Quiz file not found.",JOptionPane.ERROR_MESSAGE);
			}
			else {
				buttonPanel.setVisible(false);
				wordPanel.setVisible(false);
				quizPanel.setVisible(true);
				filePanel.setVisible(false);
				infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
				infoPanel.setVisible(true);
				
				// load map and present first quiz question
				try {
					InputStream is = new FileInputStream(file);
					contents = convertStreamToString(is);
					quizLogic.readContents(contents);		// fill map with 'word:def''s
				} catch (IOException qsioe) {
					System.out.println("quiz start io error");
				}
				
				words = quizLogic.map.keySet();
				wordsItr = words.iterator();
				defs = quizLogic.map.values();
				defsItr = defs.iterator();
				
				// load first question and definition from file  -- should update to pick a random question, and add that ftn to the next button
				int questionSelect = r.nextInt(words.size()) + 1; 
				String ques = " ";
				String def = " ";
				for(int i = 0; i < questionSelect; i++) {
					ques = wordsItr.next().toString();
					def = defsItr.next().toString();
				}
				
				used = new Stack();
				used.push((Integer) questionSelect);			// stack of generated numbers
			
				// generate 3 random wrong answers
				wrongAnswer1 = quizLogic.randomValue(def);
				wrongAnswer2 = quizLogic.randomValue(def);
				while(wrongAnswer2.equals(wrongAnswer1)) {
					wrongAnswer2 = quizLogic.randomValue(def);
				}
				wrongAnswer3 = quizLogic.randomValue(def);
				while((wrongAnswer3.equals(wrongAnswer1)) || (wrongAnswer3.equals(wrongAnswer2)) )  {
					wrongAnswer3 = quizLogic.randomValue(def);
				}
				
				// randomize a,b,c,d order
				String a = "",b = "",c = "",d = "";
				int randInt = r.nextInt(4);
				if(randInt == 0) {
					a = def;
					b = wrongAnswer2;
					c = wrongAnswer1;
					d = wrongAnswer3;
				}
				else if(randInt == 1) {
					b = def;
					a = wrongAnswer3;
					c = wrongAnswer2;
					d = wrongAnswer1;
				}
				else if(randInt == 2) {
					c = def;
					a = wrongAnswer1;
					b = wrongAnswer3;
					d = wrongAnswer2;
				}
				else if(randInt == 3) {
					d = def;
					a = wrongAnswer2;
					b = wrongAnswer1;
					c = wrongAnswer3;
				}
				question = new Question(ques, a, b, c, d, def);
				
				//*********
				
				correctAns = 0;
				results = new StringBuilder();
				radio1.setText(a);
				radio2.setText(b);
				radio3.setText(c);
				radio4.setText(d);
				lblQuestion.setText(question.getQuestion());
				currentQuestion = 1;
				infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
			}
			
		}
		if(e.getSource() == btnCreateQuiz || e.getSource() == btnModifyQuiz) {
			buttonPanel.setVisible(false);
			wordPanel.setVisible(true);					// word panel 'opened'
			btnFilePath.setEnabled(false);
			if(e.getSource() == btnCreateQuiz) {		// Create button boolean
				create = true;
			}
		}
		if(e.getSource() == btnFilePath) {				// Select File
			String[] ft = {"txt"};						// String array of acceptable file types
			FileNameExtensionFilter filter = new FileNameExtensionFilter("text document", ft);
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();					// sets file
				path = file.getAbsolutePath();
				txtFilePath.setText(path);
				txtQuestions.setText("");
				btnModifyQuiz.setEnabled(true);
				btnCreateQuiz.setEnabled(true);
			}
		}
		if(e.getSource() == btnAddWord) {	// Add word
			boolean alreadyExists = false;
			try {
				if(!word.getText().equals("")) {
					InputStream is = new FileInputStream(file);
					contents = convertStreamToString(is);
				}
				Scanner s = new Scanner(contents);
				while(s.hasNextLine()) {
					String thisLine = s.nextLine();
					if(thisLine.equals("")) {
						break;
					}
					int delim = thisLine.indexOf(':');
					String key = thisLine.substring(0, delim);
					if(word.getText().equals(key)) {
						JOptionPane.showMessageDialog(contentPane, word.getText() + " already exists in the file.","Word duplicate",JOptionPane.WARNING_MESSAGE);
						alreadyExists = true;
					}
				}
				if(alreadyExists == false) {
					if(def.getText().equals("")) {
						//System.out.println("definition scrape request on " + word.getText());
						String web_definition = scrape.webScrape(word.getText());
						if(!web_definition.equals("")) {
							def.setText(web_definition);
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "No definition for " + word.getText() + " was found.", "Definition not found",JOptionPane.WARNING_MESSAGE);
						}
					}
					if(!def.getText().equals("")) {
						// clear file
						PrintWriter writer = new PrintWriter(file);
						writer.print("");
						writer.print(contents);
						writer.println(word.getText() + ":" + def.getText());
						writer.close();
						JOptionPane.showMessageDialog(contentPane, "Definition for " + word.getText() + ": " + def.getText() + ".", "Definition found",JOptionPane.INFORMATION_MESSAGE);
						//System.out.println("Word: " + word.getText() + ", Def: " + def.getText());
					}
				}
				word.setText("");
				def.setText("");
			} catch (IOException qsioe) {
				System.out.println("add word search io error");
			}
		}
		if(e.getSource() == btnReturn) {					// Return to main menu and add write buffer to file
			wordPanel.setVisible(false);
			btnFilePath.setEnabled(true);
			buttonPanel.setVisible(true);
		}
		if(e.getSource() == radio1 || e.getSource() == radio2 || e.getSource() == radio3 || e.getSource() == radio4) {
			btnNext.setEnabled(true);
		}
		if(e.getSource() == btnNext) {						// ******* Quiz Next button
			String s = "";
			if(radio1.isSelected()) {
				s = radio1.getText();
				question.pick(question.getA());
			}
			else if(radio2.isSelected()) {					// user enters answers here
				s = radio2.getText();
				question.pick(question.getB());
			}
			else if(radio3.isSelected()) {
				s = radio3.getText();
				question.pick(question.getC());
			}
			else if(radio4.isSelected()) {
				s = radio4.getText();
				question.pick(question.getD());
			}
			if(question.isRight()) {
				correctAns++;
			}
			results.append(question.printResults());
			results.append("\n********\n");
			rg.clearSelection();							// *******
			btnNext.setEnabled(false);
			
			System.out.println("You chose " + s + " that is " + question.isRight());
			
			// detect end of quiz
			if(currentQuestion == enteredQuestions) {		// handles end of quiz
				results.append("You scored " + correctAns + " out of " + enteredQuestions);
				quizPanel.setVisible(false);				// hides quiz interface (CENTER)
				resultsPanel.setVisible(true);				// presents results text area in scroll panel
				infoPanel.setVisible(false);				// hides info panel (SOUTH) 
				endPanel.setVisible(true);					// presents end panel
				ta.setText(results.toString());
				ta.setCaretPosition(0);						// sets cursor back to beginning of the text area so that user will scroll down
			}
			currentQuestion++;
			
			wordsItr = words.iterator();
			defsItr = defs.iterator();
			
			// load an unusued question and definition from file  -- should update to pick a random question, and add that ftn to the next button
			int questionSelect = r.nextInt(words.size()) + 1;
			while (used.contains((Integer) questionSelect)) {
				questionSelect = r.nextInt(words.size()) + 1;
			}
			used.push((Integer) questionSelect);
			String ques = " ";
			String def = " ";
			for(int i = 0; i < questionSelect; i++) {
				ques = wordsItr.next().toString();
				def = defsItr.next().toString();
			}
			
			// generate 3 random wrong answers
			wrongAnswer1 = quizLogic.randomValue(def);
			wrongAnswer2 = quizLogic.randomValue(def);
			while(wrongAnswer2.equals(wrongAnswer1)) {
				wrongAnswer2 = quizLogic.randomValue(def);
			}
			wrongAnswer3 = quizLogic.randomValue(def);
			while((wrongAnswer3.equals(wrongAnswer1)) || (wrongAnswer3.equals(wrongAnswer2)) )  {
				wrongAnswer3 = quizLogic.randomValue(def);
			}
			
			// randomize a,b,c,d order
			String a = "",b = "",c = "",d = "";
			int randInt = r.nextInt(4);
			if(randInt == 0) {
				a = def;
				b = wrongAnswer2;
				c = wrongAnswer1;
				d = wrongAnswer3;
			}
			else if(randInt == 1) {
				b = def;
				a = wrongAnswer3;
				c = wrongAnswer2;
				d = wrongAnswer1;
			}
			else if(randInt == 2) {
				c = def;
				a = wrongAnswer1;
				b = wrongAnswer3;
				d = wrongAnswer2;
			}
			else if(randInt == 3) {
				d = def;
				a = wrongAnswer2;
				b = wrongAnswer1;
				c = wrongAnswer3;
			}
			question = new Question(ques, a, b, c, d, def);
			
			if(question.isRight()) {
				correctAns++;
			}
			
			radio1.setText(a);
			radio2.setText(b);
			radio3.setText(c);
			radio4.setText(d);
			lblQuestion.setText(question.getQuestion());
			
			infoLabel.setText(path + " :: Question: " + currentQuestion + "/" + enteredQuestions);
		}
		if(e.getSource() == txtQuestions) {				// Quiz questions selection 
			String s = txtQuestions.getText();
			try {
				enteredQuestions = Integer.parseInt(s);
			}
			catch(NumberFormatException nfe) {			// disables btnStartQuiz if it can't parse the text to integer
				JOptionPane.showMessageDialog(contentPane,"Enter a number.","Number format error",JOptionPane.ERROR_MESSAGE);
				txtQuestions.setText("");
				return;
			}
			btnStartQuiz.setEnabled(true);
			return;
		}
		if(e.getSource() == btnDeleteWord) {		// delete word
			boolean existsInFile = false;
			StringBuilder contents2 = new StringBuilder();
			try {
				InputStream is = new FileInputStream(file);
				contents = convertStreamToString(is);
			} catch (IOException qsioe) {
				System.out.println("add word search io error");
			}
			Scanner s = new Scanner(contents);
			while(s.hasNextLine()) {
				String thisLine = s.nextLine();
				//System.out.println("thisLine1: " + thisLine);
				if(thisLine.equals("")) {
					System.out.println("empty line break");
					break;
				}
				int delim = thisLine.indexOf(':');
				String key = thisLine.substring(0, delim);
				if(word.getText().equals(key)) {
					existsInFile = true;
					//System.out.println("word equals key " + thisLine.substring(0, delim) );
				} else {
					//System.out.println("thisLine2: " + thisLine);
					contents2.append(thisLine + "\n");
				}
				
			}
			if(existsInFile == false) {
				JOptionPane.showMessageDialog(contentPane, word.getText() + " was not found!","Word not found",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(existsInFile == true) {
				try {
					PrintWriter writer = new PrintWriter(file);
					writer.print("");
					writer.print(contents2);
					//writer.println(word.getText() + ":" + def.getText());
					writer.close();
				} catch (FileNotFoundException e1) {
					System.out.println("delete overwrite error.");
				}
				
				
				// DELETE WORD FROM FILE
				// *****************
				// getting to here means word exists in the file
				//
				// 1. store first half of text file as it is looped above
				// 2. skip the line with the word in it
				// 3. store the rest of the file and overwrite to save
				//
				// *****************
				JOptionPane.showMessageDialog(contentPane, word.getText() + " deleted.");
				word.setText("");
				def.setText("");
			}
		}
		//
		if(e.getSource() == btnSearchWord) {
			try {
				InputStream is = new FileInputStream(file);
				contents = convertStreamToString(is);
			} catch (IOException qsioe) {
				System.out.println("search io error");
			}
			boolean found = false;
			Scanner s = new Scanner(contents);
			while(s.hasNextLine()) {
				String thisLine = s.nextLine();
				if(thisLine.equals(null)) {
					break;
				}
				int delim = thisLine.indexOf(':');
				String key = thisLine.substring(0, delim);
				String value = thisLine.substring(delim+1, thisLine.length());
				if(word.getText().equals(key)) {
					JOptionPane.showMessageDialog(contentPane, word.getText() + " was found!","Word located",JOptionPane.INFORMATION_MESSAGE);
					def.setText(value);
					found = true;
				}
			}
			if(found == false)
				JOptionPane.showMessageDialog(contentPane, word.getText() + " was not found!", "Word not found",JOptionPane.WARNING_MESSAGE);
		}
		if(e.getSource() == btnExit) {
			int confirm = JOptionPane.showOptionDialog(this,
                    "Are You Sure to Close this Application?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
		}
		if(e.getSource() == btnReturnToMenu) {		// Return to menu
			resultsPanel.setVisible(false);			// hide results panel
			buttonPanel.setVisible(true);			// present button panel (CENTER)
			endPanel.setVisible(false);				// hide end panel
			filePanel.setVisible(true);				// present file panel (BOTTOM)	
		}
	}
	
	static String convertStreamToString(InputStream is) {		// snippet of code for file reading
	    //@SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}