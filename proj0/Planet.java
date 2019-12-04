/**
 * Planet
 * Author: Andi Wahyu Multazam
 */
public class Planet {

    /* Define variables */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /* Constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /* Constructor to copy object */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**
     * Calculate distance of two planets
     * @param p Planet to calculate the distance to
     * @return distance
     */
    public double calcDistance(Planet p) {
//        double dx = this.xxPos - p.xxPos;
//        double dy = this.yyPos - p.yyPos;
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Calculate Force exerted by other planet p
     * @param p Planet to calculate the force with
     * @return Force of the two planets
     */
    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        return (6.67e-11 * this.mass * p.mass) / (distance*distance);
    }

    public double calcForceExertedByX(Planet p) {
         double force = this.calcForceExertedBy(p);
         double dx = p.xxPos - this.xxPos;
         double r = this.calcDistance(p);
         return (force * dx) / r;
    }

    public double calcForceExertedByY(Planet p) {
         double force = this.calcForceExertedBy(p);
         double dy = p.yyPos - this.yyPos;
         double r = this.calcDistance(p);
         return (force * dy) / r;
     }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForce = 0;
        for (Planet p : planets) {
            if (this == p) {
                continue;
            }
            netForce += this.calcForceExertedByX(p);
        }
        return netForce;
     }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForce = 0;
        for (Planet p : planets) {
            if (this == p) {
                continue;
            }
            netForce += this.calcForceExertedByY(p);
        }
        return netForce;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * (this.xxVel);
        this.yyPos = this.yyPos + dt * (this.yyVel);
    }

    public void draw() {
        String img = "images/"+this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, img);
    }
}