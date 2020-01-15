import java.util.Random;

public class Sortowanie {

	private int amountOfElements;
	private int range;
	private int[] A;

	public Sortowanie() {

		amountOfElements = 0;
		range = 0;
		A = new int[0];
	}

	public Sortowanie(int amountOfElements, int range) {

		this.amountOfElements = amountOfElements;
		this.range = range;
		A = randomTable(this.amountOfElements, this.range);
	}

	public int getAmountOfElements() {

		return amountOfElements;
	}

	public int getRange() {

		return range;
	}

	public int[] getA() {

		return A;
	}

	public void setAmountOfElements(int amountOfElements) {

		this.amountOfElements = amountOfElements;
		return;
	}

	public void setRange(int range) {

		this.range = range;
		return;
	}

	public int getPositionOnList(int k) {

		int result = -1;
		int i;
		int n = A.length;
		i = 0;
		while (i < n && A[i] != k)
			i++;
		System.out.println("i " + i);
		if (i < n)
			result = i;
		System.out.println((result == -1) ? "brak elementu " + k + " w tablicy "
				: "element " + k + " znajduje sie na pozycji " + result);
		return result;
	}

	public int getPositionOnListBin(int k) {

		// Dziala tylko na posortowanej tablicy

		int result = -1;
		int lewy = 0;
		int prawy = A.length - 1;
		int srodek = (lewy + prawy) / 2;

		while (lewy < prawy && A[srodek] != k) {
			if (k > A[srodek])
				lewy = srodek + 1;
			else
				prawy = srodek - 1;
			srodek = (lewy + prawy) / 2;
		}
		if (A[srodek] == k)
			result = srodek;
		System.out.println((result == -1) ? "brak elementu " + k + " w tablicy "
				: "element " + k + " znajduje sie na pozycji " + result);
		return result;
	}

	public int getPositionOnListBin1(int k) {

		// Dziala tylko na posortowanej tablicy zwraca pozycje pierwszego
		// elementu
		// jeslii szukanych jest wiecej niz jeden

		int result = -1;
		int lewy = 0;
		int prawy = A.length - 1;
		int srodek;

		while (lewy < prawy) {
			srodek = (lewy + prawy) / 2;
			if (k > A[srodek])
				lewy = srodek + 1;
			else
				prawy = srodek;
		}
		if (A[lewy] == k)
			result = lewy;
		System.out.println((result == -1) ? "brak elementu " + k + " w tablicy "
				: "element " + k + " znajduje sie na pozycji " + result);
		return result;
	}

	public int[] randomTable(int amountOfElements, int range) {

		int p = -1;
		int i;
		Random rd = new Random();
		int negativeValues = rd.nextInt(amountOfElements); // losujemy ilosc
															// elementow o
															// wartosci ujemnej
															// w tablicy
		int[] A = new int[amountOfElements];

		for (i = 0; i < amountOfElements; i++)
			A[i] = rd.nextInt(range + 1);

		for (i = 0; i < negativeValues; i++) {
			int k = rd.nextInt(amountOfElements); // losujemy pozycje w tablicy
			while (k == p)
				k = rd.nextInt(amountOfElements);

			A[k] = -1 * A[k];
			p = k;
		}
		return A;
	}

	public int getMinimum() {

		int n = A.length;
		int min = A[0];

		for (int i = 1; i < n; i++)
			if (A[i] < min)
				min = A[i];

		return min;
	}

	public int getMaximum() {

		int n = A.length;
		int max = A[0];

		for (int i = 1; i < n; i++)
			if (A[i] > max)
				max = A[i];

		return max;
	}

	public int getMinimum(int p, int r) {

		int min = A[p];

		for (int i = p + 1; i < r + 1; i++)
			if (A[i] < min)
				min = A[i];

		return min;
	}

	public int getMaximum(int p, int r) {

		int max = A[p];

		for (int i = p + 1; i < r + 1; i++)
			if (A[i] > max)
				max = A[i];

		return max;
	}

	public int getAbsolute(int n) {

		if (n < 0)
			n = -1 * n;
		return n;
	}

	public boolean isInRange(int p, int r) {

		boolean result = false;
		if (p < 0 || p >= amountOfElements) {
			System.out.println("Pierwszy parametr rowny " + p + " poza zakresem tablcy \n");
			if (r < 0 || r >= amountOfElements)
				System.out.println("Drugi parametr rowny " + r + " poza zakresem tablcy \n");
			return result;
		} else if (r < 0 || r >= amountOfElements) {
			System.out.println("Drugi parametr rowny " + r + " poza zakresem tablcy \n");
			return result;
		} else
			result = true;
		return result;
	}

	public void replace(int p, int r) {

		int k = A[p];
		A[p] = A[r];
		A[r] = k;
	}

	public void bubbleSort(int p, int r) {

		int i, j;

		for (i = r; i > p; i--)
			for (j = p; j < i; j++)
				if (A[j] > A[j + 1])
					replace(j, j + 1);
	}

	public void bubbleSortGuard(int p, int r) {

		int i, j;
		int guard;

		i = r;
		while (i != p) {
			guard = p;
			for (j = p; j < i; j++)
				if (A[j] > A[j + 1]) {
					replace(j, j + 1);
					guard = j;
				}
			i = guard;
		}
	}

	public int partition(int p, int r) {

		int i, j, n;

		n = A[r];
		for (j = i = p; j < r; j++) {
			if (A[j] <= n)
				replace(i++, j);
		}
		replace(i, r);
		return i;
	}

	public int randomPart(int p, int r) {

		Random rd = new Random();
		int k = rd.nextInt(r + 1);
		while (k < p)
			k = rd.nextInt(r + 1);

		replace(k, r);
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
			int k = A[j];
			int i = j - 1;
			while ((i >= p) && (A[i] > k))
				A[i + 1] = A[i--];

			A[i + 1] = k;
		}
		return;
	}

	public void countSort(int p, int r) {

		int n = r - p + 1;
		int[] B = new int[n];
		int i;
		int min = getMinimum(p, r);
		int max = getMaximum(p, r);

		if (min < 0) {
			min = getAbsolute(min);
			for (i = p; i <= r; i++)
				A[i] += min;
		} else
			min = 0;

		int k = min + max + 1;
		int[] C = new int[k];

		for (i = p; i <= r; i++)
			C[A[i]] += 1;

		for (i = 1; i < k; i++)
			C[i] = C[i - 1] + C[i];

		for (i = r; i >= p; i--) {
			B[C[A[i]] - 1] = A[i];
			C[A[i]] = C[A[i]] - 1;
		}
		for (i = 0; i < n; i++)
			A[p++] = B[i] - min;

		return;
	}

	public void scal(int p, int q, int r, int max) {

		int i, j, k;
		int dlugoscL = q - p + 1;
		int dlugoscP = r - q;
		int lewa[] = new int[dlugoscL + 1];
		int prawa[] = new int[dlugoscP + 1];

		for (i = 0; i < dlugoscL; i++)
			lewa[i] = A[p + i];

		for (j = 0; j < dlugoscP; j++)
			prawa[j] = A[q + 1 + j];

		lewa[dlugoscL] = max + 1;
		prawa[dlugoscP] = max + 1;
		i = j = 0;
		for (k = p; k <= r; k++) {
			if (lewa[i] <= prawa[j])
				A[k] = lewa[i++];
			else
				A[k] = prawa[j++];
		}
	}

	public void sortScal(int p, int r, int max) {

		if (p < r) {
			int q = (p + r) / 2;
			sortScal(p, q, max);
			sortScal(q + 1, r, max);
			scal(p, q, r, max);
		}
		return;
	}

	public void call(String name, int p, int r) {

		boolean tip = false;
		// System.out.println("tablica losowa");
		System.out.printf("liczba elementow wynosi %d, zakres od %d do %d\n%s\n", amountOfElements, getMinimum(),
				getMaximum(), stringTab());
		if (isInRange(p, r)) {
			if (p > r) {
				int k = p;
				p = r;
				r = k;
				System.out.println("Zmieniono kolejnosc indeksow");
			}
			switch (name.toLowerCase()) {
			case "countsort":
				countSort(p, r);
				tip = true;
				break;

			case "insertsort":
				insertSort(p, r);
				tip = true;
				break;

			case "sortscal":
				int max = getMaximum(p, r);
				sortScal(p, r, max);
				tip = true;
				break;

			case "quicksort":
				quickSortRandom(p, r);
				tip = true;
				break;

			case "bubblesort":
				bubbleSort(p, r);
				tip = true;
				break;

			case "bubblesortguard":
				bubbleSortGuard(p, r);
				tip = true;
				break;

			}
			if (tip) {
				System.out.println("tablica posortowana od pozycji " + p + " do " + r + " (" + name.toUpperCase()
						+ ") \n" + stringTab());
			} else {
				System.out.println("nieznana nazwa algorytmu tablica losowa \n" + stringTab());
			}
			System.out.print('\n');
		}
		return;
	}

	public void call(String name) {

		boolean tip = false;
		int p = 0;
		int r = A.length - 1;
		// System.out.println("tablica losowa");
		System.out.printf("liczba elementow wynosi %d, zakres od %d do %d\n%s\n", amountOfElements, getMinimum(),
				getMaximum(), stringTab());

		switch (name.toLowerCase()) {
		case "sortscal":
			int max = getMaximum(p, r);
			sortScal(p, r, max);
			tip = true;
			break;

		case "quicksort":
			quickSortRandom(p, r);
			tip = true;
			break;

		case "countsort":
			countSort(p, r);
			tip = true;
			break;

		case "insertsort":
			insertSort(p, r);
			tip = true;
			break;

		case "bubblesort":
			bubbleSort(p, r);
			tip = true;
			break;

		case "bubblesortguard":
			bubbleSortGuard(p, r);
			tip = true;
			break;
		}
		if (tip) {
			System.out.println("tablica posortowana (" + name.toUpperCase() + ") \n" + stringTab());
		} else {
			System.out.println("nieznana nazwa algorytmu tablica losowa \n" + stringTab());
		}
		System.out.print('\n');
		return;
	}

	public String stringTab() {

		String s = "[";
		int k = A.length;
		for (int i = 0; i < k - 1; i++)
			s += A[i] + ", ";

		s += A[k - 1] + "]";
		return s;
	}

	public static void main(String[] args) {

		int n, k;
		n = 10;
		k = 5;

		Sortowanie sort = new Sortowanie(n, k);
		sort.call("bubbleSoRT", 2, 10);
		sort.call("bubbleSoRTguard");

		sort.getPositionOnListBin1(1);

		Sortowanie sort1 = new Sortowanie(n, k);
		sort1.call("CountSort", 2, 6);
		sort1.call("CountSort");

		Sortowanie sort3 = new Sortowanie(n, k);
		sort3.call("bubbleSortGUArD", 6, 2);
		sort3.call("bubbleSortGUArD");

		Sortowanie sort4 = new Sortowanie(n, k);
		sort4.call("quickSORtt", 2, 5);
		sort4.call("quickSORT");
	}
}
