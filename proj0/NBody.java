/**
 * NBody
 * @author : Andi Wahyu Multazam
 */
public class NBody {

    public static double readRadius(String file) {
        In in = new In(file);

        int number = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);

        int number = in.readInt();
        double rad = in.readDouble();
        if (number == 0) {
            return null;
        }
        Planet[] planets = new Planet[number];
        for (int i = 0; i < number; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            //create Planet
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        /* Read arguments */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /* Process argument */
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        assert planets != null;
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        String backgroundImg = "images/starfield.jpg";

        double time = 0;
        while(time < T) {
            for (int j = 0; j < planets.length; j++) {
                xForces[j] = 0;
                yForces[j] = 0;
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            /* Draw Background */
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundImg, radius * 2, radius * 2);

            /* Draw Planets */
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}