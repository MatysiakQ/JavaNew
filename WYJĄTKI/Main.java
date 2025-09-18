public class Main {
    public static void riskyMethod(boolean fail) throws MyException {
        if (fail) throw new MyException("Coś poszło nie tak!");
    }

    public static void main(String[] args) {
        try {
            riskyMethod(true);
        } catch (MyException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }
    }
}
