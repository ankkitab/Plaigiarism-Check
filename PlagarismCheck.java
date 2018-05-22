import java.util.HashMap;

/**
 * Created by ankkitabose on 5/19/18.
 */
public class PlagarismCheck {
    boolean plagiarismCheck(String[] code1, String[] code2) {
        if (code1 == null && code2 == null)
            return true ;
        if (code1 == null || code2 == null)
            return false;
        if (code1.length != code2.length)
            return false;

        HashMap<String, String> map = new HashMap<>();
        for (int i=0; i<code1.length; i++) {
            String line1 = code1[i] ;
            String line2 = code2[i] ;
            int j, k;

            for ( j=0, k=0; j<line1.length() && k<line2.length() ; j++, k++) {
                char ch1 = line1.charAt(j) ;
                char ch2 = line2.charAt(k) ;

                if (ch1!=ch2 && (ch1<97 || ch1>122)) {// if they differ on any other character
                    return false;
                }

                if (ch1 == ch2 && !validStart(ch1)) // any other character that's equal
                    continue;

                //the variables (valid start is true)
                String a = "" , b= "" ;

                while ( ( j<line1.length() && validIdentifier(line1.charAt(j))) || (k<line2.length() && validIdentifier(line2.charAt(k)) ) ) {

                    if (j<line1.length() &&  validIdentifier(line1.charAt(j))) {
                        ch1 = line1.charAt(j) ;
                        a += ch1;
                        j++;
                    }
                    if (k<line2.length() && validIdentifier(line2.charAt(k)) )  {
                        ch2 = line2.charAt(k) ;
                        b += ch2;
                        k++;
                    }

                }

                if (!map.containsKey(a)) {
                    map.put(a, b);
                }
                if (map.containsKey(a) && !map.get(a).equals(b)) //a is paired with something else
                    return  false;
                j--;
                k--;
            }

            if (j<line1.length() || k<line2.length()) //one got over before the other
                return false;
        }

        return true ;
    }

     boolean validIdentifier(char ch) {
        if (ch == '_' || (ch>= 97 && ch<=122) || (ch>= 65 && ch<=90) || (ch>= 48 && ch<=57))
            return true;
        else return false ;
    }

     boolean validStart(char ch) {

        if (ch == '_' || (ch>= 97 && ch<=122) || (ch>= 65 && ch<=90) )
            return true;
        else return false ;
    }


    public static void main(String[] arg) {
        PlagarismCheck obj = new PlagarismCheck();
        String[] code1 = {"def return_smth(a, b):",
                "  a = 2",
                "  return a + a"};
        String[] code2 = {"def return_smth(bc1, a):",
                "  bc1 = 2",
                "  return bc1 + bc1" };
        System.out.println(obj.plagiarismCheck(code1,code2));
    }
}
