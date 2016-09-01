package mawashi.alex.basis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int style = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //##############################################################################################################
        /*Simple examples of the use of Generics Inner Class*/

        //creation of first instance: String and Integer
        OrderedPair<String, Integer> paio1 = new OrderedPair<>("Even", 8);

        //Creation of second instance: String and String
        OrderedPair<String, String>  paio2 = new OrderedPair<>("hello", "world");

        //Creation of third instance: Integer and Integer
        OrderedPair<Integer, Integer> ciao;
        ciao = new OrderedPair<>(4,5);

        //Creation of four instance: String and Boolean
        OrderedPair<String, Boolean> paio4 = new OrderedPair<>();
        paio4.setKey("Mangiato");
        paio4.setValue(true);

        //Creation of noname instance: String and String
        new OrderedPair<String, String>("Ciao", "a tutti").getValue();

        //Creation of instance with complex Type: Integer and Ordered Pair of two Strings
        OrderedPair<Integer, OrderedPair<String,String>> paio5 = new OrderedPair<>(3, new OrderedPair<>("Tra","Poco"));
        //#################################################################################################################

        /*Simple examples of the use of Interfaces*/

        //Here we use the syntacs: NameInterface istance = new ClassImplementingInterface
        Polygon triangle = new Triangle(1, 2, 3);
        Log.d("Triangle", "Triangle(1, 2, 3) getPerimeter: " + triangle.getPerimeter());

        Polygon rectangle = new Rectangle(2, 3);
        Log.d("Rectangle", "Rectangle(2, 3) getPerimeter: " + rectangle.getPerimeter());

        Polygon square = new Square(2);
        Log.d("Square", "Square(2) getPerimeter: " + square.getPerimeter());
        ////////////////////////////////////////////////////////////////////////////////

        //We can also do: Class instance = new Class
        Triangle triangle2 = new Triangle(2,3,4);
        Log.d("Triangle", "Triangle(2,3,4) getPerimeter: " + triangle2.getPerimeter());

        Rectangle rectangle2 = new Rectangle(3, 4);
        Log.d("Rectangle", "Rectangle(3, 4) getPerimeter: " + rectangle.getPerimeter());

        Square square2 = new Square(3);
        Log.d("Triangle", "Square(3) getPerimeter: " + square.getPerimeter());
        ///////////////////////////////////////////////////////////////////////////////

        //We can also instantiate DIRECTLY an anonimous class from overriding the interface

        //There is no class Circle, just an instance of an anonimous class overriding the interface
        Polygon circle = new Polygon() {
            double ray = 3;

            @Override
            public int getNumberOfSides() {
                return 0;
            }

            @Override
            public double getPerimeter() {
                return 2*Math.PI*ray;
            }
        };
        Log.d("Circle", "Circle(3) getPerimeter: " + circle.getPerimeter());



        //#################################################################################################################

        /*Simple examples of the use of Inner interfaces and classes*/

        //1) Class in a class
        ExternalClass ext1 = new ExternalClass();
        ExternalClass.InternalClass instance1 = ext1. new InternalClass("Alex");
        Log.d("Class in a Class", "Salut: " + instance1.salut());

           //second method
        ExternalClass.InternalClass instance2 = new ExternalClass().new InternalClass("Alex");
        Log.d("Class in a Class", "Salut: " + instance2.salut());


        //2) Interface in an interface
        ExternalInterface.InternalInterface instance3 = new ExternalInterface.InternalInterface() {
            @Override
            public String salut(String name){  //must be public
               return "Hello " + name;
           }
        };
        Log.d("Interf in Interf", "Salut: " + instance3.salut("Alex"));


        //3) Interface in a Class (not inner class)
        Test instance4 = new Test();
        Log.d("Interf in a Class", "Salut: " + instance4.salut("Alex"));



    } //end of onCreate() callback method





    //CLASS EXAMPLE FOR GENERICS EXPLAINATION**********************************************************************

    /*  Most used types for Generics:
        E - Element (used extensively by the Java Collections Framework)
        K - Key
        N - Number
        T - Type
        V - Value
        S,U,V etc. - 2nd, 3rd, 4th types
    * */

    //interface with Generics (K = Key, V=Value)
    public interface Pair<K, V> {
        public K getKey();   //to be implemented: it returns a K value
        public V getValue(); //to be implemented: it returns a V value
    }


    //class which implements interface Pair
    //Generics are declared into Class name with <>
    public class OrderedPair<K, V> implements Pair<K, V> {

        private K key;
        private V value;

        //first constructor
        public OrderedPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        //second constructor: it lets you more free
        public OrderedPair(){
            //second constructor ONLY if we want to set variables through setter methods too!
        }

        //methods implemented from interface Pair (obblgatori)
        public K getKey()	{ return key; }
        public V getValue() { return value; }

        //methods new (facoltativi)
        public void setKey(K key) {this.key = key;}
        public void setValue(V value) {this.value = value;}
    }
    //*******************************************************************************************************************

    //EXAMPLES OF CLASSES FOR INTERFACES EXPLAINATION********************************************************************
    //interface: it's implicitly public and all its methods are abstract and public and must be implemented in the class which implements this interface
    interface Polygon{
        /** @return The number of sides of the Polygon **/
        int getNumberOfSides();
        /** @return The perimeter of the Polygon **/
        double getPerimeter();
    }

    //Declaration of Class Triangle which implements interface Polygon
    class Triangle implements Polygon {
        private int numberOfSides = 3; //if it wasn't an inner class it would have been static
        private double side1;
        private double side2;
        private double side3;

        //Constructor
        public Triangle(double side1, double side2, double side3){
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        public int getNumberOfSides(){ //implemented from the interface: obbligatorio
            return numberOfSides;
        }

        public double getPerimeter(){  //implemented from the interface: obbligatorio
            return side1 + side2 + side3;
        }
    }

    //Declaration of Class Rectangle which implements interface Polygon
    class Rectangle implements Polygon {
        private int numberOfSides = 4; //if it wasn't an inner class it would have been static
        private double side1;
        private double side2;

        //Class Constructor
        public Rectangle(double side1, double side2){
            this.side1 = side1;
            this.side2 = side2;
        }

        public int getNumberOfSides(){ //implemented from the interface: obbligatorio
            return numberOfSides;
        }

        public double getPerimeter(){ //implemented from the interface: obbligatorio
            return 2*side1 + 2*side2;
        }
    }


    //Declaration of Class Square which extends Rectangle, then implements Polygon
    class Square extends Rectangle {
        public Square(double side){
            super(side, side);
        }
    }


    //*******************************************************************************************************************

    //EXAMPLES OF INNER CLASSES AND INTERFACES FOR EXPLAINATION********************************************************************

    //Class in a class
    class ExternalClass{
        class InternalClass{
            String name;
            InternalClass(String name){this.name = name;}
            String salut(){return "Hello" + name;}
        }
    }

    //Interface in an interface
    interface ExternalInterface{
        interface InternalInterface{
            /**@return The greetings**/
            String salut(String name);
        }
    }

    //*******************************************************************************************************************


    public void ChangeStyle(View v){
        style = style +1;
        if (style == 4)
            style = 1;


        TextView myTextView = (TextView) findViewById(R.id.text);
        LinearLayout myLayout = (LinearLayout)findViewById(R.id.layout);
        Button myButton = (Button)findViewById(R.id.button);
        if(style == 1) {
            myTextView.setTextAppearance(getApplicationContext(), R.style.CustomFontStyle);
            myButton.setTextAppearance(getApplicationContext(),R.style.CustomButtonStyle);
           // myTextView.setTextAppearance(R.style.CustomButtonStyle);
        }else if (style == 2){
            myTextView.setTextAppearance(getApplicationContext(), R.style.CustomFontStyle2);
            myButton.setTextAppearance(getApplicationContext(),R.style.CustomButtonStyle2);

        }else{
            myTextView.setTextAppearance(getApplicationContext(), R.style.CustomFontStyle3);
            myButton.setTextAppearance(getApplicationContext(),R.style.CustomButtonStyle3);

        }
    }




} // END OF MAIN ACTIVITY


class Test implements ExternalClass2.InternalInterface{
    public String salut(String name){
        return "Hello " + name;
    }
}

//Interface in a class (not Inner Class)
class ExternalClass2{
    interface InternalInterface{
        String salut(String name);
    }
}

interface NestedInterface{
   String salut(String name);
}
