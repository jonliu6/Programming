package org.freecode.demo;

public class AlgorithmDemo {
	
	public static void main(String[] args) {
		
		AlgorithmDemo drv = new AlgorithmDemo();
		Integer[] values = {4,6,3,2,0,9,5,8,7,1};
		
		System.out.println("Original data: ");
		drv.displayArrayElements(values);
		System.out.println("====================");
		Integer[] newValues = drv.bubbleSort(values);
		System.out.println("====================");
		System.out.println("Sorted result: ");
		drv.displayArrayElements(newValues);
	}
	
	public Integer[] bubbleSort(Integer[] numbers) {
		int aux = 0;
		
		if (numbers != null) {
			boolean hasSwapped = true;
			int len = numbers.length;
			for (int i = 1; i < len && hasSwapped; ++i) {
				hasSwapped = false;
				for (int j = 0; j < len - 1; ++j) {
					if (numbers[j] > numbers[j+1]) {
						aux = numbers[j];
						numbers[j] = numbers[j+1];
						numbers[j+1] = aux;
						hasSwapped = true;
					}
				}
				displayArrayElements(numbers);
			}
		}
		
		return numbers;
	}

	public void displayArrayElements(Object[] objs) {
		StringBuilder outp = new StringBuilder();
		if (objs != null && objs.length > 0) {
			for (int i = 0, len = objs.length; i < len; ++i) {
				outp.append(String.valueOf(objs[i]) + " ");
			}
		}
		
		System.out.println(outp.toString().trim()); 
	}
}
