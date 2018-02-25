import java.util.*;

public class Simulate
{
    private static boolean showLogging = false;


    public static boolean testSingleMontyHallNoTip()
    {
        List<Integer> pick = new ArrayList<>(3);
        pick.add(0);
        pick.add(0);
        pick.add(0);
        Random r = new Random();

        int carIndex = r.nextInt(3);
        pick.set(carIndex, 1);
        if (showLogging) System.out.println(pick);

        //The user picks a position.
        int userChoice = r.nextInt(3);
        if (showLogging) System.out.println("The user picked " + userChoice);
        return pick.get(userChoice) == 1;
    }

    private static boolean testSingleMontyHallTipChange()
    {
        List<Integer> pick = new ArrayList<>(3);
        pick.add(0);
        pick.add(0);
        pick.add(0);
        Random r = new Random();

        int carIndex = r.nextInt(3);
        pick.set(carIndex, 1);
        if (showLogging) System.out.println(pick);

        //The user picks a position.
        int userChoice = r.nextInt(3);
        if (showLogging) System.out.println("The user picked " + userChoice);

        // The presenter picks a door without a car.
        List<Integer> doorsThatCanBeShown = new ArrayList<>();
        doorsThatCanBeShown.add(0);
        doorsThatCanBeShown.add(1);
        doorsThatCanBeShown.add(2);
        doorsThatCanBeShown.remove(new Integer(userChoice)); // The presenter will not show the option of the candidate.
        doorsThatCanBeShown.remove(new Integer(carIndex)); // The presenter will not show the car

        int randomSelectionIndex = r.nextInt(doorsThatCanBeShown.size());
        int randomlySelectedPresenterItem = doorsThatCanBeShown.get(randomSelectionIndex);
        if (showLogging) System.out.println("The presenter has shown " + randomlySelectedPresenterItem);

        //The user picks the other choice.

        List<Integer> optionsForTheCandidate = new ArrayList<>();
        optionsForTheCandidate.add(0);
        optionsForTheCandidate.add(1);
        optionsForTheCandidate.add(2);
        optionsForTheCandidate.remove(new Integer(userChoice)); // The presenter will not show the option of the candidate.
        optionsForTheCandidate.remove(new Integer(randomlySelectedPresenterItem)); // The presenter will not show the car

        //The user changed his mind and picked:
        int newUserChoice = optionsForTheCandidate.get(0);

        if (showLogging) System.out.println("The candidate has changed his mind " + newUserChoice);
        return pick.get(newUserChoice) == 1;
    }

    public static void main(String[] args)
    {
        long counter = 0;
        long correct = 0;
        for (int k = 0; k < 1000000; k++)
        {
            counter++;
            if (testSingleMontyHallTipChange()) {
                correct++;
            }
        }
        System.out.println("After receiving the presenter tip and changing:" + (double)correct / counter);

        counter = 0;
        correct = 0;
        for (int k = 0; k < 1000000; k++)
        {
            counter++;
            if (testSingleMontyHallNoTip()) {
                correct++;
            }
        }
        System.out.println("Without a tip " + (double)correct / counter);
    }
}
