/**
 * There is a round hole which accepts the pegs inserted to it. You are required to validate if a certain object
 * can be inserted in that hole. The problem however is the pegs can be both round and square. For round peg, we can
 * easily compare the radius and validate the peg, but for square peg the same object comparison won't be supported
 *
 *
 * Solution : Using adapter pattern to pass the object as round peg to validate with the round hole
 */


class RoundHole {
    private int radius;

    RoundHole(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public boolean doesFit(RoundPeg peg) {

//        System.out.println("this radius is " + this.radius);
//        System.out.println("Peg radius is " + peg.getRadius());
        return this.radius >= peg.getRadius() ;

    }
}

class RoundPeg {
    private int radius;

    RoundPeg(int radius) {
        this.radius = radius;
    }

    RoundPeg(){}

    public int getRadius() {
        return radius;
    }
}

class SquarePeg {
    private int width;

    SquarePeg(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}

class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    SquarePegAdapter(SquarePeg squarePeg) {
        this.peg = squarePeg ;
    }

    public int getRadius() {
        return  (int) ((peg.getWidth() * Math.sqrt(2)) / 2) ;
    }
}

public class Adapter {
    public static void main(String[] args) {
         RoundHole hole = new RoundHole(2) ;
         RoundPeg roundPeg = new RoundPeg(2) ;
         RoundPeg roundPegBig = new RoundPeg(3) ;
         System.out.println(hole.doesFit(roundPeg));
         System.out.println(hole.doesFit(roundPegBig));

         SquarePeg small_sqpeg = new SquarePeg(2) ;
         SquarePeg large_sqpeg = new SquarePeg(5) ;
//         hole.doesFit(small_sqpeg) ;

        SquarePegAdapter small_sqpeg_adapter = new SquarePegAdapter(small_sqpeg) ;
        SquarePegAdapter large_sqpeg_adapter = new SquarePegAdapter(large_sqpeg) ;
        System.out.println(hole.doesFit(small_sqpeg_adapter)) ;
        System.out.println(hole.doesFit(large_sqpeg_adapter)) ;
    }
}

