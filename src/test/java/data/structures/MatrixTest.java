package data.structures;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatrixTest {

    @Test
    public void populate()  {
        // Given
        Matrix matrix = new Matrix();

        // When
        matrix.add("foo", "bar", 5);

        // Then
        assertThat(matrix.get("foo", "bar"), is(5));
    }

    @Test
    public void populateAndIncrementValue()  {
        // Given
        Matrix matrix = new Matrix();

        // When
        matrix.add("foo", "bar", 5);
        matrix.add("foo", "bar", 2);

        // Then
        assertThat(matrix.get("foo", "bar"), is(7));
    }

    @Test
    public void display()    {
        // Given
        Matrix matrix = new Matrix();

        // When
        matrix.add("alpha", "red", 1);
        matrix.add("alpha", "green", 2);
        matrix.add("alpha", "blue", 3);
        matrix.add("beta", "green", 4);
        matrix.add("beta", "green", 5);
        matrix.add("gamma", "blue", 3);

        // Then
        matrix.displayAsText();

    }

    @Test
    public void grow()  {
        // Given
        Matrix matrix = new Matrix(1, 1);

        // When
        // When
        matrix.add("r1", "c1", 1);
        matrix.add("r1", "c2", 2);
        matrix.add("r1", "c3", 3);
        matrix.add("r2", "c2", 4);
        matrix.add("r2", "c2", 6);
        matrix.add("r3", "c3", 3);

        // Then
        assertThat(matrix.get("r2", "c2"), is(10));
    }

    @Test
    public void ignoreCaseAndTrim()  {
        // Given
        Matrix matrix = new Matrix();

        // When
        // When
        matrix.add("foo", "bar", 1);
        matrix.add("Foo  ", "Bar", 2);
        matrix.add("FOO", "bar", 3);
        matrix.add(" foo", "BAR", 4);
        matrix.add(" FOO ", "BAR", 5);

        // Then
        assertThat(matrix.get("foo", "bar"), is(15));
        assertThat(matrix.get("FOO", " BAR "), is(15));
    }

    @Test
    public void aggregates()  {
        // Given
        Matrix matrix = new Matrix(1, 1);



        // When
        matrix.add("r1", "c1", 1);
        matrix.add("r1", "c2", 2);
        matrix.add("r1", "c3", 3);
        matrix.add("r1", "c3", 4);

        // Then
        assertThat(matrix.getRowSum("r1"), is(10));
    }

}
