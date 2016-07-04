package data.structures;

import java.util.Arrays;

/**
 * Simple 2-dimensional matrix (rows x columns) with numerical values.
 *
 *
 * E.g.: 3 rows, 4 columns
 *
 *     | c1 c2 c3 c4
 *  ---------------
 *  r1 |  8  1  0  9
 *  r2 |  0  5  7  1
 *  r3 |  4  0  2  7
 *
 */

public class Matrix {


    private String[] rows, columns;
    private int[][] values;
    private static final int GROWTH_INCREMENT = 10;
    private static final int INITIAL_DIMENSION_SIZE = 10;

    public Matrix() {
        this(INITIAL_DIMENSION_SIZE, INITIAL_DIMENSION_SIZE);
    }

    public Matrix(int initialRowSize, int initialColumnSize) {
        if (initialRowSize <= 0)    {
            throw new IllegalArgumentException("Invalid initial row size: " + initialRowSize);
        }

        if (initialColumnSize <= 0)    {
            throw new IllegalArgumentException("Invalid initial column size: " + initialColumnSize);
        }

        rows = new String[initialRowSize];
        columns = new String[initialColumnSize];
        values = new int[rows.length][columns.length];
    }

    public void add(String row, String column, int value)   {
        this.addCell(row.trim().toLowerCase(), column.trim().toLowerCase(), value);
    }


    public void addCell(String row, String column, int value)   {
        boolean hasGrown = false;
        int rowIndex = getIndex(this.rows, row);
        int columnIndex = getIndex(this.columns, column);

        if (rowIndex < 0 )  {
            // System.out.println("Growing rows by: " + GROWTH_INCREMENT);
            this.rows = Arrays.copyOf(this.rows, this.rows.length + GROWTH_INCREMENT);
            rowIndex = getIndex(this.rows, row);
            hasGrown = true;
        }

        if (columnIndex < 0 )  {
            // System.out.println("Growing columns by: " + GROWTH_INCREMENT);
            this.columns = Arrays.copyOf(this.columns, this.columns.length + GROWTH_INCREMENT);
            columnIndex = getIndex(this.columns, column);
            hasGrown = true;
        }

        if (hasGrown)   {
            int[][] replacement = new int[this.rows.length][this.columns.length];
            // System.arraycopy( this.values, 0, replacement, 0, replacement.length );

            for(int i = 0; i < values.length; i++) {
                for (int j = 0; j < values[0].length; j++ ) {
                    replacement[i][j] = values[i][j];
                }
            }
            this.values = replacement;
        }

        rows[ rowIndex] = row;
        columns[ columnIndex ] = column;

        int existingvalue = values[rowIndex][columnIndex];
        values[rowIndex][columnIndex] = existingvalue + value;
    }

    public int get(String row, String column)   {
        return this.getCell(normalise(row), normalise(column));
    }

    public int getRowSum(String rowLabel)  {
        int count = 0;

        int index = this.getRowIndex(rowLabel.trim().toLowerCase());
        if (index < 0)  {
            return count;
        }

        for (int i = 0; i < this.values[index].length; i++) {
            count += this.values[index][i];
        }
        return count;
    }

    public String[] getRows()    {
        return getActiveArray(this.rows);
    }

    public String[] getColumns()    {
        return getActiveArray(this.columns);
    }

    private String[] getActiveArray(String[] sourceArray)    {
        int index = 0;
        while (null != sourceArray[index]) {
            index++;
        }
        return Arrays.copyOf(sourceArray, index);
    }

    private int getCell(String row, String column)   {
        int rowIndex = getIndex(this.rows, row);
        int columnIndex = getIndex(this.columns, column);

        return values[rowIndex][columnIndex];
    }

    public void displayAsText() {
        displayColumnsAsText();
        displayRowsAndDataAsText();
    }

    private void displayRowsAndDataAsText() {
        final String[] rows = this.getRows();
        for(int i = 0, j = 0; i < rows.length; i++)   {
            System.out.print(rows[i] + "\t");
            for(j = 0; j < this.columns.length && null != this.columns[j]; j++)   {
                System.out.print(this.values[i][j] + "\t\t\t");
            }
            System.out.println();
        }
    }

    private void displayColumnsAsText() {
        final String[] cols = this.getColumns();
        System.out.print("\t");
        for (int i = 0; i < cols.length; i++)    {
            System.out.print( "\t" + cols[i] + "\t");
        }
        System.out.println();
    }

    private int getNextAvailableIndex(String[] array) {
        for (int i = 0; i < array.length; i++)  {
            if (null == array[i])   {
                return i;
            }
        }
        // throw new ArrayIndexOutOfBoundsException();
        return -1;
    }

    private int getRowIndex(String label)   {
        return this.getIndex(this.rows, label);
    }

    private int getColumnIndex(String label)   {
        return this.getIndex(this.columns, label);
    }

    private int getIndex(String[] array, String label) {
        for (int i = 0; i < array.length; i++)  {
            if (label.equals( array[i] ))   {
                return i;
            }
        }

        return getNextAvailableIndex(array);
    }

    private String normalise(String value)    {
        if (value == null)   {
            throw new IllegalArgumentException("Invalid NULL value");
        }

        String str = value.trim();
        if ("".equals(str)) {
            throw new IllegalArgumentException("Invalid empty value");
        }
        return str.toLowerCase();
    }
}
