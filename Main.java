import java.util.*;

// Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.

// Example 1:

// Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
// Output: [[1,3], [4,7], [8,12]]
// Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
// Example 2:

// Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
// Output: [[1,3], [4,12]]
// Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class Main {

  public static List<Interval> merge(List<Interval> intervals, Interval newInterval) {
    if (intervals == null || intervals.isEmpty())
      return Arrays.asList(newInterval);
    int i = 0;
    List<Interval> mergedIntervals = new ArrayList<>();

    //skip and add to output all intervals comes before new interval
    while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
      mergedIntervals.add(intervals.get(i++));
    }
//merge all intervals that overlaps with new interval
    while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
      newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
      newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
      i++;
    }
    //add new interval
    mergedIntervals.add(newInterval);

    //add all remaining intervals to output
    while (i < intervals.size()) {
      mergedIntervals.add(intervals.get(i++));

    }

    return mergedIntervals;
  }

  public static void main(String[] args) {
    List<Interval> interval = new ArrayList<>();
    interval.add(new Interval(1, 3));

    interval.add(new Interval(5, 7));

    interval.add(new Interval(8, 12));
    List<Interval> result = Main.merge(interval, new Interval(4, 6));
    System.out.println("Merging Intervals");

    for (Interval interval2 : result) {
      System.out.println("[" + interval2.start + ", " + interval2.end + " ] ");
      System.out.println();
    }
  }
}