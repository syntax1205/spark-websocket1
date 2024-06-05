public class Transfer {
    private int from;
    private int to;

    private synchronized void transferTo(int amount){
        int temporary = 0;
        temporary = amount;
        from = from - amount;
        to = to + temporary;
    }
}
