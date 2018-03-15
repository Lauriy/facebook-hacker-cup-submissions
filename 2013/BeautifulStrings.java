import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
public class BeautifulStrings
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bf = new BufferedReader(new FileReader(new File("input")));
        int count = Integer.parseInt(bf.readLine());
        String[] cases = new String[count];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        TreeMap tm = new TreeMap();
        Map sm;
        int score, n = 0;
        int weight = 26;
        String lcs = "";
        for(int i = 0; i < 26; i++)
        {
            tm.put(alphabet.charAt(i), 0);
        }
        for(int i = 0; i < count; i++)
        {
            cases[i] = bf.readLine();
        }
        bf.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output")));
        for(int i = 0; i < count; i++)
        {
            lcs = cases[i].toLowerCase();
            score = 0;
            weight = 26;
            for(int k = 0; k < 26; k++)
            {
                tm.put(alphabet.charAt(k), 0);
            }
            for(int j = 0; j < lcs.length(); j++)
            {
                if(tm.containsKey(lcs.charAt(j)))
                {
                    n = (Integer)tm.get(lcs.charAt(j));
                    tm.put(lcs.charAt(j), n + 1);
                }
            }
            sm = sortByValues(tm);
            for(Object value : sm.values())
            {
                score += (Integer)value * weight;
                weight--;
            }
            bw.write("Case #" + (i + 1) + ": " + score + System.getProperty("line.separator"));
        }
        bw.close();
    }
    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map)
    {
        Comparator<K> valueComparator =  new Comparator<K>()
        {
            public int compare(K k1, K k2)
            {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0)
                {
                    return 1;
                }
                else
                {
                    return compare;
                }
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}