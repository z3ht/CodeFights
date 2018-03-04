import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StringsRearrangement {
	
	public boolean stringsRearrangement(String[] inputArray) {
		for(int c = 0; c < inputArray.length; c++)
			if (stringsRearrangement(new int[] {c}, inputArray))
				return true;
		return false;
	}
	
	private boolean stringsRearrangement(int[] currentSlotsPath, String[] inputArray) {
		if(currentSlotsPath.length == inputArray.length)
			return true;
		int[] newPath = Arrays.copyOf(currentSlotsPath, currentSlotsPath.length + 1);
		int[] compatibleSlots = findCompatibleSlots(currentSlotsPath[currentSlotsPath.length - 1], inputArray);
		for(int compatibleSlot : compatibleSlots) {
			if(IntStream.of(currentSlotsPath).anyMatch(item -> item == compatibleSlot))
				continue;
			newPath[newPath.length - 1] = compatibleSlot;
			if(stringsRearrangement(newPath, inputArray))
				return true;
		}
		return false;
	}
	
	public int[] findCompatibleSlots(int inputSlot, String[] inputArray) {
		List<Integer> availableSlots = new ArrayList<>();
		for(int c = 0; c < inputArray.length; c++)
			if(isCompatible(inputArray[inputSlot], inputArray[c]))
				availableSlots.add(c);
		return availableSlots.stream().mapToInt(item -> item).toArray();
	}
	
	public boolean isCompatible(String a, String b) {
		if(a.length() != b.length())
			return false;
		int differenceCounter = 0;
		for(int stringCounter = 0; stringCounter < a.length(); stringCounter++) {
			if(a.charAt(stringCounter) == b.charAt(stringCounter))
				continue;
			differenceCounter += 1;
		}
		return differenceCounter == 1;
	}
	
}
