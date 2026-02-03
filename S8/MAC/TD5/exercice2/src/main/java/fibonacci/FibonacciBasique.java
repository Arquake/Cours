package fibonacci;

public class FibonacciBasique implements Fibonacci{

    @Override
    public int compute(int n) throws TermeInexistantException {

        if (n == 0) {
            return 0;
        }

        if (n== 1) {
            return 1;
        }

        if (n<0) {
            throw new TermeInexistantException();
        }

        return this.compute(n-1)+this.compute(n-2);
    }
}