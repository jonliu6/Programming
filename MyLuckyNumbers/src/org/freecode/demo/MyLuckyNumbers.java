package org.freecode.demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyLuckyNumbers extends Activity {
	/** Called when the activity is first created. */
	private static Random randomGenerator = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void pickNumbers(View aView) {
		TextView txtResults = (TextView) this.findViewById(R.id.textView4);
		EditText txtPicked = (EditText) this.findViewById(R.id.editText1);
		EditText txtTotal = (EditText) this.findViewById(R.id.editText2);
		txtResults.setText(generateRandomNumbers(
				Integer.parseInt(txtPicked.getText().toString()),
				Integer.parseInt(txtTotal.getText().toString())));
	}

	private String generateRandomNumbers(int numOfPicks, int numOfTotal) {
		String aRandomString = "";

		if (numOfPicks > 0 && numOfTotal > 0 && numOfPicks < numOfTotal) {
			randomGenerator = new Random();
			ArrayList<Integer> lottoList = initPickList(numOfTotal);
			Set<Integer> pickList = new TreeSet<Integer>();

			for (int i = 0; i < numOfPicks; ++i) {
				int pickedNum = pickANumber(lottoList,
						randomGenerator.nextInt(numOfTotal - i - 1)).intValue();
				pickList.add(pickedNum);
				// aRandomString += pickedNum + " ";
			}

			aRandomString = "You picked: " + pickList.toString();
		} else {
			aRandomString = "Please enter positive numbers and make sure the number of your pick is less than the total.";
		}

		return aRandomString;
	}

	private ArrayList<Integer> initPickList(int numCount) {
		ArrayList<Integer> aList = new ArrayList<Integer>(numCount);
		for (int i = 0; i < numCount; ++i) {
			aList.add(new Integer(i + 1));
		}

		return aList;
	}

	private Integer pickANumber(ArrayList<Integer> numList, int numIdx) {
		return numList.remove(numIdx);
	}
}