import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class SortStrings {

	private BufferedReader bufR;
	private BufferedWriter bufW;
	private ArrayList<ArrayList<String>> names;
	private int nameOrSurname; // 0 oznacza imie 1 oznacza nazwisko
	private int position;

	public SortStrings(String sR, String sW) {

		try {
			bufR = new BufferedReader(new FileReader(new File(sR)));
			bufW = new BufferedWriter(new FileWriter(new File(sW)));
			names = collectorOfArrays();
			nameOrSurname = 1;
			position = 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {

		try {
			bufR.close();
			bufW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean checkString(String s1) {

		boolean result = false;
		if (s1.length() == 0)
			result = true;
		else {
			int k = s1.length();
			int j = 0;
			while (j < k && s1.charAt(j) < 48)
				j++;

			if (j == k)
				result = true;
		}
		return result;
	}

	public String fulFillString(String s) {

		int k = s.length();
		while (k++ < 14)
			s += " ";

		return s;
	}

	public ArrayList<ArrayList<String>> collectorOfArrays() {

		ArrayList<ArrayList<String>> names = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		String s = null;

		try {
			while ((s = bufR.readLine()) != null) {
				String[] s1 = s.split("[ ,.]");
				if (s1.length > 1) {
					for (String i : s1) {
						if (checkString(i))
							continue;
						i = fulFillString(i);
						name.add(i);
					}
					if (name.size() > 2) // decyduje o minimalnej liczbie
											// elementow w wypisywanym wierszu
						names.add(name);
					name = new ArrayList<>();
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return names;
	}

	public void firstDifferent(ArrayList<String> s, ArrayList<String> s1) {

		nameOrSurname = 1;

		for (int i = nameOrSurname; i >= 0; i--) {
			int k = s.get(nameOrSurname).length();
			int j = 0;
			while (j < k && (s.get(nameOrSurname).charAt(j) == s1.get(nameOrSurname).charAt(j)))
				j++;

			if (j == k)
				nameOrSurname = 0;
			else {
				position = j;
				break;
			}
		}
	}

	public void replace1(int p, int r) {

		ArrayList<String> s = names.get(p);
		names.set(p, names.get(r));
		names.set(r, s);
		return;
	}

	public int partition(int p, int r) {

		int i, j;
		ArrayList<String> line = names.get(r);
		for (j = i = p; j < r; j++) {
			firstDifferent(names.get(j), line);
			if (names.get(j).get(nameOrSurname).charAt(position) <= line.get(nameOrSurname).charAt(position))
				replace1(i++, j);
		}
		replace1(i, r);
		return i;
	}

	public int randomPart(int p, int r) {

		Random rd = new Random();
		int k = rd.nextInt(r + 1);
		while (k < p)
			k = rd.nextInt(r + 1);

		replace1(k, r);
		return partition(p, r);
	}

	public void quickSortRandom(int p, int r) {

		if (p < r) {
			int q = randomPart(p, r);
			quickSortRandom(p, q - 1);
			quickSortRandom(q + 1, r);
		}
	}

	public void insertSort(int p, int r) {

		for (int j = p + 1; j <= r; j++) {
			ArrayList<String> k = names.get(j);
			int i = j - 1;
			firstDifferent(k, names.get(i));
			while ((i >= p)
					&& (names.get(i).get(nameOrSurname).charAt(position) > k.get(nameOrSurname).charAt(position))) {
				names.set(i + 1, names.get(i));
				i--;
				if (i >= 0)
					firstDifferent(k, names.get(i));
			}
			names.set(i + 1, k);
		}
		return;
	}

	public void collectorOfStringsAndWriter() {

		String s;
		int i, j;
		int n = names.size();
		try {
			for (i = 0; i < n; i++) {
				s = "";
				int n1 = names.get(i).size();
				for (j = 0; j < n1 - 1; j++) {
					s += names.get(i).get(j);
				}

				bufW.write(s + names.get(i).get(j).trim());
				bufW.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		SortStrings so = new SortStrings("C:/Users/Krzysiek/Desktop/fileIn.txt",
				"C:/Users/Krzysiek/Desktop/fileOut.txt");
		so.quickSortRandom(0, so.names.size() - 1);
		so.collectorOfStringsAndWriter();
		so.close();

		SortStrings so1 = new SortStrings("C:/Users/Krzysiek/Desktop/fileIn.txt",
				"C:/Users/Krzysiek/Desktop/fileOut1.txt");
		so1.insertSort(0, so1.names.size() - 1);
		so1.collectorOfStringsAndWriter();
		so1.close();
	}
}
