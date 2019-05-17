
public class StripComments {
    
//    Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.
//
//    Example:
//
//    Given an input string of:
//
//    apples, pears # and bananas
//    grapes
//    bananas !apples
//
//    The output expected would be:
//
//    apples, pears
//    grapes
//    bananas
//
//    The code would be called like so:
//    var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
//    // result should == "apples, pears\ngrapes\nbananas"
    
    
    public static String stripComments(String text, String[] commentSymbols) {    
        StringBuilder result = new StringBuilder();   
        String[] strings = text.split("\n");
   
        int commentIndex = 0;
        int index = 0;
        for (int i = 0; i < strings.length; i++) {    
            commentIndex = 0;
            for (int j = 0; j < commentSymbols.length; j++) {
                if (strings[i].contains(commentSymbols[j])) {
                    index = strings[i].indexOf(commentSymbols[j]);
                    if (index < commentIndex || commentIndex == 0)
                        commentIndex = index;
                }
            }
            if (commentIndex == 0)
                commentIndex = strings[i].length();
            result.append(strings[i].substring(0, commentIndex).trim());
            if (i != strings.length - 1)
                result.append("\n");
        }
        
        return result.toString();
    }

    public static void assertEquals(String str1, String str2) {
        if (str1.equals(str2))
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String args[]) throws Exception {
        
        // test
        System.out.println(stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } ));
        System.out.println(stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } ));
        System.out.println(stripComments( "a\n f-\n g-d-$-\n g/-d-$-\n ]-\n /$\n d!d!d!d!", new String[] { "$", "-", "!" } ));
        System.out.println(stripComments( "<a\n b[ ]\n c>", new String[] { "$", " ", "/" } ));
        
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } )
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );
    }
}
