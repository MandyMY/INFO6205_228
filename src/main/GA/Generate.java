package GA;
import java.util.ArrayList;
import java.util.Random;

public class Generate {
	static int choiceNum = 1;
	static int blankNum = 1;
	static int calculateNum = 1;
	static int shortAnswerNum = 1;

	public void computeSelectChoice(ArrayList<Paper> po) {
		double fitness_all = 0;
		double accumulation = 0;
		for (Paper paper : po) {
			fitness_all += paper.fitness;
		}
		for (Paper paper : po) {
			accumulation += paper.fitness;
			paper.choice = accumulation / fitness_all;
		}
	}

	public int selectIndividualUseChoice(ArrayList<Paper> po) {
		int position = 0;
		double probability = new Random().nextDouble();
		for (int i = 0; i < po.size(); i++) {
			if (probability <= po.get(i).choice) {
				position = i;
				break;
			}
		}
		return position;
	}

	public static int randomIntNumber(int from, int to) {
		float a = from + (to - from) * (new Random().nextFloat());
		int b = (int) a;
		return ((a - b) > 0.5 ? 1 : 0) + b;
	}

	public ArrayList<Paper> initial(int poSize, QuestionReader q) {
		ArrayList<Paper> init_po = new ArrayList<Paper>();
		Paper p;
		for (int i = 0; i < poSize; i++) {
			p = new Paper(q.choiceQuestions().size(), choiceNum,
					q.blankQuestions().size(), blankNum,
					q.calculateQuestions().size(), calculateNum,
					q.shortAnswerQuestions().size(), shortAnswerNum);
			p.fitness = Paper.fitness(p, q);
			init_po.add(p);
		}
		computeSelectChoice(init_po);
		return init_po;
	}

	public int findBestIndividual(ArrayList<Paper> po) {
		int fitness = po.get(0).fitness;
		int position = 0;
		for (int i = 1; i < po.size(); i++) {
			if (po.get(i).fitness > fitness) {
				fitness = po.get(i).fitness;
				position = i;
			}
		}
		return position;
	}

	public void crossover(ArrayList<Paper> new_po, ArrayList<Paper> old_po, int ind1, int ind2) {
		Paper new_ind1 = old_po.get(ind1).copy();
		Paper new_ind2 = old_po.get(ind2).copy();
		int[] temp;
		switch (randomIntNumber(1, 4)) {
		case 1:
			temp = new_ind1.choiceChrom;
			new_ind1.choiceChrom = new_ind2.choiceChrom;
			new_ind2.choiceChrom = temp;
			break;
		case 2:
			temp = new_ind1.blankChrom;
			new_ind1.blankChrom = new_ind2.blankChrom;
			new_ind2.blankChrom = temp;
			break;
		case 3:
			temp = new_ind1.calculateChrom;
			new_ind1.calculateChrom = new_ind2.calculateChrom;
			new_ind2.calculateChrom = temp;
			break;
		default:
			temp = new_ind1.shortAnswerChrom;
			new_ind1.shortAnswerChrom = new_ind2.shortAnswerChrom;
			new_ind2.shortAnswerChrom = temp;
			break;
		}
		new_ind1.parent1 = ind1;
		new_ind1.parent2 = ind2;
		new_ind2.parent1 = ind1;
		new_ind2.parent2 = ind2;
		new_po.add(new_ind1);
		new_po.add(new_ind2);
	}

	public void Mutation(Paper p, double Mutation_degree) {
		int Mutation_sction = randomIntNumber(1, 4);
		if (new Random().nextDouble() < Mutation_degree) {
			int selectednum = 0;
			int[] temp_Mutation_section;
			switch (Mutation_sction) {
				case 1:
					temp_Mutation_section=p.choiceChrom;
					break;
				case 2:
					temp_Mutation_section=p.blankChrom;
					break;
				case 3:
					temp_Mutation_section=p.calculateChrom;
					break;
				default:
					temp_Mutation_section=p.shortAnswerChrom;
					break;
			}
			for (int i = 0; i < temp_Mutation_section.length; i++) {
				if(temp_Mutation_section[i]==1)selectednum++;
				temp_Mutation_section[i]=0;
			}
			while (selectednum != 0) {
				int select = randomIntNumber(0, temp_Mutation_section.length - 1);
				if (temp_Mutation_section[select] != 1) {
					selectednum--;
					temp_Mutation_section[select] = 1;
				}
			}
		}
	}

	public ArrayList<Paper> update(ArrayList<Paper> old_po, QuestionReader q) {
		ArrayList<Paper> new_po = new ArrayList<Paper>();
		int p1, p2;
		while (new_po.size() < old_po.size()) {
			p1 = selectIndividualUseChoice(old_po);
			do {
				p2 = selectIndividualUseChoice(old_po);
			} while (p2 == p1);
			crossover(new_po, old_po, p1, p2);
		}
		Paper p;
		for (Paper paper : new_po) {
			p = paper;
			double mutation_degree = 0.005;
			Mutation(p, mutation_degree);
			p.fitness = Paper.fitness(p, q);
		}
		computeSelectChoice(new_po);
		return new_po;
	}
}
