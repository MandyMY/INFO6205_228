package GA;

public class Paper {

	int[] choiceChrom;
	int[] blankChrom;
	int[] calculateChrom;
	int[] shortAnswerChrom;
	int fitness;
	double choice;
	int parent1, parent2;
	private static final int[][] difficult_array = {{ 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 },
			{ 0, 2, 4, 6, 8, 9, 7, 5, 3, 1 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

	public Paper(){ }

	public Paper(int length1, int selected1, int length2, int selected2, int length3, int selected3, int length4, int selected4) {
		choiceChrom = new int[length1];
		blankChrom = new int[length2];
		calculateChrom = new int[length3];
		shortAnswerChrom = new int[length4];
		selected(choiceChrom, selected1);
		selected(blankChrom, selected2);
		selected(calculateChrom, selected3);
		selected(shortAnswerChrom, selected4);
		parent1 = 0;
		parent2 = 0;
	}

	public static int fitness(Paper p, QuestionReader q) {
		int fitness = 0;
		for (int i = 1; i < p.choiceChrom.length; i++) {
			if (p.choiceChrom[i] == 1) {
				fitness += difficult_array[View.difficult][q.choiceQuestions().get(i).getDifficulty()];
			}
		}
		for (int i = 1; i < p.blankChrom.length; i++) {
			if (p.blankChrom[i] == 1) {
				fitness += difficult_array[View.difficult][q.blankQuestions().get(i).getDifficulty()];
			}
		}
		for (int i = 1; i < p.calculateChrom.length; i++) {
			if (p.calculateChrom[i] == 1) {
				fitness += difficult_array[View.difficult][q.calculateQuestions().get(i).getDifficulty()];
			}
		}
		for (int i = 1; i < p.shortAnswerChrom.length; i++) {
			if (p.shortAnswerChrom[i] == 1) {
				fitness += difficult_array[View.difficult][q.shortAnswerQuestions().get(i).getDifficulty()];
			}
		}
		return fitness;
	}


	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("[");
		for (int i : choiceChrom) {
			info.append(i);
		}
		info.append("-");
		for (int i : blankChrom) {
			info.append(i);
		}
		info.append("-");
		for (int i : calculateChrom) {
			info.append(i);
		}
		info.append("-");
		for (int i : shortAnswerChrom) {
			info.append(i);
		}
    	info.append("]  |   ").append(fitness);
		info.append("   |  [").append(parent1).append(", ").append(parent2).append("]");
		return info.toString();
	}

	Paper copy(){
		Paper copy=new Paper();
		copy.choiceChrom=this.choiceChrom.clone();
		copy.blankChrom=this.blankChrom.clone();
		copy.calculateChrom=this.calculateChrom.clone();
		copy.shortAnswerChrom=this.shortAnswerChrom.clone();
		copy.fitness=this.fitness;
		copy.choice=this.choice;
		copy.parent1=this.parent1;
		copy.parent2=this.parent2;
		return copy;
	}

	public void selected(int[] c, int selectedNum) {
		int i = selectedNum;
		while (i!= 0) {
			int select = Generate.randomIntNumber(0, c.length - 1);
			if (c[select] != 1) {
				i--;
				c[select] = 1;
			}
		}
	}
}
