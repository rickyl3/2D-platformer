import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
        int[] arr = {1, 2, 3, 2};
        int[] arr1 = {10};
        int[] arr2 = {8};
        mystery(arr);
        mystery(arr1);
        mystery(arr2);
        System.out.println();
        mysteryIteration(arr);
        mysteryIteration(arr1);
        mysteryIteration(arr2);
        System.out.println();
        ArrayList<Integer> arrList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 2));
        ArrayList<Integer> arrList1 = new ArrayList<Integer>(Arrays.asList(10));
        ArrayList<Integer> arrList2 = new ArrayList<Integer>(Arrays.asList(8));
        mysteryArrayList(arrList);
        mysteryArrayList(arrList1);
        mysteryArrayList(arrList2);
        System.out.println();
        int[] arrMerge = {80, 50, 30, 20, 60, 70};
        sort(arrMerge, 0, arrMerge.length - 1);
        System.out.println(Arrays.toString(arrMerge));
        ArrayList<Integer> arrMergeList = new ArrayList<Integer>(Arrays.asList(80, 50, 30, 20, 60, 70));
        sort(arrMergeList, 0, arrMergeList.size() - 1);
        System.out.println(arrMergeList);
        System.out.println(binarySearch(arrMerge, 0, arrMerge.length - 1, 60));
    }
    public static void mystery(int[] arr) {
        if (arr.length == 1) {
            System.out.println(arr[0]);
        } else {
            System.out.print(arr[arr.length-1] + " ");
            int[] newArr = new int[arr.length-1];
            for (int i = 0; i < newArr.length; i++) {
                newArr[i] = arr[i];
            }
            mystery(newArr);
        }
    }
    public static void mysteryIteration(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void mysteryArrayList(ArrayList<Integer> arr) {
        if (arr.size() == 1) {
            System.out.println(arr.get(0));
        } else {
            System.out.print(arr.get(arr.size() - 1) + " ");
            ArrayList<Integer> newArr = new ArrayList<Integer>();
            for (int i = 0; i < arr.size() - 1; i++) {
                newArr.add(arr.get(i));
            }
            mysteryArrayList(newArr);
        }
    }
    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int left[] = new int[n1];
        int right[] = new int[n2];
        for(int i = 0; i < n1; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[m + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    public static void merge(ArrayList<Integer> arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        for (int i = 0; i < n1; i++) {
            left.add(arr.get(l + i));
        }
        for (int j = 0; j < n2; j++) {
            right.add(arr.get(m + 1 + j));
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k, left.get(i));
                i++;
            } else {
                arr.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, right.get(j));
            j++;
            k++;
        }
    }
    public static void sort(ArrayList<Integer> arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    public static int binarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                return binarySearch(arr, left, mid - 1, x);
            } else {
                return binarySearch(arr, mid + 1, right, x);
            }
        }
        return -1;
    }
}
