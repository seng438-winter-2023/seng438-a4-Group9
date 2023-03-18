package org.jfree.data.datautilities.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;


public class DataUtilitiesTest extends DataUtilities {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    // -------------------------------------------------

    /**
     * Tests the functionality of the calculateColumnTotal method.
     * Verifies that the method returns the correct sum of the values in one column of the supplied data table.
     * @param data the table of values (null not permitted)
     * @param column the column index (zero-based)
     * @param expectedTotal the expected sum of the values in the specified column
     * @throws InvalidParameterException if invalid data object is passed in
     */
    @Test //
    public void calculateColumnTotalTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(2));
                one(values).getColumnCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(10));
                one(values).getValue(1, 0); will(returnValue(20));
                one(values).getValue(0, 1); will(returnValue(30));
                one(values).getValue(1, 1); will(returnValue(40));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The sum of all elements in the column 0 should be 30", 30, result, .000000001d);
    }

    /**
     *Tests the functionality of the calculateRowTotal method.
     *Verifies that the method returns the correct sum of the values in one row of the supplied data table.
     *@throws InvalidParameterException if invalid data object is passed in
     */
    @Test //
    public void calculateColumnTotalForFourValuesTestTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(4));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }

    /**
     * Tests the calculateColumnTotal method with invalid input.
     * Verifies that the method returns a total of zero when an invalid data object is passed in.
     */
//    @Test //
//    public void calculateColumnTotalWithInvalidInputTest() {
//
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//        mockingContext.checking(new Expectations() {
//            {
//                one(values).getRowCount();will(returnValue(4));
//                one(values).getValue(0, 0);will(returnValue(7.5));
//                one(values).getValue(1, 0);will(returnValue(2.5));
//                one(values).getValue(2, 0);will(returnValue(-3));
//                one(values).getValue(3, 0);will(returnValue(-2));
//            }
//        });
//        double result = DataUtilities.calculateColumnTotal(values, -1);
//        assertEquals(0.0, result, .000000001d);
//    }

    /**
     * Tests the calculateColumnTotal method with valid rows specified.
     * Verifies that the method returns the correct sum of the values in one column of the supplied data table
     * when valid rows are specified.
     */
    @Test //
    public void testCalculateColumnTotalWithValidRowsSpecifiedTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(7));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {0,1,2,4};
        double result = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(14.0, result, .000000001d);
    }

    /**
     * Tests the calculateColumnTotal method with invalid rows specified.
     * Verifies that the method returns a total of zero when invalid row
     * indices (out of bounds) are specified.
     */
    @Test //
    public void testCalculateColumnTotalWithInvalidRowsTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(7));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {10,11,12,14};
        double result = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(0.0, result, .000000001d);
    }

    /**
     * Tests the calculateColumnTotal method with valid rows and null values in the specified column.
     * Verifies that the method returns a total of zero when the specified column contains null values.
     */
    @Test //
    public void testCalculateColumnTotalWithValidRowsNullValuesTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(null));
                one(values).getValue(1, 0);will(returnValue(null));
                one(values).getValue(2, 0);will(returnValue(null));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(null));
                one(values).getValue(5, 0);will(returnValue(-3.5));
            }
        });
        int[] validrows = {0,1,2,4};
        double result = DataUtilities.calculateColumnTotal(values, 0, validrows);
        assertEquals(0.0, result, .000000001d);
    }

    /**
     * Tests the calculateColumnTotal method with null values in the data table.
     * Verifies that the method returns a total of zero when null is passed in as the data object.
     */
    @Test //
    public void testCalculateColumnTotalWithNullValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();will(returnValue(6));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(1, 0);will(returnValue(2.5));
                one(values).getValue(2, 0);will(returnValue(-3));
                one(values).getValue(3, 0);will(returnValue(-2));
                one(values).getValue(4, 0);will(returnValue(null));
                one(values).getValue(5, 0);will(returnValue(null));
            }

        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }


    /**
     * Tests the calculateColumnTotal method with a data table containing four values.
     * Verifies that the method correctly calculates the total of the specified column for a valid data table input.
     */
    @Test //
    public void calculateColumnTotalForFourValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(4));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(-3));
                one(values).getValue(3, 0);
                will(returnValue(-2));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }

    /**
     * Tests the calculateColumnTotal method with invalid parameter passed in.
     * Verifies that the method throws an InvalidParameterException when a null data object is passed in.
     * @throws InvalidParameterException if invalid data object is passed in
     */
//    @Test(expected = InvalidParameterException.class) //
//    public void calculateColumnTotalWithInvalidParameterTest() throws InvalidParameterException {
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//        mockingContext.checking(new Expectations() {
//            {
//                one(values).getRowCount();
//                will(returnValue(null));
//                one(values).getColumnCount();
//                will(returnValue(null));
//            }
//        });
//        double result = DataUtilities.calculateColumnTotal(values, 0);
//        assertEquals(0, result, .000000001d);
//    }

    /**
     * Tests the calculateRowTotal method with invalid parameter passed in.
     * Verifies that the method throws an InvalidParameterException when a null data object is passed in.
     * @throws InvalidParameterException if invalid data object is passed in
     */
//    @Test(expected = InvalidParameterException.class) //
//    public void calculateRowTotalWithInvalidParameterTest() throws InvalidParameterException {
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//        mockingContext.checking(new Expectations() {
//            {
//                one(values).getRowCount();
//                will(returnValue(null));
//                one(values).getColumnCount();
//                will(returnValue(null));
//            }
//        });
//        double result = DataUtilities.calculateRowTotal(values, 0);
//        assertEquals(0, result, .000000001d);
//    }

    //-----------------------------------
    /**
	* This method test the total of a given row
	*/
	@Test //
    public void calculateRowTotalTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(2));
                one(values).getColumnCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(10));
                one(values).getValue(1, 0); will(returnValue(20));
                one(values).getValue(0, 1); will(returnValue(30));
                one(values).getValue(1, 1); will(returnValue(40));
            }
        });

        double result = DataUtilities.calculateRowTotal(values, 0);
        double expected = 40;
        assertEquals("The sum of all elements in the row 0 should be 40", expected, result, .000000001d);
    }

    /**
	* This method test the total of a given row with and invalid row number
	*/
//	@Test //
//    public void calculateRowTotalWithInvalidInputTest() {
//
//        Mockery mockingContext = new Mockery();
//        final Values2D values = mockingContext.mock(Values2D.class);
//        mockingContext.checking(new Expectations() {
//            {
//                one(values).getColumnCount();will(returnValue(4));
//                one(values).getValue(0, 0);will(returnValue(7.5));
//                one(values).getValue(0, 1);will(returnValue(2.5));
//                one(values).getValue(0, 2);will(returnValue(-3));
//                one(values).getValue(0, 3);will(returnValue(-2));
//            }
//        });
//        double result = DataUtilities.calculateRowTotal(values, -1);
//        assertEquals(0.0, result, .000000001d);
//    }

    /**
	*
	*/
	@Test //
    public void calculateRowTotalForFourValuesTest() {

        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();will(returnValue(4));
                one(values).getValue(0, 0);will(returnValue(7.5));
                one(values).getValue(0, 1);will(returnValue(2.5));
                one(values).getValue(0, 2);will(returnValue(-3));
                one(values).getValue(0, 3);will(returnValue(-2));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(5.0, result, .000000001d);
    }

    /**
	* This method test the row total with null values
	*/
	@Test //
    public void calculateRowTotalWithNullValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(4));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(2.5));
                one(values).getValue(0, 2);
                will(returnValue(null));
                one(values).getValue(0, 3);
                will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(10.0, result, .000000001d);
    }

    /**
	* This method tests the row total with valid columns
	*/
	@Test //
    public void calculateRowTotalWithValidColumnsTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(6));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(2.5));
                one(values).getValue(0, 2);
                will(returnValue(-3));
                one(values).getValue(0, 3);
                will(returnValue(-2));
                one(values).getValue(0, 4);
                will(returnValue(5));
                one(values).getValue(0, 5);
                will(returnValue(6));
            }
        });
        int[] validcolumns = {0,2,3,5};
        double result = DataUtilities.calculateRowTotal(values, 0, validcolumns);
        assertEquals(8.5, result, .000000001d);
    }

    /**
	* This method tests the row total with valid columns with null values
	*/
	@Test //
    public void calculateRowTotalWithValidColumnsNullValuesTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(6));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(2.5));
                one(values).getValue(0, 2);
                will(returnValue(null));
                one(values).getValue(0, 3);
                will(returnValue(null));
                one(values).getValue(0, 4);
                will(returnValue(5));
                one(values).getValue(0, 5);
                will(returnValue(6));
            }
        });
        int[] validcolumns = {0,2,3,5};
        double result = DataUtilities.calculateRowTotal(values, 0, validcolumns);
        assertEquals(13.5, result, .000000001d);
    }

    /**
	*
	*/
	@Test //
    public void calculateRowTotalWithInValidColumnsTest() {
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(6));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(2.5));
                one(values).getValue(0, 2);
                will(returnValue(-3));
                one(values).getValue(0, 3);
                will(returnValue(-2));
                one(values).getValue(0, 4);
                will(returnValue(5));
                one(values).getValue(0, 5);
                will(returnValue(6));
            }
        });
        int[] validcolumns = {10,22,13,15};
        double result = DataUtilities.calculateRowTotal(values, 0, validcolumns);
        assertEquals(0, result, .000000001d);

    }


    // ---------------------------------------------------
    @Test
    public void createNumberArrayTestTest() {
        java.lang.Number[] expected = new java.lang.Number[] {10.0, 20.0};
        double[] data = new double[] {10.0, 20.0};
        assertEquals("The converted double array data to array of Number objects.", expected, DataUtilities.createNumberArray(data));
    }

//    @Test(expected = InvalidParameterException.class)
//
//    public void testCreateNumberArrayWithInvalidDataTest() throws InvalidParameterException {
//
//        double[] input = null;
//
//        DataUtilities.createNumberArray(input);
//
//    }

    @Test //
    public void testCreateNumberArrayTest() {
        double[] input = { 1, 2, 3, 4, 5 };
        Number[] expectedOutput = { 1.0, 2.0, 3.0, 4.0, 5.0 };
        Number[] actualOutput = DataUtilities.createNumberArray(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }


    //--------------------------------------------------------
    @Test //
    public void createNumberArray2DTestTest() {
        java.lang.Number[][] expected = new java.lang.Number[][] {{10.0, 20.0}, {30.0, 40.0}};;
        double[][] data = new double[][] {{10.0, 20.0}, {30.0, 40.0}};
        java.lang.Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals("The converted 2D double array data to 2D array of Number objects.", expected, result);
    }

//    @Test(expected = InvalidParameterException.class) //
//    public void testCreateNumberArray2DWithInvalidDataTest() throws InvalidParameterException {
//        double[][] input = null;
//        DataUtilities.createNumberArray2D(input);
//    }

    @Test //
    public void testCreateNumberArray2D() {
        double[][] input = { { 1, 2 }, { 3, 4 }, { 5, 9 } };
        Number[][] expectedOutput = { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 9.0 } };
        Number[][] actualOutput = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }


    //--------------------------------------------------------
//    @Test //
//    public void testGetCumulativePercentagesTest() {
//        Mockery context = new Mockery();
//        final KeyedValues data = context.mock(KeyedValues.class, "data");
//        final KeyedValues expectedResult = context.mock(KeyedValues.class, "expectedResult");
//
//        context.checking(new Expectations() {
//            {
//                allowing(data).getItemCount(); will(returnValue(3));
//                allowing(data).getValue(0); will(returnValue(5));
//                allowing(data).getValue(1); will(returnValue(9));
//                allowing(data).getValue(2); will(returnValue(2));
//                allowing(data).getKey(0); will(returnValue(0));
//                allowing(data).getKey(1); will(returnValue(1));
//                allowing(data).getKey(2); will(returnValue(2));
//                allowing(expectedResult).getItemCount(); will(returnValue(3));
//                allowing(expectedResult).getValue(0); will(returnValue(0.3125));
//                allowing(expectedResult).getValue(1); will(returnValue(0.875));
//                allowing(expectedResult).getValue(2); will(returnValue(1.0));
//                allowing(expectedResult).getKey(0); will(returnValue(0));
//                allowing(expectedResult).getKey(1); will(returnValue(1));
//                allowing(expectedResult).getKey(2); will(returnValue(2));
//            }
//        });
//
//        KeyedValues result = DataUtilities.getCumulativePercentages(data);
//        assertEquals("Cumulative percentages failed and the new KeyValues object contains wrong values.", expectedResult, result);
//        context.assertIsSatisfied();
//    }

    @Test //
    public void testGetCumulativePercentageTest() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getItemCount();
                will(returnValue(3));
                allowing(values).getKeys();
                will(returnIterator(0, 1, 2));
                allowing(values).getValue(0);
                will(returnValue(5));
                allowing(values).getValue(1);
                will(returnValue(9));
                allowing(values).getValue(2);
                will(returnValue(2));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getKey(2);
                will(returnValue(2));
            }
        });
        Mockery mocking = new Mockery();
        final KeyedValues expectedOutput = mocking.mock(KeyedValues.class);
        mocking.checking(new Expectations() {
            {
                one(expectedOutput).getItemCount();
                will(returnValue(3));
                one(expectedOutput).getKeys();
                will(returnIterator(0, 1, 2));
                one(expectedOutput).getValue(0);
                will(returnValue(0.3125));
                one(expectedOutput).getValue(1);
                will(returnValue(0.875));
                one(expectedOutput).getValue(2);
                will(returnValue(1.0));
            }
        });
        KeyedValues actualOutput = DataUtilities.getCumulativePercentages(values);
        assertEquals(expectedOutput.getValue(0), actualOutput.getValue(0));
        assertEquals(expectedOutput.getValue(1), actualOutput.getValue(1));
        assertEquals(expectedOutput.getValue(2), actualOutput.getValue(2));
    }

    @Test //
    public void testGetCumulativePercentageWithNullValuesTest() {
        Mockery mockingContext = new Mockery();
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getItemCount();
                will(returnValue(3));
                allowing(values).getKeys();
                will(returnIterator(0, 1, 2));
                allowing(values).getValue(0);
                will(returnValue(2));
                allowing(values).getValue(1);
                will(returnValue(null));
                allowing(values).getValue(2);
                will(returnValue(2));
                allowing(values).getKey(0);
                will(returnValue(0));
                allowing(values).getKey(1);
                will(returnValue(1));
                allowing(values).getKey(2);
                will(returnValue(2));
            }
        });
        Mockery mocking = new Mockery();
        final KeyedValues expectedOutput = mocking.mock(KeyedValues.class);
        mocking.checking(new Expectations() {
            {
                one(expectedOutput).getItemCount();will(returnValue(3));
                one(expectedOutput).getKeys();will(returnIterator(0, 1, 2));
                one(expectedOutput).getValue(0);will(returnValue(0.50));
                one(expectedOutput).getValue(1);will(returnValue(0.50));
                one(expectedOutput).getValue(2);will(returnValue(1.0));
            }
        });
        KeyedValues actualOutput = DataUtilities.getCumulativePercentages(values);
        assertEquals(expectedOutput.getValue(0), actualOutput.getValue(0));
        assertEquals(expectedOutput.getValue(1), actualOutput.getValue(1));
        assertEquals(expectedOutput.getValue(2), actualOutput.getValue(2));
    }

    // Non categorized tests---------


    /**
     * Tests the equal method with two equal arrays passed in.
     * Verifies that the method returns true when two equal arrays are compared.
     */
    @Test //
    public void testEqualWithAnEqualArraysTest() {
        double[][] first = {{2,2}, {5,4}, {-9, 10}};
        double[][] second = {{2,2}, {5,4}, {-9, 10}};
        boolean expected = true;
        boolean actual = DataUtilities.equal(first, second);
        assertEquals(expected, actual);
    }

    /**
     * Tests the equal method with two null arrays passed in.
     * Verifies that the method returns true when two null arrays are passed in, indicating that they are equal.
     */
    @Test //
    public void testEqualWithAnNullArraysTest() {
        double[][] firstArray = null;
        double[][] secondArray = null;
        boolean expected = true;
        boolean actual = DataUtilities.equal(firstArray, secondArray);
        assertEquals(expected, actual);
    }

    /**
     * Tests the equal method with one null and one non-null array passed in.
     * Verifies that the method returns false when one array is null and the other is not.
     */
    @Test //
    public void testEqualWithOneANullAndNonNullTest() {
        double[][] first = {{1,4}, {4,4}, {-2, 13}};
        double[][] second = null;
        boolean expected = false;
        boolean actual = DataUtilities.equal(first, second);
        assertEquals(expected, actual);
    }

    /**
     * Tests the equal method with arrays of different lengths.
     * Verifies that the method returns false when two arrays of different dimensions are passed in.
     */
    @Test //
    public void testEqualWithADifferentLengthArraysTest() {
        double[][] first = {{1,2}, {3,4}, {-9, 10}};
        double[][] second = {{2,4}};
        boolean expected = false;
        boolean actual = DataUtilities.equal(first, second);
        assertEquals(expected, actual);
    }

    /**
     * Tests the equal method with different arrays of the same length.
     * Verifies that the method returns false when comparing two arrays with different values.
     */
    @Test //
    public void testEqualWithSameLengthForDifferentArraysTest() {
        double[][] first = {{4,2}, {3,4}, {-8, 10}};
        double[][] second = {{4,2}, {3,5}, {-8, 10}};
        boolean expected = false;
        boolean actual = DataUtilities.equal(first, second);
        assertEquals(expected, actual);
    }

    /**
     * Tests the clone method with a valid array passed in.
     * Verifies that the method returns a clone of the specified array.
     */
    @Test //
    public void testCloneWithAValidArrayTest() {
        double[][] expected = {{1,2}, {3,4}, {-8, 11}};
        double[][] actual = DataUtilities.clone(expected);
        assertArrayEquals(expected, actual);
    }
}

