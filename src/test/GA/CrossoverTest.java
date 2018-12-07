package GA;
import GA.Generate;
import GA.Paper;
import GA.QuestionReader;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CrossoverTest {

    @Test
    public void crossover() {
        int pop_size = 3;
        ArrayList<Paper> old_pop = new ArrayList<Paper>();
        ArrayList<Paper> new_pop = new ArrayList<Paper>();
        Paper ind;
        QuestionReader q = new QuestionReader();
        for (int i = 0; i < pop_size; i++) {
            ind = new Paper(q.choiceQuestions().size(),1, q
                    . blankQuestions().size(), 1, q
                    . shortAnswerQuestions().size(), 1, q
                    .calculateQuestions().size(), 1);
            old_pop.add(ind);
        }

        for (Paper List:old_pop )
        {
            System.out.println(List);
        }
        System.out.println("**");

        Generate A = new Generate();
        A.crossover(new_pop,old_pop,1,2);
      for (Paper List:new_pop )
        {
            System.out.println(List);
        }
    }
}