import java.*;

/**
 * Simple test class
 */
public class FirstTest extends Testt
{
    /**
     * An example static value
     */
    public final static int  staticValue = 5;

    /**
     * Value A
     */
    int aValue;

    /**
     * Value B
     */
    int bValue;

    /**
     * A simple static method that prints two integer value
     *
     * @param a  The first value
     * @param b  The second value
     * @return  None
     */
    public static void static_method1( int a, int b )
    {
        System.out.println( a );
        System.out.println( b );
    }

    /**
     * The main entry point
     *
     * @param argv  Command line arguments
     * @return  None
     */
    public static void main( String argv[] )
    {
        static_method1( 10, 20 );
    }
}