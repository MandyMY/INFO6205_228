package GA;

import GA.Generate;
import GA.Paper;
import GA.QuestionReader;


import org.junit.Test;

import java.util.ArrayList;

public class initialTest {

    @Test
    public void initial() {

        QuestionReader q = new QuestionReader();
        Generate A = new Generate();
        ArrayList<Paper> show = A.initial(4,q);
        for (Paper List:show )
        {
            System.out.println(List);
        }
    }
}