public class Methods {

    public boolean isLastCharOperator(String s) {
        int length = s.length();
        return length > 0 && isOperator(s.substring(length - 1));
    }

    public int containsOperator (String input) {
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(String.valueOf(input.charAt(i)))) {
                return i;
            }
        }
        return -1;
    }

    public boolean isOperator(String s) {
        return s.length() == 1 && "+-/*".contains(s);
    }

    public boolean containsPoint(String input) {
        for (int i = input.length()-1; i >= 0; i--) {
            if (input.charAt(i) == '.') {
                return true;
            } else if (isOperator(String.valueOf(input.charAt(i)))) {
                return false;
            }
        }
        return false;
    }
}
