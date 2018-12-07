package GA;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {
	private static QuestionReader q = null;
	private static List<Question> choiceQuestions = null;
	private static List<Question> blankQuestions = null;
	private static List<Question> shortAnswerQuestions = null;
	private static List<Question> calculateQuestions = null;

	private static Object getObject(String filePath) {
		XMLDecoder d = null;
		Object object = null;
		try {
			d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assert d != null;
		object = d.readObject();
		d.close();
		return object;
	}

	private static List<Question> getObjects(String objs_path) {
		List<Question> objs = new ArrayList<Question>();
		String[] DataFileNames = new File(objs_path).list();
		assert DataFileNames != null;
		for (String dataFileName : DataFileNames) {
			objs.add((Question) getObject(objs_path + "/" + dataFileName));
		}
		return objs;
	}

	public synchronized List<Question> choiceQuestions() {
		if (choiceQuestions == null) {
			choiceQuestions = getObjects("questionFile/Choice");
		}
		return choiceQuestions;
	}

	public synchronized List<Question> blankQuestions() {
		if (blankQuestions == null) {
			blankQuestions = getObjects("questionFile/Blank");
		}
		return blankQuestions;
	}

	public synchronized List<Question> shortAnswerQuestions() {
		if (shortAnswerQuestions == null) {
			shortAnswerQuestions = getObjects("questionFile/ShortAnswer");
		}
		return shortAnswerQuestions;
	}

	public synchronized List<Question> calculateQuestions() {
		if (calculateQuestions == null) {
			calculateQuestions = getObjects("questionFile/Calculate");
		}
		return calculateQuestions;
	}

	synchronized static QuestionReader QFile() {
		if (q == null) {
			q = new QuestionReader();
		}
		return q;
	}
}
