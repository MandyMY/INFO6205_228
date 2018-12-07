package GA;

import GA.Generate;
import GA.Paper;
import GA.QuestionReader;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class mutTest {

    @Test
    public void Mutation() {

        Generate A = new Generate();
        QuestionReader q = new QuestionReader();

        Paper nind1 =new Paper(q.choiceQuestions().size(),1, q
                . blankQuestions().size(), 1, q
                . shortAnswerQuestions().size(), 1, q
                .calculateQuestions().size(), 1);
        Paper nind2 = nind1;

        System.out.println("Before mutation");
        System.out.println(nind1);
        A.Mutation(nind1,1);//set variation_degree large enough to make sure there is mutation
        System.out.println("After mutation");
        System.out.println(nind1);
    }
}