import java.util.Random;

public class SimpleGA {
    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;
    int initial_Gen_fittest = 0;
    int compare = 0;
    int[] combo = new int[6];
    int index = 0;
    String[] item_backpack = new String[]{"Canivete","Corda","Tocha","Garrafa","Saco de dormir","Comida"};

    public static void main(String[] args) {

        Random rn = new Random();

        SimpleGA demo = new SimpleGA();

        //Initialize population
        demo.population.initializePopulation(10);

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        demo.initial_Gen_fittest = demo.population.fittest;
        //While population gets an individual with maximum fitness
        while (demo.population.fittest < 31) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        if (demo.population.fittest > demo.compare) {
            demo.compare = demo.population.fittest;
            demo.index = demo.generationCount;
            for (int i = 0; i < 6; i++) {
                demo.combo[i] = demo.population.getFittest().genes[i];
            }
        }
    }

    if(demo.compare < demo.initial_Gen_fittest) {
        SimpleGA demo;
        demo.compare = demo.initial_Gen_fittest;
        demo.index = 0;
    }

    System.out.println("\nSolution found in generation " + demo.intex);
    System.out.println("Fitness: "+demo.compare);
    System.out.print("Genes: ");

    for(int i = 0; i < 6; i++) {
        System.out.println(demo.item_backpack[i] + " = " + demo.combo[i]);
    }

    System.out.println("");


}
    //Selection
    void selection() {

        //Select the most fittest individual
        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }
}
