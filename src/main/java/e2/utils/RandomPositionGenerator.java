package e2.utils;



import java.util.Random;

public class RandomPositionGenerator implements PositionGenerator {

    private final Random random = new Random();

    @Override
    public Pair<Integer, Integer> generatePosition(int minIndex, int maxIndex) {
        return new Pair<>(this.random.nextInt(minIndex, maxIndex), this.random.nextInt(minIndex, maxIndex));
    }

    @Override
    public Pair<Integer, Integer> generatePosition(int maxIndex) {
       return this.generatePosition(0,maxIndex);
    }

}
