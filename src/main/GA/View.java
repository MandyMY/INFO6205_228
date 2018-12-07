package GA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View {
	static int difficult = 0;
	private static int maxGen = 100;
	private static int poSize = 50;
	private static ArrayList<Paper> po;
	private static Generate g = new Generate();
	private static QuestionReader q = QuestionReader.QFile();
	private static StringBuffer resultContent;

	public static void main(String[] args) {
		final TestFrame show = new TestFrame("Paper Builder");
		show.maxGen_f.setText(Integer.toString(maxGen));
		show.poSize_f.setText(Integer.toString(poSize));
		show.show();

		show.start.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				resultContent = new StringBuffer();
				if (!show.maxGen_f.getText().equals(""))
					maxGen = Integer.parseInt(show.maxGen_f.getText());
				if (!show.poSize_f.getText().equals(""))
					poSize = Integer.parseInt(show.poSize_f.getText());
				po = g.initial(poSize, q);

				resultContent.append("\t\tGenotype\t\t\t| Fitness | Parents(number in the last generation)\n");
				resultContent.append(po.get(g.findBestIndividual(po))).append(">>>>>>>>The best individual in the initial group\n\n\n");
				for (int i = 0; i < maxGen; i++) {
					po = g.update(po, q);
					resultContent.append(po.get(g.findBestIndividual(po))).append(">>>>>>>>The best individual in the ")
							.append(i + 1).append("th group\n");
				}
				resultContent.append("\n\n==============P a p e r================P a p e r================P a p e r" +
						"================P a p e r==============\n\n");

				int j = 1;
				Paper best = po.get(g.findBestIndividual(po));
				int[] c = best.choiceChrom;
				resultContent
						.append("[Multiple-Choice]\n\n");
				for (int i = 0; i < c.length; i++)
					if (c[i] == 1) {
						resultContent.append("Question").append(j++);
						resultContent.append(q.choiceQuestions().get(i)).append("\n\n");
					}
				;

				c = best.blankChrom;
				j = 1;
				resultContent
						.append("[Fill In the Blanks]\n\n");
				for (int i = 0; i < c.length; i++)
					if (c[i] == 1) {
						resultContent.append("Question").append(j++);
						resultContent.append(q.blankQuestions().get(i)).append("\n\n");
					}
				;

				c = best.calculateChrom;
				j = 1;
				resultContent
						.append("[Calculation]\n\n");
				for (int i = 0; i < c.length; i++)
					if (c[i] == 1) {
						resultContent.append("Question").append(j++);
						resultContent.append(q.calculateQuestions().get(i)).append("\n\n");
					}

				c = best.shortAnswerChrom;
				j = 1;
				resultContent
						.append("[Short Answer]\n\n");
				for (int i = 0; i < c.length; i++)
					if (c[i] == 1) {
						resultContent.append("Question").append(j++);
						resultContent.append(q.shortAnswerQuestions().get(i)).append("\n\n");
					}
				;

				resultContent.append("\n\n\n\n\n\n");
				show.result.setText(resultContent.toString());
			}
		});

	}
}

class TestFrame extends JFrame {
	TextField maxGen_f;
	TextField poSize_f;
	JButton start;
	TextArea result;
	private JComboBox<String> choice_ComboBox = null;
	private JComboBox<String> blank_ComboBox = null;
	private JComboBox<String> shortAnswer_ComboBox = null;
	private JComboBox<String> calculate_ComboBox = null;
	private JComboBox<String> difficult_ComboBox = null;

	TestFrame(String name) {
		super(name);
		setLocation(140, 41);
		setSize(1000, 700);

		String[] obj = { "1","2","3","4","5","6","7","8","9","10"};
		choice_ComboBox = new JComboBox<>(obj);
		choice_ComboBox.setSize(25, 25);
		choice_ComboBox.setMaximumRowCount(11);
		blank_ComboBox  = new JComboBox<>(obj);
		blank_ComboBox.setSize(25, 25);
		blank_ComboBox.setMaximumRowCount(11);
		shortAnswer_ComboBox  = new JComboBox<>(obj);
		shortAnswer_ComboBox.setSize(25, 25);
		shortAnswer_ComboBox.setMaximumRowCount(11);
		calculate_ComboBox  = new JComboBox<>(obj);
		calculate_ComboBox.setSize(25, 25);
		calculate_ComboBox.setMaximumRowCount(11);


		choice_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Generate.choiceNum = choice_ComboBox.getSelectedIndex() + 1;
			}
		});
		blank_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Generate.blankNum = blank_ComboBox.getSelectedIndex() + 1;
			}
		});
		shortAnswer_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Generate.shortAnswerNum = shortAnswer_ComboBox.getSelectedIndex() + 1;
			}
		});
		calculate_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Generate.calculateNum = calculate_ComboBox.getSelectedIndex() + 1;
			}
		});

		String[] obj1 = { "easy", "normal", "difficult" };
		difficult_ComboBox = new JComboBox<>(obj1);
		difficult_ComboBox.setSize(60, 25);
		difficult_ComboBox.setMaximumRowCount(3);

		difficult_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				View.difficult = difficult_ComboBox.getSelectedIndex();
			}
		});

		maxGen_f = new TextField();
		maxGen_f.setPreferredSize(new Dimension(50, 25));
		poSize_f = new TextField();
		poSize_f.setPreferredSize(new Dimension(50, 25));
		start = new JButton();
		start.setLabel("Start");
		start.setPreferredSize(new Dimension(80, 25));
		result = new TextArea();

		JPanel testcontent = new JPanel();
		testcontent.setPreferredSize(new Dimension(900, 540));
		testcontent.setLayout(new BorderLayout());
		testcontent.add(result);
		JPanel control = new JPanel();
		control.setPreferredSize(new Dimension(900, 60));

		control.setLayout(new FlowLayout());
		control.add(new JLabel("Paper: "));
		control.add(new JLabel("Multiple-Choice"));
		control.add(choice_ComboBox);
		control.add(new JLabel("Fill In the Blanks"));
		control.add(blank_ComboBox);
		control.add(new JLabel("Calculation"));
		control.add(calculate_ComboBox);
		control.add(new JLabel("Short Answer"));
		control.add(shortAnswer_ComboBox);
		control.add(new JLabel("Difficulty"));
		control.add(difficult_ComboBox);
		control.add(new JLabel("               "));
		control.add(new JLabel("GA Parameters: "));
		control.add(new JLabel("Generations"));
		control.add(maxGen_f);
		control.add(new JLabel("Population"));
		control.add(poSize_f);
		control.add(start);

		getContentPane().add(control);
		getContentPane().add(new JScrollPane(testcontent), BorderLayout.SOUTH);

	}
}
