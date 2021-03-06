package com.cutsquash.chyokin.utils;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 25/01/2016.
 */
public class Utils {

    // http://stackoverflow.com/a/18669307
    public static List<View> getAllChildren(View v) {
        List<View> visited = new ArrayList<View>();
        List<View> unvisited = new ArrayList<View>();
        unvisited.add(v);

        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            visited.add(child);
            if (!(child instanceof ViewGroup)) continue;
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i=0; i<childCount; i++) unvisited.add(group.getChildAt(i));
        }

        return visited;
    }

    // Adding £ and p symbols to value
    public static String formatValue(int value) {
        String formatted = Integer.toString(value);
        if (Math.abs(value) < 100) { // pence
            formatted += "p";
        } else { // pounds
            formatted = new StringBuilder(formatted)
                    .insert(formatted.length() - 2, ".")
                    .insert(0, "£")
                    .toString();
        }
        return formatted;
    }
}
