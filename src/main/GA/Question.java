package GA;

public class Question {

	private String type;
	private int score;
	private String context;
	private String answer;
	private int difficulty;
	private int identify;

	public Question() {
		this.type = "";
		this.context = "";
		this.difficulty = 0;
		this.identify = 0;
	}

	public Question(String type, String context, String answer, int difficulty, int identify, int score) {
		this.type = type;
		this.context = context;
		this.difficulty = difficulty;
		this.identify = identify;
		this.score = score;
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return  "  (Score£º" + this.score + ", Difficulty Level£º"
				+ this.difficulty + " )\n "+this.context ;
	}
}
