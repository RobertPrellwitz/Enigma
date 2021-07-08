package enigma;

public class PolyAlphabet 
{
public PolyAlphabet(String sample)
{
    //String message = sample;
    cipher(sample);
}
public String cipher(String message)
{
    StringBuilder caesar = new StringBuilder(message.length());

    int c1 = 5; int c2 = 19; int shift; char temp; int j;
    for(int i = 0; i < message.length(); i++)
    {
        j = i/5;
        if ( (i == j*5+1) || (i==j*5+2) || (i==j*5+4) )
        {
            shift = c2;
        }
        else { shift = c1; }

        char C = message.charAt(i);
        int test = C;
        if (test > 64 && test < 91)
        {   test = test + shift;
            if (test > 90)
                test = test - 26;
            temp = (char) (test);
        }
        else if (test > 96 && test < 123)
        {
            test = test + shift;
            if (test > 122)
                test = test - 26;
            temp = (char) (test);
        }
        else {
            temp = (char) (test);
        }
        caesar.append(temp);
    }

    return caesar.toString();

}
}
