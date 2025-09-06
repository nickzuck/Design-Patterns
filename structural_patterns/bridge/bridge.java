/*
Consider that you have mulitple shapes that can be drawn with either raster or vector graphic tools
In future the tools can change (added or replaced). The shapes can also be added. Write a program for this

Solution : Let's say we have square and circle for now. We could either create SquareVector, SquareRaster, CircleVector
like classes which will cause multiple classes to get created. The better solution however is to use bridge design 
pattern to solve this problem. Since the functionality of shapes won't vary too much and only properties might change
we will use abstract class for them and use interface for graphics tools (a.k.a renderer). Depending on what type of
rendering we need to do we can refer to the object of renderer to the object of shape. The renderer can change and 
so does the reference.
 */



interface Renderer{
	void drawSquare(int x) ;
	void drawCircle(int x) ;
}


abstract class Shape {
    protected Renderer renderer;
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    public abstract void draw();
}


class Circle extends Shape {
    int radius;
    public Circle(Renderer renderer, int radius) {
        super(renderer);
        this.radius = radius ;
    }
    @Override
    public void draw() {
        renderer.drawCircle(this.radius);
    }
}

class Square extends Shape {
    int side ;
    public Square(Renderer renderer, int side) {
        super(renderer);
        this.side = side;
    }


    @Override
    public void draw() {
        renderer.drawSquare(this.side);
    }

    // Writing an extra member function here to show that the renderer can be changed with the
    // same shape object and used with the same shape object
    public void updateRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}

class VectorRenderer implements Renderer {
    public void drawSquare(int side) {
        System.out.printf("Drawing square using Vector rendering with side of length %d\n", side);
    }

    public void drawCircle(int radius) {
        System.out.printf("Drawing circle using Vector rendering with radius %d\n", radius);
    }

}

class RasterRenderer implements Renderer {
    public void drawSquare(int side) {
        System.out.printf("Drawing square using Raster rendering with side of length %d\n", side);
    }

    public void drawCircle(int radius) {
        System.out.printf("Drawing circle using Raster rendering with radius %d\n", radius);
    }
}

class bridge {
    public static void main(String[] args) {
        VectorRenderer vectorRenderer = new VectorRenderer() ;
        Square squareObj = new Square(new RasterRenderer(), 5);
        squareObj.draw();
        squareObj.updateRenderer(vectorRenderer);
        squareObj.draw();

        Circle circleObj = new Circle(vectorRenderer, 5);
        circleObj.draw();
    }
}