import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Sort extends JFrame {

	private int numsToSort;
    public Sort() {
        super("Visualizing Sorting Algorithms");
        numsToSort = 100;
        getContentPane().setBackground(Color.WHITE);
        setSize(1500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    int[] drawRectangles(Graphics2D g2d) {
    	Random rand = new Random();
    	int[] notSorted = new int[numsToSort];
    	int randNum, xCoords;
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < numsToSort; i++)
        {
        	randNum = rand.nextInt(750 - 20);
        	xCoords = (1500 / numsToSort) * i;
        	notSorted[i] = randNum;
        	g2d.drawRect(xCoords, 30, 1500 / numsToSort, randNum);
        	g2d.fillRect(xCoords, 30, 1500 / numsToSort, randNum);
        }
        return notSorted;
    }

    //Bubble Sort
    void bubbleSort(Graphics2D g2d, int[] arr)
    {
    	boolean arraySorted = false;
    	int temp;
    	while (!arraySorted)
    	{
    		arraySorted = true;
    		for (int i = 0; i < arr.length - 1; i++)
    		{
    			if (arr[i] > arr[i + 1])
    			{
    				swapRects(g2d, i, arr[i], i + 1, arr[i+1]);
    				temp = arr[i];
    				arr[i] = arr[i+1];
    				arr[i+1] = temp;
    				arraySorted = false;
    			}
    		}
    	}
    }

    //Insertion Sort
    void insertionSort(Graphics2D g2d, int[] arr)
    {
    	int holder, temp;
    	for (int i = 1; i < arr.length; i++)
    	{
    		holder = i;
    		while (arr[i] < arr[i-1])
    		{
    			swapRects(g2d, i, arr[i], i - 1, arr[i-1]);
    			temp = arr[i];
    			arr[i] = arr[i-1];
    			arr[i-1] = temp;
    			i--;
    			if (i == 0)
    			{
    				break;
    			}
    		}
    		i = holder;
    	}
    }

    //Selection Sort
    void selectionSort(Graphics2D g2d, int[] arr)
    {
    	int min, minIndex, temp;
    	for (int i = 0; i < arr.length; i++)
    	{
    		min = arr[i];
    		minIndex = i;
       		for (int j = i; j < arr.length; j++)
    		{
    			if (arr[j] < min)
    			{
    				min = arr[j];
    				minIndex = j;
    			}
    		}
    		swapRects(g2d, i, arr[i], minIndex, min);
    		temp = arr[i];
    		arr[i] = arr[minIndex];
    		arr[minIndex] = temp;
    	}
    }

    //Merge Sort
    void mergeSort(Graphics2D g2d, int[] arr, int s, int e)
    {
    	if (s < e)
    	{
    		int mid = (s + e) / 2;
    		mergeSort(g2d, arr, s, mid);
    		mergeSort(g2d, arr, mid + 1, e);

    		int l1 = mid - s + 1;
    		int l2 = e - mid;

    		int firstArr[] = new int[l1];
    		int secondArr[] = new int[l2];

    		for (int i = 0; i < l1; i++)
    		{
    			firstArr[i] = arr[s + i];
    		}

    		for (int i = 0; i < l2; i++)
    		{
    			secondArr[i] = arr[mid + i + 1];
    		}

    		int i = 0;
    		int j = 0;
    		int k = s;
    		while (i < l1 && j < l2)
    		{
    			if (firstArr[i] <= secondArr[j])
    			{
    				replaceRect(g2d, k, arr[k], firstArr[i]);
    				arr[k] = firstArr[i];
    				i++;
    			}
    			else
    			{
    				replaceRect(g2d, k, arr[k], secondArr[j]);
    				arr[k] = secondArr[j];
    				j++;
    			}
    			k++;
    		}

    		while (i < l1)
    		{
    			replaceRect(g2d, k, arr[k], firstArr[i]);
    			arr[k] = firstArr[i];
    			k++;
    			i++;
    		}
    		while (j < l2)
    		{
    			replaceRect(g2d, k, arr[k], secondArr[j]);
    			arr[k] = secondArr[j];
    			k++;
    			j++;
    		}
    	}
    }

    //Quick Sort
    void quickSort(Graphics2D g2d, int[] arr, int s, int e)
    {
    	if (s < e)
    	{
    		int pi;
    		int pivot = arr[e];
    		int i = (s - 1);
    		for (int j = s; j < e; j++)
    		{
    			if (arr[j] < pivot)
    			{
    				i++;
    				swapRects(g2d, i, arr[i], j, arr[j]);
    				int temp = arr[i];
    				arr[i] = arr[j];
    				arr[j] = temp;
    			}
    		}
    		swapRects(g2d, i + 1, arr[i + 1], e, arr[e]);
    		int temp = arr[i + 1];
    		arr[i + 1] = arr[e];
    		arr[e] = temp;
    		pi = i + 1;

    		quickSort(g2d, arr, s, pi - 1);
    		quickSort(g2d, arr, pi + 1, e);
    	}
    }

    void replaceRect(Graphics2D g2d, int x1, int v1, int v2)
    {
    	g2d.clearRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v1);
    	g2d.drawRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v2);
    	g2d.fillRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v2);
    }

    void swapRects(Graphics2D g2d, int x1, int v1, int x2, int v2)
    {
    	g2d.clearRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v1);
    	g2d.clearRect((1500 / numsToSort) * x2, 30, 1500 / numsToSort, v2);
        g2d.drawRect((1500 / numsToSort) * x2, 30, 1500 / numsToSort, v1);
        g2d.fillRect((1500 / numsToSort) * x2, 30, 1500 / numsToSort, v1);
    	g2d.drawRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v2);
        g2d.fillRect((1500 / numsToSort) * x1, 30, 1500 / numsToSort, v2);
    }

    void sleep(int time)
    {
    	try
    	{
    		Thread.sleep(time);
    	}
    	catch(InterruptedException ex)
    	{
    		Thread.currentThread().interrupt();
    	}
    }

    public void paint(Graphics g) {
        super.paint(g);
        int[] arr;

        Graphics2D g2d = (Graphics2D) g;
        
        arr = drawRectangles(g2d);
        bubbleSort(g2d, arr);

        sleep(3000);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        arr = drawRectangles(g2d);
        insertionSort(g2d, arr);
		

		sleep(3000);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        arr = drawRectangles(g2d);
        selectionSort(g2d, arr);

        sleep(3000);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        arr = drawRectangles(g2d);
        mergeSort(g2d, arr, 0, arr.length - 1);

		
        sleep(3000);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        arr = drawRectangles(g2d);
        quickSort(g2d, arr, 0, arr.length - 1);


    }
 
    public static void main(String[] args) throws Exception {
 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sort().setVisible(true);
            }
        });
    }
}