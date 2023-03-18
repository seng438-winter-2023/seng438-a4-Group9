/**
 *
 */
package org.jfree.data.range.test;

import static org.junit.Assert.*;
import org.jmock.Expectations;
import org.jmock.Mockery;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Saman Pordanesh
 * @author Thevin Mahawatte
 *
 */
public class RangeTest  {

    private Range exampleRange;
    private class RangeTestClass extends Range {
        public RangeTestClass(double lower, double upper) {
            super(lower, upper);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-2, 3);
    }

    // combine()--------------------------------
    /**
     * This method will be testing the coverage of the combine method
     * there will be a test of the combined range of two different ranges 
     */
    @Test //
    public void testCombineWithBothRangesAsNullTest() {
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combine( range1, range2);
        assertNull("We are combining two null ranges. Result should be null.", result);
    }


    /**
     * This method will test the combined range with the first range being null. 
     * The range will be tested within the whole program
     */
    @Test //
    public void testCombineWithRange1AsNullTest() {
        Range range1 = null;
        Range range2 = new Range(0, 10);
        Range result = Range.combine(range1, range2);
        assertEquals("We are combining one null range and one non-null one. Result should be 0-10",range2, result);
    }

    /**
     * This method will be testing the combined range of two ranges where the second one will be null 
     */
    @Test 
    public void testCombineWithRange2AsNullTest() {
        Range range1 = new Range(0, 10);
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertEquals("We are combining one null range and one non-null one. Result should be 0-10",range1, result);
    }

    /**
     * This test will be checking the combination of two ranges where they are both not null 
     */
    @Test //
    public void testCombineWithBothRangesNotNullTest() {
        Range range1 = new Range(0, 5);
        Range range2 = new Range(5, 10);
        Range expectedResult = new Range(0, 10);
        Range result = Range.combine(range1, range2);
        assertEquals("We are combining two non-null ranges. Result should be 0-10", expectedResult, result);
    }

    @Test
    public void combineRange1NullTest() {
        assertEquals("The range is -3 to 2",
                exampleRange, Range.combine(null, exampleRange));
    }

    @Test
    public void combineRange2NullTest() {
        assertEquals("The range is -3 to 2",
                exampleRange, Range.combine(exampleRange, null));
    }

    @Test
    public void combineRightTest() {
        Range R = new Range(1, 3);
        Range C = new Range(-2, 3);
        assertEquals("The range is -3 to 3",
                C, Range.combine(exampleRange, R));
    }

    // constrain()---------------------------------

    @Test //
    public void constrainTestTest() {
        assertEquals("Expected output is equal to the input since 0.1 falls within the defined range.",0.1,exampleRange.constrain(0.1), .000000001d);
    }

    /**
     * The method will be used to check the constrain between two ranges.
     * 
     */
    @Test //
    public void constrainBetweenTest() {
        assertEquals("Expected output: 2",
                2 , exampleRange.constrain(2), .000000001d);
    }

    /**
     * This method will be testing the upper bounds of the ranges to be contstrained for a set number. 
     * in this case it will be 4. 
     *
     */
    @Test //
    public void constrainUpperBoundTest() {
        assertEquals("Expected output: 3",
                3 , exampleRange.constrain(3), .000000001d);
    }

    /**
     * This method will be checking the constrains of the lower bound of the range. 
     * There will be restricted to a lower bound specified in the constrain argument.  
     * 
     */
    @Test //
    public void constrainLowerBoundTest() {
        assertEquals("Expected output: -2",
                -2 , exampleRange.constrain(-3), .000000001d);
    }

    // contains()--------------------------------
    // this test will be used to check if the range contains a specified value that is set in the argument. in this case it is 0.1
    @Test //
    public void containsTest() {
        Range exampleRange;
        exampleRange = new Range(-2, 3);
        assertTrue("Expected output is True as 0.1 falls within the defined range.", exampleRange.contains(0.1));
    }

    @Test //
    public void containsShouldBeFalseLowerTest() {
        assertFalse("The answer should be false",
                exampleRange.contains(-5));
    }

    @Test //
    public void containsShouldBeFalseUpperTest() {
        assertFalse("The answer should be false",
                exampleRange.contains(6.3));
    }

    @Test //
    public void containsShouldBeTrueLowerBoundTest() {
        assertTrue("The answer should be true",
                exampleRange.contains(-2));
    }

    @Test //
    public void containsShouldBeTrueUpperBoundTest() {
        assertTrue("The answer should be true",
                exampleRange.contains(3));
    }

    @Test //
    public void containsShouldBeTrueBetweenTest() {
        assertTrue("The answer should be true",
                exampleRange.contains(0));
    }

    @Test //
    public void containsCorrectTest() {
        assertTrue("The answer should be true",
                exampleRange.contains(1));
    }


    // equals()---------------------------------

    /**
     * This method will be used to test if the range equals a certain value 
     */
    @Test //
    public void testEquals_withEqualObjects_returnsTrueTest() {
        java.lang.Object obj1 = new Object();
        java.lang.Object obj2 = new Object();
        assertTrue("The two object are equal, so we should receive True here.", obj1.equals(obj2));
    }

    /**
     * This method will be used to test if a false boolean is returned from two ranges that are not equal to eachother 
     */
    @Test //
    public void testEquals_withNonEqualObjects_returnsFalseTest() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertFalse("The two object aren't equal, so we should receive False here.", obj1.equals(obj2));
    }

    /**
     * This method will be used to check for a false return between an object that is null 
     */
    @Test //
    public void testEquals_withNullObject_returnsFalseTest() {
        Object obj = new Object();
        assertFalse("We are comparing an object with null, so expectation is false", obj.equals(null));
    }

    /**
     * This method will be used to check if one range is not equal to a lower bound of another range 
     */
    @Test //
    public void equalsIsLowerTest() {
        Range newRange = new Range(1,2);
        assertFalse("The lower bound of R is not equal to exampleRange lower bound",
                exampleRange.equals(newRange));
    }

    /**
     * This method will be used to check if there is a range that does not contain a specified value
     */
    @Test //
    public void equalsIsNotInRangeTest() {
        assertFalse("The Object is not a Range",
                exampleRange.equals("COVID-19"));
    }

    /**
     * This method will be used to check if there is a specific range that 
     */
    @Test //
    public void equalsIsUpperTest() {
        Range newRange = new Range(-2,6);
        assertFalse("The upper bound of R is not equal to exampleRange upper bound",
                exampleRange.equals(newRange));
    }

    /**
     * This method will be used to check if there is a true return statement that is returned from 
     * two ranges that are equal. 
     */
    @Test //
    public void equalsIsTrueTest() {
        Range newRange = new Range(-2,3);
        assertTrue("Both Ranges of exampleRange and R are equal",
                exampleRange.equals(newRange));
    }

    @Test
    public void equalsIsNotRangeTest() {
        assertFalse("The Object is not a Range",
                exampleRange.equals("Fluminense"));
    }


    @Test
    public void equalsTrueTest() {
        Range R = new Range(-2,3);
        assertTrue("Both Ranges of exampleRange and R are equal",
                exampleRange.equals(R));
    }


    //expand()------------------------------------
    /**
     * This method will be used to test the expand method of the ranges. 
     * It should be returning the expanded ranges. 
     */
    @Test //
    public void testExpandWithValidInputs1Test() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0.25, 0.5);
        assertEquals("The current range is 2-6, and expanded range should be 1-8. Here we are testing the correctness of the lower bound after expansion", 1, expandedRange.getLowerBound(), .000000001d);
    }

    /**
     * This method will be used to check if there are ranges that will be expanded 
     * with two inputs that are both valid 
     */
    @Test //
    public void testExpandWithValidInputs2Test() {
        Range range = new Range(2, 6);
        Range expandedRange = Range.expand(range, 0.25, 0.5);
        assertEquals("The current range is 2-6, and expanded range should be 1-8. Here we are testing the correctness of the lower bound after expansion.", 8, expandedRange.getUpperBound(), .000000001d);
    }


    //expandToInclude()----------------------------

    /**
     * This method will be used to check if the ranges can be 
     */
    @Test //
    public void testExpandToIncludeTest() {
        Range range = new Range(0, 10);
        Range expandedRange = Range.expandToInclude(range, 12);
        assertTrue("Number 13 should be in the range now.", range.contains(12));
    }

    /**
     * Expand the given range to include the value given to the mathod, expandToInclude
     */
    @Test //
    public void expandToIncludeValueLessThanLowerTest() {
        Range newRange = new Range(-4,3);
        assertEquals("The range should be -4 to 3",
                newRange, Range.expandToInclude(exampleRange, -4));
    }

    /**
     * Expand the given range to include the value given to the mathod, expandToInclude that is lower than higher boundary
     */
    @Test //
    public void expandToIncludeValueMoreThanUpperTest() {
        Range newRange = new Range(-2,4);
        assertEquals("The range should be -2 to 4",
                newRange, Range.expandToInclude(exampleRange, 4));
    }

    /**
     * The value given to the mathod, expandToInclude will be equal to the upper boundary
     */
    @Test //
    public void expandToIncludeEqualTest() {
        assertEquals("The range should be -3 to 2",
                exampleRange, Range.expandToInclude(exampleRange, 2));
    }

    /**
     * The value given to the mathod, expandToInclude will be equal to both upper and lower boundary
     */
    @Test //
    public void expandToIncludeRangeNullTest() {
        Range newRange = new Range(2,2);
        assertEquals("The range should be 2 to 2",
                newRange, Range.expandToInclude(null, 2));
    }

    @Test
    public void expandRangeTest() {
        Range newRange = new Range(18,23);
        assertEquals("The range should be 18 to 23", newRange, Range.expand(exampleRange, -4, 4));
    }


    // getCentralValue()---------------------------

    /**
     * This method returns the central value of the given range
     */
    @Test //
    public void testGetCentralValueTest()
    {
        Range exampleRange;
        exampleRange = new Range(-2, 2);
        double actual = exampleRange.getCentralValue();
        assertEquals("The expected central value is 0",0,actual, .000000001d);
    }

    @Test
    public void getCentralValueShouldBeZeroPointFiveTest() {
        assertEquals("The central value of -2 and 3 should be 0.5",
                0.5, exampleRange.getCentralValue(), .000000001d);
    }


    // getLength()---------------------------------

    /**
     * This method return the length of the given range
     */
    @Test //
    public void getLengthTest() {
        assertEquals("The expected length between lower-bound and upper-bound is 5",5,exampleRange.getLength(), .000000001d);
    }


     /**
     * This method return the length of the given range, na dwith given range being (0, 0), legth should also be 0
     */
    @Test //
    public void getLengthShouldBeZeroTest() {
        Range newRange = new Range(0,0);
        assertEquals("The expected length between lower-bound and upper-bound is 5", 0, newRange.getLength(), .000000001d);
    }

    @Test
    public void getLengthShouldBeFiveTest() {
        assertEquals("The central value of -2 and 3 should be 5",5, exampleRange.getLength(), .000000001d);
    }

    // getLowerBound()-------------------------------
    /**
     * This method return the lower bound of the given range
     */
	 @Test //
    public void getLowerBoundTest() {
        assertEquals("The expected upper bound of the range is 3",3,exampleRange.getLowerBound(), .000000001d);
    }

    @Test //
    public void getLowerBoundShouldBeMinusTwoTest() {
        assertEquals("The lower bound value of -2 and 3 should be -2", -2, exampleRange.getLowerBound(), .000000001d);
    }

    @Test //
    public void getLowerBoundShouldBeOneTest() {
        Range newRange = new Range(1,5);
        assertEquals("The lower bound value of 1 and 5 should be 1", 1, newRange.getLowerBound(), .000000001d);
    }

    @Test //
    public void LowerBiggerThanUpperTest() {
        try {
            Range newRange = new Range(3, 1);
            assertTrue("The value is bigger than the lower bound", newRange.getUpperBound() > newRange.getLowerBound());
        } catch(Exception e) {
            throw e;
        }
    }


    // getUpperBound()-------------------------------
    /**
     * This method return the upper bound of the range
     */
	 @Test //
    public void getUpperrBoundTestTest() {
        Range exampleRange;
        exampleRange = new Range(-2, 4);
        assertEquals("The expected lower bound of the range is -2",-2,exampleRange.getUpperBound(), .000000001d);
    }

    @Test //
    public void getUpperBoundShouldBeThreeTest() {
        assertEquals("The upper bound value of -2 and 3 should be 3",
                3, exampleRange.getUpperBound(), .000000001d);
    }


    // hashCode()-------------------------------------
	
	/**
     * This method return the hashcode given to the each range
     */
    @Test //
    public void testRangeHashCodeTest()
    {
        int returnHashCode = hashCode();
        assertEquals("HashCode for the range -2, 4 should be 1", 1, returnHashCode);
    }

    @Test //
    public void hashCodeTestTest() {
        assertEquals("The hash code is 524288",
                524288 , exampleRange.hashCode());
    }


    // intersect()-----------------------------------
    /**
     * This method checks if two ranges intersects between each other
     */
	@Test //
    public void testRangeIntersectTest() {
        Range exampleRange;
        exampleRange = new Range(-2, 4);
        assertTrue("The intersection between (-2, 4) and (3, 10) should be true.", exampleRange.intersects(3, 10));
    }

    /**
     * This method checks if two ranges intersects between each other
     */
	 @Test //
    public void intersectsShouldBeTrueOnLowerTest() {
        assertTrue("The lines intersect with each other",
                exampleRange.intersects(-3, 0));
    }

    /**
     * This method checks if two ranges intersects between each other
     */
	 @Test //
    public void intersectsShouldBeTrueOnMiddleTest() {
        assertTrue("The lines intersect with each other",
                exampleRange.intersects(1, 2));
    }

    /**
     * This method checks if two ranges intersects between each other
     */
	 @Test //
    public void intersectsShouldBeTrueOnUpperTest() {
        assertTrue("The lines intersect with each other",
                exampleRange.intersects(2, 6));
    }

    @Test //
    public void intersectsShouldBeTrueLowerOnUpperTest() {
        assertTrue("The lines intersect with each other",
                exampleRange.intersects(-4, 4));
    }

    @Test //
    public void intersectsShouldBeFalseOnLowerTest() {
        assertFalse("The lines should not intersect with each other",
                exampleRange.intersects(-7, -4));
    }

    @Test //
    public void intersectsShouldBeFalseOnUpperTest() {
        assertFalse("The lines should not intersect with each other",
                exampleRange.intersects(4, 6));
    }

    @Test //
    public void intersectsShouldBeTrueForLowerTest() {
        assertTrue("The lines should intersect",
                exampleRange.intersects(-3, 0));
    }

    @Test //
    public void intersectsShouldBeTrueForMiddleTest() {
        assertTrue("The lines should intersect",
                exampleRange.intersects(1, 2));
    }

    @Test //
    public void intersectsShouldBeTrueForUpperTest() { // This test case is not working
        assertTrue("The lines should intersect",
                exampleRange.intersects(2, 6));
    }

    @Test //
    public void intersectsShouldBeTrueLowerForUpperTest() {
        assertTrue("The lines should intersect",
                exampleRange.intersects(-4, 4));
    }

    @Test //
    public void intersectsShouldBeFalseForLowerTest() { // This test case is not working
        assertFalse("The lines should not intersect",
                exampleRange.intersects(-7, -4));
    }

    @Test //
    public void intersectsShouldBeFalseForUpperTest() {
        assertFalse("The lines should not intersect",
                exampleRange.intersects(4, 6));
    }

    @Test //
    public void intersectsWithBooleanTest() {
        Range R = new Range(0,1);
        assertTrue("The lines intersect",
                exampleRange.intersects(R));
    }

    @Test //
    public void intersectsWithBoolean2Test() {
        Range R = new Range(0,1);
        assertTrue("The lines intersect",
                exampleRange.intersects(R));
    }


    // shift()---------------------------------------
    /**
     * This method checks if shifting lower boundry of the range move pass 0, and provides error message if so
     */
	 @Test //  - Zero Crossing is not alowed
    public void testShiftNotAllowZeroCrossingLowerBoundTest()
    {
        Range base;
        base = new Range(-5.0, 5.0);
        double delta = 2.1;
        Range returnRange = Range.shift(base, delta);
        assertEquals("Lower bound of range after shifted from -5.0 by 2.1 to the right should -2.9", returnRange.getLowerBound(), base.getLowerBound(), .000000001d);
    }

    @Test  //  - Zero Crossing is not alowed
    public void testShiftNotAllowZeroCrossingUpperBoundTest()
    {
        Range base;
        base = new Range(-5.0, 5.0);
        double delta = 2.1;
        Range returnRange = Range.shift(base, delta);
        assertEquals("Upper bound of range after shifted from 5.0 by 2.1 to the right should 7.1", returnRange.getUpperBound(), base.getUpperBound(), .000000001d);
    }

    @Test  //  - Zero Crossing is alowed
    public void testShiftAllowZeroCrossingLowerBoundTest()
    {
        Range base;
        base = new Range(-5.0, 5.0);
        double delta = 5.1;
        boolean allowZeroCrossing = true;
        Range returnRange = Range.shift(base, delta, allowZeroCrossing);
        assertEquals("Lower bound of range after shifted from -5.0 by 2.1 to the right should 0.1", returnRange.getLowerBound(), base.getLowerBound(), .000000001d);
    }

    @Test  //  - Zero Crossing is alowed
    public void testShiftAllowZeroCrossingUpperBoundTest()
    {
        Range base;
        base = new Range(-5.0, 5.0);
        double delta = 5.1;
        boolean allowZeroCrossing = true;
        Range returnRange = Range.shift(base, delta, allowZeroCrossing);
        assertEquals("Upper bound of range after shifted from 5.0 by 2.1 to the right should 10.1", returnRange.getUpperBound(), base.getUpperBound(), .000000001d);
    }

    @Test //
    public void shiftIsTrueTest() {
        Range R = new Range(0, 5);
        assertEquals("The range should be 0 to 5",
                R, Range.shift(exampleRange, 2, true));
    }

    @Test //
    public void shiftIsFalseTest() {
        Range R = new Range(0, 5);
        assertEquals("The range should be 0 to 5",
                R, Range.shift(exampleRange, 2, false));
    }

    @Test //
    public void shiftTwoArgsTest() {
        Range R = new Range(0, 5);
        assertEquals("The range should be -1 to 5",
                R, Range.shift(exampleRange, 2));
    }


    // toString()------------------------------------
    /**
     * This method convert range to a specific format string
     */
	 @Test //
    public void testRangeToStringTest()
    {
        Range exampleRange;
        exampleRange = new Range(-3.0, 2.0);
        String rangeString = "Range[-3.0,2.0]";
        assertEquals("Range using toString function should be Range[-3.0,2.0]", rangeString, exampleRange.toString());
    }

    @Test //
    public void toStringNegativeByPositiveTest() {
        assertEquals("The answer should be 'Range[-2.0,3.0]'",
                "Range[-2.0,3.0]" , exampleRange.toString());
    }

    @Test //
    public void toStringPositiveByPositiveTest() {
        Range newRange = new Range(3, 4.3);
        assertEquals("The answer should be 'Range[2.0,4.2]'",
                "Range[3.0,4.3]" , newRange.toString());
    }

    @Test //
    public void toStringNegativeByNegativeTest() {
        Range R = new Range(-3.3, -2.2);
        assertEquals("The answer should be 'Range[-3.3,-2.2]'",
                "Range[-3.3,-2.2]" , R.toString());
    }


    // Test non documented functions ******************


    //scale()------------------------
    @Test //
    public void scaleWithPositiveTest() {
        Range newRange = new Range(-5,5);
        assertEquals("The range should be -5 to 5",
                newRange, Range.scale(exampleRange, 2));
    }

    @Test //
    public void scaleWithNegativeTest() {
        try {
            assertEquals("The factor should be negative",
                    null, Range.scale(exampleRange, -2));
        } catch(Exception e) {
            throw e;
        }
    }

    //isNaNRange() ---------------------------
    @Test //
    public void isNaNRangeTrueTest() {
        assertFalse("The range should be NaN",
                exampleRange.isNaNRange());
    }

    //---------------------------
    @Test //
    public void combineWithIgnoringNaNNullNullTest() {
        assertEquals("The returned value should be null",
                null, Range.combineIgnoringNaN(null, null));
    }

    @Test //
    public void combineWithIgnoringNaNNullNotNullTest() {
        Range R = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value should be a range of NaN",
                null, Range.combineIgnoringNaN(null, R));
    }

    @Test //
    public void combineWithIgnoringNaNNotNullNullTest() {
        Range R = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value should be a range of NaN",
                null, Range.combineIgnoringNaN(R, null));
    }

    @Test //
    public void combineWithIgnoringNaNNumberNullTest() {
        assertEquals("The returned value should be a range of -3 to 2", exampleRange, Range.combineIgnoringNaN(exampleRange, null));
    }

    @Test //
    public void combineWithIgnoringNaNNumberNumbersTest() {
        Range newRange = new Range(0,3);
        Range newRange2 = new Range(-3,3);
        assertEquals("The returned value should be a range of -3 to 3", newRange, Range.combineIgnoringNaN(exampleRange, newRange2));
    }

    @Test //
    public void combineWithIgnoringNaNNaNNaNTest() {
        Range R = new Range(Double.NaN,Double.NaN);
        Range S = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value should be a range of NaN",
                null, Range.combineIgnoringNaN(R, S));
    }
}
