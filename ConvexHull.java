import java.util.*;
class ConvexHull {
    public static void main(String[] args) {
        int maxPoints = 70;
        double[] yVal = new double[maxPoints];
        //Both arrays are being intialised.
        double[] xVal = new double[maxPoints];
        int pointCount = loadPoints(yVal, xVal, maxPoints);
        //System.out.println("\n" + pointCount);

        if (checkDuplicates(pointCount, xVal, yVal)) return;
        computeConvexHull(pointCount, xVal, yVal);
    }

    public int[] conArray;

    static int loadPoints(double[] yVal, double[] xVal, int maxPoints) {
        Scanner ask = new Scanner(System.in);
        int numPoints = 0;
        int askVal = 1;
        int count = 0;
        System.out.println("Enter Values One-by-One please \n-------------------------------");
        while ((askVal >= 0) || (askVal <= 2 * maxPoints)) {
            System.out.print("X: ");
            int askValx = ask.nextInt();
            if (askValx >= 2 * maxPoints) {
                System.out.println("\nMaximum capacity of array has been reached");
                //To be used if the value has reached 2 times maxPoints.
                break;
            } else if (askValx < 0) {
                System.out.println("\nNegative");
                //Statement for negative values.
                break;
            } else {
                //Assigning the values inputted to the arrays.
                xVal[count] = askValx;
            }
            System.out.print("Y: ");
            int askValy = ask.nextInt();
            System.out.print("\n");
            if (askValy >= 2 * maxPoints) {
                System.out.println("\nMaximum capacity of array has been reached");
                //To be used if the value has reached 2 times maxPoints.
                break;
            } else if (askValy < 0) {
                System.out.println("\nNegative");
                //Statement for negative values.
                break;
            } else {
                //Assigning the values inputted to the arrays.
                yVal[count] = askValy;
                numPoints++;
            }
            count++;
        }
        return numPoints;
    }

    static boolean checkDuplicates(int pointCount, double xVal[], double yVal[]) {
        //System.out.println(pointCount);
        for (int i = pointCount - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if ((xVal[i] == xVal[j]) && (yVal[i] == yVal[j]) && i != j) {
                    System.out.println("ERROR - Duplicate Detected");
                    return true;
                }
            }
        }
        return false;
    }

    static void computeConvexHull(int pointCount, double xVal[], double yVal[]) {
        double m, c;
        for (int i = pointCount - 1; i >= 1; i--) {
            for (int j = 0; j < pointCount; j++) {
                int above = 0;
                int below = 0;
                double pointXi = xVal[i];
                double pointXj = xVal[j];
                double pointYi = yVal[i];
                double pointYj = yVal[j];
                m = (pointYi - pointYj) / (pointXi - pointXj);
                double pointXk, pointYk;

                if ((m != Double.POSITIVE_INFINITY) || (m != Double.NEGATIVE_INFINITY)) {
                    for (int k = 0; k < pointCount; k++) {
                        c = pointYi - (m * pointXi);
                        pointXk = xVal[k];
                        pointYk = yVal[k];
                        //System.out.println("(" + pointXk + " , " + pointYk + ")");
                        if (pointYk > ((m * pointXk) + c)) {
                            above++;
                        } else if (pointYk < ((m * pointXk) + c)) {
                            below++;
                        }
                    }
                } else {
                    for (int k = 0; k < pointCount; k++) {
                        c = pointXi;
                        pointXk = xVal[k];
                        pointYk = yVal[k];
                        if (pointXk > c) {
                            above++;
                        } else if (pointXk < c) {
                            below++;
                        }

                    }
                }
                if (((above == 0) && (below > 0)) || (below == 0) && (above > 0)) {
                    System.out.print("\nEdges: \n(" + (int) pointXi + " , " + (int) pointYi + ") ------>");
                    System.out.print(" (" + (int) pointXj + " , " + (int) pointYj + ")");
                }
            }
        }
    }

}