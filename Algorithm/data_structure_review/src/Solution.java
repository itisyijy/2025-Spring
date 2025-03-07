import java.io.*;
import java.util.*;

class Pair
{
    String first, second;

    Pair(String first, String second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Pair)
        {
            Pair pair = (Pair) object;
            return this.first.equals(pair.first) && this.second.equals(pair.second);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        String str = first + second;
        return str.hashCode();
    }
}

public class Solution
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int T = Integer.parseInt(in.nextLine());

        Set<Pair> set = new HashSet<Pair>();
        for(int i = 0; i < T; i++)
        {
            set.add(new Pair(in.next(), in.next()));
            System.out.println(set.size());
        }

        in.close();
    }
}