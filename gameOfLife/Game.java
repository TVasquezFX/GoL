import java.io.File;

public class Game{

    private Board prev;
    private Board next;
    private int n;

    public Game() {
        File f = new File("Start.txt");
        prev = new Board(f);
        next = new Board();
        n = 20;
    }

    public static void main(String [] args){

        if (args.length > 0) {
            Game g = new Game();
            System.out.println(g);
            int y= Integer.parseInt(args[0]);
            for (int i = 0; i < y-1; i++) {
                g.update();
                System.out.println(g);
            }
        }
        else{
            System.out.println("No command line arguments found.");
        }

    }



    public String toString() {
        return prev.toString();
    }

    public void update(){
        for (int i= 0; i<n; i++)
        {
            for (int j= 0; j<n; j++)
            {
                int value= prev.neighbors(i,j);
                if (value <= 1)
                {
                    next.set(i,j, false);

                }else if(value ==2)
                {
                    boolean temp= prev.get(i,j);
                    next.set(i,j, temp);

                }else if(value ==3)
                {
                    next.set(i,j, true);

                }else if (value >= 4)
                {
                    next.set(i,j, false);

                }
            }

        }
        Board tmp = prev;
        prev = next;
        next = tmp;


    }
}