import java.util.Arrays;
import java.util.Iterator;

public class ArgumentReader {
    private final Iterator<String> arguments;

    public ArgumentReader(String[] args) {
        arguments = Arrays.stream(args).iterator();
    }

    public String next() throws Exception {
        if (!arguments.hasNext()) {
            throw new Exception("Not enough arguments");
        }
        return arguments.next();
    }

}
