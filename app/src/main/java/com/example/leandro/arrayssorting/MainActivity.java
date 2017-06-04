package com.example.leandro.arrayssorting;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        String unSorted = "\n UNSORTED\u25b2    \u25bcSORTED \n";

        TextView testText1 = new TextView(this);
        arrayTextViewStyle(testText1, "#5cd6d6");
        mainLayout.addView(testText1);

        int[] ints1 = new int[]{3, 1, 7, 2, 9, 0, 1, 1};
        testText1.setText(arrayToSimpleString(ints1) + unSorted + arrayToSimpleString(bubbleSorting(ints1)));

        TextView testText2 = new TextView(this);
        arrayTextViewStyle(testText2, "#ffb3b3");
        mainLayout.addView(testText2);

        int[] ints2 = new int[]{1, 3, 6, 0, 2, 1, 9, 4};
        testText2.setText(arrayToSimpleString(ints2) + unSorted + arrayToSimpleString(selectionSorting(ints2)));

        TextView testText3 = new TextView(this);
        arrayTextViewStyle(testText3, "#d9b3ff");
        mainLayout.addView(testText3);

        int[] ints3 = new int[]{3, 1, 8, 4, 5, 2, 0, 999, -8};
        testText3.setText(arrayToSimpleString(ints3) + unSorted + arrayToSimpleString(insertionSorting(ints3)));

        TextView testText4 = new TextView(this);
        arrayTextViewStyle(testText4, "#33cc33");
        mainLayout.addView(testText4);

        int[] ints4 = new int[]{1, 2, 11, 4, 8, 6, 7, 5, 9, 10, 3, 12, 13, 14, 15};

        /**
         * Set parameters to "searchElement" inside an array (or inside an interval).
         */
        int[] array = ints4;
        int maxConsideredIndex = 14;
        int minConsideredIndex = 0;
        int element = 9;

        if (maxConsideredIndex <= array.length - 1 && minConsideredIndex >= 0) {
            int search = searchElement(insertionSorting(array), element, minConsideredIndex, maxConsideredIndex + 1);
            if (search >= 0)
                testText4.setText(element + " " + getString(R.string.search) + " " + String.valueOf(search));
            else
                testText4.setText(element + " " + getString(R.string.notEl));
        } else if (maxConsideredIndex >= array.length && minConsideredIndex >= 0) {
            testText4.setText(getString(R.string.excMax));
        } else if (maxConsideredIndex <= array.length && minConsideredIndex < 0) {
            testText4.setText(getString(R.string.excMin));
        } else
            testText4.setText(getString(R.string.excAll));
    }

    public void arrayTextViewStyle(TextView textView, String color) {
        textView.setBackgroundColor(Color.parseColor(color));
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(5, 5, 5, 5);
        textView.setTextSize(18);
    }

    public String arrayToSimpleString(int[] array) {
        return Arrays.toString(array).replace('[', ' ').replace(']', ' ').replaceAll("\\s", "");
    }

    public int searchElement (int[] array, int el, int min, int max) {
        int range = max - min;
        if (range > 2) {
            int mid = min + range/2;
            if (array[mid] >= el)
                return searchElement(array, el, min, mid);
            else
                return searchElement(array, el, mid, max);
        } else {
            if (array[max - 1] == el)
                return max - 1;
            else if (array[min] == el)
                return min;
            else if (array[array.length/2] == el)
                return array.length/2;
            else
                return -1;
        }
    }

    /*public int searchElement (int[] array, int el, int rightLimit, int leftLimit){
        if (array.length == 0)
            return -2;

        for (int i = rightLimit; i > leftLimit; i /= 2) {
            if (array[i - 1] < el) {
                leftLimit = i - 1;
                rightLimit = 2*i;
                searchElement(array, el, rightLimit, leftLimit);
            } else if (array[i - 1] == el)
                return i - 1;
        }
        return -1;
    }*/

    public int[] bubbleSorting(int[] array) {
        boolean swap = false;
        for (int i = 0; i < array.length; i++) {
            for (int subI = 0; subI < array.length - 1; subI++) {
                if (array[subI] > array[subI + 1]) {
                    swap = true;
                    int temp = array[subI];
                    array[subI] = array[subI + 1];
                    array[subI + 1] = temp;
                }
            }
            if (!swap)
                break;
        }
        return array;
    }

    public int[] selectionSorting(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minVal = i;
            for (int subI = i + 1; subI < array.length; subI++) {
                if (array[minVal] > array[subI]) {
                    minVal = subI;
                }
            }
            if (minVal != i) {
                int temp = array[i];
                array[i] = array[minVal];
                array[minVal] = temp;
            }
        }
        return array;
    }

    public int[] insertionSorting(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int subI = i; subI > 0; subI--) {
                if (array[subI] < array[subI - 1]) {
                    int temp = array[subI];
                    array[subI] = array[subI - 1];
                    array[subI - 1] = temp;
                } else
                    break;
            }
        }
        return array;
    }
}
