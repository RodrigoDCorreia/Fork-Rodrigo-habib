import java.util.Random;

public class Individual {
    int weight = 0;
    int fitness = 0;
    int[] genes = new int[6];
    int[] weight_fitness = new int[] {2,3,5,9,15,20};
    int[] item_points = new int[]{10,7,5,8,15,17};
    int geneLength = 6;

    public Individual() {
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        fitness = 0;
    }
    //Calculate fitness
    public void calcFitness() {
        fitness = 0;
        weight = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == 0) {
                weight = weight + weight_fitness[i];
                fitness = fitness + item_points[i];
                ++fitness;
            }
        }
        if (weight >= 31){
            fitness = 0;
        }
    }

}
